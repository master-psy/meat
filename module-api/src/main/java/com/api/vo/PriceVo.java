package com.api.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Desc
 */
@Data
public class PriceVo {
    private String productName;
    private BigDecimal price;
}
