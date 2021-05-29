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
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Cellule;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.mycompany.myapp.utils.Statics;

/**
 *
 * @author Administrateur
 */
public class ServiceCellule {
    
     public ArrayList<Cellule> tasks;
    
    public static ServiceCellule instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceCellule() {
         req = new ConnectionRequest();
    }

    public static ServiceCellule getInstance() {
        if (instance == null) {
            instance = new ServiceCellule();
        }
        return instance;
    }

    public boolean addCellule(Cellule t) {
        String url = "http://127.0.0.1:8000/addCelluleJson/new?idCellule=" + t.getIdCellule() + "&dispo=" + t.getDispo(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
             
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
/*
    public ArrayList<Cellule> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Cellule t = new Cellule();
               // float id = Float.parseFloat(obj.get("id").toString());
               // t.setIdCellule((String)id);
                t.setDispo(true);
                t.setIdCellule(obj.get("name").toString());
                //Ajouter la tâche extraite de la réponse Json à la liste
                tasks.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        
        return tasks;
    }
    
    public ArrayList<Cellule> getAllCellule(){
        String url = "http://127.0.0.1:8000/ListeCellule";
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

 */   
    
    
    public ArrayList<Cellule> parseTasks(String jsonText) throws ParseException{
        try {
             tasks =new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
             Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
           
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
               
                Cellule f = new Cellule();
           
               f.setIdCellule(obj.get("idcellule").toString());
                 f.setDispo(Boolean.valueOf(obj.get("dispo").toString()));
               
              //  float categorie = Float.parseFloat(obj.get("categorie").toString());
//                f.setCategorie((obj.get("categorie").toString()));
                //Ajouter la tâche extraite de la réponse Json à la liste
                tasks.add(f);
            }
        } catch (IOException ex) {
           
        }
        return tasks;
    }
   
    public ArrayList<Cellule> getAllCellule(){
        String url = "http://127.0.0.1:8000/afficherjsonCellule/";
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

 /*   public void addCellule(Cellule c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } */
    
}
