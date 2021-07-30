/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.firstassignment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GHADA KAMEL
 */
public class PyramidCSVDAO {

    public PyramidCSVDAO() {
    }
    
    
    public List<Pyramid> readCSV(String filename){
         List<Pyramid> pyramids = new ArrayList<>();
    
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            
            String line = br.readLine();
            if (line != null){
                line = br.readLine();
                
            }
            
            while(line != null ){
                System.out.println("line " + line);
                String [] data = line.split(",");
                Pyramid pyramid = getPyramidsData(data);
                pyramids.add(pyramid);
                line  = br.readLine();
            }
        
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return pyramids;
    }
        public Pyramid getPyramidsData(String[] fields){
        String pharaoh = fields[0];
        String modern_name = fields[2];
        String site = fields[4];
        double height=0;
        if(fields[7]!=null && fields[7].length()>0)
        height= Double.parseDouble(fields[7]);

        // create and return Pyramid of this metadata
        return new Pyramid(pharaoh, modern_name, site, height); 
        
        }
    
    
        
    
    
}
