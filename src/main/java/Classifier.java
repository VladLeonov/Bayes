import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class Classifier {

    private double spamProbability, hamProbability;
    double probabilityBorder;
    private HashMap<Integer, Double> wordsProbabilities = new HashMap<>();

    void learn(ArrayList<Message> messages) {

        int spamNumber = 0, hamNumber = 0;
        HashSet<Integer> words = new HashSet<>();

        for (Message message : messages) {

            if (message.isSpam) {
                spamNumber++;
            } else {
                hamNumber++;
            }

            words.addAll(message.title);
            words.addAll(message.body);
        }

        spamProbability = (double) spamNumber / (spamNumber + hamNumber);
        hamProbability = (double) hamNumber / (spamNumber + hamNumber);

        HashMap<Integer, Integer> spamCounter = new HashMap<>(), hamCounter = new HashMap<>();

        for (int word : words) {

            spamCounter.put(word, 0);
            hamCounter.put(word, 0);
        }

        for (Message message : messages) {

            words.clear();
            words.addAll(message.title);
            words.addAll(message.body);

            if (message.isSpam) {

                for (int word : words) {

                    spamCounter.put(word, spamCounter.get(word) + 1);
                }
            } else {

                for (int word : words) {

                    hamCounter.put(word, hamCounter.get(word) + 1);
                }
            }
        }

        wordsProbabilities.clear();

        double wordSpamProbability, wordHamProbability;
        for (int word : spamCounter.keySet()) {

            wordSpamProbability = (double) spamCounter.get(word) / spamNumber;
            wordHamProbability = (double) hamCounter.get(word) / hamNumber;
            wordsProbabilities.put(word, wordSpamProbability * spamProbability
                    / (wordSpamProbability * spamProbability + wordHamProbability * hamProbability));
        }

        probabilityBorder = 0.5;//TODO
    }

    private double check(Message message) {

        ArrayList<Integer> words = new ArrayList<>();
        words.addAll(message.title);
        words.addAll(message.body);

        double p1 = 1, p2 = 1, pBuffer;
        for (int word : words) {

            if (wordsProbabilities.containsKey(word)) {

                pBuffer = wordsProbabilities.get(word);
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
