package ru.chern.weather.WeatherCheck.Frontend;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import elemental.json.impl.JsonUtil;
import org.springframework.web.client.RestTemplate;
import ru.chern.weather.WeatherCheck.WeatherDTO;
@PreserveOnRefresh
@Route("")
@PageTitle("Погода")
public class MainLayout extends VerticalLayout {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=f074ef6bb80008a4320e72c3ba2b1aad";
    public MainLayout() {
        System.out.print("Front запустился?");
        NumberField latField = new NumberField("Широта");
        NumberField lonField = new NumberField("Долгота");

        Button fetchWeatherButton = new Button("Узнать погоду", event -> {
            Double lat = latField.getValue();
            Double lon = lonField.getValue();
            if (lat == null || lon == null){
                Notification.show("Введите координаты!");
                return;
            }
            String url = String.format(API_URL, lat, lon);
            try {
                WeatherDTO response = restTemplate.getForObject(url, WeatherDTO.class);
                Notification.show(response != null ? response.toString() : "Ошибка получения данных");
            } catch (Exception e){
                Notification.show("Ошибка запроса: " + e.getMessage());
            }

        });
        add(latField, lonField, fetchWeatherButton);

    }
}
