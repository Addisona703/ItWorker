package com.training.itworker.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.training.itworker.common.MyException;
import com.training.itworker.common.R;
import com.training.itworker.common.ResponseEnum;
import com.training.itworker.entity.Article;
import com.training.itworker.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** 文章管理 **/
@RestController
@RequestMapping("/article")
@Tag(name = "文章管理接口", description = "这是文章管理的操作")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    /** 查找所有文章 **/
    @GetMapping("/findAll")
    @Operation(summary = "获取所有文章")
//    @ApiResponse(responseCode = "200", description = "查找成功")
    public R<List<Article>> findAll() {
        List<Article> articles = articleService.list();
        return R.ok(articles);
    }

    /** 根据Id查找文章 **/
    @GetMapping("/findById")
    @Operation(summary = "获取指定Id的文章")
    public R<Article> findById(@RequestParam Integer id) {
        Article article = articleService.getById(id);
        if(article == null) {
            throw new MyException(ResponseEnum.FAIL);
        }
        return R.ok(article);
    }

    /** 根据标题名查找文章 **/
    @GetMapping("/findByName")
    @Operation(summary = "获取指定名称的文章")
    public R<Article> findByName(@RequestParam String name) {
        Article article = articleService.getOne(new QueryWrapper<Article>().eq("name", name));
        if(article == null) {
            throw new MyException(ResponseEnum.FAIL);
        }
        return R.ok(article);
    }

    /** 更新文章内容 **/
    @PostMapping("/updateProperty")
    @Operation(summary = "更新文章内容文章")
    public R<String> updateProperty(@RequestBody Article article) {
        boolean isSuccess = articleService.updateById(article);
        if(!isSuccess) {
            throw new MyException(ResponseEnum.FAIL);
        }
        return R.ok("更新成功！");
    }

    /** 新增文章 **/
    @PostMapping("/addProperty")
    @Operation(summary = "插入文章")
    public R<String> addProperty(@RequestBody Article article) {
        boolean isSuccess = articleService.save(article);
        if(!isSuccess) {
            throw new MyException(ResponseEnum.FAIL);
        }
        return R.ok("插入成功！");
    }
}
