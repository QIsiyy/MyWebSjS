package com.sjs.studentjournal.entity;

import lombok.Data;

@Data
public class Coment {
    private int id;
    private int thumbs;
    private String writer;
    private String content;
    private String date;
}
