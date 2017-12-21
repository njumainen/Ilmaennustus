

public class WeatherRequest {
    private String city;
    private String country;
    private String units;


    WeatherRequest(String city, String code) {
        this.city = city;
        this.country = country;
        this.units = "metric";
    }

    String getCity() {
        return city;
    }


    String getCountry() {
        return country;
    }


    String getUnits() {
        return units;
    }

}
