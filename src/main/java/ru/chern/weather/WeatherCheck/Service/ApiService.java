package ru.chern.weather.WeatherCheck.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import ru.chern.weather.WeatherCheck.WeatherDTO;

import java.util.Objects;

@Service
public class ApiService {
    private final RestTemplate restTemplate;

    public ApiService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public String getWeather(){
        String url = "https://api.openweathermap.org/data/2.5/weather?lat=59.865989&lon=30.263793&appid=f074ef6bb80008a4320e72c3ba2b1aad";
        WeatherDTO response = restTemplate.getForObject(url, WeatherDTO.class);
        if (response != null){
            return String.format("Город: %s, Температура: %.2f°C, Погода: %s",
                    response.getName(),
                    response.getMain().getTemp()-273.15,
                    Objects.requireNonNull(response.getWeather().get(0).toString(), "WeatherList is empty"));
        }

        return "Ошибка получения данных";
    }
}
