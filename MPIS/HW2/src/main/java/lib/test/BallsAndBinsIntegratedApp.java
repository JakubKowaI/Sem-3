package lib.test;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.apache.commons.math3.random.MersenneTwister;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.JFrame;

import java.util.HashMap;
import java.util.Map;

public class BallsAndBinsIntegratedApp extends ApplicationFrame {

    public BallsAndBinsIntegratedApp(String title) {
        super(title);
    }

    public static void main(String[] args) {
        int repetitions = 50;

        Map<Integer, Map<String, Double>> averages = new HashMap<>();
        for (int i = 1000;i<=100000;i+=1000) {
            averages.put(i, runSimulations(i, repetitions));
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.removeAllSeries();
        XYSeries series=null;

        // Bn, Un, Cn, Dn, Dn-Cn

        series = new XYSeries("Bn");
        for (Map.Entry<Integer, Map<String, Double>> entry : averages.entrySet()) {
            int n = entry.getKey();
            double value = entry.getValue().get("Bn");
            series.add(n, value);
        }
        dataset.addSeries(series);

        series = new XYSeries("Un");
        for (Map.Entry<Integer, Map<String, Double>> entry : averages.entrySet()) {
            int n = entry.getKey();
            double value = entry.getValue().get("Un");
            series.add(n, value);
        }
        dataset.addSeries(series);

        series = new XYSeries("Cn");
        for (Map.Entry<Integer, Map<String, Double>> entry : averages.entrySet()) {
            int n = entry.getKey();
            double value = entry.getValue().get("Cn");
            series.add(n, value);
        }
        dataset.addSeries(series);

        series = new XYSeries("Dn");
        for (Map.Entry<Integer, Map<String, Double>> entry : averages.entrySet()) {
            int n = entry.getKey();
            double value = entry.getValue().get("Dn");
            series.add(n, value);
        }
        dataset.addSeries(series);

        series = new XYSeries("Dn-Cn");
        for (Map.Entry<Integer, Map<String, Double>> entry : averages.entrySet()) {
            int n = entry.getKey();
            double value = entry.getValue().get("Dn-Cn");
            series.add(n, value);
        }
        dataset.addSeries(series);
/*
        // Bn/n, Bn/sqrt(n)

//        series = new XYSeries("Bn/n");
//        for (Map.Entry<Integer, Map<String, Double>> entry : averages.entrySet()) {
//            int n = entry.getKey();
//            double value = entry.getValue().get("Bn")/n;
//            series.add(n, value);
//        }
//        dataset.addSeries(series);
//
//        series = new XYSeries("Bn/sqrt(n)");
//        for (Map.Entry<Integer, Map<String, Double>> entry : averages.entrySet()) {
//            int n = entry.getKey();
//            double value = entry.getValue().get("Bn")/Math.sqrt(n);
//            series.add(n, value);
//        }
//        dataset.addSeries(series);

        // Un/n

//        series = new XYSeries("Un/n");
//        for (Map.Entry<Integer, Map<String, Double>> entry : averages.entrySet()) {
//            int n = entry.getKey();
//            double value = entry.getValue().get("Un") / n;
//            series.add(n, value);
//        }
//        dataset.addSeries(series);

        // Cn/n, Cn/n*log(n), Cn/n^2

//        series = new XYSeries("Cn/n");
//        for (Map.Entry<Integer, Map<String, Double>> entry : averages.entrySet()) {
//            int n = entry.getKey();
//            double value = entry.getValue().get("Cn") / n;
//            series.add(n, value);
//        }
//        dataset.addSeries(series);
//
//        series = new XYSeries("Cn/n*log(n)");
//        for (Map.Entry<Integer, Map<String, Double>> entry : averages.entrySet()) {
//            int n = entry.getKey();
//            double value = entry.getValue().get("Cn") / (n*Math.log(n));
//            series.add(n, value);
//        }
//        dataset.addSeries(series);
//
//        series = new XYSeries("Cn/n^2");
//        for (Map.Entry<Integer, Map<String, Double>> entry : averages.entrySet()) {
//            int n = entry.getKey();
//            double value = entry.getValue().get("Cn") / (n*n);
//            series.add(n, value);
//        }
//        dataset.addSeries(series);

        // Dn/n, Dn/n*log(n), Dn/n^2

//        series = new XYSeries("Dn/n");
//        for (Map.Entry<Integer, Map<String, Double>> entry : averages.entrySet()) {
//            int n = entry.getKey();
//            double value = entry.getValue().get("Dn") / n;
//            series.add(n, value);
//        }
//        dataset.addSeries(series);
//
//        series = new XYSeries("Dn/n*log(n)");
//        for (Map.Entry<Integer, Map<String, Double>> entry : averages.entrySet()) {
//            int n = entry.getKey();
//            double value = entry.getValue().get("Dn") / (n*Math.log(n));
//            series.add(n, value);
//        }
//        dataset.addSeries(series);
//
//        series = new XYSeries("Dn/n^2");
//        for (Map.Entry<Integer, Map<String, Double>> entry : averages.entrySet()) {
//            int n = entry.getKey();
//            double value = entry.getValue().get("Dn") / (n*n);
//            series.add(n, value);
//        }
//        dataset.addSeries(series);

        // Dn-Cn/n, Dn-Cn/n*log(n), Dn-Cn/n*log(log(n))

//        series = new XYSeries("Dn-Cn/n");
//        for (Map.Entry<Integer, Map<String, Double>> entry : averages.entrySet()) {
//            int n = entry.getKey();
//            double value = entry.getValue().get("Dn-Cn") / n;
//            series.add(n, value);
//        }
//        dataset.addSeries(series);
//
//        series = new XYSeries("Dn-Cn/n*log(n)");
//        for (Map.Entry<Integer, Map<String, Double>> entry : averages.entrySet()) {
//            int n = entry.getKey();
//            double value = entry.getValue().get("Dn-Cn") / (n*Math.log(n));
//            series.add(n, value);
//        }
//        dataset.addSeries(series);
//
//        series = new XYSeries("Dn-Cn/n*log(log(n))");
//        for (Map.Entry<Integer, Map<String, Double>> entry : averages.entrySet()) {
//            int n = entry.getKey();
//            double value = entry.getValue().get("Dn-Cn") / (n*Math.log(Math.log(n)));
//            series.add(n, value);
//        }
//        dataset.addSeries(series);

*/
        BallsAndBinsIntegratedApp app = new BallsAndBinsIntegratedApp("Balls and Bins Simulation");

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Balls and Bins Simulation",
                "n (Liczba urn)",
                "Wartosc",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        app.setContentPane(chartPanel);

        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setSize(800, 600);
        app.setLocationRelativeTo(null);
        app.setVisible(true);
    }

    private static Map<String, Double> runSimulations(int n, int repetitions) {
        double totalBn = 0, totalUn = 0, totalCn = 0, totalDn = 0, totalDnCn = 0;

        for (int i = 0; i < repetitions; i++) {
            int[] results = runExperiment(n);
            totalBn += results[0];
            totalUn += results[1];
            totalCn += results[2];
            totalDn += results[3];
            totalDnCn += results[4];
        }

        Map<String, Double> averages = new HashMap<>();
        averages.put("Bn", totalBn / repetitions);
        averages.put("Un", totalUn / repetitions);
        averages.put("Cn", totalCn / repetitions);
        averages.put("Dn", totalDn / repetitions);
        averages.put("Dn-Cn", totalDnCn / repetitions);

        return averages;
    }

    private static int[] runExperiment(int n) {
        MersenneTwister random = new MersenneTwister();

        int[] bins = new int[n];

        int Bn = 0, Untemp = n, Cn = 0, Dn = 0;
        int Un = -1;
        int balls = 0;
        boolean allTwo = false;
        int countC = 0;
        int countD = 0;

        while (true) {
            int bin = random.nextInt(n);
            balls++;

            if (bins[bin] == 0) {
                bins[bin]++;
                countC++;
                Untemp--;
                if(countC==n){
                    Cn = balls;
                }
            } else if (bins[bin] == 1) {
                if(Bn==0){
                    Bn = balls;
                }
                countD++;
                if(countD==n){
                    Dn = balls;
                    break;
                }
                bins[bin]++;
            } else if (balls==n-1){
                Un=Untemp;
            }
        }

        int[] results = new int[5];
        results[0] = Bn;
        results[1] = Un;
        results[2] = Cn;
        results[3] = Dn;
        results[4] = Dn - Cn;
        return results;
    }
}