package Module7;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather/fresno")
    public String getFresnoWeather() {
        String url = "https://api.weather.gov/gridpoints/HNX/53,100/forecast";
        return weatherService.getRawWeatherData(url);
    }

    @GetMapping("/weather/newyork")
    public String getNewYorkWeather() {
        String url = "https://api.weather.gov/gridpoints/OKX/33,35/forecast";
        return weatherService.getRawWeatherData(url);
    }
}
