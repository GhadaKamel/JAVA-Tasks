/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jupai5.lab;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler;
import static tech.tablesaw.aggregate.AggregateFunctions.mean;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.IntColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.selection.Selection;

/**
 *
 * @author GHADA KAMEL
 */
public class Main_tableSaw {
    public static void main(String[] args) throws IOException {

       
        Table tablesaw_data  = new TableSaw().loadDataFromCVS("target\\titanic.csv");
        TableSaw data_saw = new TableSaw(tablesaw_data);
        
        //all data 
        System.out.println(tablesaw_data.toString());
        
        System.out.println("** Data Structure **");
        data_saw.getDataInfoStructure(tablesaw_data);
        System.out.println("** Data Summary **");
        data_saw.getDataSummary(tablesaw_data);
        
       
        DoubleColumn tmpAges = tablesaw_data.doubleColumn("age");

        // add column
        IntColumn numOfPets  = IntColumn.create("Number of Pets");
        
        Random rand = new Random();
        for (int i = 0; i < tmpAges.size(); i++) {
            
            numOfPets.append(Math.abs(rand.nextInt()) % 4);

        }
        
        tablesaw_data.addColumns(numOfPets);
        
        System.out.println(tablesaw_data.sampleN(7));
        
        ArrayList<Double> ages = new ArrayList<>(tmpAges.removeMissing().asList());

        
        double maxAge = ages.stream()
                            .max((x, y) -> Double
                            .compare(x, y)).get();
        double minAge = ages.stream()
                            .min((x, y) -> Double
                            .compare(x, y)).get();

       
        System.out.println("**Max Age**: " + maxAge);
        
       
        System.out.println( "**Min Age**: " + minAge);
        
        System.out.println("** Average survivors per class **");
        Table survByClass = tablesaw_data.copy().retainColumns("pclass", "survived")
                              .summarize("survived", mean).by("pclass");

        System.out.println(survByClass);

        System.out.println("** Average survivors per gender**");
        Table survByGender = tablesaw_data.copy().retainColumns("sex", "survived")
                                .summarize("survived",mean).by("sex");

        System.out.println(survByGender);
        

        
        System.out.println("** Join two tables **");

        StringColumn  col = tablesaw_data.column("name").unique().asStringColumn();
        Selection names = col.isIn(col.asList());

        Table table1 = tablesaw_data.copy().retainColumns("name", "survived")
                      .dropRowsWithMissingValues().where(names);
        Table table2 = tablesaw_data.copy().retainColumns("name", "sex", "pclass")
                     .dropRowsWithMissingValues().where(names);
        
        System.out.println("** join by name **");
        
        System.out.println(table1.first(7));
        System.out.println(table2.first(7));
        
        System.out.println("** Joint two Table **");
        
        Table table3 = table1.joinOn("name").inner(table2);
        
        System.out.println(table3.first(7));
        
        
        
     }
  
 
}
