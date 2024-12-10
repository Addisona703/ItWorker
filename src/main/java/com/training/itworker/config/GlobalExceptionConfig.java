package com.training.itworker.config;

import com.training.itworker.common.MyException;
import com.training.itworker.common.R;
import com.training.itworker.common.ResponseEnum;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *  自定义异常配置
 * */

@RestControllerAdvice // 全局异常处理器
public class GlobalExceptionConfig {

    /** 捕获自定义异常 **/
    @ExceptionHandler(MyException.class)
    public R<String> handle(MyException e) {
        // 异常处理，输出到控制台，后续更换成日志记录
        e.printStackTrace();
        return R.exception(e.getCode(), e.getMessage());
    }

    /** 捕获其他未处理的异常 **/
    @ExceptionHandler(Exception.class)
    public R<String> handleException(Exception e) {
        // 打印堆栈信息
        e.printStackTrace();
        return R.exception(ResponseEnum.ERROR.getCode(), "系统错误：" + e.getMessage());  // 返回默认错误信息
    }
}
