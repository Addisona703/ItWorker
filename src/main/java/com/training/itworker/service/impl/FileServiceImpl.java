package com.training.itworker.service.impl;


import com.training.itworker.common.MyException;
import com.training.itworker.common.R;
import com.training.itworker.common.ResponseEnum;
import com.training.itworker.entity.Files;
import com.training.itworker.entity.User;
import com.training.itworker.mapper.FileMapper;
import com.training.itworker.mapper.UserMapper;
import com.training.itworker.service.FileService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service("fileService")
public class FileServiceImpl implements FileService {

    @Resource
    private FileMapper fileMapper;

    @Resource
    private UserMapper userMapper;

    @Value("${file.upload-dir}")
    private String dir;

    /**
     * &#064;Async  开启异步上传
     * &#064;Transactional  开启事务，当文件保存或者上传路径任意一个操作失败后，所有操作都会回滚
     * @param file 上传的文件
     * @param userId 上传文件的用户
     * @param description 上传文件的类型
     * @return CompletableFuture
     * */
    @Async
    @Transactional
    public CompletableFuture<R<String>> uploadFileAsync(MultipartFile file, Integer userId, String description, boolean isAvator) {
        // 获取原始文件名
        String fileName = file.getOriginalFilename();

        if(fileName == null || fileName.isEmpty()){
            throw new MyException(ResponseEnum.ERROR.getCode(), "文件不能为空！");
        }

        // 生成新的文件名防止重复，保存fileExtension文件扩展名，减少存储空间
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + "_" + fileExtension;

        // 定义保存路径，并保存文件，当路径不存在时应该创建路径，还有文件大小限制
        File saveDir = new File(dir);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }

        try {
        File saveFile = new File(dir + File.separator + newFileName);
        file.transferTo(saveFile);

        Files files = new Files(
                null,
                fileName,
                dir,
                file.getSize(),
                file.getContentType(),
                LocalDateTime.now(),
                userId,
                description
        );

            if(isAvator) {
                updateUserAvatar(userId, dir + File.separator + newFileName);
            }

            fileMapper.insert(files);
            return CompletableFuture.completedFuture(R.ok("上传成功"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(500, "文件上传失败：" + e.getMessage());
        }
    }

    /** 更新用户头像 **/
    private void updateUserAvatar(Integer userId, String avatarPath) {
        User user = new User();
        user.setId(userId);
        user.setImage(avatarPath);
        userMapper.updateById(user);
    }
}
