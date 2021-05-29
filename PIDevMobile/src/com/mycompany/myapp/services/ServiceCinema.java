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
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Cinema;
import com.mycompany.myapp.entities.Film;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mahas
 */
public class ServiceCinema {
    public ArrayList<Cinema> tasks;
    
    public static ServiceCinema instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

  
       private ServiceCinema() {
         req = new ConnectionRequest();
    }

    public static ServiceCinema getInstance() {
        if (instance == null) {
            instance = new ServiceCinema();
        }
        return instance;
    }
     public boolean addTask(Cinema t) {
        String url = Statics.BASE_URL + "/addCinemaJSON/new?nbr=" +t.getNbr()+"&date="+t.getDate(); //création de l'URL
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
     public ArrayList<Cinema> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Cinema f = new Cinema();
//                Date date = new Date();
//               
//              f.setDate((java.sql.Date) date);
                float num = Float.parseFloat(obj.get("num").toString());
                f.setNum((int)num);
float id = Float.parseFloat(obj.get("nbr").toString());
                f.setNbr((int)id);
                //Ajouter la tâche extraite de la réponse Json à la liste
                tasks.add(f);
            } 
        } catch (IOException ex) {
        }
        return tasks;
    }
   //  ArrayList<Film> listReclamations= new ArrayList<>();
    public ArrayList<Cinema> getAllTasks(){
        String url = Statics.BASE_URL +"/afficherCinemajsonTwig";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;

    }
}
