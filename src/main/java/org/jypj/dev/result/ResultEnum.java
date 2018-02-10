package org.jypj.dev.result;

import lombok.Getter;

/**
 * @author ChenYu
 * @date 2017-12-1 18:09:30
 */
@Getter
public enum ResultEnum {

    SUCCESS(0, "成功"),

    FAIL(-1, "失败"),

    PARAM_ERROR(1, "参数不正确"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
