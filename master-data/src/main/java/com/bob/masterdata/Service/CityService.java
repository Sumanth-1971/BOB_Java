package com.bob.masterdata.Service;

import com.bob.db.entity.CityEntity;
import com.bob.db.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public CityEntity createCity(CityEntity city) {
        try {
            city.setCreatedDate(LocalDateTime.now());
            cityRepository.save(city);
            return city;
        } catch (Exception e) {
            return null;
        }
    }

    public List<CityEntity> getAllCities() throws Exception {
        try {
            return cityRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Failed to fetch cities");
        }
    }

    public CityEntity updateCities(Long id, CityEntity city) {
        try {
            CityEntity replica=city;
            Optional<CityEntity> existingCity = cityRepository.findById(id);
            if (existingCity.isPresent()) {
                city.setCityId(id);
                cityRepository.save(city);
                return replica;
            } else {
                throw new Exception("ID DOESN'T EXIST!");
            }
        } catch (Exception e) {
            return null;
        }
    }

    public CityEntity deleteCities(Long id) {
        try {
            if (cityRepository.existsById(id)) {
                CityEntity city=cityRepository.findById(id).get();
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
