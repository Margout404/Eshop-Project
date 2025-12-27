package com.example.eshop.DTO;

import com.example.eshop.model.Citizen;

public class CitizenMapper {
    public static CitizenResponseDTO toDTO(Citizen citizen) {
        return new CitizenResponseDTO(
                citizen.getAfm(),
                citizen.getFirstName(),
                citizen.getLastName(),
                citizen.getEmail()
        );
    }
}
