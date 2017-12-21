import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class ThreeDaysWeather {
    public static List<Weather> threeDaysWeather(WeatherRequest request)
    {

        List<Weather> ThreeDaysWeather = new ArrayList<>();
        List<List<Weather>> sortByDate = new ArrayList<>();
        List<Weather> reportThreeDaysWeather = new ArrayList<>();

        Date date = new Date();
        Calendar calendarReport = Calendar.getInstance();
        calendarReport.setTime(date);
        int currentDay = calendarReport.get(Calendar.DAY_OF_MONTH);

        JSONObject response = ConnectionMaker.connectionToService(request, "forecast");

        if (response == null){
            return null;
        }

        try {
            JSONArray list = response.getJSONArray("list");

            for (int i = 0; i < list.length(); i++){
                JSONObject threeDaysWeatherObject = (JSONObject)list.get(i);
                Date dateOfForecast = new java.util.Date(threeDaysWeatherObject.getLong("dt")*1000);
                calendarReport.setTime(dateOfForecast);


                Weather threeDaysWeather = new Weather();
                threeDaysWeather.setDate(calendarReport.getTime());
                threeDaysWeather.setCityName(response.getJSONObject("city").getString("name"));
                threeDaysWeather.setCode(response.getJSONObject("city").getString("country"));
                threeDaysWeather.setTemperature(threeDaysWeatherObject.getJSONObject("main").getDouble("temp"));
                threeDaysWeather.setMinTemp(threeDaysWeatherObject.getJSONObject("main").getDouble("temp_min"));
                threeDaysWeather.setMaxTemp(threeDaysWeatherObject.getJSONObject("main").getDouble("temp_max"));
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
                weather.setCityName(maxTempOfDay.getCityName());
                weather.setCoordLat(maxTempOfDay.getCoordLat());
                weather.setCoordLon(maxTempOfDay.getCoordLon());
                weather.setTemperature(maxTempOfDay.getTemperature());
                weather.setMaxTemp(maxTempOfDay.getTemperature());
                weather.setMinTemp(minTempOfDay.getTemperature());
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
