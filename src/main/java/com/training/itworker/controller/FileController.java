package com.training.itworker.controller;

import com.training.itworker.common.MyException;
import com.training.itworker.common.R;
import com.training.itworker.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

/**
 * 文件的上传下载等功能
 * */

@RestController
@RequestMapping("/file")
@Tag(name = "文件上传功能", description = "可以上传文件，图片等")
public class FileController {

    @Resource
    private FileService fileService;

    /**
     * @param file 上传的文件
     * @param userId 上传的用户id
     * @param description 文件描述
     * @param isAvatar 是否是图像
     * */
    @PostMapping("/upload")
    @Operation(summary = "文件上传功能")
    public R<String> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("id") Integer userId,
            @RequestParam("description") String description,
            @RequestParam(value = "isAvatar", defaultValue = "false") boolean isAvatar
    ) {
        // 异步上传，返回一个CompletableFure
        CompletableFuture<R<String>> resultFuture = fileService.uploadFileAsync(file, userId, description, isAvatar);

        // 使用 join() 阻塞等待结果
        R<String> result = resultFuture.join();

        if(result.getCode() == 200) {
            return R.ok("上传成功！");
        } else {
            throw new MyException(result.getCode(), result.getMsg());
        }
    }
}
