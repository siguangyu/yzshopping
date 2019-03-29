package com.lynu.yzshopping.mybatis.entity;

import java.io.Serializable;

/**
 * @program: data-interface
 * @description: 响应结果
 * @author: houyu
 * @create: 2018-12-10 00:59
 */
public class Result implements Serializable{

    //状态码
    private int code;
    //消息
    private String message;
    //额外的内容
    private Object data;

    public Result() {
    }

    public Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public Result setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    public static class Code{
        public final static int SUCCESS           = 200; // 成功
        public final static int FAIL              = 400; // 失败
        public final static int FREQUENTLY        = 406; // 请求频繁
        public final static int SERVER_ERROR      = 500; // 服务器内部错误
    }

    @Override
    public String toString() {
        return "ResResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
