package com.sjs.studentjournal.mapper;

import com.sjs.studentjournal.controller.request.LoginRequest;
import com.sjs.studentjournal.controller.request.UserPageRequest;
import com.sjs.studentjournal.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper{

    @Select("select * from user")
    List<User> listUsers();


    List<User> listByCondition(UserPageRequest userPageRequest);

    void save(User user);

    User getById(Integer id);

    void update(User user);

    void deleteById(Integer id);

   User getByAccountAndPassword(LoginRequest loginRequest);
}
