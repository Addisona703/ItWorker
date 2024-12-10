package com.training.itworker;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.training.itworker.entity.User;
import com.training.itworker.mapper.UserMapper;
import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)  // 禁用嵌入式数据库自动配置
public class MybatisPlusSampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testInsert() {
//        Sample sample = new Sample();
//        sampleMapper.insert(sample);
//        assertThat(sample.getId()).isNotNull();
//        
//        User user = new User();
//        user.setId(2);
//        user.setName("张三");
//        user.setStatement("正常");
//        user.setPassword("123");
//        user.setImage("path/image.jpg");
//        userMapper.insert(user);
//        assertThat(user.getId()).isNotNull();

        User x = userMapper.selectById(1);
        System.out.println();
    }
}
