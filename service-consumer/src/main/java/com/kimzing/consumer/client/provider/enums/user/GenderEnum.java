package com.kimzing.consumer.client.provider.enums.user;

/**
 * 性别枚举类.
 *
 * @author KimZing - kimzing@163.com
 * @since 2018-08-07 02:02
 */
public enum GenderEnum {

    //女
    WOMAN(0, "女"),
    //男
    MAN(1, "男");

    private Integer code;

    private String desc;

    GenderEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return this.desc;
    }
}
