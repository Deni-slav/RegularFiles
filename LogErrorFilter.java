import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogErrorFilter {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Using: java LogErrorFilter <log_file>");
            System.exit(1);
        }

        String logFilePath = args[0];
        String errorPattern = "ERROR";

        filterErrorLines(logFilePath, errorPattern);
    }
    private static void filterErrorLines(String logFilePath, String errorPattern) {
        try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath))) {
            String line;
            Pattern pattern = Pattern.compile(errorPattern);

            System.out.println("Филтрирани редове с грешки:");
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
