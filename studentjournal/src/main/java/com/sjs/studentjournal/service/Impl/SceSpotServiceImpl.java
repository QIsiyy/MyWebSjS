package com.sjs.studentjournal.service.Impl;

import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sjs.studentjournal.common.Result;
import com.sjs.studentjournal.controller.dto.LoginDTO;
import com.sjs.studentjournal.controller.request.LoginRequest;
import com.sjs.studentjournal.controller.request.SceSpotPageRequest;
import com.sjs.studentjournal.controller.request.UserPageRequest;
import com.sjs.studentjournal.entity.SceSpot;
import com.sjs.studentjournal.entity.User;
import com.sjs.studentjournal.mapper.SceSpotMapper;
import com.sjs.studentjournal.service.SceSpotService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SceSpotServiceImpl implements SceSpotService {

    @Autowired
    SceSpotMapper scespotMapper;

    @Override
    public List<SceSpot> list() {
        try {
            return scespotMapper.list();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object page(SceSpotPageRequest scespotPageRequest) {
        PageHelper.startPage(scespotPageRequest.getPagenumber(),scespotPageRequest.getPagesize());
        List<SceSpot> scespots=scespotMapper.listByCondition(scespotPageRequest);
        PageInfo<SceSpot> pageInfo=new PageInfo<>(scespots);
        return pageInfo;
    }

    @Override
    public void save(SceSpot scespot) {
        scespotMapper.save(scespot);
    }



    @Override
    public void update(SceSpot scespot) {
        scespotMapper.update(scespot);
    }

    @Override
    public void deleteById(Integer id) {
        scespotMapper.deleteById(id);
    }

    @Override
    public SceSpot getById(Integer id) {
        return  scespotMapper.getById(id);
    }

}
