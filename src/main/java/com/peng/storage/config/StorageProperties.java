package com.peng.storage.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/***
 * 将配置文件（myapplication.storage）转换成对象
 */
@Data
@ConfigurationProperties(prefix = "myapplication.storage")
public class StorageProperties {
    private String active;
    private Local local;
    private Qiniu qiniu;

    @Data
    public static class Local {
        private String address;
        private String storagePath;
    }

    @Data
    public static class Qiniu {
        private String endpoint;
        private String accessKey;
        private String secretKey;
        private String bucketName;
        private String baseUrl;
        private String templateid;


    }


}
