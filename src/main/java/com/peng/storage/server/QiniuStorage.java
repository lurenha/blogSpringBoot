package com.peng.storage.server;

import com.qiniu.common.QiniuException;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.Data;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.stream.Stream;

@Data
public class QiniuStorage implements Storage {

    private final Log logger = LogFactory.getLog(QiniuStorage.class);

    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;
    private String baseUrl;

    private Auth auth;
    private UploadManager uploadManager;
    private BucketManager bucketManager;


    /**
     * 七牛云OSS对象存储简单上传实现
     */
    @Override
    public boolean store(InputStream inputStream, long contentLength, String contentType, String keyName) {
        if (uploadManager == null) {
            if (auth == null) {
                auth = Auth.create(accessKey, secretKey);
            }
            uploadManager = new UploadManager(new Configuration());
        }

        try {
            String upToken = auth.uploadToken(bucketName);
            uploadManager.put(inputStream, keyName, upToken, null, contentType);
        } catch (QiniuException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return true;
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String keyName) {
        return null;
    }

    @Override
    public Resource loadAsResource(String keyName) {
        try {
            URL url = new URL(generateUrl(keyName));
            Resource resource = new UrlResource(url);
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
        } catch (MalformedURLException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void delete(String keyName) {
        if (bucketManager == null) {
            if (auth == null) {
                auth = Auth.create(accessKey, secretKey);
            }
            bucketManager = new BucketManager(auth, new Configuration());
        }
        try {
            bucketManager.delete(bucketName, keyName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public String generateUrl(String keyName) {
        return baseUrl + keyName;
    }
}
