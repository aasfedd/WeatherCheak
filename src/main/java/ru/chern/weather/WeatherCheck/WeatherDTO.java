package ru.chern.weather.WeatherCheck;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.tools.javac.Main;

import java.util.List;
import java.util.Map;
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDTO {
    private String name;
    private Main main;

    @JsonProperty("weather")
    private List<Weather> weather;

    public String getName(){
        return name;
    }
    public Main getMain(){
        return main;
    }
    public List<Weather> getWeather(){
        return weather;
    }
    @Override
    public String toString() {
        String weatherDescription = (weather != null && !weather.isEmpty())
                ? weather.get(0).getDescription()
                : "Нет данных";

        return String.format(
                "Город: %s, Температура: %.2f°C, Давление: %d hPa, Влажность: %d%%, Погода: %s",
                name != null ? name : "Неизвестно",
                main != null ? main.getTemp() - 273.15 : 0.0,
                main != null ? main.getPressure() : 0,
                main != null ? main.getHumidity() : 0,
                weatherDescription
        );
    }
    @JsonIgnoreProperties
    public static class Main {
        private double temp;
        private int pressure;
        private int humidity;

        public double getTemp(){
            return temp;
        }
        public int getPressure(){
            return pressure;
        }
        public int getHumidity(){
            return humidity;
        }
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Weather{
        private String main;
        private String icon;
        private String description;
        public String getDescription(){
            return description;
        }
        @Override
        public String toString() {
            return description != null ? description : "Нет данных о погоде";
        }
        public String getMain() {
            return main;
        }

        public String getIcon() {
            return icon;
        }
    }
}
