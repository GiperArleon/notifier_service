package com.notifier.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LeadsThread implements Runnable {

    @Override
    public void run() {
        if(NotificationService.checkTracksForLeads())
            log.info("LeadsThread task done success!");
        else
            log.warn("LeadsThread task perform error");
    }
}
