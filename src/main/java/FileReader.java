import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

abstract class FileReader {

    private FileReader() {}

    private static Message readMessage(File file) {

        ArrayList<Integer> title = new ArrayList<>();
        ArrayList<Integer> body = new ArrayList<>();

        try {

            Scanner fileScanner = new Scanner(file).useLocale(Locale.US);

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

        return new Message(file.getName().contains("spmsg"), title, body);
    }

    static ArrayList<ArrayList<Message>> readAllMessages() {

        ArrayList<ArrayList<Message>> allData = new ArrayList<>();
        ArrayList<Message> dataGroup;

        File[] folders = new File(System.getProperty("user.dir") + "/src/main/res").listFiles();
        File[] files;

        for (File folder : folders != null ? folders : new File[0]) {

            dataGroup = new ArrayList<>();

            files = folder.listFiles();
            for (File file : files != null ? files : new File[0]) {

                dataGroup.add(readMessage(file));
            }

            allData.add(dataGroup);
        }

        return allData;
    }
}
