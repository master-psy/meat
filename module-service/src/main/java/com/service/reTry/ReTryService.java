package com.service.reTry;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.github.rholder.retry.*;
import com.google.common.base.Predicates;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @Desc
 */
@Slf4j
public class ReTryService {
    private static int num = 1;

    public static void main(String[] args) {
        Callable<Boolean> callable = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return method(4);
            }
        };
        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfResult(Predicates.<Boolean>isNull())
                .retryIfExceptionOfType(ArithmeticException.class)
                .retryIfRuntimeException()
                // 等待时间
                .withWaitStrategy(WaitStrategies.fixedWait(3, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .build();
        try {
            retryer.call(callable);
        } catch (RetryException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Boolean method(int type) {
        log.info("来啦{}", num++);
        HttpResponse execute = HttpRequest.get("https://www.baidu.com").execute();
        int status = execute.getStatus();
        String body = execute.body();
        //System.out.println(body);
        System.out.println(status);
        switch (type) {
            case 1:
                return true;
            case 2:
                return false;
            case 3:
                throw new RuntimeException("服务未启动");
            case 4:
                int a = 1 / 0;
            default:
                return null;
        }
    }
}
