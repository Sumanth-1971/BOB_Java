package com.example.MasterData.Service;


import com.example.MasterData.Model.Country;

import com.example.MasterData.Repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public Country createCountry(Country country) {
        try {
            country.setCreated_date(LocalDateTime.now());
            countryRepository.save(country);
            return country;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Country> getAllCountries() throws Exception {
        try {
            return countryRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Failed to fetch countries");
        }
    }

    public Country updateCountry(Long id, Country country) {
        try {
            Country country1=country;
            Optional<Country> existingCountry = countryRepository.findById(id);
            if (existingCountry.isPresent()) {
                country.setCountry_id(id);
                countryRepository.save(country);
                return country1;
            } else {
                throw new Exception("ID DOESN'T EXIST!");
            }
        } catch (Exception e) {
            return null;
        }
    }

    public Country deleteCountry(Long id) {
        try {
            if (countryRepository.existsById(id)) {
                Country country=countryRepository.findById(id).get();
                countryRepository.deleteById(id);
                return country;
            } else {
                throw new Exception("ID DOESN'T EXIST");
            }
        } catch (Exception e) {
            return null;
        }
    }
}
