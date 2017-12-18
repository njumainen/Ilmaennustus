
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class WeatherTest {
    private WeatherRequest weatherRequest;
    private WeatherRepository weatherRepository;


    @Before
    public void setUpTest() throws Exception {
        weatherRequest = new WeatherRequest("Tallinn", "EE");
    }


    @Test
    public void getCurrentWeatherTest() {
        Weather response = GetCurrentTemperature.getCurrentTemperature(weatherRequest);

        assertNotNull(response);
    }

    @Test
    public void getThreeDaysWeatherTest() {
        List<Weather> response = ThreeDaysWeather.threeDaysWeather(weatherRequest);

        assertNotNull(response);
        assertEquals(3, response.size());

        for (Weather e : response) {
            assertNotNull(e.getCity());
        }

    }

    @Test
    public void ifMaxTemperatureIsMaxThanMinTemperatureTest() {
        List<Weather> response = ThreeDaysWeather.threeDaysWeather(weatherRequest);

        for (Weather day : response) {
            assertTrue(day.getHighestTemp() > day.getLowestTemp());
        }
    }
    @Test
    public void ifLatitudeAvailabeTest() {
        List<Weather> response = ThreeDaysWeather.threeDaysWeather(weatherRequest);

        for (Weather latitude : response) {
            assertTrue(latitude.getCoordLat() > -90);
            assertTrue(latitude.getCoordLat() < 90);
        }
    }
    @Test
    public void ifLongitudeAvailabeTest() {
        List<Weather> response = ThreeDaysWeather.threeDaysWeather(weatherRequest);

        for (Weather longitude : response) {
            assertTrue(longitude.getCoordLat() > -180);
            assertTrue(longitude.getCoordLat() < 180);
        }
    }



}