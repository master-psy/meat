package com.api.service.impl;

import com.api.dto.PriceDto;
import com.api.service.PriceDispatcherService;
import com.api.service.PriceService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @Desc
 */
@Service
public class PriceDispatcherServiceImpl implements PriceDispatcherService {
    private Map<String, Function<String, String>> checkResultDispatcher = new HashMap<>();

    @PostConstruct
    public void checkResultDispatcher() {
        checkResultDispatcher.put("1", order -> String.format("对%s执行业务逻辑1", order));
        checkResultDispatcher.put("2", order -> String.format("对%s执行业务逻辑2", order));
        checkResultDispatcher.put("3", order -> String.format("对%s执行业务逻辑3", order));
        checkResultDispatcher.put("4", order -> String.format("对%s执行业务逻辑4", order));
        checkResultDispatcher.put("5", order -> String.format("对%s执行业务逻辑5", order));
        checkResultDispatcher.put("6", order -> String.format("对%s执行业务逻辑6", order));
        checkResultDispatcher.put("7", order -> String.format("对%s执行业务逻辑7", order));
        checkResultDispatcher.put("8", order -> String.format("对%s执行业务逻辑8", order));
        checkResultDispatcher.put("9", order -> String.format("对%s执行业务逻辑9", order));
    }

    @Override
    public String getCheckResultSuper(String order) {
        //从逻辑分派Dispatcher中获得业务逻辑代码，result变量是一段lambda表达式
        Function<String, String> result = checkResultDispatcher.get(order);
        if (result != null) {
            //执行这段表达式获得String类型的结果
            return result.apply(order);
        }
        return "不在处理的逻辑中返回业务错误";
    }

    private Map<Integer, PriceService> checkResultService = new HashMap<>();

    @PostConstruct
    public void resultDispatcher() {
        checkResultService.put(1,new JDPriceServiceImpl());
        checkResultService.put(2,new YDPriceServiceImpl());
        checkResultService.put(3,new YTPriceServiceImpl());
    }

    @Override
    public PriceService getCheckResult(PriceDto dto) {
        return checkResultService.get(dto.getType());
    }
}
