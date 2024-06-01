package com.sjs.studentjournal.controller.request;

import lombok.Data;

@Data
public class DairyPageRequest extends BaseRequest{
    private Integer id;
    private String writer;
    private Integer mode=1;
}