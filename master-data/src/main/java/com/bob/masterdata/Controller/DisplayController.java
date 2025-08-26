package com.bob.masterdata.Controller;

import com.bob.masterdata.Service.DisplayService;
import com.bob.masterdata.model.GetCompleteDataResponse;
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
    public GetCompleteDataResponse getCompleteData2(){
        return displayService.getAllData3();
    }


}
