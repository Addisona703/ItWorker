package com.training.itworker.service;

import com.training.itworker.common.R;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

public interface FileService {
    CompletableFuture<R<String>> uploadFileAsync(MultipartFile file, Integer userId, String description, boolean isAvatar);
}