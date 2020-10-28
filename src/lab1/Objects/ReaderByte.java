package lab1.Objects;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class ReaderByte {

    public ReaderByte () {}

    public byte[] readFile_byte(String file_path) {
        try {
            File file = new File(file_path);
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            System.err.println("\n######\nException! Ошибка - не  удалось прочитать файл");
            System.err.println("Filepath = '" + file_path + "'\n######");
            return null;
        }
    }


    public boolean writeFile_byte(String file_path, byte[] array) {
        try (FileOutputStream stream = new FileOutputStream(file_path)) {
            stream.write(array);
            return true;
        } catch (IOException e) {
            System.err.println("\n######\nException! Ошибка - не  удалось записать в файл");
            System.err.println("Filepath = '" + file_path + "'\n######");
            return false;
        }
    }
}
