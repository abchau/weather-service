package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping(path="/weather")
public class WeatherController {

    private final WeatherRepository weatherRepository;

    public WeatherController(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @GetMapping("/city1")
    public @ResponseBody Weather getWeatherForCity1(@RequestParam("name") String cityName) {
        System.out.println("city()...invoked at: " + LocalDateTime.now());
        return weatherRepository.findById(cityName).get();
    }
    
    @GetMapping("/city")
    public @ResponseBody Weather getWeatherForCity(@RequestParam("name") String cityName) {
        System.out.println("city()...invoked at: " + LocalDateTime.now());
        return weatherRepository.findById(cityName).map(weather -> {
            weather.setDescription("It's always sunny on Azure Spring Apps");
            weather.setIcon("weather-sunny");
            return weather;
        }).get();
    }
}