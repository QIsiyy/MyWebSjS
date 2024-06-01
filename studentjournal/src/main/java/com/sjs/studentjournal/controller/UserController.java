package com.sjs.studentjournal.controller;

import com.sjs.studentjournal.common.Result;
import com.sjs.studentjournal.controller.dto.LoginDTO;
import com.sjs.studentjournal.controller.request.LoginRequest;
import com.sjs.studentjournal.controller.request.UserPageRequest;
import com.sjs.studentjournal.entity.User;
import com.sjs.studentjournal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/list")
    public Result getAllUsers() {
        try {
            List<User> users=userService.listUser();
            return Result.success(users);
        }catch(Exception e) {
            return Result.error(e.getMessage());
        }
    }



    //增删改查
    @PutMapping("/update")
    public Result update(@RequestBody User user) {
        userService.update(user);
        return Result.success();
    }

    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        User user=userService.getByid(id);
        return Result.success(user);
    }

    @PostMapping("/add")
    public Result save(@RequestBody User user) {
        userService.save(user);
        return Result.success();
    }


    //分页递交
    @GetMapping("/page")
    public Result Page(UserPageRequest userPageRequest){

        return Result.success(userService.page(userPageRequest));
    }


    //登录、注册
    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest loginRequest) {
        try {
            LoginDTO login = userService.login(loginRequest);
            return Result.success(login);
        }catch(Exception e) {
            return Result.error(e.getMessage());
        }

    }

    @PostMapping("/signin")
    public Result signin(@RequestBody User user) {
        userService.signin(user);
        return Result.success();
    }


}
