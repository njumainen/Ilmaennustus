
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class WeatherRepository {
    private static String weatherUrl = "http://api.openweathermap.org/data/2.5/";
    private static String key = "05a83a8890d0af69b62aab7de7229515";

    public static Weather getCurrentTemperature(WeatherRequest request){
        try {

            JSONObject report = askWeather(request, "weather");
            if (report == null) {
                return null;
            }

            Weather currentWeather = new Weather();

            currentWeather.setCity(report.getString("name"));
            currentWeather.setCode(report.getJSONObject("sys").getString("country"));
            currentWeather.setTemperature(report.getJSONObject("main").getDouble("temp"));
            currentWeather.setLowestTemp(report.getJSONObject("main").getDouble("temp_min"));
            currentWeather.setHighestTemp(report.getJSONObject("main").getDouble("temp_max"));
            currentWeather.setCoordLat(report.getJSONObject("coord").getDouble("lat"));
            currentWeather.setCoordLon(report.getJSONObject("coord").getDouble("lon"));
            return currentWeather;


        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }
    private static JSONObject askWeather(WeatherRequest request, String option){
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
}
