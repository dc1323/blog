package com.blog.base.storage.impl;

import com.blog.base.lang.MtonsException;
import com.blog.base.storage.Storage;
import com.blog.base.utils.FileKit;
import com.blog.base.utils.FilePathUtils;
import com.blog.base.utils.ImageUtils;
import com.blog.base.utils.MD5;
import com.blog.config.SiteOptions;
import com.blog.modules.entity.Resource;
import com.blog.modules.repository.ResourceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

/**
 * @author langhsu
 * @since 3.0
 */
@Slf4j
public abstract class AbstractStorage implements Storage {
    @Autowired
    protected SiteOptions options;
    @Autowired
    protected ResourceRepository resourceRepository;

    protected void validateFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new MtonsException("文件不能为空");
        }

        if (!FileKit.checkFileType(file.getOriginalFilename())) {
            throw new MtonsException("文件格式不支持");
        }
    }

    @Override
    public String store(MultipartFile file, String basePath) throws Exception {
        validateFile(file);
        return writeToStore(file.getBytes(), basePath, file.getOriginalFilename());
    }

    @Override
    public String storeScale(MultipartFile file, String basePath, int maxWidth) throws Exception {
        validateFile(file);
        byte[] bytes = ImageUtils.scaleByWidth(file, maxWidth);
        return writeToStore(bytes, basePath, file.getOriginalFilename());
    }

    @Override
    public String storeScale(MultipartFile file, String basePath, int width, int height) throws Exception {
        validateFile(file);
        byte[] bytes = ImageUtils.screenshot(file, width, height);
        return writeToStore(bytes, basePath, file.getOriginalFilename());
    }

    public String writeToStore(byte[] bytes, String src, String originalFilename) throws Exception {
        String md5 = MD5.md5File(bytes);
        Resource resource = resourceRepository.findByMd5(md5);
        if (resource != null){
            return resource.getPath();
        }
        String path = FilePathUtils.wholePathName(src, originalFilename, md5);
        path = writeToStore(bytes, path);

        // 图片入库
        resource = new Resource();
        resource.setMd5(md5);
        resource.setPath(path);
        resource.setCreateTime(LocalDateTime.now());
        resourceRepository.save(resource);
        return path;
    }

}