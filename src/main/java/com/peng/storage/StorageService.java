package com.peng.storage;


import com.peng.storage.server.Storage;
import lombok.Data;

import java.io.InputStream;
import java.util.UUID;

/**
 * 提供存储服务类，所有存储服务均由该类对外提供
 */
@Data
public class StorageService  {
    private String active;
    private Storage storage;

    /**
     * 存储一个文件对象
     *
     * @param inputStream   文件输入流
     * @param contentLength 文件长度
     * @param contentType   文件类型
     * @param fileName      文件索引名
     */
    public String store(InputStream inputStream, long contentLength, String contentType, String fileName) {
        String key=generateKey(fileName);
        storage.store(inputStream, contentLength, contentType, key);
        return generateUrl(key);
    }
    private String generateKey(String originalFilename) {
        int index = originalFilename.lastIndexOf('.');
        String suffix = originalFilename.substring(index);
        String key = UUID.randomUUID().toString();
        key = key.replace("-", "");
        key+=suffix;
        return key;
    }

    private String generateUrl(String keyName) {
        return storage.generateUrl(keyName);
    }

    public void delete(String keyName) {
        storage.delete(keyName);
    }


}
