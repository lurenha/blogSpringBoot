package com.peng.util;


import com.peng.entity.Result.ResultCode;
import com.peng.entity.Result.ResultUtil;
import com.peng.storage.StorageService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

/**
 * 文件上传工具类
 *
 */

@Component
public class FileUploadUtils {
    @Autowired
    private  StorageService storageService;

    private static int counter = 0;

    /**
     * 以默认配置进行文件上传
     *
     * @param file 上传的文件
     * @return url路径
     * @throws Exception
     */
    public  String upload(MultipartFile file) throws IOException {
        String filename = extractFilename(file);
        String url = storageService.store(file.getInputStream(), file.getSize(),
                file.getContentType(), filename);
        return url;
    }

    /**
     * 编码文件名
     */
    public String extractFilename(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String extension = getExtension(file);
        fileName = encodingFilename(fileName) + "." + extension;
        return fileName;
    }


    /**
     * 编码文件名
     */
    private  String encodingFilename(String fileName) {
        fileName = fileName.replace("_", " ");
        fileName = Md5Utils.hash(fileName + System.nanoTime() + counter++);
        return fileName;
    }

    /**
     * 获取文件名的后缀
     *
     * @param file 表单文件
     * @return 后缀名
     */
    public String getExtension(MultipartFile file) {

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (StringUtils.isEmpty(extension)) {
            extension = MimeTypeUtils.getExtension(file.getContentType());
        }
        return extension;
    }
}