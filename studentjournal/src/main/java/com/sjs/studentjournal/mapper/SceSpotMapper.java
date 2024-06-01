package com.sjs.studentjournal.mapper;

import com.sjs.studentjournal.controller.request.SceSpotPageRequest;
import com.sjs.studentjournal.entity.SceSpot;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SceSpotMapper {

    void update(SceSpot scespot);

    @Select("select * from scespot")
    List<SceSpot> list();

    void deleteById(Integer id);

    SceSpot getById(Integer id);

    void save(SceSpot scespot);

    Object page(SceSpotPageRequest scespotPageRequest);

    List<SceSpot> listByCondition(SceSpotPageRequest scespotPageRequest);
}
