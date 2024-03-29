package com.notifier.soap.server;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style= SOAPBinding.Style.RPC)
public interface NotificatorReportService {
    @WebMethod
    void sendNotificationReportToLeads();

    @WebMethod
    void sendNotificationReportToLectors();
}
