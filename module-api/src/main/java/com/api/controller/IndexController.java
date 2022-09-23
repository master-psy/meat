package com.api.controller;

import com.mapper.shanyou.entity.TbUser;
import com.mapper.shanyou.mapper.TbUserMapper;
import com.mapper.yuemenu.entity.TbUsers;
import com.mapper.yuemenu.mapper.TbUsersMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desc
 */
@Slf4j
@RestController
public class IndexController {
    @Autowired
    private TbUserMapper tbUserMapper;
    @Autowired
    private TbUsersMapper tbUsersMapper;

    @RequestMapping("/")
    public String index() {
        TbUser tbUser = tbUserMapper.selectByPrimaryKey(5);
        log.info("shanyou username:{}", tbUser.getUsername());
        TbUsers tbUsers = tbUsersMapper.selectByPrimaryKey(3);
        log.info("yuemenu username:{}", tbUsers.getUsername());
        return "hello springboot module";
    }
}
