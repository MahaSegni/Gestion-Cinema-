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
import com.mycompany.myapp.utils.CnxRequest;
//import com.mycompany.myapp.utils.CnxRequest;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author user
 */
public class ServiceEmploye {

    private ConnectionRequest req;
    private ArrayList<Employe> liste;

    public ServiceEmploye() {
        req = CnxRequest.getInstance().getConnectionRequest();
    }

    public void ajouter(Employe e) {
        String url = Statics.BASE_URL + "/addJSONEmploye/new?nomemp=" + e.getNomemp() + "&prenomemp=" + e.getPrenomemp() + "&numtelemp=" + e.getNumtelemp() + "&adresseemp=" + e.getAdresseemp();
        req.setUrl(url);
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public ArrayList<Employe> getEmployes() {

        String url = Statics.BASE_URL + "/afficherjsonTwigEmploye";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent a) {
                liste = parseEmploye(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        //c.setDetails(liste);
        return liste;
    }

    private ArrayList<Employe> parseEmploye(String json) {
        try {
            liste = new ArrayList<>();
            JSONParser parser = new JSONParser();
            Map<String, Object> produitsJSON = parser.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> employes = (List<Map<String, Object>>) produitsJSON.get("root");
            for (Map<String, Object> employe : employes) {
                Employe pc = new Employe();
                pc.setIdemp((int) Float.parseFloat(employe.get("idemp").toString()));
                pc.setPrenomemp(employe.get("prenomemp").toString());
                pc.setNomemp(employe.get("nomemp").toString());
                pc.setNumtelemp((int) Float.parseFloat(employe.get("numtelemp").toString()));
                pc.setAdresseemp(employe.get("adresseemp").toString());

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

    public void supprimerEmploye(Employe pc) {
        String url = Statics.BASE_URL + "/deleteEmployeJSON/" + pc.getIdemp();
        req.setUrl(url);
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public void genererListe() {
        String url = Statics.BASE_URL + "/pdfAction/";
        req.setUrl(url);
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

}
