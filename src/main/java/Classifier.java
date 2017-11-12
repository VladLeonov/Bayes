import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class Classifier {

    private double spamProbability, hamProbability;
    double probabilityBorder;
    HashMap<Integer, Double> wordSprobabilities;

    void learn(ArrayList<Message> messages) {

        //TODO
    }

    double check(Message message) {

        return 0;//TODO
    }

    ArrayList<Pair<Message, Double>> fullCheck(ArrayList<Message> messages) {

        ArrayList<Pair<Message, Double>> checkResult = new ArrayList<>();

        for (Message message : messages) {

            checkResult.add(new Pair<>(message, check(message)));
        }

        return checkResult;
    }
}
