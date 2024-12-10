package com.training.itworker.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *  自定义运行时异常
 * */

@EqualsAndHashCode(callSuper = true)
@Data
public class MyException extends RuntimeException {
    private final Integer code;
    /**
     * @param code 状态码
     * @param message 异常信息
     * */
    public MyException(Integer code, String message) {
        super(message);
        this.code = code;
    }
    /**
     * @param responseEnum 枚举类型对象
     * */
    public MyException(ResponseEnum responseEnum) {
        super(responseEnum.getMsg());
        this.code = responseEnum.getCode();
    }
}
