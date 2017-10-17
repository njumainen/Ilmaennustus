

public class WeatherRequest {
    private String city;
    private String country;
    private String units;


    WeatherRequest() {
        this.city = "Tallinn";
        this.country = "EE";
        this.units = "metric";
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUnits() {
        return units;
    }

    public void setMetric(String units) {
        this.units = units;
    }
}
