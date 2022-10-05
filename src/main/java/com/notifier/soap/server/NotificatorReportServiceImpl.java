package com.notifier.soap.server;

import com.notifier.service.LeadsThread;
import com.notifier.service.LectorsThread;
import lombok.extern.slf4j.Slf4j;
import javax.jws.WebService;

@Slf4j
@WebService(endpointInterface = "com.notifier.soap.server.NotificatorReportService")
public class NotificatorReportServiceImpl implements NotificatorReportService {

    @Override
    public void sendNotificationReportToLeads() {
        LeadsThread leadsThread = new LeadsThread();
        Thread thread = new Thread(leadsThread);
        thread.start();
    }

    @Override
    public void sendNotificationReportToLectors() {
        LectorsThread lectorsThread= new LectorsThread();
        Thread thread = new Thread(lectorsThread);
        thread.start();
    }
}
