/*
 * Copyright (c) 2025
 * Gihan Viduranga - 2025
 */

package lk.ijse.vehicleservice.service.impl;

import lk.ijse.vehicleservice.dto.ResponseDTO;
import lk.ijse.vehicleservice.dto.VehicleDTO;
import lk.ijse.vehicleservice.entity.Vehicle;
import lk.ijse.vehicleservice.repo.VehicleRepo;
import lk.ijse.vehicleservice.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepo vehicleRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public boolean addVehicle(VehicleDTO vehicleDTO) {
        if (vehicleRepo.existsByPlateNumber(vehicleDTO.getPlateNumber())) {
            return false;
        }

        String email = vehicleDTO.getUserEmail();
        boolean isValidEmail = checkUserEmail(email);

        if (!isValidEmail) {
            return false;
        }
        vehicleRepo.save(modelMapper.map(vehicleDTO, Vehicle.class));
        return true;
    }

    @Override
    public VehicleDTO parkVehicle(VehicleDTO vehicleDTO) {
        Vehicle existingVehicle = vehicleRepo.findByPlateNumber(vehicleDTO.getPlateNumber());

        if (existingVehicle != null) {
            existingVehicle.setParked(vehicleDTO.isParked());
            vehicleRepo.save(existingVehicle);
            return modelMapper.map(existingVehicle, VehicleDTO.class);
        }

        return null;
    }

    public boolean checkUserEmail(String email) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8082/user-auth-service/api/v1/user/checkUserEmail/" + email;

            ResponseEntity<ResponseDTO> response = restTemplate.getForEntity(url, ResponseDTO.class);
            ResponseDTO responseBody = response.getBody();

            return responseBody != null && Boolean.TRUE.equals(responseBody.getData());

        } catch (HttpClientErrorException.NotFound e) {
            System.out.println("User not found for email: " + email);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
