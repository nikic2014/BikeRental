package com.example.BikeRental.Controllers;

import com.example.BikeRental.services.ImageService;
import com.example.BikeRental.services.PlaceMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/map")
public class MapController {

    @Autowired
    private PlaceMarkService placeMarkService;
    @Autowired
    private ImageService imageService;

    @GetMapping()
    public String showMap(){
        return "map";
    }

    @ResponseBody
    @GetMapping("/placeMarks")
    public String showPlaceMarks() throws IOException {
        String jsonPlaceMarks = placeMarkService.WriteOnJson();
        return jsonPlaceMarks;
    }
    @ResponseBody
    @GetMapping("/placeMarks/images/{id}")
    public String showImages(@PathVariable("id") int id){
        String jsonImages = imageService.WriteOnJson(id);
        return jsonImages;
    }
}
