package com.sjs.studentjournal.controller.request;

import lombok.Data;

@Data
public class UserPageRequest extends BaseRequest {
    private Integer id;
    private String account;
}
