package com.sjs.studentjournal.service;

import com.sjs.studentjournal.controller.request.DairyPageRequest;
import com.sjs.studentjournal.entity.Dairy;

import java.util.List;

public interface DairyService {
    Object page(DairyPageRequest dairyPageRequest);

    List<Dairy> listDairy();

    void update(Dairy dairy);

    void deleteById(Integer id);

    Dairy getById(Integer id);

    void save(Dairy dairy);

    void normallist(List<Dairy> dairyList,int mode);

    void partition(List<Dairy> dairyList,  int start, int end,int mode);

    void swap(List<Dairy> dairyList, int i, int j);

}
