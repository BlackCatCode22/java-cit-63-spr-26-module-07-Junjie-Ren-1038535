package Module7;

public class WeatherDashboardData {
    private String cityName;
    private int temperature;
    private String shortForecast;
    private String icon;

    // Constructor
    public WeatherDashboardData(String cityName, int temperature, String shortForecast, String icon) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.shortForecast = shortForecast;
        this.icon = icon;
    }

    // Add these so Thymeleaf can "read" the data
    public String getCityName() { return cityName; }
    public int getTemperature() { return temperature; }
    public String getShortForecast() { return shortForecast; }
    public String getIconUrl() { return icon; }
}