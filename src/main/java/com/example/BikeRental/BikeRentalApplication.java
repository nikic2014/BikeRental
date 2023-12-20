package com.example.BikeRental;

import com.example.BikeRental.Model.PlaceMark;
import com.example.BikeRental.services.PlaceMarkService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BikeRentalApplication {
	public static void main(String[] args)  {
		SpringApplication.run(BikeRentalApplication.class, args);
	}
}
