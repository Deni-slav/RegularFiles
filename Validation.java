
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

    public class Validation {
        public static void main(String[] args) throws FileNotFoundException {
            if (args.length != 1) {
                System.out.println("Using: java DateValidator <input_file>");
                System.exit(1);
            }

            String inputFile = args[0];

            validateDates(inputFile);
        }
        private static void validateDates(String inputFile) throws FileNotFoundException {
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                String line;
                List<String> validDates = new ArrayList<>();
                List<String> invalidDates = new ArrayList<>();
                Pattern datePattern = Pattern.compile("\\b(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}\\b");

                while ((line = reader.readLine()) != null) {
                    Matcher matcher = datePattern.matcher(line);

                    if (matcher.matches()) {
                        validDates.add(line);
                    } else {
                        invalidDates.add(line);
                    }
                }
                System.out.println("Валидни дати:");
                for (String validDate : validDates) {
                    System.out.println(validDate);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }




