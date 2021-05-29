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
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Cat;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.twilio.Twilio ;
/**
 *
 * @author bhk
 */

public class ServiceCat {
    
    //singleton
    public static ServiceCat instance=null;
    
    public static boolean resultOK = true;
    
    //initialistation connction reqest
    public ConnectionRequest req;

    public static ServiceCat getInstance(){
        if(instance == null)
            instance=new ServiceCat();
        return instance;
    }
    
    public ServiceCat(){
        req=new ConnectionRequest();
    }
    
    //ajout
    public void ajouterCat(Cat cat){
        String url=Statics.BASE_URL+"/addCatJSON/new?nomc="+cat.getNomc();
        req.setUrl(url);
        req.addResponseListener((e)-> {
            String str = new String(req.getResponseData());//reponse json dans le navigateur
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution de request sinn ça va pas marcher   
    }
    
    //affichage
    public ArrayList<Cat>afficherCat(){
        ArrayList<Cat> result = new ArrayList();
        
        String url = Statics.BASE_URL+"/afficherCatjsonTwig";      
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try{
                    Map<String,Object>mapCat = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> listOfMaps =(List<Map<String,Object>>) mapCat.get("root");
                    
                    for(Map<String,Object> obj : listOfMaps){
                        Cat ca =new Cat();
                        //ps: id dima float ndakhlouha fel codenameone
                        float idc = Float.parseFloat(obj.get("idc").toString());
                        String nomc = obj.get("nomc").toString();
                        
                        ca.setIdc((int)idc);
                        ca.setNomc(nomc);
                        
                        //insert data into ArrayList result
                        result.add(ca);
                    }
                    
                }catch(Exception ex){
                    
                    ex.printStackTrace();
                }
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution de request sinn ça va pas marcher   
        return result; 
    }
    
//    
//api sms
//    /*
    public void SendSms(String numTel, String username) {
        String ACCOUNT_SID = "AC2739851b59ae3ab61444061c8d074169";
        String AUTH_TOKEN = "047351f5d0f12cb786846e6ede5175d6";
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Response<Map> v = Rest.post("https://api.twilio.com/2010-04-01/Accounts/" + "AC2739851b59ae3ab61444061c8d074169" + "/Messages.json").
                queryParam("To", "+216"+numTel).
                queryParam("From", "+14153845759").
                queryParam("Body", "Bonjour " + username + " , Vous avez ajoutez une nouvelle categorie !").
                basicAuth("AC2739851b59ae3ab61444061c8d074169", "047351f5d0f12cb786846e6ede5175d6").//header("Authorization", "Basic " + Base64.encodeNoNewline((ACCOUNT_SID + ":" + AUTH_TOKEN).getBytes())).
                getAsJsonMap();
    }
//*/
    
     public void notification() {
       LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("It's time to take a break and look at me");
        n.setAlertTitle("Break Time!");
        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound


        Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
                );
    }

    
    //delete
     public boolean deleteCat(int idc){
         String url = Statics.BASE_URL + "/deleteCatJSON/"+idc;
         req.setUrl(url);
         
         req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
               req.removeResponseCodeListener(this);
             }
         });
         
         NetworkManager.getInstance().addToQueueAndWait(req);
         
         return resultOK;
     }
             
    //update
     public boolean modifierCat(Cat cat){
         String url = Statics.BASE_URL+"/updateCatJSON/"+cat.getIdc()+"?nomc="+cat.getNomc();
         req.setUrl(url);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 resultOK = req.getResponseCode() == 200; //code reponse http 200 ok
                 req.removeExceptionListener(this);        
             }
         });
         
         NetworkManager.getInstance().addToQueueAndWait(req);//execution de request sinn ca va pas apparetre 
         return resultOK;
     }
             
             
}
