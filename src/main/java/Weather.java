import java.util.Date;


public class Weather {
    private double temperature;
    private double maxTemp;
    private double minTemp;
    private Date date;
    private String cityName;
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

    double getMaxTemp() {
        return maxTemp;
    }

    void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    double getMinTemp() {
        return minTemp;
    }

    void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    Date getDate() {
        return date;
    }

    void setDate(Date date) {
        this.date = date;
    }

    String getCityName() {
        return cityName;
    }

    void setCityName(String cityName) {
        this.cityName = cityName;
    }

    String getCode() {
        return code;
    }

    void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Weather report:" + "City: " + cityName + ',' + code +"; Date: " + date + "; Current Temperature: " + temperature + "; Latitude: " + coordLat + "; Longitude: " + coordLon + ", min:" + minTemp +
                ", max:" + maxTemp;
    }
}
