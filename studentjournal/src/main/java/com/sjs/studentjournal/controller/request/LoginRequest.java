package com.sjs.studentjournal.controller.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String account;
    private String password;
}
