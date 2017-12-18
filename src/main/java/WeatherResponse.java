import org.json.*;

public class WeatherResponse {
    private String city;
    private String code;
    private double currentTemp;

    public WeatherResponse(JSONObject json){
        JSONArray list = json.getJSONArray("list");
        JSONObject data = list.getJSONObject(0);

        this.city = data.getString("name");
        this.code = data.getJSONObject("sys").getString("country");
        this.currentTemp = data.getJSONObject("main").getDouble("temp");
    }

    public String getCity() {
        return city;
    }

    public String getCode() {
        return code;
    }

    public Double getCurrentTemp() {
        return currentTemp;
    }



    @Override
    public String toString() {
        return "CurrentWeatherReport{" +
                "city='" + city + '\'' +
                ", code='" + code + '\'' +
                ", currentTemperature=" + currentTemp +
                '}';
    }
}
