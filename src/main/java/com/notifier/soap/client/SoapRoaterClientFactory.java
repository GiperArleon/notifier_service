package com.notifier.soap.client;

import com.router.notify.NotifyService;
import com.router.notify.NotifyServiceImplService;
import lombok.extern.slf4j.Slf4j;
import java.net.URL;

@Slf4j
public class SoapRoaterClientFactory {

    private static NotifyService instance;

    private SoapRoaterClientFactory() {
    }

    public static NotifyService getSoapRoaterClient() {
        if(instance == null) {
            try {
                URL url = new URL("http://localhost:8066/notification?wsdl");
                NotifyServiceImplService notifyServiceImplService = new NotifyServiceImplService(url);
                instance = notifyServiceImplService.getNotifyServiceImplPort();
            } catch(Exception e) {
                log.error("Can not init user soap interface: {}", e.toString());
            }
        }
        return instance;
    }
}
