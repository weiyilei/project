package com.example.login.controller;


import cn.hutool.json.JSONUtil;
import com.example.login.entity.ChangeData;
import com.example.login.entity.Device;
import com.example.login.repository.DeviceRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;


@RestController
@RequestMapping("/device")
public class DeviceController {

    @Resource
    private DeviceRepository deviceRepository;

    @PostMapping("/check")
    public String checkRepairAvailability(@RequestBody Device device){
        Device deviceFind = deviceRepository.findById(device.getId());
        if(deviceFind == null){
            return "未能找到该设备";
        }
        else{
            if(deviceFind.getWork_status().equals("不能工作")){
                return "该设备不能报修";
            }
            else{
                return "该设备可以报修";
            }
        }
    }

    @PostMapping("/getAll")
    public List<Device> getAllDevices(@RequestParam(value = "temp", required = false) String temp){
        return deviceRepository.findAll();
    }

    @PostMapping("/addDevice")
    public String addNewDevice(@RequestBody Device device){
        device.setWork_status("未工作");
        if(device.getCategory().equals("口腔颌面锥形束计算机体层摄影")){
            device.setImg_url("../../images/KQCT.jpg");
        }
        else if(device.getCategory().equals("超声经颅多普勒血流分析仪")){
            device.setImg_url("../../images/NCC.jpg");
        }
        else if(device.getCategory().equals("数控车床")){
            device.setImg_url("../../images/CC.jpg");
        }
        deviceRepository.save(device);
        return device.getId();
    }

    @PostMapping("/changeDevice")
    public Device changeSpecificDevice(@RequestBody ChangeData changeData){
        String id = changeData.getId();
        String column = changeData.getColumn();
        String value = changeData.getValue();
        Device device = deviceRepository.findById(id);
        if(column.equals("产品")){
            device.setCategory(value);
            deviceRepository.save(device);
        }
        else if(column.equals("品牌")){
            device.setBrand(value);
            deviceRepository.save(device);
        }
        else if(column.equals("型号")){
            device.setProduct(value);
            deviceRepository.save(device);
        }
        else if(column.equals("地点")){
            device.setDepartment(value);
            deviceRepository.save(device);
        }
        else if(column.equals("工作状态")){
            device.setWork_status(value);
            deviceRepository.save(device);
        }
        else if(column.equals("购买日期")){
            device.setBuy_date(value);
            deviceRepository.save(device);
        }
        else if(column.equals("购买价格")){
            device.setPrice(Integer.parseInt(value));
            deviceRepository.save(device);
        }
        return device;
    }

    @PostMapping("/deleteDevice")
    @Transactional
    public Device deleteSpecificDevice(@RequestBody Device device){
        String id = device.getId();
        Device deviceFind = deviceRepository.findById(id);
        deviceRepository.deleteById(id);
        return deviceFind;
    }

    @PostMapping("/getString")
    public List<Device> getAllDevicesByString(@RequestParam(value = "temp", required = false) String temp){
        List<Device> devices = deviceRepository.findAll();
//        String rst = JSONUtil.toJsonStr(devices);
//        return rst;
        return devices;
    }
}
