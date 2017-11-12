import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public abstract class FileReader {

    private FileReader() {}

    public static Message readData(String fileName) {

        ArrayList<Integer> title = new ArrayList<>();
        ArrayList<Integer> body = new ArrayList<>();

        try {

            Scanner fileScanner = new Scanner(new File(fileName)).useLocale(Locale.US);

            Scanner lineScanner = new Scanner(fileScanner.nextLine());
            lineScanner.next();

            while (lineScanner.hasNext()) {

                title.add(lineScanner.nextInt());
            }

            fileScanner.nextLine();
            lineScanner = new Scanner(fileScanner.nextLine());

            while (lineScanner.hasNext()) {

                body.add(lineScanner.nextInt());
            }

            fileScanner.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        return new Message(fileName.contains("spmsg"), title, body);
    }
}
