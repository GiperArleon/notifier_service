package com.notifier;

import com.notifier.service.LeadsThread;
import com.notifier.service.LectorsThread;
import com.notifier.soap.server.NotificatorReportServiceImpl;
import com.notifier.tools.Utils;
import lombok.extern.slf4j.Slf4j;
import javax.xml.ws.Endpoint;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Main {
    static final int CHECK_LEADS_TIME = 1350;    // 1060 = 17:40    1215 = 20:15   1280 = 21:20    1350 = 22:30     1380 = 23:00     1440 = 24:00
    static final int CHECK_LECTORS_TIME = 1380;
    static final int POOL_SIZE = 2;

    public static void main(String[] args) {
        try {
            log.info("* * *");
            final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(POOL_SIZE);
            scheduler.scheduleAtFixedRate(new LeadsThread(), Utils.initialDelayMinutes(CHECK_LEADS_TIME), Utils.MINUTES_IN_DAY, TimeUnit.MINUTES);
            scheduler.scheduleAtFixedRate(new LectorsThread(), Utils.initialDelayMinutes(CHECK_LECTORS_TIME), Utils.MINUTES_IN_DAY, TimeUnit.MINUTES);

            Endpoint endpoint = Endpoint.create(new NotificatorReportServiceImpl());
            endpoint.publish("http://localhost:8033/notification");
        } catch(Throwable e) {
            log.error(e.toString());
        }
    }
}
