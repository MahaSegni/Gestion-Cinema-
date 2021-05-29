/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.notifications.LocalNotification;
import com.codename1.l10n.ParseException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.api.Mail;
import com.mycompany.myapp.entities.Event;
import com.mycompany.myapp.utils.DataSource;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;

/**
 *
 * @author mehdi
 */
public class EventService {
   private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Event> Events;
    
    public static final String ACCOUNT_SID = "ACe19f6c0a4c86acf138ab451e7d813d6e";
    public static final String AUTH_TOKEN = "99965de79b5710509a4e766cad045706";

    public EventService() {
        request = DataSource.getInstance().getRequest();
    }

    public boolean addEvent(Event e) {
        String url = Statics.BASE_URL+"/addevent/new?name="+e.getNom()+"&capacity="+e.getCapacite()+"&emplacement="+e.getEmplacement();

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
                sendSms();
                Mail.sendMail();
                Notification();
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    public ArrayList<Event> getAllEvents() {
        String url = Statics.BASE_URL + "/allEvents/" ;

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Events = parseEvents(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Events;
    }
    
   

    public ArrayList<Event> parseEvents(String jsonText) {
      
        try {
            Events = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                int id = (int)Float.parseFloat(obj.get("id").toString());
                String Name = obj.get("name").toString();
                int Capacite = (int)Float.parseFloat(obj.get("c").toString());
                String empl = obj.get("emplacement").toString();
               Events.add(new Event( id,Name,Capacite,empl));

            }
        } catch (IOException ex) {
        }
        return Events;
    }
   
    public ArrayList<Event> getEvent(int id) {
        String url = Statics.BASE_URL + "/event/"+id;

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Events = parseEvents(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Events;
    }
    public ArrayList<Event> TriEvent() {
            String url =Statics.BASE_URL +"/event/trier";
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Events = parseEvents(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return Events;
    }
    public void sendSms(){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21652645760"),
                new com.twilio.type.PhoneNumber("+17279456347"),
                "évènement ajouté avec succée")
            .create();

        System.out.println(message.getSid());
    }
    public void Notification(){
        LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("It's time to take a break and look at me");
        n.setAlertTitle("Break Time!");
        n.setAlertSound("/notification_sound_beep-01a.mp3");
            // alert sound file name must begin with notification_sound

        Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
        );
    }
}

