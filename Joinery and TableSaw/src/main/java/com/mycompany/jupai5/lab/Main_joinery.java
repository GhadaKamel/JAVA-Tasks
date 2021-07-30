/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jupai5.lab;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import joinery.DataFrame;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;

/**
 *
 * @author GHADA KAMEL
 */
public class Main_joinery {
    
    public static void main(String[] args) throws IOException {
        
        DataFrame<Object> joinery_data = DataFrame.readCsv("target\\titanic.csv");
        
        System.out.println(joinery_data.toString(10));
   
       
        System.out.println("** Showing data description **");
        System.out.println(joinery_data.describe());
        
        List<Object> tmpAges = joinery_data.col("age");
        
       
        List<Object> temp = new ArrayList<>();
        Random rand = new Random();
        tmpAges.forEach(_item -> {
            temp.add(Math.abs(rand.nextInt()) % 4);
        });
        
        joinery_data.add("Number Of Pets", temp);
        
        
        System.out.println(joinery_data.head(5));
        
        
        ArrayList<Double> ages = new ArrayList<>(tmpAges.stream()
                .filter(x -> x != null)
                .map(Object::toString)
                .map(Double::valueOf).collect(Collectors.toList()));
   
     
        double maxAge = ages.stream()
                        .max((x,y) -> Double.compare(x, y)).get();
        System.out.println("**Max Age**: " + maxAge);
        double minAge = ages.stream()
                        .min((x,y) -> Double.compare(x, y)).get();
        System.out.println("**Min Age**: " + minAge);
        
        
        System.out.println("**Average survivors per class**");
        DataFrame<Object> survByClass = joinery_data.retain("pclass", "survived").groupBy(row -> row.get(0)).mean();
        
        System.out.println(survByClass);
        
        System.out.println("**Average survivors per gender**");
        DataFrame<Object> survByGender = joinery_data.retain("sex", "survived").groupBy(row -> row.get(1)).mean();
        
        System.out.println(survByGender);
        
        
        System.out.println("**Joining two tables**");
        
        DataFrame<Object> df1 = joinery_data.retain("name", "survived").groupBy("survived").unique("name");
        DataFrame<Object> df2 = joinery_data.retain("name", "sex", "pclass").groupBy("sex", "pclass").unique("name");
            //join
        DataFrame<Object> joinedDf = df1.joinOn(df2, DataFrame.JoinType.INNER, "name");
        //System.out.println(joinedDf.describe());
        System.out.println(joinedDf.head(10));
        
        
        int [] result = new int[3];
           int index;
            List<Object> Pclass = joinery_data.col(0);
            for (Object c : Pclass){
                index = Integer.parseInt(c.toString());
                result[index-1]++;
            }
               
            System.out.println(result[0]);
        PieChart chart = new PieChartBuilder().width(800).height(600).title("Pclass values").build();
 
            // Customize Chart
            Color[] sliceColors = new Color[11];
        //use to generate list of  random color.
        for(int i=0;i<11;i++){
            sliceColors[i] = new Color((int) (Math.random( )*256), (int)(Math.random( )*256), (int)(Math.random( )*256));
        }
            chart.getStyler().setSeriesColors(sliceColors);
 
            // Series
           
            chart.addSeries("Class 1", result[1]);
            chart.addSeries("Class 2", result[2]);
           
           
 
            new SwingWrapper<>(chart).displayChart();
        
        

    }

}
