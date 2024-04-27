//This code is a modified version of getPRCArea() from Weka to run separately.

import java.util.ArrayList;

public class weka_AUPRC {

    public static void main(String[] args) {

        // Set the probs, labels and classIndex(Just for the modified codes)
        int classIndex = 1;
        double[] score = {0.9, 0.8, 0.7, 0.6, 0.5};
        int[] label = {1, 0, 1, 0, 0};

        calculate(classIndex, score, label);
    }

    public static void calculate(int classIndex, double[] probs, int[] labels) {
        // Sorting probabilities in ascending order along with labels
        for (int i = 0; i < probs.length - 1; i++) {
            for (int j = 0; j < probs.length - i - 1; j++) {
                if (probs[j] > probs[j + 1]) {
                    // Swap probabilities
                    double tempProb = probs[j];
                    probs[j] = probs[j + 1];
                    probs[j + 1] = tempProb;
                    // Swap labels accordingly
                    int tempLabel = labels[j];
                    labels[j] = labels[j + 1];
                    labels[j + 1] = tempLabel;
                }
            }
        }

        // Now probs[] and labels[] are sorted in ascending order of probabilities

        double totPos = 0, totNeg = 0;
        // Get distribution of positive/negatives
        for (int i = 0; i < probs.length; i++) {
            int pred = labels[i];
            if (pred == classIndex) {
                totPos += 1;
            } else {
                totNeg += 1;
            }
        }

        double threshold = 0;
        double cumulativePos = 0;
        double cumulativeNeg = 0;

        ArrayList<Double> tps = new ArrayList<>();
        ArrayList<Double> fps = new ArrayList<>();
        double fns = 0;
        double tns = 0;
        double tp = totPos;
        double fp = totNeg;
        for (int i = 0; i < probs.length; i++) {

            if ((i == 0) || (probs[i] > threshold)) {

                tps.add(tp - cumulativePos);
                fps.add(fp - cumulativeNeg);

                tp = tp - cumulativePos;
                fp = fp - cumulativeNeg;

                threshold = probs[i];
                cumulativePos = 0;
                cumulativeNeg = 0;
            }

            int pred = labels[i];

            if (pred == classIndex) {
                cumulativePos += 1;
            } else {
                cumulativeNeg += 1;
            }
        }

        fns = totPos - tps.get(tps.size() - 1);
        tns = totNeg - fps.get(fps.size() - 1);

        if (fns != totPos || tns != totNeg) {
            tps.add(0.0);
            fps.add(0.0);
        }

        ArrayList<Double> precision = new ArrayList<>();
        ArrayList<Double> recall = new ArrayList<>();
        for (int i = 0; i < tps.size(); i++) {
            precision.add(tps.get(i) / (tps.get(i) + fps.get(i)));
            recall.add(tps.get(i) / totPos);
        }

        // Calculate the AUPRC
        int n = tps.size();
        double[] pVals = new double[n];
        double[] rVals = new double[n];
        for (int i = 0; i < n; i++) {
            pVals[i] = precision.get(i);
            rVals[i] = recall.get(i);
        }

        double area = 0;
        double xlast = rVals[n - 1];

        // start from the first real p/r pair (not the artificial zero point)
        for (int i = n - 2; i >= 0; i--) {
            double recallDelta = rVals[i] - xlast;
            area += (pVals[i] * recallDelta);

            xlast = rVals[i];
        }
        System.out.println("AUPRC: " + area);
    }
}