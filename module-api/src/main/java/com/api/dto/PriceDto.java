package com.api.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Desc
 */
@Data
public class PriceDto {
    private BigDecimal unitPrice;
    private Integer type;
    private BigDecimal distance;
}
