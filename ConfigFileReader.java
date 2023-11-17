
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigFileReader {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Using: java ConfigFileReader <input_file>");
            System.exit(1);
        }

        String inputFile = args[0];

        Map<String, String> configValues = readConfigFile(inputFile);

        System.out.println("Извлечени стойности на ключове:");
        for (Map.Entry<String, String> entry : configValues.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }

    private static Map<String, String> readConfigFile(String inputFile) {
        Map<String, String> configValues = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    configValues.put(key, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return configValues;
    }
}

