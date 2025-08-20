package com.example.MasterData.Controller;

import com.example.MasterData.Model.RazorpayOrdersResponse;
import com.example.MasterData.Service.RazorpayService;
import com.example.MasterData.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/razorpay")
public class RazorpayController {
    @Autowired
    private RazorpayService razorpayService;


    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<RazorpayOrdersResponse>>> getAllUsers(){
        try{
            List<RazorpayOrdersResponse> orders = razorpayService.getAllRazorpayOrders();
            ApiResponse<List<RazorpayOrdersResponse>> response=new ApiResponse<>(true,"ORDERS FETCHED SUCCESSFULLY!",orders);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<List<RazorpayOrdersResponse>> response=new ApiResponse<>(true,"ORDERS NOT FETCHED!",null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
