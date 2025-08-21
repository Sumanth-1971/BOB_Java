package com.bob.masterdata.Controller;

import com.bob.masterdata.Service.DisplayService;
//import com.bob.db.dto.MasterDTO;
import com.bob.db.dto.MasterDTO2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
