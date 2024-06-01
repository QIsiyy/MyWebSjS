package com.sjs.studentjournal.service.Impl;

import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sjs.studentjournal.controller.dto.LoginDTO;
import com.sjs.studentjournal.controller.request.LoginRequest;
import com.sjs.studentjournal.controller.request.UserPageRequest;
import com.sjs.studentjournal.entity.User;
import com.sjs.studentjournal.exception.CustomException;
import com.sjs.studentjournal.mapper.UserMapper;
import com.sjs.studentjournal.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper  userMapper;

    @Override
    public List<User> listUser() {
        try {
           return userMapper.listUsers();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> listByCondition(UserPageRequest userPageRequest) {
        return userMapper.listByCondition(userPageRequest);
    }

    @Override
    public Object page(UserPageRequest userPageRequest) {
        PageHelper.startPage(userPageRequest.getPagenumber(),userPageRequest.getPagesize());
        List<User> users=userMapper.listByCondition(userPageRequest);
        PageInfo<User> pageInfo=new PageInfo<>(users);
        return pageInfo;
    }

    @Override
    public void save(User user) {

        userMapper.save(user);
    }

    @Override
    public User getByid(Integer id) {
        return userMapper.getById(id);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public LoginDTO login(LoginRequest loginRequest){

        //非空判断

        //新增不重复判断


        User user=userMapper.getByAccountAndPassword(loginRequest);
        LoginDTO dto=new LoginDTO();
        BeanUtils.copyProperties(user,dto);
        return dto;

    }

    @Override
    public void signin(User user) {
        //重复判断
        List<User> users = listUser();
        for (User user1 : users) {
            if(user1.getAccount().equals(user.getAccount())){
                   throw new CustomException("账号已存在，请进行修改");
            }
        }
        //添加用户
        user.setRoleid(1);
        user.setIsvalid("Y");
        userMapper.save(user);
    }

}
