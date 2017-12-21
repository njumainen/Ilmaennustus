import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeatherForPlaces {
    public List<Weather> getWeatherForPlaces(List<String> places) {
        List<Weather> weather = new ArrayList<>();
        for (String place: places) {
            List<String> countryInfo = Arrays.asList(place.split("\\s*,\\s*"));
            WeatherRequest weatherRequest = new WeatherRequest(countryInfo.get(0), countryInfo.get(1));
            weather.add(GetCurrentTemperature.getCurrentTemperature(weatherRequest));
            weather.addAll(ThreeDaysWeather.threeDaysWeather(weatherRequest));
        }

        return weather;
    }
}
