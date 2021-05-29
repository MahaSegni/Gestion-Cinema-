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
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Film;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



/**
 *
 * @author mahas
 */
public class ServiceFilm {
    public ArrayList<Film> tasks;
   
    public static ServiceFilm instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

  
       public ServiceFilm() {
         req = new ConnectionRequest();
    }

    public static ServiceFilm getInstance() {
        if (instance == null) {
            instance = new ServiceFilm();
        }
        return instance;
    }
     public boolean addTask(Film t) {
        String url = Statics.BASE_URL + "/addJSON/new?nomfilm=" + t.getNomfilm()+ "&descriptionf=" + t.getDescriptionf()+ "&filename=" + t.getFilename()+ "&datesortie=" + t.getDatesortie(); //création de l'URL
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

    public ArrayList<Film> parseTasks(String jsonText) throws ParseException{
        try {
             tasks=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
             Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
               
                Film f = new Film();
            
                f.setId_film((int)Float.parseFloat(obj.get("id_film").toString()));
                 f.setNomfilm(obj.get("nomFilm").toString());
                f.setDescriptionf(obj.get("descriptionf").toString());
                f.setFilename(obj.get("filename").toString());
                 f.setDatesortie((Date) obj.get("datesortie"));
              //  float categorie = Float.parseFloat(obj.get("categorie").toString());
//                f.setCategorie((obj.get("categorie").toString()));
                //Ajouter la tâche extraite de la réponse Json à la liste
                tasks.add(f);
            }
        } catch (IOException ex) {
            
        }
        return tasks;
    }
   
    public ArrayList<Film> getAllTasks(){
        String url = Statics.BASE_URL+"/afficherjson/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
             
                try {
                    tasks = parseTasks(new String(req.getResponseData()));
                } catch (ParseException ex) {
                }
                    req.removeResponseListener(this);
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
       public ArrayList<Film> SearchFilm(String search){
        String url = Statics.BASE_URL+"/searchFilmJSON/"+search;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
             
                try {
                    tasks = parseTasks(new String(req.getResponseData()));
                } catch (ParseException ex) {
                }
                    req.removeResponseListener(this);
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
    
     public boolean supprimerFilm(Film e) {
        
        String Url = Statics.BASE_URL+"/deleteFilmJSON/"+ e.getId_film();
       req.setUrl(Url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
     public boolean EditFilm(Film r) {
        String url = Statics.BASE_URL + "/updateFilmJSON/" + r.getId_film()
                +"?nomfilm="+r.getNomfilm()
                + "&descriptionf=" + r.getDescriptionf()
            ;
        
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     public boolean resFilm(Film r) {
        
                resultOK = req.getResponseCode() == 200;
               
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
   
}
