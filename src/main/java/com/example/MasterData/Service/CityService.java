package com.example.MasterData.Service;

import com.example.MasterData.Model.City;
import com.example.MasterData.Repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public City createCity(City city) {
        try {
            city.setCreated_date(LocalDateTime.now());
            cityRepository.save(city);
            return city;
        } catch (Exception e) {
            return null;
        }
    }

    public List<City> getAllCities() throws Exception {
        try {
            return cityRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Failed to fetch cities");
        }
    }

    public City updateCities(Long id, City city) {
        try {
            City replica=city;
            Optional<City> existingCity = cityRepository.findById(id);
            if (existingCity.isPresent()) {
                city.setCity_id(id);
                cityRepository.save(city);
                return replica;
            } else {
                throw new Exception("ID DOESN'T EXIST!");
            }
        } catch (Exception e) {
            return null;
        }
    }

    public City deleteCities(Long id) {
        try {
            if (cityRepository.existsById(id)) {
                City city=cityRepository.findById(id).get();
                cityRepository.deleteById(id);
                return city;
            } else {
                throw new Exception("ID DOESN'T EXIST");
            }
        } catch (Exception e) {
            return null;
        }
    }
}
