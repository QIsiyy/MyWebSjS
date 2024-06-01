package com.sjs.studentjournal.common;

import lombok.Data;

@Data
public class Result {
    private static final String SUCCESS = "200";
    private static final String ERROR = "400";

    private String code;
    private Object data;
    private String msg;

    public static Result success() {
        Result result = new Result();
        result.setCode(SUCCESS);
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.setCode(ERROR);
        result.setMsg(msg);
        return result;
    }
}
