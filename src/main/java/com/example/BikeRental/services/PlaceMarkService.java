package com.example.BikeRental.services;


import com.example.BikeRental.Model.PlaceMark;
import com.example.BikeRental.repositories.PlaceMarkRepository;
import com.google.gson.Gson;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PlaceMarkService {
    private final PlaceMarkRepository placeMarkRepository;

    @Autowired
    public PlaceMarkService(PlaceMarkRepository placeMarkRepository) {
        this.placeMarkRepository = placeMarkRepository;
    }

    public List<PlaceMark> findAll(){
        return placeMarkRepository.findAll();
    }

    public PlaceMark findById(int id) throws Exception {
        Optional<PlaceMark> opt = this.placeMarkRepository.findById(id);
        if (opt.isEmpty())
            throw new Exception("Пользователь не найден");
        else
            return opt.get();
    }


    public void save(PlaceMark placeMark){
        this.placeMarkRepository.save(placeMark);
    }

    public void update(int id, PlaceMark placeMark){
        placeMark.setId(id);
        this.placeMarkRepository.save(placeMark);
    }

    public void deleteById (int id) throws Exception {
        this.placeMarkRepository.deleteById(id);
    }

    public String WriteOnJson() throws IOException {
        List<PlaceMark> placeMarkList = this.placeMarkRepository.findAll();
        Gson gson = new Gson();
        return gson.toJson(placeMarkList);
    }

    public List<PlaceMark> findByTagPlaceMark(String tagPlaceMark){
        return this.placeMarkRepository.findPlaceMarkByTag(tagPlaceMark);
    }
}
