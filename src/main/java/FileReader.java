import java.io.*;
import java.util.*;

public class FileReader {

    private static String inputFilePath = "C:/Users/Artjom Njumainen/IdeaProjects/Ilmaennustus/src/main/";

    public List<String> getDataFromConsole() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Choose option: 1 - a city from console; 2 - read from file: ");
        String choice = br.readLine();

        if(Objects.equals(choice, "1")) {
            try {
                System.out.print("Enter a city in format \"City, CC\" where CC is country code:");
                List<String> res = new ArrayList<>();
                res.add(br.readLine().trim());
                return res;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (Objects.equals(choice, "2")) {
            try {
                return getDataFromFile("input.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid input");
            return null;
        }

        System.out.println("Something went wrong!");
        return null;
    }


    public List<String> getDataFromFile(String fileName) throws IOException {

        List<String> countryInfo = new ArrayList<>();
        BufferedReader fileIn = new BufferedReader(new java.io.FileReader(inputFilePath + fileName));
        String line;
        while ((line = fileIn.readLine()) != null) {
            countryInfo.add(line);
        }
        fileIn.close();
        return countryInfo;
    }
}
