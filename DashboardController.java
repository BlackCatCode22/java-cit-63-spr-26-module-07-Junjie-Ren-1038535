package Module7;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.ArrayList;
import java.util.List;

@Controller // Use @Controller for HTML views, not @RestController
public class DashboardController {

    private final WeatherService weatherService;
    private final ObjectMapper mapper = new ObjectMapper();

    public DashboardController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) throws Exception {
        List<WeatherDashboardData> weatherList = new ArrayList<>();

// Fresno, CA (Land)
        weatherList.add(fetchCityData("Fresno, CA", "https://api.weather.gov/gridpoints/HNX/53,100/forecast"));

// New York, NY (Land - Central Park area)
        weatherList.add(fetchCityData("New York, NY", "https://api.weather.gov/gridpoints/OKX/33,37/forecast"));

        model.addAttribute("cities", weatherList);
        return "dashboard"; // Points to src/main/resources/templates/dashboard.html
    }

    private WeatherDashboardData fetchCityData(String cityName, String url) throws Exception {
        String rawJson = weatherService.getRawWeatherData(url);
        JsonNode root = mapper.readTree(rawJson);

        // 1. Navigate to the periods array
        JsonNode periods = root.path("properties").path("periods");

        // 2. Check if the periods exist and have at least one entry
        if (periods.isArray() && periods.size() > 0) {
            JsonNode currentPeriod = periods.get(0);

            return new WeatherDashboardData(
                    cityName,
                    currentPeriod.path("temperature").asInt(),
                    currentPeriod.path("shortForecast").asText(),
                    currentPeriod.path("icon").asText()
            );
        } else {
            // 3. Plan B: If the API didn't give us data, return a "Placeholder" object
            // This prevents the NullPointerException!
            return new WeatherDashboardData(
                    cityName,
                    0,
                    "Forecast Unavailable",
                    "https://api.weather.gov/icons/placeholder.png"
            );
        }
    }
}
