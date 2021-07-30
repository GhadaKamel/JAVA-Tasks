/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.firstassignment;

import java.util.List;

/**
 *
 * @author GHADA KAMEL
 */
public class PyramidMain {
    public static void main(String[] args) {
        
        PyramidCSVDAO  DAO = new PyramidCSVDAO();
        List <Pyramid> pyramids = DAO.readCSV("target\\pyramids.csv");
        int i=0;
        for(Pyramid p:pyramids)
        {
            //System.out.println("#"+(i++)+p);
            System.out.println(p.toString());;
        }
        
    }
}
