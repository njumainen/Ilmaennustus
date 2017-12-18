import java.util.Date;


public class Weather {
    private double temperature;
    private double highestTemp;
    private double lowestTemp;
    private Date date;
    private String city;
    private String code;
    private Double coordLat;
    private Double coordLon;

    double getTemperature() {
        return temperature;
    }

    Double getCoordLat() {
        return coordLat;
    }

    void setCoordLat(Double coordLat) {
        this.coordLat = coordLat;
    }

    Double getCoordLon() {
        return coordLon;
    }

    void setCoordLon(Double coordLon) {
        this.coordLon = coordLon;
    }

    void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    double getHighestTemp() {
        return highestTemp;
    }

    void setHighestTemp(double highestTemp) {
        this.highestTemp = highestTemp;
    }

    double getLowestTemp() {
        return lowestTemp;
    }

    void setLowestTemp(double lowestTemp) {
        this.lowestTemp = lowestTemp;
    }

    Date getDate() {
        return date;
    }

    void setDate(Date date) {
        this.date = date;
    }

    String getCity() {
        return city;
    }

    void setCity(String city) {
        this.city = city;
    }

    String getCode() {
        return code;
    }

    void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Weather report:" + "City: " + city + ',' + code +"; Date: " + date + "; Current Temperature: " + temperature + "; Latitude: " + coordLat + "; Longitude: " + coordLon + ", min:" + lowestTemp +
                ", max:" + highestTemp;
    }
}
