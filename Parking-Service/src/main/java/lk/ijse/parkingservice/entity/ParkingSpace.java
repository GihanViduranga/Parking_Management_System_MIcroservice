/*
 * Copyright (c) 2025
 * Gihan Viduranga - 2025
 */

package lk.ijse.parkingservice.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
public class ParkingSpace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private int spotNumber;
    private String location;
    private boolean isAvailable;
    private String userEmail;
    private String zone;

    public ParkingSpace() {
    }

    public ParkingSpace(int id, int spotNumber, String location, boolean isAvailable, String userEmail, String zone) {
        this.id = id;
        this.spotNumber = spotNumber;
        this.location = location;
        this.isAvailable = isAvailable;
        this.userEmail = userEmail;
        this.zone = zone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(int spotNumber) {
        this.spotNumber = spotNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}
