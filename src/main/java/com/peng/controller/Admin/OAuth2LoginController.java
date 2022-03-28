package com.peng.controller.Admin;

import com.alibaba.fastjson.JSON;
import com.peng.entity.User;
import com.peng.entity.other.OAuthTypeEnum;
import com.peng.service.IUserService;
import com.peng.util.FileUploadUtils;
import com.peng.util.HttpUtils;
import com.peng.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: weipeng
 * @date: 2022/3/26
 */
@Slf4j
@Controller
public class OAuth2LoginController {
    @Value("${oauth2.github.clientId}")
    private String githubClientId;
    @Value("${oauth2.github.clientSecret}")
    private String githubClientSecret;

    @Value("${oauth2.gitee.clientId}")
    private String giteeClientId;
    @Value("${oauth2.gitee.clientSecret}")
    private String giteeClientSecret;
    @Value("${oauth2.gitee.grantType}")
    private String giteeGrantType;
    @Value("${oauth2.gitee.redirectUri}")
    private String giteeRedirectUri;

    @Value("${oauth2.vueHost}")
    private String vueHost;

    @Resource
    private IUserService iUserService;
    @Resource
    private FileUploadUtils fileUploadUtils;

    /**
     * Github返回授权码处理
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "/oauth/login/github", method = RequestMethod.GET)
    public String githubLogin(@RequestParam("code") String code) {
        try {
            //1. 使用code换取accessToken
            Map<String, String> query = new HashMap<>();
            query.put("client_id", githubClientId);
            query.put("client_secret", githubClientSecret);
            query.put("code", code);
            log.info("开始请求：https://github.com/login/oauth/access_token");
            HttpResponse response = HttpUtils.doPost("https://github.com/", "login/oauth/access_token", "post", new HashMap<String, String>(), query, new HashMap<String, String>());
            log.info("请求结束：https://github.com/login/oauth/access_token");
            final String accessToken = extractAccessTokenByGithub(response);
            //2. accessToken获取github用户信息
            Map<String, String> headers = new HashMap<>();
            headers.put("Authorization", "token " + accessToken);
            log.info("开始请求：https://api.github.com/user");
            final HttpResponse httpResponse = HttpUtils.doGet("https://api.github.com/", "user", "get", headers, new HashMap<String, String>());
            log.info("请求结束：https://api.github.com/user");
            final User saveUser = extractSaveUserByGithub(httpResponse, accessToken);
            //3. 本地是否有对应账号 没有则注册 并返回本系统Token
            final User realUser = iUserService.generateUserByGithubUsId(saveUser.getOauthUsId(), saveUser);
            final String pengToken = TokenUtil.sign(realUser);
            return "redirect:" + vueHost + "&pengToken=" + pengToken;
        } catch (Exception e) {
            log.error(e.getMessage());
            return "redirect:" + vueHost + "&error=" + e.getMessage();
        }
    }

    /**
     * Gitee返回授权码处理
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "/oauth/login/gitee", method = RequestMethod.GET)
    public String giteeLogin(@RequestParam("code") String code) {
        try {
            //1. 使用code换取accessToken
            Map<String, String> query = new HashMap<>();
            query.put("client_id", giteeClientId);
            query.put("client_secret", giteeClientSecret);
            query.put("grant_type", giteeGrantType);
            query.put("redirect_uri", giteeRedirectUri);
            query.put("code", code);
            log.info("开始请求：https://gitee.com/oauth/token");
            HttpResponse response = HttpUtils.doPost("https://gitee.com/", "oauth/token", "get", new HashMap<>(), query, new HashMap<>());
            log.info("请求结束：https://gitee.com/oauth/token");
            final String accessToken = extractAccessTokenByGitee(response);
            //2. accessToken获取github用户信息
            Map<String, String> params = new HashMap<>();
            params.put("access_token", accessToken);
            log.info("开始请求：https://gitee.com/api/v5/user");
            final HttpResponse httpResponse = HttpUtils.doGet("https://gitee.com/", "api/v5/user", "get", new HashMap<>(), params);
            log.info("请求结束：https://gitee.com/api/v5/user");
            final User saveUser = extractSaveUserByGitee(httpResponse, accessToken);
            //3. 本地是否有对应账号 没有则注册 并返回本系统Token
            final User realUser = iUserService.generateUserByGithubUsId(saveUser.getOauthUsId(), saveUser);
            final String pengToken = TokenUtil.sign(realUser);
            return "redirect:" + vueHost + "&pengToken=" + pengToken;
        } catch (Exception e) {
            log.error(e.getMessage());
            return "redirect:" + vueHost + "&error=" + e.getMessage();
        }
    }


    private User extractSaveUserByGithub(HttpResponse httpResponse, String accessToken) throws IOException {
        String jsonStr = EntityUtils.toString(httpResponse.getEntity());
        Map map = (Map) JSON.parse(jsonStr);
        final Long githubUsId = ((Integer) map.get("id")).longValue();
        final String githubName = (String) map.get("name");
        final String githubLogin = (String) map.get("login");
        final String githubAvatarUrl = (String) map.get("avatar_url");
        final String githubEmail = (String) map.get("email");
        return User.builder().roleId(2L).password("123456").name(githubName).username(githubLogin).oauthType(OAuthTypeEnum.GITHUB.getCode()).oauthUsId(githubUsId).oauthToken(accessToken).avatar(githubAvatarUrl).email(githubEmail).build();
    }

    private User extractSaveUserByGitee(HttpResponse httpResponse, String accessToken) throws IOException {
        String jsonStr = EntityUtils.toString(httpResponse.getEntity());
        Map map = (Map) JSON.parse(jsonStr);
        final Long giteeUsId = ((Integer) map.get("id")).longValue();
        final String giteeName = (String) map.get("name");
        final String giteeLogin = (String) map.get("login");
        String giteeAvatarUrl = (String) map.get("avatar_url");
        final String giteeEmail = (String) map.get("email");
        try {
            giteeAvatarUrl = fileUploadUtils.remoteUrl2Local(giteeAvatarUrl);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return User.builder().roleId(2L).password("123456").name(giteeName).username(giteeLogin).oauthToken(accessToken).oauthUsId(giteeUsId).oauthType(OAuthTypeEnum.GITEE.getCode()).avatar(giteeAvatarUrl).email(giteeEmail).build();
    }

    private String extractAccessTokenByGithub(HttpResponse response) throws IOException {
        String resMsg = EntityUtils.toString(response.getEntity());
        final String[] split = resMsg.split("&");
        final String[] r0 = split[0].split("=");
        return r0[1];
    }

    private String extractAccessTokenByGitee(HttpResponse response) throws IOException {
        String jsonStr = EntityUtils.toString(response.getEntity());
        Map map = (Map) JSON.parse(jsonStr);
        return (String) map.get("access_token");
    }
}
