import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Form  extends JFrame {

    private JPanel mainPanel;

    private Form() {

        ArrayList<ArrayList<Message>> dataSet = FileReader.readAllMessages();
        ArrayList<Message> teachSet = new ArrayList<>(), testSet = new ArrayList<>();
        Classifier classifier = new Classifier();
        ArrayList<Pair<Message, Double>> checkResults = new ArrayList<>();
        ContingencyTable contingencyTable = new ContingencyTable();

        for (int i = 0; i < dataSet.size(); i++) {

            teachSet.clear();
            testSet.clear();

            for (int j = 0; j < dataSet.size(); j++) {

                if (j != i) {

                    teachSet.addAll(dataSet.get(j));
                } else {

                    testSet.addAll(dataSet.get(j));
                }
            }

            classifier.learn(teachSet);

            checkResults = classifier.fullCheck(testSet);

            contingencyTable.add(new ContingencyTable(checkResults, classifier.probabilityBorder));
        }

        System.out.println("F measure = " + contingencyTable.getFMeasure());
        System.out.println("FP part = " + (int) contingencyTable.FP + " / " + (int) contingencyTable.getAllCases());
        System.out.println("Probability Border = " + classifier.probabilityBorder);

        for (Pair<Message, Double> result : checkResults) {

            System.out.println((result.getKey().isSpam ? "Spam | " : "Ham  | ") + result.getValue());
        }

        adjustDisplay();
    }


    private void adjustDisplay() {

        setContentPane(mainPanel);
        setSize(new Dimension(600, 600));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Title");
        setResizable(false);
        setLocationRelativeTo(null);
        //setVisible(true);
    }

    public static void main(String[] args) throws InterruptedException {
        new Form();
    }
}
