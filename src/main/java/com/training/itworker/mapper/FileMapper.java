package com.training.itworker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.training.itworker.entity.Files;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper extends BaseMapper<Files> {
}
