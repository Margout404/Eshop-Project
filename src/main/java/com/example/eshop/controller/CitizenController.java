package com.example.eshop.controller;

import com.example.eshop.DTO.CitizenCreateDTO;
import com.example.eshop.DTO.CitizenResponseDTO;
import com.example.eshop.service.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citizens")
public class CitizenController {

    @Autowired
    CitizenService citizenService;

    @GetMapping
    public List<CitizenResponseDTO> getAllCitizens(){
        return citizenService.getAllCitizens();
    }

    @GetMapping("/{afm}")
    public CitizenResponseDTO getCitizenByAfm(@PathVariable int afm){
        return citizenService.getCitizenByAfm(afm);
    }

    @PostMapping
    public CitizenResponseDTO createCitizen(@RequestBody CitizenCreateDTO dto){
        return citizenService.createCitizen(dto);
    }

}
