
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class WeatherRepository {
    private static String weatherUrl = "http://api.openweathermap.org/data/2.5/";
    private static String key = "05a83a8890d0af69b62aab7de7229515";

    static JSONObject connectionToService(WeatherRequest request, String option){
        try {

            URL url = new URL(weatherUrl + option + "?q=" + request.getCity() + "," + request.getCountry() + "&units=" + request.getUnits() + "&APPID=" + key);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Accept", "application/json");

            if (c.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + c.getResponseCode());
            }

            BufferedReader streamReader = new BufferedReader(new InputStreamReader(c.getInputStream(), "UTF-8"));
            StringBuilder responseBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseBuilder.append(inputStr);

            JSONObject jsonObj = new JSONObject(responseBuilder.toString());

            c.disconnect();

            return jsonObj;

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }
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
