/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.firstassignment;

/**
 *
 * @author GHADA KAMEL
 */
public class Pyramid {
    private String pharaoh;
    private String modern_name;
    private String  site;
    private double height_m;
    
    public Pyramid(){};
    public Pyramid(String pharaoh, String modern_name, String site, double height_m){
        this.pharaoh = pharaoh;
        this.modern_name = modern_name;
        this.site = site;
        this.height_m = height_m;
    }

    public void setPharaoh(String pharaoh) {
        this.pharaoh = pharaoh;
    }

    public void setModern_name(String modern_name) {
        this.modern_name = modern_name;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setHeight_m(double height_m) {
        this.height_m = height_m;
    }

    public String getPharaoh() {
        return pharaoh;
    }

    public String getModern_name() {
        return modern_name;
    }

    public String getSite() {
        return site;
    }

    public double getHeight_m() {
        return height_m;
    }
   
   
    @Override    
    public String toString() {
        return "Pyramid{" + "pharaoh=" + pharaoh + " , modern_name=" + modern_name  + " , site=" + site + " , height_m=" + height_m + "}";
    }
        
}
