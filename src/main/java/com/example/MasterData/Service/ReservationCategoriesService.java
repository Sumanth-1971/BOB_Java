package com.example.MasterData.Service;

import com.example.MasterData.Model.JobGrade;
import com.example.MasterData.Model.ReservationCategories;
import com.example.MasterData.Repository.ReservationCategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationCategoriesService {

    @Autowired
    private ReservationCategoriesRepository reservationCategoriesRepository;

    public ReservationCategories createReservationCategories(ReservationCategories reservationCategories) {
        try {
            reservationCategories.setCreated_date(LocalDateTime.now());
            reservationCategories.setUpdated_date(LocalDateTime.now());
            reservationCategoriesRepository.save(reservationCategories);
            return reservationCategories;
        } catch (Exception e) {
            return null;
        }
    }

    public List<ReservationCategories> getAllResCategories() throws Exception {
        try {
            return reservationCategoriesRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Failed to fetch Reservation categories");
        }
    }

    public ReservationCategories updateResCategories(Long id, ReservationCategories reservationCategories) {
        try {
            ReservationCategories reservationCategories1=reservationCategories;
            Optional<ReservationCategories> existingReservationCategories = reservationCategoriesRepository.findById(id);
            if (existingReservationCategories.isPresent()) {
                reservationCategories.setUpdated_date(LocalDateTime.now());
                reservationCategoriesRepository.save(reservationCategories);
                return reservationCategories;
            } else {
                throw new Exception("ID DOESN'T EXIST!");
            }
        } catch (Exception e) {
            return null;
        }
    }

    public ReservationCategories deleteReservationCategory(Long id) {
        try {
            if (reservationCategoriesRepository.existsById(id)) {
                ReservationCategories reservationCategories=reservationCategoriesRepository.findById(id).get();
                reservationCategoriesRepository.deleteById(id);
                return reservationCategories;
            } else {
                throw new Exception("ID DOESN'T EXIST");
            }
        } catch (Exception e) {
            return null;
        }
    }
}
