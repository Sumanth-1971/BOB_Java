package com.example.MasterData.Service;

import com.example.MasterData.Model.Country;
import com.example.MasterData.Model.Location;
import com.example.MasterData.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public Location createLocation(Location location) {
        try {

            location.setCreated_date(LocalDateTime.now());
            locationRepository.save(location);
            return location;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Location> getAllLocations() throws Exception {
        try {
            return locationRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Failed to fetch locations");
        }
    }

    public Location updateLocations(Long id,Location location) {
        try {
            Location location1=location;
            Optional<Location> existingLocation = locationRepository.findById(id);
            if (existingLocation.isPresent()) {
                location.setLocation_id(id);
                locationRepository.save(location);
                return location1;
            } else {
                throw new Exception("ID DOESN'T EXIST!");
            }
        } catch (Exception e) {
            return null;
        }
    }

    public Location deleteLocation(Long id) {
        try {
            if (locationRepository.existsById(id)) {
                Location location=locationRepository.findById(id).get();
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
