import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

class Classifier {

    private double spamProbability, hamProbability;
    double probabilityBorder;
    private HashMap<Integer, Double> wordSprobabilities;

    void learn(ArrayList<Message> messages) {

        //TODO
    }

    private double check(Message message) {

        ArrayList<Integer> words = new ArrayList<>();
        words.addAll(message.title);
        words.addAll(message.body);

        double p1 = 1, p2 = 1, pBuffer;
        for (int word : words) {

            if (wordSprobabilities.containsKey(word)) {

                pBuffer = wordSprobabilities.get(word);
                p1 *= pBuffer;
                p2 *= 1 - pBuffer;
            }
        }

        return p1 / (p1 + p2);
    }

    ArrayList<Pair<Message, Double>> fullCheck(ArrayList<Message> messages) {

        ArrayList<Pair<Message, Double>> checkResult = new ArrayList<>();

        for (Message message : messages) {

            checkResult.add(new Pair<>(message, check(message)));
        }

        return checkResult;
    }
}
