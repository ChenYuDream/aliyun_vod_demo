package org.jypj.dev.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ChenYu
 * @date 2017-12-1 18:09:16
 */
@Data
public class Result implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private int code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回数据对象
     */
    private Object data;

    public Result() {
    }

    public Result(Object object) {
        this.data = object;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
