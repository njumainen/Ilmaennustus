import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class WeatherTest {
    private WeatherRequest weatherRequest;
    private WeatherRepository weatherRepository;


    @Before
    public void setUpTest() throws Exception {
        weatherRequest = new WeatherRequest();
    }


    @Test
    public void getCurrentWeatherTest() {
        Weather response = WeatherRepository.getCurrentTemperature(weatherRequest);

        assertNotNull(response);
    }

    @Test
    public void getThreeDaysWeatherTest() {
        List<Weather> response = WeatherRepository.threeDaysWeather(weatherRequest);

        assertNotNull(response);
        assertEquals(3, response.size());

        for (Weather e : response) {
            assertNotNull(e.getCity());
        }

    }


    @Test
    public void ifMaxTemperatureIsHigherThanMinTemperatureTest() {
        List<Weather> response = weatherRepository.threeDaysWeather(weatherRequest);

        for (Weather day : response) {
            assertTrue(day.getHighestTemp() > day.getLowestTemp());
        }
    }

}