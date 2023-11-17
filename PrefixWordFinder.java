import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrefixWordFinder {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Using: java PrefixWordFinder <input_file> <prefix>");
            System.exit(1);
        }
        String inputFile = args[0];
        String prefix = args[1];

        List<String> matchingWords = findWordsWithPrefix(inputFile, prefix);
        System.out.println("Думи с префикс '" + prefix + "':");
        for (String word : matchingWords) {
            System.out.println(word);
        }
    }
    private static List<String> findWordsWithPrefix(String inputFile, String prefix) {
        List<String> matchingWords = new ArrayList<>();
        String regex = "\\b" + Pattern.quote(prefix) + "\\w*\\b";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    matchingWords.add(matcher.group());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return matchingWords;
    }
}
