/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.api;

import com.codename1.messaging.Message;
import com.codename1.ui.Display;

/**
 *
 * @author HP
 */
public class Mail {
    public static void sendMail(){
        Message m = new Message("évenement ajouté");
    Display.getInstance().sendMessage(new String[] {"fares.aissa@esprit.tn"}, "Service Evenement", m);
    
    }
}
