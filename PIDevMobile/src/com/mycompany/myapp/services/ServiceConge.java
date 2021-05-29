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
import com.mycompany.myapp.entities.Employe;
import com.mycompany.myapp.entities.Conge;
import com.mycompany.myapp.utils.CnxRequest;
//import com.mycompany.myapp.utils.CnxRequest;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class ServiceConge {

    public boolean resultOK;

    private ConnectionRequest req;
    private ArrayList<Conge> liste;
    public static ServiceConge instance = null;

    public ServiceConge() {
        req = CnxRequest.getInstance().getConnectionRequest();
    }

    public static ServiceConge getInstance() {
        if (instance == null) {
            instance = new ServiceConge();
        }
        return instance;
    }

    public boolean ajouter(Conge e) {
        String url = Statics.BASE_URL + "/addJSONConge/new?dateconge=" + e.getDateconge() + "&motifconge=" + e.getMotifconge() + "&nbjourconge=" + e.getNbjourconge() + "&employe_id" + e.getEmploye_id();
        req.setUrl(url);
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Conge> getConges() {

        String url = Statics.BASE_URL + "/afficherjsonTwigConge";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent a) {
                liste = parseConge(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        //c.setDetails(liste);
        return liste;
    }

    private ArrayList<Conge> parseConge(String json) {
        try {
            liste = new ArrayList<>();
            JSONParser parser = new JSONParser();
            Map<String, Object> produitsJSON = parser.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> conges = (List<Map<String, Object>>) produitsJSON.get("root");
            for (Map<String, Object> conge : conges) {
                Conge pc = new Conge();
                pc.setIdconge((int) Float.parseFloat(conge.get("idconge").toString()));
                pc.setDateconge( (String) conge.get("dateconege"));//(Date)

                pc.setMotifconge(conge.get("motifconge").toString());

                pc.setNbjourconge((int) Float.parseFloat(conge.get("nbjourconge").toString()));

                liste.add(pc);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return liste;
    }

    public void modifierEmploye(Employe pc) {
        String url = Statics.BASE_URL + "/updateEmployeJSON/" + pc.getIdemp() + "?nomemp=" + pc.getNomemp() + "&prenomemp=" + pc.getPrenomemp() + "&numtelemp=" + pc.getNumtelemp() + "&adresseemp=" + pc.getAdresseemp();
        req.setUrl(url);
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public Boolean supprimerConge(Conge pc) {
        String url = Statics.BASE_URL + "/deleteCongeJSON/" + pc.getIdconge();
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

    public void genererListec() {
        String url = Statics.BASE_URL + "/pdfActionc/";
        req.setUrl(url);
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

}
