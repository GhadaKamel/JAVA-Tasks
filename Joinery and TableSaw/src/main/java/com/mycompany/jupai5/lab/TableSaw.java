/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jupai5.lab;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tech.tablesaw.api.Table;

/**
 *
 * @author GHADA KAMEL
 */
public class TableSaw {
    
    
   Table tablesaw_data;
    public TableSaw() {
    }
    public TableSaw(Table tablesaw_data) {
        this.tablesaw_data = tablesaw_data;
    }

    
    public Table loadDataFromCVS(String path) {
        Table titanic = Table.create();
        try {
            titanic = Table.read().csv(path);
        } catch (IOException ex) {
            Logger.getLogger(LoadData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return titanic;
    }
    
    public Table getDataInfoStructure(Table titanic){
            System.out.println(titanic.structure().toString());
            return titanic.structure();
        }
        
    public Table getDataSummary(Table titanic){
            System.out.println(titanic.summary().toString());
            return titanic.summary();
        }
        
          
    
}
