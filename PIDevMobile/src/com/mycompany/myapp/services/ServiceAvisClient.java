/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.AvisClient;

import com.mycompany.myapp.utils.Statics;

import java.util.ArrayList;

/**
 *
 * @author mahas
 */
public class ServiceAvisClient {
     public ArrayList<AvisClient> tasks;
    
    public static ServiceAvisClient instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
 public ServiceAvisClient() {
         req = new ConnectionRequest();
    }

    public static ServiceAvisClient getInstance() {
        if (instance == null) {
            instance = new ServiceAvisClient();
        }
        return instance;
    }
    public boolean addTask(AvisClient t) {
        String url = Statics.BASE_URL + "/addRJSON/new?descR=" + t.getDescR()+ "&rating=" + t.getRating(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
             
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
}
