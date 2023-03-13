package com.api.controller;

import com.api.dto.LoginDto;
import com.api.vo.LoginVo;
import com.common.base.BaseRespDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @Desc
 */
@Slf4j
@RestController
@RequestMapping("/admin")
public class VueController {
    @RequestMapping("/doLogin")
    public BaseRespDto doLogin(LoginDto dto) {
        log.info("{}",dto);
        LoginVo vo = new LoginVo();
        vo.setId("10");
        return new BaseRespDto(vo);
    }
    @RequestMapping("/main")
    public BaseRespDto main(String id) {
        Map map = new HashMap<>();
        map.put("msg", "hello");
        return new BaseRespDto(map);
    }
}
