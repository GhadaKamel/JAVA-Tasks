/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab4;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author GHADA KAMEL
 */
public class PyramidCSVDAO {
    
   public PyramidCSVDAO() {
    }
    
    
    public List<Pyramid> readCSV(String filename)throws FileNotFoundException{
        
        List<Pyramid> pyramids = new ArrayList<>();
         
        Scanner scan  = new Scanner(new File(filename));
         //Scanner scan  = new Scanner(new File(fileName));
        scan.useDelimiter("\n");
        scan.next();
        while (scan.hasNext()) {
             
            String data = scan.next();
            String[] data1 = data.split(",");
            Pyramid pyramid = getPyramidsData(data1);
            if(pyramid!=null)
                 pyramids.add(pyramid);
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
