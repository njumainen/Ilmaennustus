import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WeatherTest {
    private WeatherRepository weatherRepository;
    private WeatherRequest weatherRequest;


    @Before
    public void setUpTest() throws Exception {
        weatherRepository = new WeatherRepository();
        weatherRequest = new WeatherRequest();
    }


    @Test
    public void getCurrentWeatherTest(){
        //Weather response = WeatherRepository.getCurrentTemperature(weatherRequest);
        //assertNotNull(response);
    }

    @Test
    public void getThreeDaysWeatherTest (){

    }

    @Test
    public void getHighestTemperatureTest(){
        assertTrue(false);
    }

    @Test
    public void getLowestTemperatureTest(){
        assertTrue(false);
    }

    @Test
    public void getGeoTest(){
        assertTrue(false);
    }
}
