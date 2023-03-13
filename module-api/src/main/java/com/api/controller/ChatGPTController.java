package com.api.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.api.service.ChatService;
import com.common.base.BaseRespDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/chat")
public class ChatGPTController {
    @Resource
    private ChatService chatService;

    @ApiOperation("测试")
    @GetMapping("/test")
    public BaseRespDto test() {
        HttpResponse execute = HttpRequest.get("www.baidu.com").execute();
        return new BaseRespDto(execute.body());
    }
}
