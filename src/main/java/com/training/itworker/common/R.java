package com.training.itworker.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 *  自定义返回格式类
 * */

@Data
@NoArgsConstructor
public class R<T> implements Serializable {

    /**
     * SerialVersionUID 在类序列化过程中用于标识类版本的唯一标识符
     */
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "响应信息", example = "登录成功")
    private String msg;

    @Schema(description = "响应码", example = "200")
    private Integer code;

    @Schema(description = "响应数据", example = "")
    private T data;

    /** 操作成功执行 **/
    public static <T> R<T> ok(T data, String msg) {
        R<T> r = new R<T>();
        r.setCode(ResponseEnum.SUCCESS.getCode());
        r.setData(data);
        r.setMsg(msg);
        return r;
    }

    public static <T> R<T> ok(String msg) {
        return ok(null, msg);
    }

    public static <T> R<T> ok(T data) {
        return ok(data, ResponseEnum.SUCCESS.getMsg());
    }

    /** 编译失败执行 **/
    public static <T> R<T> buildFailure(Integer errCode, String errMessage) {
        return exception(errCode, errMessage);
    }

    /** 操作失败执行 **/
    public static <T> R<T> exception(Integer errCode, String errMessage) {
        R<T> r = new R<>();
        r.setCode(errCode);
        r.setMsg(errMessage);
        return r;
    }
}

