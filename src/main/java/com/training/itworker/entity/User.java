package com.training.itworker.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("`users`")
@Schema(description = "用户信息")
public class User {

    @TableId(type = IdType.AUTO) // 设置为自增
    @Schema(description = "用户id", example = "1")
    private Integer id;

    @Schema(description = "用户名", example = "龙丽")
    private String name;

    @Schema(description = "用户密码", example = "123456")
    private String password;

    @Schema(description = "用户头像存储路径", example = "/path/example.img")
    private String image;

    @Schema(description = "用户简介", example = "我是米米世界老玩家")
    private String statement;
}
