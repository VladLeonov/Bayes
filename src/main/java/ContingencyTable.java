import javafx.util.Pair;

import java.util.ArrayList;

class ContingencyTable {

    double TP = 0, FP = 0, FN = 0, TN = 0;

    ContingencyTable() {}

    ContingencyTable(ArrayList<Pair<Message, Double>> probabilities, double probabilityBorder) {

        for (Pair<Message, Double> probability : probabilities) {

            if (probability.getKey().isSpam) {
                if (probability.getValue() > probabilityBorder) {
                    TP++;
                } else {
                    FN++;
                }
            } else {
                if (probability.getValue() > probabilityBorder) {
                    FP++;
                } else {
                    TN++;
                }
            }
        }
    }

    void add(ContingencyTable other) {

        TP += other.TP;
        FP += other.FP;
        FN += other.FN;
        TN += other.TN;
    }

    double getFMeasure() {

        double recall0 = TP / (TP + FN);
        double precision0 = TP / (TP + FP);

        double recall1 = TN / (TN + FP);
        double precision1 = TN / (TN + FN);

        double recall = (recall0 + recall1) / 2;
        double precision = (precision0 + precision1) / 2;

        return  2 * (recall * precision) / (recall + precision);
    }

    double getAllCases() {

        return TP + FP + FN + TN;
    }
}
