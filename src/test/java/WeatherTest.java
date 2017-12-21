
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class WeatherTest {
    private WeatherRequest weatherRequest;
    private ConnectionMaker connectionMaker;


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
            assertNotNull(e.getCityName());
        }

    }

    @Test
    public void ifMaxTemperatureIsHigherThanMinTemperatureTest() {
        List<Weather> response = ThreeDaysWeather.threeDaysWeather(weatherRequest);

        for (Weather day : response) {
            assertTrue(day.getMaxTemp() > day.getMinTemp());
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
    @Test
    public void testBothRequestsFilesCreatedMock() throws IOException {
        WeatherForPlaces weatherForPlaces = mock(WeatherForPlaces.class);
        FileWriter fileWriter = new FileWriter();
        List<String> places = new ArrayList<>();
        places.add("Tallinn,EE");
        List<Weather> response = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Weather rsp = new Weather();
            rsp.setCityName("Tallinn");
            rsp.setCode("EE");
            rsp.setCoordLon(0.000000);
            rsp.setCoordLat(0.000000);

            rsp.setDate(new Date());
            response.add(rsp);
        }

        File file = new File("C:/Users/Artjom Njumainen/IdeaProjects/Ilmaennustus/src/main/Tallinn.txt");
        if (file.exists() && !file.isDirectory()){
            file.delete();
        }

        when(weatherForPlaces.getWeatherForPlaces(places)).thenReturn(response);
        response = weatherForPlaces.getWeatherForPlaces(places);
        verify(weatherForPlaces, times(1)).getWeatherForPlaces(places);
        fileWriter.writeCityDataToFile(response);

        file = new File("C:/Users/Artjom Njumainen/IdeaProjects/Ilmaennustus/src/main/Tallinn.txt");

        assertTrue(file.exists());
    }


    /*@Test
    public void testGetCurrentTemp() throws IOException {

        FileReader mockGetDataFromFile = mock(FileReader.class);
        when(mockGetDataFromFile.getDataFromFile("input.txt")).thenReturn(places);

        FileReader fileReader = new FileReader(mockGetDataFromFile);
        assertEquals("[Tallinn, EE]", places);


    }*/


}