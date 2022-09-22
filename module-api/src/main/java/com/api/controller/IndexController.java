package com.api.controller;

import com.mapper.entity.TbUser;
import com.mapper.mapper.TbUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desc
 */
@RestController
public class IndexController {
    @Autowired
    private TbUserMapper tbUserMapper;

    @RequestMapping("/")
    public String index() {
        TbUser tbUser = tbUserMapper.selectByPrimaryKey(5);
        System.out.println(tbUser.getUsername());
        return "hello springboot module";
    }
}
