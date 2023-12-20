package com.example.BikeRental.repositories;

import com.example.BikeRental.Model.PlaceMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceMarkRepository extends JpaRepository<PlaceMark, Integer> {
    List<PlaceMark> findPlaceMarkByTag(String tagPlaceMark);
}
