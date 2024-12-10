package com.training.itworker.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("`files`")
@Schema(description = "文件信息")
public class Files {

    @TableId(type = IdType.AUTO)
    @Schema(description = "文章id", example = "1")
    private Integer id;

    @Schema(description = "文件名", example = "javaWeb从入门到精通")
    private String filename;

    @Schema(description = "文件存放路径", example = "/path/file/storage")
    private String filePath;

    @Schema(description = "文件大小", example = "32MB")
    private Long fileSize;

    @Schema(description = "文件类型", example = ".img")
    private String fileType;

    @Schema(description = "上传时间", example = "3:20")
    private LocalDateTime uploadTime;

    @Schema(description = "上传的用户id", example = "1")
    private Integer userId;

    @Schema(description = "文件描述", example = "这是实训作业")
    private String description;
}
