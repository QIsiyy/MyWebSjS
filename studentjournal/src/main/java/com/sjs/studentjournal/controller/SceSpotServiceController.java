package com.sjs.studentjournal.controller;


import com.sjs.studentjournal.common.Result;
import com.sjs.studentjournal.controller.request.SceSpotPageRequest;

import com.sjs.studentjournal.entity.SceSpot;

import com.sjs.studentjournal.service.SceSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/spot")
public class SceSpotServiceController {
    @Autowired
    SceSpotService scespotService;


    @GetMapping("/list")
    public Result getAllSpots() {
        try {
            List<SceSpot> spots=scespotService.list();
            return Result.success(spots);
        }catch(Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("addImage")
    public Result addImage(@RequestParam("file") MultipartFile file) {
        if(file.isEmpty()) {
            return Result.error("no picture");
        }
        String OriginFileName = file.getOriginalFilename();

        String FileName = System.currentTimeMillis() + OriginFileName;

        String path ="G:\\myWebSjs\\Mywebinfro\\vue\\src\\assets\\images\\spot\\";

        File dest=new File(path+FileName);

        if(!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return Result.success(FileName);
        }catch(Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    //内部路线的构建




    //景点内寻路






    //增删改
    @PutMapping("/update")
    public Result update(@RequestBody SceSpot scespot) {
        scespotService.update(scespot);
        return Result.success();
    }

    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable Integer id) {
        scespotService.deleteById(id);
        return Result.success();
    }

    @PostMapping("/add")
    public Result save(@RequestBody SceSpot scespot) {
        scespotService.save(scespot);
        return Result.success();
    }

    @GetMapping("page")
    public Result page(SceSpotPageRequest scespotPageRequest) {

        return Result.success( scespotService.page(scespotPageRequest));
    }
}
