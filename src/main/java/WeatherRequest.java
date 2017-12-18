

public class WeatherRequest {
    private String city;
    private String country;
    private String units;


    WeatherRequest(String city, String code) {
        this.city = city;
        this.country = country;
        this.units = units;
    }

    String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    String getUnits() {
        return units;
    }

    public void setMetric(String units) {
        this.units = units;
    }
}
