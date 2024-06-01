package com.sjs.studentjournal.exception;


import com.sjs.studentjournal.common.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(basePackages = "com.sjs.studentjournal.controller")
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(HttpServletRequest req, Exception e) {
        log.error("异常信息", e);
        return Result.error("系统异常");
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Result customError(HttpServletRequest req,Exception e){
       return Result.error(e.getMessage());
    }

}
