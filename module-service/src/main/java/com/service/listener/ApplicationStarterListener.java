package com.service.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Administrator
 * @Desc
 */
@Slf4j
@Component
public class ApplicationStarterListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            log.info(InetAddress.getLocalHost() + " :系统启动成功! 我监听的");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
