package com.sjs.studentjournal.mapper;

import com.sjs.studentjournal.controller.request.DairyPageRequest;
import com.sjs.studentjournal.entity.Dairy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DairyMapper {

    @Select("select * from dairy")
    List<Dairy> listDairy();

    void update(Dairy dairy);

    void deleteById(Integer id);

    Dairy getById(Integer id);

    void save(Dairy dairy);
}
