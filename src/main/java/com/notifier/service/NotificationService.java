package com.notifier.service;

import com.notifier.soap.client.SoapRoaterClientFactory;
import com.router.notify.NotifyService;
import com.router.notify.User;
import com.router.notify.UserArray;
import java.util.List;
import java.util.stream.Collectors;

public class NotificationService {
    static NotifyService soapRoaterNotifyService = SoapRoaterClientFactory.getSoapRoaterClient();

    public static boolean checkTracksForLeads() {
        try {
            UserArray users = soapRoaterNotifyService.getUsersWithoutTracks(1);
            List<User> list = users.getItem();
            String message = "сегодня не делали записей:\n" + list.stream()
                    .map(t -> String.format("%s %s", t.getUsername(), t.getSurname()))
                    .collect(Collectors.joining("\n"));
            soapRoaterNotifyService.sendNotificationToLeads(message);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkTracksForLectors() {
        try {
            UserArray users = soapRoaterNotifyService.getUsersWithoutTracks(3);
            List<User> list = users.getItem();
            String message = "не делали записей в течении трех дней:\n" + list.stream()
                    .map(t -> String.format("%s %s", t.getUsername(), t.getSurname()))
                    .collect(Collectors.joining("\n"));
            soapRoaterNotifyService.sendNotificationToLectors(message);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
