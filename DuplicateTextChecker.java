import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DuplicateTextChecker {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Using: java DuplicateTextChecker <text_file>");
            System.exit(1);
        }
        String textFilePath = args[0];
        checkForDuplicates(textFilePath);
    }

    private static void checkForDuplicates(String textFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(textFilePath))) {
            String line;
            Map<String, Set<Integer>> occurrences = new HashMap<>();

            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;

                String normalizedLine = line.toLowerCase();

                if (occurrences.containsKey(normalizedLine)) {
                    occurrences.get(normalizedLine).add(lineNumber);
                } else {
                    Set<Integer> lineNumbers = new HashSet<>();
                    lineNumbers.add(lineNumber);
                    occurrences.put(normalizedLine, lineNumbers);
                }
            }

            for (Map.Entry<String, Set<Integer>> entry : occurrences.entrySet()) {
                Set<Integer> lineNumbers = entry.getValue();
                if (lineNumbers.size() > 1) {
                    System.out.println("Дублиран текст: \"" + entry.getKey() + "\", Линии: " + lineNumbers);
                }
            }

            System.out.println("Проверката за дублиран текст приключи успешно.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
