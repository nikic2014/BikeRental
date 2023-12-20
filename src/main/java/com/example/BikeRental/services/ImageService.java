package com.example.BikeRental.services;

import com.example.BikeRental.Model.Image;
import com.example.BikeRental.Model.PlaceMark;
import com.example.BikeRental.repositories.ImageRepository;
import com.google.gson.Gson;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ImageService {
    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public List<Image> findAll(){
        return imageRepository.findAll();
    }

    public Image findById(int id) throws Exception {
        Optional<Image> opt = this.imageRepository.findById(id);
        if (opt.isEmpty())
            throw new Exception("Пользователь не найден");
        else
            return opt.get();
    }

    public void save(Image image){
        this.imageRepository.save(image);
    }

    public void deleteById (int id) throws Exception {
        this.imageRepository.deleteById(id);
    }

    public List<Image> findByPlaceMarkId(int id){
        return imageRepository.findImageByIdPlaceMark(id);
    }

    public String WriteOnJson(int id) {
        List<Image> imageList =
                this.imageRepository.findImageByIdPlaceMark(id);
        Gson gson = new Gson();
        return gson.toJson(imageList);
    }
}
