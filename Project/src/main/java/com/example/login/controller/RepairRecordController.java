package com.example.login.controller;


import com.example.login.entity.Device;
import com.example.login.entity.RepairRecord;
import com.example.login.repository.DeviceRepository;
import com.example.login.repository.RepairRecordRepository;
import com.example.login.util.RandomId;
import org.hibernate.id.IntegralDataTypeHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/repairReport")
public class RepairRecordController {

    @Resource
    private RepairRecordRepository repairRecordRepository;

    @Resource
    private DeviceRepository deviceRepository;

    @PostMapping("/report")
    public String addRepairReport(@RequestBody RepairRecord repairRecord){
        String device_id_temp = repairRecord.getDevice_id();
        Device device_temp = deviceRepository.findById(device_id_temp);
        String img_url = device_temp.getImg_url();
        repairRecord.setImg_url(img_url);
        String randomId = RandomId.getUniqueKey();
        repairRecord.setId(randomId);
        repairRecord.setRepairstatus("待维修");
        repairRecordRepository.save(repairRecord);
        String device_id = repairRecord.getDevice_id();
        Device device = deviceRepository.findById(device_id);
        device.setWork_status("不能工作");
        deviceRepository.save(device);
        return randomId;
    }

    @PostMapping("/causeChart")
    public Integer[] getCauseNumber(@RequestParam(value = "temp", required = false) String temp){
        Integer cause0 = repairRecordRepository.countAllByCause("操作使用不当");
        Integer cause1 = repairRecordRepository.countAllByCause("产品质量不佳");
        Integer cause2 = repairRecordRepository.countAllByCause("设备老化");
        Integer cause3 = repairRecordRepository.countAllByCause("长时间未保养");
        return new Integer[]{cause0, cause1, cause2, cause3};
    }

    @PostMapping("/getAll")
    public List<RepairRecord> getAllRepairRecord(@RequestParam(value = "temp", required = false) String temp){
        return repairRecordRepository.findAll();
    }

    @PostMapping("/getWaiting")
    public List<RepairRecord> getWaitingRepairRecord(@RequestParam(value = "temp", required = false) String temp){
        return repairRecordRepository.findAllByRepairstatus("待维修");
    }

    @PostMapping("/getRepairing")
    public List<RepairRecord> getRepairingRepairRecord(@RequestParam(value = "temp", required = false) String temp){
        return repairRecordRepository.findAllByRepairstatus("维修中");
    }

    @PostMapping("/getDone")
    public List<RepairRecord> getDoneRepairRecord(@RequestParam(value = "temp", required = false) String temp){
        return repairRecordRepository.findAllByRepairstatus("已维修");
    }

    @PostMapping("/repairData")
    public String editRepairData(@RequestBody RepairRecord repairRecord){
        RepairRecord record = repairRecordRepository.findById(repairRecord.getId());
        if(repairRecord.getName() != null){
            record.setName(repairRecord.getName());
        }
        if(repairRecord.getRepair_date() != null){
            record.setRepair_date(repairRecord.getRepair_date());
        }
        if(repairRecord.getRepairstatus() != null){
            record.setRepairstatus(repairRecord.getRepairstatus());
        }
        if(repairRecord.getExpenditure() != null){
            record.setExpenditure(repairRecord.getExpenditure());
        }
        repairRecordRepository.save(record);
        return record.getDevice_id();
    }

    @PostMapping("/changeStatus")
    public Device changeDeviceStatus(@RequestBody Device device){
        Device deviceFind = deviceRepository.findById(device.getId());
        deviceFind.setWork_status("未工作");
        deviceRepository.save(deviceFind);
        return deviceFind;
    }

    @PostMapping("/repairDateChange")
    public Device changeDeviceRepair(@RequestBody Device device){
        Device deviceFind = deviceRepository.findById(device.getId());
        deviceFind.setRepair_date(device.getRepair_date());
        deviceRepository.save(deviceFind);
        return deviceFind;
    }

    @PostMapping("/bothChange")
    public Device BothChange(@RequestBody Device device){
        Device deviceFind = deviceRepository.findById(device.getId());
        deviceFind.setWork_status("未工作");
        deviceFind.setRepair_date(device.getRepair_date());
        deviceRepository.save(deviceFind);
        return deviceFind;
    }

    @PostMapping("/predict")
    public List<Device> predictDevices(@RequestParam(value = "temp", required = false) String temp) throws ParseException {
        List<Device> all = deviceRepository.findAll();
        List<Device> rst = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        for(Device device : all){
            String dateString = device.getRepair_date();
            Date last = dateFormat.parse(dateString);
            Date now = new Date();
            long daysBetween = (now.getTime() - last.getTime() + 1000000)/(3600*24*1000);
            if(daysBetween > 30){
                rst.add(device);
            }
        }
        return rst;
    }
}
