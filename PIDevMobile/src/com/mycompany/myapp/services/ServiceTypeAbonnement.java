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
import com.mycompany.myapp.entities.TypeAbonnement;
import static com.mycompany.myapp.services.ServiceAbonne.instance;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zeineb
 */
public class ServiceTypeAbonnement {

    public static ServiceTypeAbonnement instance = null;

    private ConnectionRequest req;

    public ServiceTypeAbonnement(ConnectionRequest req) {
        this.req = req;
    }

    private ServiceTypeAbonnement() {
        req = new ConnectionRequest();
    }

    public static ServiceTypeAbonnement getInstance() {
        if (instance == null) {
            instance = new ServiceTypeAbonnement();
        }
        return instance;
    }

    public ArrayList<TypeAbonnement> getListTypeAbonnement() {
        ArrayList<TypeAbonnement> listTypeAbo = new ArrayList<>();

        req.setUrl(Statics.BASE_URL + "/TypeAboJSON");
        req.addResponseListener((NetworkEvent evt) -> {
            //listTasks = getListTask(new String(con.getResponseData()));
            JSONParser jsonp = new JSONParser();

            try {
                Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

//System.out.println(tasks);
                List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                for (Map<String, Object> obj : list) {
                 
                    TypeAbonnement ta = new TypeAbonnement();
                    ta.setId((int) Float.parseFloat(obj.get("id").toString()));
                    ta.setType(obj.get("type").toString());
                    ta.setPrix(Float.parseFloat(obj.get("prix").toString()));
                    ta.setDescription(obj.get("description").toString());
                    ta.setImage(obj.get("image").toString());
                   ta.setPlacesdispo((int) Float.parseFloat(obj.get("placesdispo").toString()));


                    System.out.println("id  : " + ta.toString());
                    listTypeAbo.add(ta);

                }

            } catch (Exception ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listTypeAbo;
    }

}
