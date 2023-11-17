

    import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

    public class OldNew {
        public static void main(String[] args) {
            if (args.length != 2) {
                System.out.println("Using: java ReplaceTextInFile <input_file> <output_file>");
                System.exit(1);
            }

            String inputFile = args[0];
            String outputFile = args[1];

            replaceTextInFile(inputFile, outputFile);
            System.out.println("Заместването на текст приключи успешно.");
        }

        private static void replaceTextInFile(String inputFile, String outputFile) {
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

                String line;
                while ((line = reader.readLine()) != null) {

                    String updatedLine = line.replaceAll("\\bстаро\\b", "ново");
                    writer.write(updatedLine);
                    writer.newLine();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


