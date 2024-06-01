package com.sjs.studentjournal.entity;

import lombok.Data;

@Data
public class User {
    private int id;
    private String name;
    private String account;
    private String password;
    private Integer age;
    private Integer sex;
    private String phone;
     private Integer roleid;
    private String isvalid;
}
