package ru.chern.weather.WeatherCheck.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chern.weather.WeatherCheck.Service.ApiService;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final ApiService apiService;

    public ApiController(ApiService apiService){
        this.apiService = apiService;
    }
    @GetMapping("/fetch")
    public String fetchData(){
        return apiService.getWeather();
    }

}
