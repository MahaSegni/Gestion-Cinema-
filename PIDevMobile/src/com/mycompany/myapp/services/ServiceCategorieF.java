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
import com.mycompany.myapp.entities.CategorieF;

import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mahas
 */
public class ServiceCategorieF {
    public ArrayList<CategorieF> tasks;
    
    public static ServiceCategorieF instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
 public ServiceCategorieF() {
         req = new ConnectionRequest();
    }

    public static ServiceCategorieF getInstance() {
        if (instance == null) {
            instance = new ServiceCategorieF();
        }
        return instance;
    }
    public ArrayList<CategorieF> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                CategorieF f = new CategorieF();
//                Date date = new Date();
//               
//              f.setDate((java.sql.Date) date);
                float num = Float.parseFloat(obj.get("id").toString());
                f.setId((int)num);

                f.setDesc_c(obj.get("descC").toString());
                //Ajouter la tâche extraite de la réponse Json à la liste
                tasks.add(f);
            } 
        } catch (IOException ex) {
        }
        return tasks;
    }
   //  ArrayList<Film> listReclamations= new ArrayList<>();
    public ArrayList<CategorieF> getAllTasks(){
        String url = Statics.BASE_URL +"/afficherCatejsonTwig";
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
      public ArrayList<CategorieF> parseTasks1(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                CategorieF f = new CategorieF();
//                Date date = new Date();
//               
//              f.setDate((java.sql.Date) date);
                float num = Float.parseFloat(obj.get("id").toString());
                f.setId((int)num);

                //Ajouter la tâche extraite de la réponse Json à la liste
                tasks.add(f);
            } 
        } catch (IOException ex) {
        }
        return tasks;
    }
   //  ArrayList<Film> listReclamations= new ArrayList<>();
    public ArrayList<CategorieF> getAllTasks1(String val){
        String url = Statics.BASE_URL +"/searchCateJSON/"+val;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks1(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;

    }
}
