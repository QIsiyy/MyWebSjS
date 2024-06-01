package com.sjs.studentjournal.controller;

import com.sjs.studentjournal.common.Result;
import com.sjs.studentjournal.controller.request.DairyPageRequest;
import com.sjs.studentjournal.controller.request.DairySearchRequest;
import com.sjs.studentjournal.controller.request.DairyThumbRequest;
import com.sjs.studentjournal.entity.Dairy;
import com.sjs.studentjournal.service.DairyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;


@CrossOrigin
@RestController
@RequestMapping("/dairy")
public class DairyController {
    @Autowired
    DairyService dairyService;

    //不同推荐模式下的数据
    //分页
    @GetMapping("/GetAllDairys/{mode}")
    public Result getAllDairys(@PathVariable("mode") String mode) {
        try {
            int mode0=Integer.parseInt(mode);
            List<Dairy> dairys = dairyService.listDairy();
            dairyService.normallist(dairys,mode0);
            return Result.success(dairys);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    //给用户端提供
    //访问一次popularity++
    //点赞一次thumbs++
    @GetMapping("/detail")
    public Result getDairyById(@RequestBody DairyThumbRequest request) {
        try {
            List<Dairy> dairies = dairyService.listDairy();
            Integer id=request.getId();
            Dairy dairy=new Dairy();
            for (Dairy dairy1 : dairies) {
                if (dairy1.getId()==id) {
                    dairy = dairy1;
                    break;
                }
            }
            int temp=dairy.getDairypopularity();
            dairy.setDairypopularity(temp+1);
            if(request.getIfthumbs()==1){
                int temp2= dairy.getDairythumbs();
                dairy.setDairythumbs(temp2+1);
            }
           dairyService.update(dairy);
            return Result.success(dairy);
        }catch (Exception e){
            return Result.error(e.getMessage());
        }

    }

    @GetMapping("dairysearch")
    public Result searchDairy(@RequestBody DairySearchRequest request) {
        List<Dairy> dairys = dairyService.listDairy();
        List<Dairy> res=new ArrayList();
        String content=request.getContents();
        for (Dairy dairy : dairys) {
            if(dairy.contains(content)){
                res.add(dairy);
            }

        }
        return Result.success(res);
    }


    //图片的无损压缩
    @PostMapping("addImage")
    public Result addImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("no picture");
        }
        String originFileName = file.getOriginalFilename();

        // 添加压缩后的文件扩展名
        String compressedFileName = System.currentTimeMillis() + "_" + originFileName;

        String path = "G:\\myWebSjs\\Mywebinfro\\vue\\src\\assets\\images\\dairy\\";

        File dest = new File(path + compressedFileName);

        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        try {
            // 将MultipartFile转换为File
            File inputFile = convertMultipartFileToFile(file);

            // 读取图片
            BufferedImage originalImage = ImageIO.read(inputFile);

            // 获取JPEG图片写入器
            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
            if (!writers.hasNext()) throw new IllegalStateException("No writers found");

            ImageWriter writer = writers.next();

            // 设置压缩参数
            ImageWriteParam param = writer.getDefaultWriteParam();
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(0.8f); // 设置压缩质量，0.8是一个常用的值

            // 输出到文件
            ImageOutputStream outputStream = ImageIO.createImageOutputStream(dest);
            writer.setOutput(outputStream);
            writer.write(null, new IIOImage(originalImage, null, null), param);
            outputStream.flush();
            outputStream.close();
            writer.dispose();

            // 删除临时文件
            inputFile.delete();

            return Result.success(compressedFileName);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }


    //图片的无损压缩
    private static File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File file = Files.createTempFile("temp", multipartFile.getOriginalFilename()).toFile();
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        return file;
    }


    //增删改，用数据库实现
    @PutMapping("/update")
    public Result update(@RequestBody Dairy dairy) {
        dairyService.update(dairy);
        return Result.success();
    }

    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable Integer id) {
        dairyService.deleteById(id);
        return Result.success();
    }



    @PostMapping("/add")
    public Result save(@RequestBody Dairy dairy) {
        dairyService.save(dairy);
        return Result.success();
    }


    //递交分页
    @GetMapping("page")
    public Result page(DairyPageRequest dairyPageRequest) {

        return Result.success(dairyService.page(dairyPageRequest));
    }
}
