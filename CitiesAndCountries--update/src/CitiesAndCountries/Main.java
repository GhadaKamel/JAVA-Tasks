/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CitiesAndCountries;

import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.max;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Ghada Kamel
 */
public class Main {
 
    public static void main(String[] args) {
        // TODO code application logic here
        
        DAO dao = new DAO();
        List<String> lines = new ArrayList<>();
        
                                  //Read pop file
        String filePath = ("src\\Data\\cities.csv");
        lines = DAO.readFile(filePath);
//        lines.forEach(l -> System.out.println(l));
        List<City> cityData =  DAO.readCities(lines);
        //cityData.forEach(pop -> System.out.println(pop));
        //System.out.println(pop);
//======================================================================================================================
                            //Read country file
        String countryFilePath = ("src\\Data\\countries.csv");
        List<Country> countryData = DAO.readCountry(DAO.readFile(countryFilePath));
       // countryData.forEach(country -> System.out.println(country));
        //list of cities name
       
//  =====================================================================================================================  

       Map<String, List<City>> ccode = dao.Mapped(cityData,countryData);  
        // System.out.println(ccode);
        
        
        
        
//================================================================        
        for(Country country:countryData) { //hold country and country (loops)
            List<City> citiesvalues = new LinkedList<City>();//create list of cities
		for(City city:cityData) { 
                    if(city.getCountryCode().equals(country.countryCode)) { 
			citiesvalues.add(city); //add the cities here for one country in the loop
                    } 					
                }
                
           ccode.put(country.countryCode, citiesvalues); //finally adda cuntry code as key and list of cities as value
//            ccode.forEach((i, c) -> {
//            System.out.println("key = " + i + " cities= " + c);
//        });
                
 //========================================================================================================================
       //sort cities
//	  Iterator<String> itr = ccode.keySet().iterator();
//        itr.forEachRemaining(key -> {
//            ccode.get(key).sort((o1, o2) -> {
//                if (o1.population < o2.population)
//                    return 1;
//                else if (o1.population == o2.population)
//                    return 0;
//                return -1;
//            });
//        });
//        System.out.println("=== sorted Map ===");
//        ccode.forEach((i, c) -> {
//            System.out.println("key = " + i + " cities= " + c);
//        });




 // i multiplyed by -1 to sort cities in ascending order
            String cc = country.getCountryCode();
            cityData.sort((o1, o2) -> {return (o2.population - o1.population) * (-1); });
            ccode.put(cc, cityData);
            
        
        Iterator<City> x = ccode.get("AFG").iterator();
        while(x.hasNext()){
            City temp = x.next();
            //System.out.println(temp.population + "\t\t" + temp.getCityName());
        }
       }

//=============================================================================================================================
        //Highest population pop of each country
        //"built in main"
     List<City> HPopulation = new ArrayList<>();       
        Map<String , List<City>> k;
        k = cityData.stream().collect(Collectors.groupingBy((a) -> a.getCountryCode()));
           // System.out.println(k.keySet());
        k.keySet()
                .forEach(t -> HPopulation.add(k.get(t)
                        .stream().sorted()
                        .max(Comparator.comparing(City::getPopulation)).get()));
          //HPopulation.forEach(t -> System.out.println(t.getCountryCode()  +"\t\t" + t.getPopulation() + "\t\t" + t.getCityName()));
        
                                   //=======================================
        // Method from DAO
        Map<String, City> HPC= dao.HighestPopulationCityPerCountry(cityData);
      
       // System.out.println("Highest Population City OF each Country: "+HPC);  
        
      
//============================================================================================================================
     //Highest population city by continent

      //dao.HighestPopulationCityPerContient(cityData,countryData);
     
     
     
       
 //===========================================================================================================================
    //Highest population capital      
       
         //dao.HPCC(cityData);
//    OR      
                               //=========================================
//        System.out.println("Highest Population Capital: "+dao.HighestPopulationCapital(cityData));
 
 //   OR         
                             //===========================================
//    cityData.stream().filter(t -> t.isIsCapital()).forEach((t) -> System.out.println(t.isIsCapital() + "\t\t"+t.getCityName()));
//        City maxCapital1 = cityData.stream().filter(t -> t.isIsCapital()).max((o1, o2) -> o1.population - o2.population).get();
//        System.out.println("max capital pop in population is " + maxCapital1.getCityName() + " with population = " + maxCapital1.population);

//======================================================================================================================
    
        
   }
    
}
   
     
    

