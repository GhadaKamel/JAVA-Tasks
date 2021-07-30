/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CitiesAndCountries;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ghada Kamel
 */
public class Country {
    String countryCode;
    String Name;
    String continent;
    List<City> cities;
    

    public Country() {
        cities = new ArrayList<City>();
    }
    

    public Country(String countryCode, String Name, String continent, List<City> cities) {
        this.countryCode = countryCode;
        this.Name = Name;
        this.continent = continent;
        this.cities  = cities;
    }
    

    public String getCountryCode() {
        return countryCode;
    }

    public String getName() {
        return Name;
    }

    public String getContinent() {
        return continent;
    }

    public List<City> getCities() {
        return cities;
    }

  
    
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setOfficialName(String officialName) {
        this.Name = officialName;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "Country{" + "countryCode=" + countryCode + ", officialName=" + Name + ", continent=" + continent + '}';
    }
    
    
    
    
}
