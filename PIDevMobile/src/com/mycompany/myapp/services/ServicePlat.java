/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;


import com.mycompany.myapp.entities.Plat;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ServicePlat {
    
    //singleton
    public static ServicePlat instance=null;
    
    //initialistation connction reqest
    public ConnectionRequest req;

    public static ServicePlat getInstance(){
        if(instance == null)
            instance=new ServicePlat();
        return instance;
    }
    
    public ServicePlat(){
        req=new ConnectionRequest();
    }
    
    //ajout
    public void ajouterPlat(Plat plat){
        String url=Statics.BASE_URL+"/addPlatJSON/new?nomc="+plat.getNomp()+"&prixp"+plat.getPrixp()+"&categorie_id"+plat.getCategorie_id()+"&imagep"+plat.getImagep()+"&description"+plat.getDescription();
        req.setUrl(url);
        req.addResponseListener((e)-> {
            String str = new String(req.getResponseData());//reponse json dans le navigateur
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution de request sinn ça va pas marcher   
    }
    
    //affichage
    public ArrayList<Plat>afficherPlat(){
        ArrayList<Plat> result = new ArrayList();
        
        String url = Statics.BASE_URL+"/afficherPlatjsonTwig";      
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try{
                    Map<String,Object>mapPlat = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> listOfMaps =(List<Map<String,Object>>) mapPlat.get("root");
                    
                    for(Map<String,Object> obj : listOfMaps){
                        Plat pa =new Plat();
                        //ps: id dima float ndakhloha fel codenameone
                        float idp = Float.parseFloat(obj.get("idp").toString());
                        String nomp = obj.get("nomp").toString();
                        float prixp = Float.parseFloat(obj.get("prixp").toString());
                        String categorie_id = obj.get("categorie_id").toString();
                        String imagep = obj.get("imagep").toString();
                        String description = obj.get("description").toString();
                        
                        pa.setIdp((int)idp);
                        pa.setNomp(nomp);
                        pa.setPrixp((int)prixp);
                        pa.setCategorie_id(categorie_id);
                        pa.setImagep(imagep);
                        pa.setDescription(description);
                        
                        //insert data into ArrayList result
                        result.add(pa);
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
}
