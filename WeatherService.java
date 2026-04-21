package Module7;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final RestTemplate restTemplate;

    public WeatherService() {
        this.restTemplate = new RestTemplate();
    }

    public String getRawWeatherData(String url) {
        RestTemplate restTemplate = new RestTemplate();

        // Create headers - NWS requires a User-Agent
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "MyWeatherApp (contact@example.com)");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Make the call with headers
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response.getBody();
    }
}
