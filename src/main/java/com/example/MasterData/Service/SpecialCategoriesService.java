package com.example.MasterData.Service;

import com.example.MasterData.Model.Location;
import com.example.MasterData.Model.SpecialCategories;
import com.example.MasterData.Repository.SpecialCategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SpecialCategoriesService {
    @Autowired
    private SpecialCategoriesRepository specialCategoriesRepository;

    public SpecialCategories createSpecialCategory(SpecialCategories specialCategories) {
        try {
            specialCategories.setCreated_date(LocalDateTime.now());
            specialCategories.setUpdated_date(LocalDateTime.now());
            specialCategoriesRepository.save(specialCategories);
            return specialCategories;
        } catch (Exception e) {
            return null;
        }
    }

    public List<SpecialCategories> getAllSpecialCategories() throws Exception {
        try {
            return specialCategoriesRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Failed to fetch categories");
        }
    }

    public SpecialCategories updateSpecialCategory(Long id,SpecialCategories specialCategories) {
        try {
            SpecialCategories specialCategories1=specialCategories;
            Optional<SpecialCategories> existingSpecialCategory = specialCategoriesRepository.findById(id);
            if (existingSpecialCategory.isPresent()) {
                specialCategories.setSpecial_category_id(id);
                specialCategories.setCreated_date(LocalDateTime.now());
                specialCategories.setUpdated_date(LocalDateTime.now());
                specialCategoriesRepository.save(specialCategories);
                return specialCategories1;
            } else {
                throw new Exception("ID DOESN'T EXIST!");
            }
        } catch (Exception e) {
            return null;
        }
    }

    public SpecialCategories deleteCategory(Long id) {
        try {
            if (specialCategoriesRepository.existsById(id)) {
                SpecialCategories specialCategories=specialCategoriesRepository.findById(id).get();
                specialCategoriesRepository.deleteById(id);
                return specialCategories;
            } else {
                throw new Exception("ID DOESN'T EXIST");
            }
        } catch (Exception e) {
            return null;
        }
    }
}
