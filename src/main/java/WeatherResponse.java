import org.json.*;

public class WeatherResponse {
    public String city;
    public String countryCode;
    public double currentTemp;

    public WeatherResponse(JSONObject json){
        JSONArray list = json.getJSONArray("list");
        JSONObject data = list.getJSONObject(0);

        this.city = data.getString("name");
        this.countryCode = data.getJSONObject("sys").getString("country");
        this.currentTemp = data.getJSONObject("main").getDouble("temp");
    }

    public String getCity() {
        return city;
    }

    public String getCode() {
        return countryCode;
    }

    public Double getCurrentTemp() {
        return currentTemp;
    }



    @Override
    public String toString() {
        return "CurrentWeatherReport{" +
                "city='" + city + '\'' +
                ", code='" + countryCode + '\'' +
                ", currentTemperature=" + currentTemp +
                '}';
    }
}
