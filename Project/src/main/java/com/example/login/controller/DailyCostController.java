package com.example.login.controller;


import com.example.login.entity.DailyCost;
import com.example.login.repository.DailyCostRepository;
import com.example.login.repository.EventRepository;
import com.example.login.repository.UnionRepository;
import com.example.login.util.RandomId;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/cost")
public class DailyCostController {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

    @Resource
    private DailyCostRepository dailyCostRepository;

//    private final UnionRepository unionRepository;
//
//    public DailyCostController(UnionRepository unionRepository) {
//        this.unionRepository = unionRepository;
//    }

    @PostMapping("/getCurrentMonth")
    public Integer getCurrentMonth(@RequestParam(value = "temp", required = false) String temp){
        Date date = new Date();
        String current = formatter.format(date);
        return Integer.parseInt(current.substring(5,7));
    }

    @PostMapping("/month")
    public Integer[] getMonthlyCost(@RequestParam(value = "temp", required = false) String temp){
        Date date = new Date();
        String current = formatter.format(date);
        String currentYear = current.substring(0,4);
        String currentMonth = current.substring(5,7);
        String everyDate;
        String year = "";
        String month = "";
        int diff = 0;
        Integer[] rst = new Integer[12];
        for(int i = 0;i < 12; i++){
            rst[i] = 0;
        }
        List<DailyCost> data =  dailyCostRepository.findAll();
        for (DailyCost datum : data) {
            everyDate = datum.getDate();
            year = everyDate.substring(0, 4);
            month = everyDate.substring(5, 7);
            diff = (Integer.parseInt(currentYear) - Integer.parseInt(year)) * 12 + Integer.parseInt(currentMonth) - Integer.parseInt(month);
            if (diff < 12) {
                rst[diff] += datum.getCost();
            }
        }
        return rst;
    }

    @PostMapping("/add")
    public String addDailyCost(@RequestBody DailyCost dailyCost){
        DailyCost dailyCostFind = dailyCostRepository.findByDate(dailyCost.getDate());
        if(dailyCostFind != null){
            int originCost = dailyCostFind.getCost();
            dailyCostFind.setCost(originCost + dailyCost.getCost());
            dailyCostRepository.save(dailyCostFind);
            return "已有";
        }
        else{
            String random = RandomId.getUniqueKey();
            dailyCost.setId(random);
            dailyCostRepository.save(dailyCost);
            return "新增";
        }
    }

    @PostMapping("/getCost")
    public Integer[] getCost(@RequestParam(value = "temp", required = false) String temp){
        Integer[] rst = {0,0};
        Date date = new Date();
        String current = formatter.format(date);
        DailyCost dailyCostFind = dailyCostRepository.findByDate(current);
        if(dailyCostFind != null){
            rst[0] = dailyCostFind.getCost();
        }
        String currentYear = current.substring(0,4);
        String currentMonth = current.substring(5,7);
        List<DailyCost> list = dailyCostRepository.findAll();
        for(DailyCost dailyCost : list){
            if(dailyCost.getDate().substring(5,7).equals(currentMonth) && dailyCost.getDate().substring(0,4).equals(currentYear)){
                rst[1] += dailyCost.getCost();
            }
        }
        return rst;
    }
}
