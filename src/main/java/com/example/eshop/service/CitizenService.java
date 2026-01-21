package com.example.eshop.service;

import com.example.eshop.DTO.CitizenCreateDTO;
import com.example.eshop.DTO.CitizenMapper;
import com.example.eshop.DTO.CitizenResponseDTO;
import com.example.eshop.model.Citizen;
import com.example.eshop.repository.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitizenService {

    @Autowired
    CitizenRepository citizenRepository;

    public CitizenResponseDTO createCitizen(CitizenCreateDTO dto){
        if (citizenRepository.findByEmail(dto.email()).isPresent()) {
            throw new IllegalArgumentException("Υπάρχει χρήστης με αυτο το email : "+ dto.email());
        }
        if (citizenRepository.findById(dto.afm()).isPresent()){
            throw new IllegalArgumentException("Υπάρχει χρήστης με αυτο το ΑΦΜ : "+ dto.afm());
        }
        Citizen citizen=new Citizen();
        citizen.setAfm(dto.afm());
        citizen.setEmail(dto.email());
        citizen.setFirstName(dto.firstName());
        citizen.setLastName(dto.lastName());
        citizen.setPassword(dto.password());
        Citizen saved = citizenRepository.save(citizen);
        return CitizenMapper.toDTO(saved);
    }
    public List<CitizenResponseDTO> getAllCitizens(){
        return citizenRepository.findAll().stream().map(CitizenMapper::toDTO).toList();
    }
    public CitizenResponseDTO getCitizenByAfm(int afm){
        Citizen citizen = citizenRepository.findById(afm).orElseThrow(()-> new IllegalArgumentException("Ο χρήστης δεν βρέθηκε"));
        return CitizenMapper.toDTO(citizen);
    }
}
