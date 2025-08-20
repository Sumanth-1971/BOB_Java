package com.example.CandidateDetails.Repository;

import com.example.CandidateDetails.entity.RazorpayOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RazorpayOrderRepository extends JpaRepository<RazorpayOrderEntity, Long> {

}
