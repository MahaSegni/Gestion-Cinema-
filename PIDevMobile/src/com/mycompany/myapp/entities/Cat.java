/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import com.mycompany.myapp.services.ServiceCat;

/**
 *
 * @author ASUS
 */
public class Cat {
    private int idc;
    private String nomc;

    public Cat() {}
    
    public Cat(int idc, String nomc) {
        this.idc = idc;
        this.nomc = nomc;
    }

    public Cat(String nomc) {
        this.nomc = nomc;
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public String getNomc() {
        return nomc;
    }

    public void setNomc(String nomc) {
        this.nomc = nomc;
    }

    public void SendSms(ServiceCat instance) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
