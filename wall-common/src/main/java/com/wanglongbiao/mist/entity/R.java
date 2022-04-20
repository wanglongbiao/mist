package com.wanglongbiao.mist.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class R<T> {
    private int code;
    private String message;
    private T data;

    public static <E> R<E> ok(E data) {
        return new R<E>(200, "success", data);
    }

    public static <E> R<E> error(E data) {
        return new R<E>(400, "success", data);
    }
}
