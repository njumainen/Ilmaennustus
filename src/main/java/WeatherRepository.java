
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;


class WeatherRepository {
    private static String weatherUrl = "http://api.openweathermap.org/data/2.5/";
    private static String key = "05a83a8890d0af69b62aab7de7229515";

    static Weather getCurrentTemperature(WeatherRequest request){
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
    public static List<Weather> threeDaysWeather(WeatherRequest request)
    {
        List<Weather> ThreeDaysWeather = new ArrayList<>();
        List<List<Weather>> sortByDate = new ArrayList<>();
        List<Weather> reportThreeDaysWeather = new ArrayList<>();

        Date date = new Date();
        Calendar calendarReport = Calendar.getInstance();
        calendarReport.setTime(date);
        int currentDay = calendarReport.get(Calendar.DAY_OF_MONTH);

        JSONObject response = askWeather(request, "forecast");

        if (response == null){
            return null;
        }

        try {
            JSONArray list = response.getJSONArray("list");

            for (int i = 0; i < list.length(); i++){
                JSONObject threeDaysWeatherObj = (JSONObject)list.get(i);
                Date dateOfForecast = new java.util.Date(threeDaysWeatherObj.getLong("dt")*1000);
                calendarReport.setTime(dateOfForecast);


                Weather threeDaysWeather = new Weather();
                threeDaysWeather.setDate(calendarReport.getTime());
                threeDaysWeather.setCity(response.getJSONObject("city").getString("name"));
                threeDaysWeather.setCode(response.getJSONObject("city").getString("country"));
                threeDaysWeather.setTemperature(threeDaysWeatherObj.getJSONObject("main").getDouble("temp"));
                threeDaysWeather.setLowestTemp(threeDaysWeatherObj.getJSONObject("main").getDouble("temp_min"));
                threeDaysWeather.setHighestTemp(threeDaysWeatherObj.getJSONObject("main").getDouble("temp_max"));
                threeDaysWeather.setCoordLat(response.getJSONObject("city").getJSONObject("coord").getDouble("lat"));
                threeDaysWeather.setCoordLon(response.getJSONObject("city").getJSONObject("coord").getDouble("lon"));

                int forecastDay = calendarReport.get(Calendar.DAY_OF_MONTH);
                int day = (forecastDay - currentDay);

                if (day > 0 && day <= 3) {
                    if (sortByDate.size() < day || sortByDate.size() == 0) {
                        sortByDate.add(new ArrayList<>());

                    }
                    sortByDate.get(sortByDate.size() - 1).add(threeDaysWeather);
                }
            }

            for (List<Weather> threeDays: sortByDate) {
                Weather minTempOfDay = Collections.min(threeDays, Comparator.comparingDouble(Weather::getTemperature));
                Weather maxTempOfDay = Collections.max(threeDays, Comparator.comparingDouble(Weather::getTemperature));

                Weather weather = new Weather();
                weather.setCode(maxTempOfDay.getCode());
                weather.setCity(maxTempOfDay.getCity());
                weather.setCoordLat(maxTempOfDay.getCoordLat());
                weather.setCoordLon(maxTempOfDay.getCoordLon());
                weather.setTemperature(maxTempOfDay.getTemperature());
                weather.setHighestTemp(maxTempOfDay.getTemperature());
                weather.setLowestTemp(minTempOfDay.getTemperature());
                weather.setDate(maxTempOfDay.getDate());

                reportThreeDaysWeather.add(weather);
            }

            return reportThreeDaysWeather;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ThreeDaysWeather;
    }
}
