package com.api.controller;

import com.api.dto.IndexDto;
import com.api.service.IndexService;
import com.common.base.BaseRespDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Desc
 */
@Slf4j
@RestController
public class IndexController {
    @Autowired
    private IndexService indexService;

    @RequestMapping("/")
    public BaseRespDto index(IndexDto dto) {
        return indexService.index(dto);
    }

    @RequestMapping("/export")
    public void exportUser(HttpServletResponse response) {
        indexService.exportUser(response);
    }
}
