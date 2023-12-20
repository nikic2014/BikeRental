package com.example.BikeRental.repositories;

import com.example.BikeRental.Model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    List<Image> findImageByIdPlaceMark(int idPlaceMark);
}
