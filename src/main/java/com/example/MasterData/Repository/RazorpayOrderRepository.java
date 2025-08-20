package com.example.MasterData.Repository;

import com.example.MasterData.entity.RazorpayOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RazorpayOrderRepository extends JpaRepository<RazorpayOrderEntity, Long> {

}
