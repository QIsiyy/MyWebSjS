package com.sjs.studentjournal.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sjs.studentjournal.controller.request.DairyPageRequest;
import com.sjs.studentjournal.entity.Dairy;
import com.sjs.studentjournal.mapper.DairyMapper;
import com.sjs.studentjournal.service.DairyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class DairyServiceImpl implements DairyService {
    private final DairyMapper dairyMapper;

    public DairyServiceImpl(DairyMapper dairyMapper) {
        this.dairyMapper = dairyMapper;
    }

    @Override
    public Object page(DairyPageRequest dairyPageRequest) {
        PageHelper.startPage(dairyPageRequest.getPagenumber(), dairyPageRequest.getPagesize());
        List<Dairy> dairies = dairyMapper.listDairy();

        //xuanzepaixufangshi
        int mode=dairyPageRequest.getMode().intValue();
        int n = dairies.size();
        int k = n-1;// 计算第20%的元素位置
        Random random = new Random();
        Dairy pivot = dairies.get(random.nextInt(n)); // 随机选择一个枢纽元素
        partition(dairies, 0, n - 1, mode);
        PageInfo<Dairy> pageInfo = new PageInfo<Dairy>(dairies);
        return pageInfo;


    }

    @Override
    public List<Dairy> listDairy() {
        return dairyMapper.listDairy();
    }

    @Override
    public void update(Dairy dairy) {
        dairyMapper.update(dairy);
    }

    @Override
    public void deleteById(Integer id) {
        dairyMapper.deleteById(id);

    }

    @Override
    public Dairy getById(Integer id) {
        return dairyMapper.getById(id);
    }

    @Override
    public void save(Dairy dairy) {
        dairyMapper.save(dairy);
    }

    @Override
    public void normallist(List<Dairy> dairyList,int mode) {
        int n = dairyList.size();
        int k = (n * 20) / 100;
        Random random = new Random();
        Dairy pivot = dairyList.get(random.nextInt(n)); // 随机选择一个枢纽元素
        partition(dairyList, 0, n - 1, mode);
    }

    @Override
    public void partition(List<Dairy> dairyList,  int low, int high,int mode) {
        if (low < high) {
            Dairy pivot = dairyList.get(low);
            int left = low;
            int right = high;
            if(mode==0){
                return;
            }
            if(mode==1){
                while (left < right) {
                    // 从右向左找第一个小于基准值的元素
                    while (left < right && dairyList.get(right).getScore() <= pivot.getScore()) {
                        right--;
                    }
                    // 从左向右找第一个大于或等于基准值的元素
                    while (left < right && dairyList.get(left).getScore() >= pivot.getScore()) {
                        left++;
                    }
                    // 交换两个元素
                    if (left < right) {
                        swap(dairyList, left, right);
                    }
                }


            }
            if(mode==2){
                while (left < right) {
                    // 从右向左找第一个小于基准值的元素
                    while (left < right && dairyList.get(right).getDairypopularity() <= pivot.getDairypopularity()) {
                        right--;
                    }
                    // 从左向右找第一个大于或等于基准值的元素
                    while (left < right && dairyList.get(left).getDairypopularity() >= pivot.getDairypopularity()) {
                        left++;
                    }
                    // 交换两个元素
                    if (left < right) {
                        swap(dairyList, left, right);
                    }
                }
            }
            if(mode==3){
                while (left < right) {
                    // 从右向左找第一个小于基准值的元素
                    while (left < right && dairyList.get(right).getDairythumbs() <= pivot.getDairythumbs()) {
                        right--;
                    }
                    // 从左向右找第一个大于或等于基准值的元素
                    while (left < right && dairyList.get(left).getDairythumbs() >= pivot.getDairythumbs()) {
                        left++;
                    }
                    // 交换两个元素
                    if (left < right) {
                        swap(dairyList, left, right);
                    }
                }
            }
            swap(dairyList, low, left);

           partition(dairyList, low,left-1, mode);
            partition(dairyList, left+1,high, mode);
        }
        }

    @Override
    public void swap(List<Dairy> dairyList, int i, int j) {
        Dairy temp=dairyList.get(i);
        dairyList.set(i,dairyList.get(j));
        dairyList.set(j,temp);
    }
}
