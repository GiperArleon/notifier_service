package com.notifier.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LectorsThread implements Runnable {

    @Override
    public void run() {
        if(NotificationService.checkTracksForLectors())
            log.info("LectorsThread task success!");
        else
            log.error("LectorsThread task error");
    }
}
