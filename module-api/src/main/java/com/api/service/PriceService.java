package com.api.service;

import com.api.dto.PriceDto;
import com.api.vo.PriceVo;

public interface PriceService {
    /**
     * 选择对应的实现类
     *
     * @param type
     * @return
     */
    boolean isCurrentProduct(Integer type);

    /**
     * 计算价钱
     *
     * @param dto
     * @return
     */
    PriceVo calculatePrice(PriceDto dto);
}
