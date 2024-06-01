package com.sjs.studentjournal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Dairy {
    private int id;
    private String writer;
    private String location;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date dairydate;
    private String contents;
    private Integer dairythumbs;
    private Integer dairypopularity;
    private String title;
    private String image;

    public double getScore() {
        return this.dairythumbs * 0.6 + this.dairypopularity * 0.4;
    }

    public boolean contains(String needle) {
        // 如果needle为空或contents为空，直接返回结果
        if (needle == null || needle.isEmpty()) {
            return true; // 空字符串总是被认为包含在任何字符串中
        }
        if (contents == null || contents.isEmpty()) {
            return false; // 非空字符串不会包含在空字符串中
        }
        // 使用循环遍历contents，检查needle的每个字符是否按顺序出现在contents中
        int needleLength = needle.length();
        int contentsLength = contents.length();
        for (int i = 0; i <= contentsLength - needleLength; i++) {
            // 检查needle的第一个字符是否与contents中的当前字符匹配
            if (contents.charAt(i) == needle.charAt(0)) {
                // 如果第一个字符匹配，检查剩余的字符是否也匹配
                boolean found = true;
                for (int j = 1; j < needleLength; j++) {
                    if (contents.charAt(i + j) != needle.charAt(j)) {
                        found = false;
                        break;
                    }
                }
                // 如果所有字符都匹配，返回true
                if (found) {
                    return true;
                }
            }
        }
        return false;
    }
}
