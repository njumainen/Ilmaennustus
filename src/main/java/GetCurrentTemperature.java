import org.json.JSONObject;

public class GetCurrentTemperature {
    public static Weather getCurrentTemperature(WeatherRequest request){
        try {

            JSONObject report = WeatherRepository.connectionToService(request, "weather");
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
}
