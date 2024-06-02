package com.example.login.controller;


import com.example.login.entity.User;
import com.example.login.repository.UserRepository;
import com.example.login.util.RandomId;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

//modified

@RestController
@RequestMapping("/user")
public class UserController {

    String regex = "[1][34578][0-9]{9}";

    @Resource
    private UserRepository userRepository;

    @PostMapping("/register")
    public String addNewUser(@RequestBody User user){
        if(!user.getTelephone().matches(regex)){
            return "号码格式错误";
        }
        User userFind = userRepository.findByTelephone(user.getTelephone());
        if(userFind != null){
            return "用户已存在";
        }
        String random = RandomId.getUniqueKey();
        user.setId(random);
        user.setNickname(random);
        user.setImg("../../images/default.png");
        userRepository.save(user);
        return "注册成功";
    }

    @PostMapping("/login")
    //用账号和密码匹配用户
    public String login(@RequestBody User user){
        //requestBody自动把前端的json转化为实体类
        User userFind = userRepository.findByTelephone(user.getTelephone());
        if(userFind == null){
            return "该用户不存在";
        }
        String passwordFind = userFind.getPassword();
        if(!user.getPassword().equals(passwordFind)){
            return "密码错误";
        }else{
            return userFind.getId();
        }
    }

    @PostMapping("/getSpecific")
    public User getSpecific(@RequestBody User user){
        return userRepository.findById(user.getId());
    }


    @PostMapping("/changeImg")
    public User changeImg(@RequestBody User user){
        User userFind = userRepository.findById(user.getId());
        userFind.setImg(user.getImg());
        userRepository.save(userFind);
        return userFind;
    }

    @PostMapping("/changeTelephone")
    public String changeTelephone(@RequestBody User user){
        if(userRepository.findByTelephone(user.getTelephone()) != null){
            return "用户已存在";
        }
        User userFind = userRepository.findById(user.getId());
        userFind.setTelephone(user.getTelephone());
        userRepository.save(userFind);
        return userFind.getTelephone();
    }

    @PostMapping("/changeNickname")
    public User changeNickname(@RequestBody User user){
        User userFind = userRepository.findById(user.getId());
        userFind.setNickname(user.getNickname());
        userRepository.save(userFind);
        return userFind;
    }

    @PostMapping("/changeType")
    public User changeType(@RequestBody User user){
        User userFind = userRepository.findById(user.getId());
        userFind.setType(user.getType());
        userRepository.save(userFind);
        return userFind;
    }

    @PostMapping("/changePassword")
    public User changePassword(@RequestBody User user){
        User userFind = userRepository.findById(user.getId());
        userFind.setPassword(user.getPassword());
        userRepository.save(userFind);
        return userFind;
    }
}
