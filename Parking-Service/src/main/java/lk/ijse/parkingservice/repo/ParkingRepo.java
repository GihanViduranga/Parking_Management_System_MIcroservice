/*
 * Copyright (c) 2025
 * Gihan Viduranga - 2025
 */

package lk.ijse.parkingservice.repo;

import lk.ijse.parkingservice.entity.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepo extends JpaRepository<ParkingSpace, Integer> {
    //boolean existsByUniqSpot(String parkingSpot);
    boolean existsBySpotNumber(int spotNumber);

    //ParkingSpace existsParkingSpot(String parkingSpot);
    ParkingSpace findBySpotNumber(int spotNumber);

}
