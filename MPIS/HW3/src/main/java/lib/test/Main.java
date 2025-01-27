package lib.test;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.apache.commons.math3.random.MersenneTwister;
import org.jfree.data.function.Function2D;
import org.jfree.data.function.LineFunction2D;
import org.jfree.data.statistics.Regression;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.JFrame;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;
import java.util.Map;

public class Main extends ApplicationFrame {

    public Main(String title) {
        super(title);
    }
    static XYSeries Ln1Series = new XYSeries("Ln1 points");
    static XYSeries Ln2Series = new XYSeries("Ln2 points");
    public static void main(String[] args) {
        int repetitions = 50;

        /*
        Map<Integer, Map<String, Double>> averages = new HashMap<>();
        for (int i = 1000;i<=100000;i+=1000) {
            averages.put(i, runSimulations(i, repetitions));
        }
        */



        Map<Integer, Map<String, Double>> HW3 = new HashMap<>();
        for (int i = 10000;i<=1000000;i+=10000) {
            HW3.put(i, runSimulationsHW3(i, repetitions));
        }

        XYSeriesCollection dots = new XYSeriesCollection();
        dots.addSeries(Ln1Series);
        dots.addSeries(Ln2Series);
        XYDataset dotsdataset = dots;
        XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer(false,true);
        renderer1.setSeriesShape(0, new Ellipse2D.Double(-1, -1, 2, 2));

        NumberAxis xAxis = new NumberAxis("n");
        xAxis.setAutoRangeIncludesZero(false);
        NumberAxis yAxis = new NumberAxis("value");
        yAxis.setAutoRangeIncludesZero(false);

        XYPlot plot = new XYPlot();
        plot.setDomainAxis(xAxis);
        plot.setRangeAxis(yAxis);

        plot.setDataset(1, dotsdataset);
        plot.setRenderer(1, renderer1);
        renderer1.setSeriesFillPaint(0, Color.DARK_GRAY);


        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.removeAllSeries();
        XYSeries series=null;
        //dataset.addSeries(Ln1Series);
        //dataset.addSeries(Ln2Series);
        // Bn, Un, Cn, Dn, Dn-Cn
/*
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
 */
        series = new XYSeries("Ln(1)");
        for (Map.Entry<Integer, Map<String, Double>> entry : HW3.entrySet()) {
            int n = entry.getKey();
            double value = entry.getValue().get("Ln1");
            series.add(n, value);
        }
        dataset.addSeries(series);

        series = new XYSeries("Ln(2)");
        for (Map.Entry<Integer, Map<String, Double>> entry : HW3.entrySet()) {
            int n = entry.getKey();
            double value = entry.getValue().get("Ln2");
            series.add(n, value);
        }
        dataset.addSeries(series);

        series = new XYSeries("ln(1)/f1(n)");
        for (Map.Entry<Integer, Map<String, Double>> entry : HW3.entrySet()) {
            int n = entry.getKey();
            double value = entry.getValue().get("Ln1");
            series.add(n, value/(Math.log(n)/Math.log(Math.log(n))));
        }
        dataset.addSeries(series);

        series = new XYSeries("ln(2)/f2(n)");
        for (Map.Entry<Integer, Map<String, Double>> entry : HW3.entrySet()) {
            int n = entry.getKey();
            double value = entry.getValue().get("Ln2");
            series.add(n, value/(Math.log(Math.log(n))/Math.log(2)));
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
        Main app = new Main("Balls and Bins Simulation");

        /*JFreeChart chart = ChartFactory.createXYLineChart(
                "Balls and Bins Simulation",
                "n (Liczba urn)",
                "Wartosc",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );*/
        plot.setDataset(0, dataset);
        plot.setRenderer(0, new XYLineAndShapeRenderer(true, false));

        JFreeChart chart = new JFreeChart(
                "Bins and Balls Simulation",
                null,  // null means to use default font
                plot,  // combination chart as CategoryPlot
                true); // legend

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
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


    private static Map<String, Double> runSimulationsHW3(int n, int repetitions) {
        int Ln1 = 0, Ln2 = 0;

        for (int i = 0; i < repetitions; i++) {
            int[] temp = runExperimentHW3(n, 2);

            Ln1Series.add(n, temp[0]);
            Ln1 += temp[0];

            Ln2Series.add(n, temp[1]);
            Ln2 += temp[1];
        }

        Map<String, Double> averages = new HashMap<>();
        averages.put("Ln1", (double) Ln1 / repetitions);
        averages.put("Ln2", (double) Ln2 / repetitions);

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


    private static int[] runExperimentHW3(int n,int d) {
        MersenneTwister random = new MersenneTwister();

        int[] bins1 = new int[n];
        int[] bins2 = new int[n];
        int[] holes = new int[d];

        int Ln1 = 0, Ln2 = 0;

        for(int i=0; i<n; i++){
            int hole = random.nextInt(n);
            int min=0;
            bins1[hole]++;
            if(bins1[hole]>=Ln1){
                Ln1=bins1[hole];
            }
            for(int j=0; j<d; j++){
                int temp = random.nextInt(n);
                if(j==0){
                    min = bins2[temp];
                }
                if(bins2[temp]<=min){
                    hole = temp;
                    min = bins2[temp];
                }
            }
            bins2[hole]++;

            if(bins2[hole]>=Ln2){
                Ln2=bins2[hole];
            }
        }


        int[] results = new int[2];
        results[0] = Ln1;
        results[1] = Ln2;
        return results;
    }
}