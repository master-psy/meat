package com.api.controller;

import com.api.dto.PriceDto;
import com.api.service.PriceDispatcherService;
import com.api.service.PriceService;
import com.common.base.BaseRespDto;
import com.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 价格计算器
 * 策略模式
 * 需要对扩展开放,对修改关闭
 *
 * @author Administrator
 * @Desc
 */
@Slf4j
@RestController
@RequestMapping("/price")
public class PriceCalculatorController {
    @Autowired
    private List<PriceService> priceService;

    @Autowired
    private PriceDispatcherService priceDispatcherService;

    @PostMapping("/getPrice")
    public BaseRespDto getPrice(PriceDto dto) {
        PriceService priceService = this.priceService.stream().filter(p ->
                p.isCurrentProduct(dto.getType())).findFirst().orElseThrow(() -> new BizException("产品类型错误"));
        return new BaseRespDto(priceService.calculatePrice(dto));
    }

    @PostMapping("/testSuper")
    public BaseRespDto test2(String order) {
        return new BaseRespDto(priceDispatcherService.getCheckResultSuper(order));
    }
    @PostMapping("/testSuper1")
    public BaseRespDto test3(PriceDto dto) {
        PriceService checkResult = priceDispatcherService.getCheckResult(dto);
        return new BaseRespDto(checkResult.calculatePrice(dto));
    }

    public static void main(String[] args) {
        System.out.println(new Double(0)==0);
    }
}
