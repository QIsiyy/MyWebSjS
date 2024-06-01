package com.sjs.studentjournal.service;

import com.sjs.studentjournal.controller.dto.LoginDTO;
import com.sjs.studentjournal.controller.request.LoginRequest;
import com.sjs.studentjournal.controller.request.UserPageRequest;
import com.sjs.studentjournal.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface UserService {
    List<User> listUser();

    List<User>listByCondition(UserPageRequest userPageRequest);

    Object page(UserPageRequest userPageRequest);

    void save(User user);

    User getByid(Integer id);

    void update(User user);

    void deleteById(Integer id);

    LoginDTO login(LoginRequest loginRequest);

    void signin(User user);
}
