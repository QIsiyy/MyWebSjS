package com.sjs.studentjournal.controller.request;

import lombok.Data;

@Data
public class BaseRequest {
    private Integer pagenumber=1;
    private Integer pagesize=10;
}
