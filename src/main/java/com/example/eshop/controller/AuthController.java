package com.example.eshop.controller;

import com.example.eshop.DTO.LoginRequestDTO;
import com.example.eshop.DTO.LoginResponseDTO;
import com.example.eshop.model.Citizen;
import com.example.eshop.model.Store;
import com.example.eshop.repository.CitizenRepository;
import com.example.eshop.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    CitizenRepository citizenRepository;

    @Autowired
    StoreRepository storeRepository;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequest) {

        // 1. Έλεγχος αν είναι Πολίτης
        Optional<Citizen> citizenOpt = citizenRepository.findById(loginRequest.afm());
        if (citizenOpt.isPresent()) {
            Citizen citizen = citizenOpt.get();
            // Προσοχή: Ελέγχουμε αν ταιριάζει ο κωδικός
            if (citizen.getPassword().equals(loginRequest.password())) {
                return new LoginResponseDTO(true, "Login Successful", "CITIZEN", citizen.getAfm(), citizen.getFirstName() + " " + citizen.getLastName());
            }
        }

        // 2. Έλεγχος αν είναι Μαγαζί
        Optional<Store> storeOpt = storeRepository.findById(loginRequest.afm());
        if (storeOpt.isPresent()) {
            Store store = storeOpt.get();
            // Προσοχή: Ελέγχουμε αν ταιριάζει ο κωδικός
            if (store.getPassword().equals(loginRequest.password())) {
                return new LoginResponseDTO(true, "Login Successful", "STORE", store.getAfm(), store.getStoreName());
            }
        }

        // 3. Δεν βρέθηκε ή λάθος κωδικός
        return new LoginResponseDTO(false, "Invalid credentials", null, 0, null);
    }
}