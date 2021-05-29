/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author HP
 */
public class EmployeAffi {
   private int idemp;
    private String nomemp;
    private String prenomemp;
    private int numtelemp;
    private String adresseemp;

    public EmployeAffi(int idemp, String nomemp, String prenomemp, int numtelemp, String adresseemp) {
        this.idemp = idemp;
        this.nomemp = nomemp;
        this.prenomemp = prenomemp;
        this.numtelemp = numtelemp;
        this.adresseemp = adresseemp;
    }

    public int getIdemp() {
        return idemp;
    }

    public String getNomemp() {
        return nomemp;
    }

    public String getPrenomemp() {
        return prenomemp;
    }

    public int getNumtelemp() {
        return numtelemp;
    }

    public String getAdresseemp() {
        return adresseemp;
    }

    public void setIdemp(int idemp) {
        this.idemp = idemp;
    }

    public void setNomemp(String nomemp) {
        this.nomemp = nomemp;
    }

    public void setPrenomemp(String prenomemp) {
        this.prenomemp = prenomemp;
    }

    public void setNumtelemp(int numtelemp) {
        this.numtelemp = numtelemp;
    }

    public void setAdresseemp(String adresseemp) {
        this.adresseemp = adresseemp;
    }

 

}
