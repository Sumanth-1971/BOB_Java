package com.example.MasterData.Controller;

import com.example.MasterData.Model.ReservationCategories;
import com.example.MasterData.Service.DisplayService;
//import com.example.MasterData.dto.MasterDTO;
import com.example.MasterData.dto.MasterDTO2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DisplayController {
    @Autowired
    private DisplayService displayService;

    @GetMapping("/all")
    public MasterDTO2 getCompleteData2(){
        return displayService.getAllData3();
    }
}
