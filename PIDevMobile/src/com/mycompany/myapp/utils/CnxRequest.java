/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.utils;

import com.codename1.io.ConnectionRequest;

/**
 *
 * @author khalil
 */
public class CnxRequest {
    private static CnxRequest instance=null;
    private ConnectionRequest cr;
    
    private CnxRequest() {
        cr=new ConnectionRequest();
    }
    
    public static CnxRequest getInstance() {
        if (instance == null)
            instance=new CnxRequest();
        return instance;
    }
    
    public ConnectionRequest getConnectionRequest() {
        return cr;
    }
}
