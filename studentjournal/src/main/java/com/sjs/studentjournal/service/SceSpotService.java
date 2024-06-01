package com.sjs.studentjournal.service;

import com.sjs.studentjournal.controller.request.SceSpotPageRequest;
import com.sjs.studentjournal.entity.SceSpot;

import java.util.List;

public interface SceSpotService {
    List<SceSpot> list();

    void update(SceSpot scespot);

    void deleteById(Integer id);

    SceSpot getById(Integer id);

    void save(SceSpot scespot);

    Object page(SceSpotPageRequest scespotPageRequest);
}
