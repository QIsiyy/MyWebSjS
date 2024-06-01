package com.sjs.studentjournal.controller.request;

import lombok.Data;

@Data
public class DairySearchRequest extends DairyPageRequest{
    private String dairyName;
    private String contents;
}
