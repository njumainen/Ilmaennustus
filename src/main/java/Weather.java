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

    public double getTemperature() {
        return temperature;
    }

    public Double getCoordLat() {
        return coordLat;
    }

    public void setCoordLat(Double coordLat) {
        this.coordLat = coordLat;
    }

    public Double getCoordLon() {
        return coordLon;
    }

    public void setCoordLon(Double coordLon) {
        this.coordLon = coordLon;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHighestTemp() {
        return highestTemp;
    }

    public void setHighestTemp(double highestTemp) {
        this.highestTemp = highestTemp;
    }

    public double getLowestTemp() {
        return lowestTemp;
    }

    public void setLowestTemp(double lowestTemp) {
        this.lowestTemp = lowestTemp;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
