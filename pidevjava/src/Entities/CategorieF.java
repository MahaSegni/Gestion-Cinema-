/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author mahas
 */
public class CategorieF {

    private int id;
    private String desc_c;

    public CategorieF(int id, String desc_c) {
        this.id = id;
        this.desc_c = desc_c;
    }

    public CategorieF() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc_c() {
        return desc_c;
    }

    public void setDesc_c(String desc_c) {
        this.desc_c = desc_c;
    }

}
