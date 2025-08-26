package com.bob.masterdata.Service;

import com.bob.db.entity.LocationEntity;
import com.bob.db.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public LocationEntity createLocation(LocationEntity location) {
        try {

            location.setCreatedDate(LocalDateTime.now());
            locationRepository.save(location);
            return location;
        } catch (Exception e) {
            return null;
        }
    }

    public List<LocationEntity> getAllLocations() throws Exception {
        try {
            return locationRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Failed to fetch locations");
        }
    }

    public LocationEntity updateLocations(Long id, LocationEntity location) {
        try {
            LocationEntity location1=location;
            Optional<LocationEntity> existingLocation = locationRepository.findById(id);
            if (existingLocation.isPresent()) {
                location.setLocationId(id);
                locationRepository.save(location);
                return location1;
            } else {
                throw new Exception("ID DOESN'T EXIST!");
            }
        } catch (Exception e) {
            return null;
        }
    }

    public LocationEntity deleteLocation(Long id) {
        try {
            if (locationRepository.existsById(id)) {
                LocationEntity location=locationRepository.findById(id).get();
                locationRepository.deleteById(id);
                return location;
            } else {
                throw new Exception("ID DOESN'T EXIST");
            }
        } catch (Exception e) {
            return null;
        }
    }
}
