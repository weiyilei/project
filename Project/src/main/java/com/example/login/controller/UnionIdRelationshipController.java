package com.example.login.controller;


import com.example.login.entity.OpenIdAndUnionId;
import com.example.login.entity.UnionIdRelationship;
import com.example.login.repository.UnionRepository;
import com.example.login.util.WxLoginUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/WxAuthorize")
public class UnionIdRelationshipController {

    @Resource
    private UnionRepository unionRepository;

    @PostMapping("/login")
    public UnionIdRelationship getUserInfo(@RequestParam String code){
        OpenIdAndUnionId openIdAndUnionId = WxLoginUtil.wxLogin(code);
        UnionIdRelationship unionIdRelationship = unionRepository.findByUnionId(openIdAndUnionId.getUnionId());
        if(unionIdRelationship != null){// old user
            return unionIdRelationship;
        }
        else{
            UnionIdRelationship temp = new UnionIdRelationship("", "", openIdAndUnionId.getOpenId(), openIdAndUnionId.getUnionId());
            return temp;
        }
    }
}
