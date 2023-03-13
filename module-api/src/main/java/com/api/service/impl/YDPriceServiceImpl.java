package com.api.service.impl;

import com.api.dto.PriceDto;
import com.api.service.PriceService;
import com.api.vo.PriceVo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @Desc
 */
@Service
public class YDPriceServiceImpl implements PriceService {
    private String productName = "韵达";
    private BigDecimal pickFee = BigDecimal.TEN;
    private BigDecimal minDistance = BigDecimal.valueOf(120);

    @Override
    public boolean isCurrentProduct(Integer type) {
        return Objects.equals(type, 3);
    }

    @Override
    public PriceVo calculatePrice(PriceDto dto) {
        PriceVo vo = new PriceVo();
        BigDecimal distance = minDistance.compareTo(dto.getDistance()) > 0 ? minDistance : dto.getDistance();
        BigDecimal fee = distance.multiply(dto.getUnitPrice()).add(pickFee);
        vo.setPrice(fee);
        vo.setProductName(productName);
        return vo;
    }
}
