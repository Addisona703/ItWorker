package com.training.itworker.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.training.itworker.entity.Article;
import com.training.itworker.mapper.ArticleMapper;
import com.training.itworker.service.ArticleService;
import org.springframework.stereotype.Service;

@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
}
