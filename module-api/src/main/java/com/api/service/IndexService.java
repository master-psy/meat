package com.api.service;

import com.api.dto.IndexDto;
import com.common.base.BaseRespDto;

import javax.servlet.http.HttpServletResponse;

public interface IndexService {
    BaseRespDto index(IndexDto dto);

    void exportUser(HttpServletResponse response);
}
