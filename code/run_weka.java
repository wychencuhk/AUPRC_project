import java.util.ArrayList;

public class weka_AUPRC {

    public static void main(String[] args) {

        // Set the probs, labels and classIndex(Just for the modified codes)
        int classIndex = 1;
        double[] probs = {0.9, 0.8, 0.7, 0.6, 0.5};
        int[] labels = {1, 0, 1, 0, 0};

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

        int[] sorted = {4, 3, 2, 1, 0};

        double threshold = -10;
        double cumulativePos = 0;
        double cumulativeNeg = 0;

        ArrayList<Double> tps = new ArrayList<>();
        ArrayList<Double> fps = new ArrayList<>();
        ArrayList<Double> thresholds = new ArrayList<>();
        double fns = 0;
        double tns = 0;
        double tp = totPos;
        double fp = totNeg;
        for (int i = 0; i < sorted.length; i++) {

            if ((i == 0) || (probs[sorted[i]] > threshold)) {

                tps.add(tp - cumulativePos);
                fps.add(fp - cumulativeNeg);

                tp = tp - cumulativePos;
                fp = fp - cumulativeNeg;

                threshold = probs[sorted[i]];
                thresholds.add(threshold);
                cumulativePos = 0;
                cumulativeNeg = 0;

                if (i == sorted.length - 1) {
                    break;
                }
            }

            int pred = labels[sorted[i]];

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
            threshold = probs[sorted[sorted.length - 1]] + 10e-6;
            thresholds.add(threshold);
        }

        ArrayList<Double> tpr = new ArrayList<>();
        ArrayList<Double> fpr = new ArrayList<>();
        ArrayList<Double> precision = new ArrayList<>();
        ArrayList<Double> recall = new ArrayList<>();
        for (int i = 0; i < tps.size(); i++) {
            if (0 == (totPos)) {
                tpr.add(Double.NaN);
            } else {
                tpr.add(tps.get(i) / totPos);
            }
            if (0 == (totNeg)) {
                fpr.add(Double.NaN);
            } else {
                fpr.add(fps.get(i) / (totNeg));
            }
            if (0 == (tps.get(i) + fps.get(i))) {
                precision.add(Double.NaN);
            } else {
                precision.add(tps.get(i) / (tps.get(i) + fps.get(i)));
            }
            if (0 == (totPos)) {
                recall.add(Double.NaN);
            } else {
                recall.add(tps.get(i) / totPos);
            }
        }

        // Calculate the AUPRC
        int n = tps.size();
        double[] pVals = new double[n];
        double[] rVals = new double[n];
        for (int i = 0; i < n; i++) {
            pVals[i] = precision.get(i);
            rVals[i] = recall.get(i);
        }

        double area = 0.0;

        n = precision.size();

        area = 0;
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
