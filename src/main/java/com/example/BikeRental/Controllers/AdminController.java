package com.example.BikeRental.Controllers;


import com.example.BikeRental.Model.Image;
import com.example.BikeRental.Model.PlaceMark;
import com.example.BikeRental.services.ImageService;
import com.example.BikeRental.services.PlaceMarkService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private PlaceMarkService placeMarkService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private Gson gson;

    @GetMapping()
    public String showAdmin(){
        return "admin/index";
    }

    @GetMapping("/new")
    public String newPlaceMark(@ModelAttribute("placeMark") PlaceMark placeMark){
        return "admin/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("placeMark") PlaceMark placeMark) {
        placeMarkService.save(placeMark);
        return "redirect:/admin";
    }

    @GetMapping("show/{id}")
    public String show(@PathVariable("id") int id, Model model) throws Exception {
        model.addAttribute("placeMark", placeMarkService.findById(id));
        model.addAttribute("listImage", imageService.findByPlaceMarkId(id));
        return "admin/show";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) throws Exception {
        placeMarkService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String editPlaceMark(Model model, @PathVariable("id") int id) throws Exception {
        model.addAttribute("placeMark", placeMarkService.findById(id));
        return "admin/edit";
    }

    @PatchMapping("/{id}")
    public String edit(@ModelAttribute("placeMark") PlaceMark placeMark,
                       @PathVariable("id") int id) {
        placeMarkService.update(id, placeMark);
        return "redirect:/admin/show/{id}";
    }

//    @GetMapping("/statistics")
//    public String showAll(Model model){
//        model.addAttribute("listPlaceMarks", placeMarkService.findAll());
//        return "admin/showAll";
//    }

    @ResponseBody
    @GetMapping("/statistics")
    public String statistics() {
        String statistic = "";

        statistic =
                "Количество велопрокатов: " + placeMarkService.findByTagPlaceMark("BikeRental").size() + "<br>" +
                        "Количество велопарковок: " + placeMarkService.findByTagPlaceMark("BikeParking").size() + "<br>" +
                        "Количество точек ремонта велосипедов: " + placeMarkService.findByTagPlaceMark("BikeRepairs").size() + "<br>" +
                        "Количество веломагазинов: " + placeMarkService.findByTagPlaceMark("BikeShop").size() + "<br>" +
                        "Количество опасных участков: " + placeMarkService.findByTagPlaceMark("DangerousArea").size() + "<br>" +
                        "Количество точек отдыха: " + placeMarkService.findByTagPlaceMark("ChillZone").size() + "<br>" +
                        "Количество спортивных кружков: " + placeMarkService.findByTagPlaceMark("SportSection").size() + "<br>" +
                        "Количество фотографий: " + imageService.findAll().size();
        return statistic;
    }

    @ResponseBody
    @PostMapping("{id}/newImage")
    public String addImage(HttpEntity<String> httpEntity, @PathVariable("id") int id) {
        Image image = gson.fromJson(httpEntity.getBody(), Image.class);
        image.setIdPlaceMark(id);
        imageService.save(image);
        return gson.toJson("Image saved", String.class);
    }

    @ResponseBody
    @DeleteMapping("removeImage")
    public String deleteImage(HttpEntity<String> httpEntity) throws Exception {
        Image image = gson.fromJson(httpEntity.getBody(), Image.class);
        imageService.deleteById(image.getId());
        return gson.toJson("Image removed", String.class);
    }
}
