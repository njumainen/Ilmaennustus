import java.io.*;
import java.util.List;

public class Main {
    //private static String inputFile = "C:/Users/Artjom Njumainen/IdeaProjects/Ilmaennustus/src/main/input.txt";
    //private static String outputFile = "C:/Users/Artjom Njumainen/IdeaProjects/Ilmaennustus/src/main/output.txt";


    public static void main(String[] args) {

        try {

            WeatherForPlaces weatherForPlaces = new WeatherForPlaces();
            FileReader fileReader = new FileReader();
            List<String> places = fileReader.getDataFromConsole();

            List<Weather> weather = weatherForPlaces.getWeatherForPlaces(places);
            FileWriter fileWriter = new FileWriter();
            fileWriter.writeCityDataToFile(weather);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

   /* public static void main(String[] args) {
        Scanner scr = null;
        BufferedWriter bfw = null;
        FileWriter fwr = null;
        try {

            WeatherRequest request = null;
            scr  = new Scanner(new InputStreamReader(System.in));

            label:
            while (true) {
                System.out.print("Choose option: 1 - a city from console; 2 - read from file: ");
                String choice = scr.next();
                List<String> cityData = null;

                switch (choice) {
                    case "1":
                        scr = new Scanner(System.in);
                        System.out.println("Please insert city name: ");
                        String city = scr.nextLine();
                        System.out.println("Please insert city code: ");
                        String code = scr.nextLine();

                        request = new WeatherRequest(city.toUpperCase(), code.toUpperCase());
                        break label;

                    case "2":
                        BufferedReader fileWithData = new BufferedReader(new FileReader(inputFile));
                        String line;
                        while ((line = fileWithData.readLine()) != null) {
                            cityData = Arrays.asList(line.split("\\s*,\\s*"));
                            request = new WeatherRequest(cityData.get(0), cityData.get(1));
                        }
                        fileWithData.close();
                        break label;
                    default:
                        System.out.println("Error!");
                        continue;
                }
            }

            fwr = new FileWriter(outputFile);
            bfw = new BufferedWriter(new FileWriter(outputFile));
            List<Weather> response = ThreeDaysWeather.threeDaysWeather(request);
            for (Weather element : response) {
                bfw.write(element.toString());
                bfw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (scr  != null) {
                try {
                    scr .close();
                    if (bfw != null)
                        bfw.close();

                    if (fwr != null)
                        fwr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/
}
