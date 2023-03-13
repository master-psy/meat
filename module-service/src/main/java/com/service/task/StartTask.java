package com.service.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Desc
 */
@Slf4j
@Configuration
@EnableScheduling
public class StartTask {

    //@Scheduled(cron = " */30 * * * * ?")
    public void test() {
        log.info("我输出了");
    }

}
