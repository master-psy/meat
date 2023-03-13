package com.api.service;

import com.api.dto.PriceDto;

/**
 * @Desc
 */
public interface PriceDispatcherService {
    String getCheckResultSuper(String order);
    PriceService getCheckResult(PriceDto dto);
}
