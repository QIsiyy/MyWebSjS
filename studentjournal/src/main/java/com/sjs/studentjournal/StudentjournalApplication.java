package com.sjs.studentjournal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class StudentjournalApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentjournalApplication.class, args);
    }

    @GetMapping
    public String health() {
        return "SUCCESS";
    }
}
