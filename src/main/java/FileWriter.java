import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileWriter {

    private static String outputDir = "C:/Users/Artjom Njumainen/IdeaProjects/Ilmaennustus/src/main/";

    static void writeToFile(String fileName, String cityData) throws IOException {
        String outputFile = outputDir + fileName;
        BufferedWriter bfw = new BufferedWriter(new java.io.FileWriter(outputFile));
        bfw.write(cityData);
        bfw.close();


    }
    public static void writeCityDataToFile(List<Weather> weather) throws IOException{
        Map<String, List<Weather>> groupedList = weather.stream().collect(Collectors.groupingBy(Weather::getCityName));

        for (Map.Entry<String, List<Weather>> group : groupedList.entrySet()){
            String fileName = group.getKey() + ".txt";
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < group.getValue().size(); i++) {
                Weather element = group.getValue().get(i);
                if (i == 0) {
                    builder.append(Calendar.getInstance().getTime()+ " \n");
                    builder.append(element.toString() + " \n");
                } else {
                    builder.append(element.getDate() + " \n");
                    builder.append("Minimum temperature: " + String.valueOf(element.getMinTemp()) + " \n");
                    builder.append("Maximum temperature: " + String.valueOf(element.getMaxTemp()) + " \n");
                }
            }
            writeToFile(fileName, builder.toString());
        }
    }
}
