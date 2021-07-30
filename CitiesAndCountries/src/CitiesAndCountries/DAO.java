/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CitiesAndCountries;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author Ghada Kamel
 */
public class DAO 
{
    List<City> cities = new ArrayList<>();
    List<Country> countries = new ArrayList<>();
    
    public DAO(){};
    
    public static List<String> readFile(String filePath) {
        
        File file = new File(filePath);
        String line = null;
        List<String> fileLines = new ArrayList<>();   

        try {
            Scanner scan = new Scanner(file);
            if(scan.hasNext())  
                scan.nextLine();
            
            while (scan.hasNextLine()) {
                line = scan.nextLine();
                //System.out.println(line);     
                fileLines.add(line);     
            }scan.close();
            
        } catch (IOException e) {
            System.out.println("Exception error happen" + e);
        }
        

        return fileLines;
    }
    

    public static List<City> readCities(List<String> lines) {

        List<City> cities = new ArrayList<>();
        for (String line : lines) {

            String[] fields = line.split("\",\"");
            City city = new City();

            city.setCityName(fields[0].trim());
            city.setCountryName(fields[4].trim());
            city.setCountryCode(fields[6].trim());
            if (fields[8].equalsIgnoreCase("primary")){
                city.setIsCapital(true);      }
            else
                city.setIsCapital(false);
            if (fields[9].length() != 0) {
                city.setPopulation(Integer.valueOf(fields[9].trim().replace(".","")));
            }

            cities.add(city);
        }
        return cities;
    }

    
    
    
    public static List<Country> readCountry(List<String> lines) {

        List<Country> countries = new ArrayList<>();
        for (String line : lines) {

            String[] fields = line.split(",");
            Country country = new Country();
            
            country.setCountryCode(fields[0].trim());
            country.setOfficialName(fields[1].trim());
            country.setContinent(fields[2].trim());
            
            countries.add(country);
        }

        return countries;
    }
    
    // map country with cities
    
    public void getCities(List<City> cities) {
        this.cities = cities; }
    //
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//    public HashMap<String, List<String>> getcountryCities(){
//        HashMap<String,List<String>> countryCities=new HashMap<>();
//        for(Country country: countries){
//            countryCities.put(country.getName(),cities.stream()
//                    .filter(city -> city.getCountryCode().equals(" "+country.getCountryCode()))
//                    .map(City::getCityName)
//                    .collect(Collectors.toList()));
//                    
//        	
//         countryCities.forEach((i, c) -> {
//             System.out.println("key = " + i + " cities= " + c);});
//    }
//        return countryCities; 
//    }
    
//    public HashMap<String, Integer> highestPopulationPerCountry() {
//        
//        HashMap<String,List<City>> citiesPerCountry = (HashMap<String, List<City>>) cities
//                .stream().collect(Collectors.groupingBy(City::getCountryCode));
//        HashMap<String, Integer> finalResult = new HashMap<>();
//        
//        citiesPerCountry.forEach((s, cities) -> {
//            City max = cities.stream()
//                    .max(Comparator.comparingDouble(City::getPopulation)).get();
//            finalResult.put(s,max.getPopulation());
//        });
//        return finalResult;
//    }
//    
    
//    public List<String> HighestCapitalPop() {
//    	List<City> capitals = new ArrayList<>(); 
//    	
//    	List<String> capitalCodes = cities.stream()
//        			.map(City::getCapital)
//        			.distinct().collect(Collectors.toList());
//    	//List<City> capitals = this.cities.contains(capitalCodes); 
////    	for(String code:capitalCodes) {
////    		cities.stream()
////    				.filter(city -> city.getId().equals(code))
////    				.forEach(city -> capitals.add(city));
////    	}
//        return capitals.stream()
//        		.sorted(Comparator.comparing(City::getPopulation).reversed())
//        		.limit(1)
//        		.map(City::getCityName)
//        		.collect(Collectors.toList());
//    }
    
   
    
}