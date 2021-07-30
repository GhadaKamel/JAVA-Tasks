/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab4;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GHADA KAMEL
 */
public class PyramidMain {
   public static void main(String[] args) throws FileNotFoundException {
       
        PyramidCSVDAO  DAO = new PyramidCSVDAO();
        List <Pyramid> pyramids = DAO.readCSV("target\\pyramids.csv");
        int i=0;
        for(Pyramid p:pyramids)
        {
            //System.out.println("#"+(i++)+p);
            System.out.println(p.toString());;
        }
        
        
        String sep = "-".repeat(100);
        System.out.println(
                String.format("%-20s | %-50s | %-4s | %-20s", "Pharaoh", "Modern Name", "Site", "Height (meters)"));
        System.out.println(sep);
        for (Pyramid p : pyramids) {

            System.out.println(String.format("%-20s | %-50s | %-4s | %-20s", p.getPharaoh(), p.getModern_name(),
                    p.getSite(), p.getHeight_m()));
        }

        ArrayList<Double> data = dataStats(pyramids);

        System.out.println("Average = " + data.get(0));
        System.out.println("median = " + data.get(1));
        System.out.println("lowerQuartile = " + data.get(2));
        System.out.println("upperQuartile = " + data.get(3));
        
        
        
   }
   
    public static ArrayList<Double> dataStats(List<Pyramid> pyramids) {

        ArrayList<Double> results = new ArrayList<>();
        ArrayList<Double> heights = new ArrayList<>();

        for (Pyramid p : pyramids) {

            heights.add((double) p.getHeight_m());

        }

        int num_Heights = heights.size();
        heights.sort((x, y) -> Double.compare(x, y));

        results.add(heights.stream().reduce(0.0, (x, y) -> x + y) / num_Heights);

        results.add(quartile(heights));

        int splitInd = num_Heights / 2;
        results.add(quartile(heights.subList(0, splitInd)));

        if (num_Heights % 2 != 0)
            splitInd++;
        results.add(quartile(heights.subList(splitInd, num_Heights)));

        return results;

    }

    private static double  quartile(List<Double> arr) {

        int numOfCities = arr.size();
        double median = numOfCities % 2 == 0
                ? (arr.get((int) (numOfCities / 2)) + arr.get((int) (numOfCities / 2) - 1)) / 2
                : arr.get((int) (numOfCities / 2));

        return median;

    }

}
