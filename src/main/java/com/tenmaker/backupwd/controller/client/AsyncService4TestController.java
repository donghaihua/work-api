package com.tenmaker.backupwd.controller.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncService4TestController {
    private static final Logger logger = LoggerFactory.getLogger(AsyncService4TestController.class);

    @Async
    public void asyncMethod(int t) {
        try {
            logger.debug("Invocation service " + t + " started.");
            Thread.sleep(20 * 1000L);
            logger.debug("Invocation service " + t + " finished.");
        } catch (InterruptedException e) {
            logger.debug("Invocation service " + t + " is interrupted.");
        }
    }

    @Async
    synchronized public void lockedAsyncMethod(int t) {
        try {
            logger.debug("Invocation service(locked) " + t + " started.");
            Thread.sleep(20 * 1000L);
            logger.debug("Invocation service(locked) " + t + " finished.");
        } catch (InterruptedException e) {
            logger.debug("Invocation service(locked) " + t + " is interrupted.");
        }
    }
}
