/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CitiesAndCountries;

import java.util.List;

/**
 *
 * @author Ghada Kamel
 */
public class City implements Comparable<City>{

     String cityName;
     String countryName;
     String countryCode;
     String capital;
     int population;
     boolean isCapital;
     String continent;
     List<Country> countries;

    public City() {}

    public City(String cityName, String country, String countryCode, String capital, int population, boolean isCaptial) {
        this.cityName = cityName;
        this.countryName = country;
        this.countryCode = countryCode;
        this.capital = capital;
        this.population = population;
        this.isCapital = isCaptial;
        
       
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setIsCapital(boolean isCapital) {
        this.isCapital = isCapital;
    }
    

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCapital() {
        return capital;
    }

    public int getPopulation() {
        return population;
        
    }

    public boolean isIsCapital() {
        return isCapital;
    }

    public List<Country> getCountries() {
        return countries;
    }
    
    
    @Override
    public String toString() {
        return "City{" + "cityName=" + cityName + ", country=" + countryName + ", countryCode=" + countryCode + ", capital=" + capital+ ", population=" + population + '}';
    }

    @Override
    public int compareTo(City o) {
      return Integer.valueOf(this.getPopulation()).compareTo(o.getPopulation()) ;
    }
    
    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }
    
}