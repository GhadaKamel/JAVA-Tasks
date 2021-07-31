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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.groupingBy;

/**
 *
 * @author Ghada Kamel
 */
public class DAO 
{
    List<City> cities = new ArrayList<>();
    List<Country> countries = new ArrayList<>();
    
    public DAO(){};
    
    public static List<String> readFile(String filePath) 
    {
        File file = new File(filePath);
        String line = null;
        List<String> fileLines = new ArrayList<>();   

        try {
            Scanner scan = new Scanner(file);
            if(scan.hasNext())  
                scan.nextLine();
            
            while (scan.hasNextLine()) 
            {
                line = scan.nextLine();
                //System.out.println(line);     
                fileLines.add(line);     
            }scan.close();
            
            } catch (IOException e) 
            {
            System.out.println("Exception error happen" + e);
            } 
        return fileLines;
    }
    

    public static List<City> readCities(List<String> lines) 
    {

        List<City> cities = new ArrayList<>();
        for (String line : lines) 
        {
            String[] fields = line.split("\",\"");
            City city = new City();

            city.setCityName(fields[0].trim());
            city.setCountryName(fields[4].trim());
            city.setCountryCode(fields[6].trim());
            if (fields[8].equalsIgnoreCase("primary"))
            {
                city.setIsCapital(true);     
            }
            else
                city.setIsCapital(false);
            if (fields[9].length() != 0)
            {
                city.setPopulation(Integer.valueOf(fields[9].trim().replace(".","")));
            }
            cities.add(city);
        }
        return cities;
    }
    
    public static List<Country> readCountry(List<String> lines)
    {

        List<Country> countries = new ArrayList<>();
        for (String line : lines)
        {
            String[] fields = line.split(",");
            Country country = new Country();
            country.setCountryCode(fields[0].trim());
            country.setOfficialName(fields[1].trim());
            country.setContinent(fields[2].trim());
            countries.add(country);
        }

        return countries;
    }
    
    
    public void getCities(List<City> cities) 
    {
        this.cities = cities; 
    }
 
    public void getCounries(List<Country> countries) 
    {
        this.countries = countries; 
    }
    
//========================================================================================================================
   //Map
    
    public Map<String, List<City>> Mapped (List<City> cities,List<Country>countries)
    {
        Map<String, List<City>> ccode = new HashMap<String,List<City>>();

	for(Country country:countries) { //hold country and country (loops)
            List<City> citiesvalues = new LinkedList<>();//create list of cities
		for(City city:cities) { 
                    if(city.getCountryCode().equals(country.countryCode)) { 
			citiesvalues.add(city); //add the cities here for one country in the loop
                    } 					
		}
            ccode.put(country.countryCode, citiesvalues); //finally adda cuntry code as key and list of cities as value
            ccode.forEach((i, c) -> {
            System.out.println("key = " + i + " cities= " + c);
            });
         
        }     
        return ccode;
        
    }
    
    
    
  
    
    
    
//======================================================================================================================
    public Map<String , City> HighestPopulationCityPerCountry (List<City> cities)
    {
        Map<String, City> HPC = new HashMap<>();
        cities.stream()
                .collect(groupingBy(City::getCountryName, Collectors.maxBy(Comparator.comparing(City::getPopulation))))
                .forEach((k, v) -> HPC.put(k, v.orElse(null)));

        return HPC;
    }
//=======================================================================================================================
    public void HighestPopulationCityPerContient(List<City> cities, List<Country> countries)
    {
        Map<String , Country> countryCode = new HashMap<>();
        List<String> lst = new ArrayList<>();
       
        countries.forEach(count -> {
           countryCode.put(count.getCountryCode(), count);
        });
        
        for(City city : cities)
        {
            try
            {
                String t = countryCode.get(city.getCountryCode()).getContinent();
            }
            catch(Exception e){
               lst.add(city.getCountryCode());
            }
           
          
        }
        System.out.println(lst.stream().distinct().count());
    
        System.out.println(cities.stream()
                                    .map(City::getCountryCode)
                                    .distinct().count());
        System.out.println(countries.stream()
                                    .map(Country::getCountryCode)
                                    .distinct().count());
    
    }
    

//=========================================================================================================================
    public Optional <City> HighestPopulationCapital (List<City> cities) 
    {
       Optional<City> HPopCapital = cities.stream()
               .filter(h -> h.getCapital() == "primary")
               .max(Comparator.comparing(City::getPopulation));
        return HPopCapital;
    }
    
    
                    //======================================
    public void HPCC(List<City> cities){
    cities.stream()
                .filter(tt -> tt.isIsCapital())
                .forEach((tt) -> System.out.println(tt.isIsCapital() + "\t\t"+tt.getCityName()));
        City maxCapital = cities.stream()
                .filter(tt -> tt.isIsCapital())
                .max(Comparator.comparing(City::getPopulation))
                .get();
            System.out.println("max capital pop in population is " + maxCapital.getCityName()+" with population = " + maxCapital.getPopulation());
            
    }
  
 
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
    
//    public Map<String, Integer> highestPopulationPerCountry() {
//        
//        Map<String,List<City>> citiesPerCountry = (HashMap<String, List<City>>) cities
//                .stream().collect(Collectors.groupingBy(City::getCountryCode));
//        Map<String, Integer> finalResult = new HashMap<>();
//        
//        citiesPerCountry.forEach((s, cities) -> {
//            City max = cities.stream()
//                    .max(Comparator.comparingDouble(City::getPopulation)).get();
//            finalResult.put(s,max.getPopulation());
//        });
//        return finalResult;
//    }
    
    
  
   
    
}