import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileListGenerator {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Using: java FileListGenerator <path_to_folder>");
            System.exit(1);
        }

        String folderPath = args[0];
        generateFileListFile(folderPath);
        System.out.println("Генерирането на списък на файловете и директориите приключи успешно.");
    }
    private static void generateFileListFile(String folderPath) {
        File folder = new File(folderPath);

        if (!folder.isDirectory()) {
            System.out.println("Указаният път не води до директория.");
            System.exit(1);
        }

        File fileListFile = new File("FileList.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileListFile))) {
            listFilesAndDirectories(folder, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void listFilesAndDirectories(File folder, BufferedWriter writer) throws IOException {
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                writer.write(file.getName());
                writer.newLine();
                if (file.isDirectory()) {
                    listFilesAndDirectories(file, writer);
                }
            }
        }
    }
}
