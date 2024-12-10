package com.training.itworker.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("`articles`")
@Schema(description = "文章信息")
public class Article {

    @TableId(type = IdType.AUTO)
    @Schema(description = "文章id", example = "1")
    private Integer id;

    @Schema(description = "标题名", example = "世界最高城理塘")
    private String name;

    @Schema(description = "文章内容", example = "“理塘”是四川省甘孜藏族自治州的一个县，地处中国青藏高原的东缘，位于川藏线上的重要节点，素有“世界高城”之称。")
    private String property;
}
