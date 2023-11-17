import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLExtractor {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Using: java URLExtractor <text_file>");
            System.exit(1);
        }
        String textFilePath = args[0];

        List<String> extractedURLs = extractURLs(textFilePath);

        System.out.println("Extracted URL addresses:");
        for (String url : extractedURLs) {
            System.out.println(url);
        }
    }
    private static List<String> extractURLs(String textFilePath) {
        List<String> extractedURLs = new ArrayList<>();
        String regex = "\\b(?:https?|ftp)://\\S+\\b";
        try (BufferedReader reader = new BufferedReader(new FileReader(textFilePath))) {
            String line;
            Pattern pattern = Pattern.compile(regex);

            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    extractedURLs.add(matcher.group());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return extractedURLs;
    }
}
