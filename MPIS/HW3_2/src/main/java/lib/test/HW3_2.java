package lib.test;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.apache.commons.math3.random.MersenneTwister;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.JFrame;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;
import java.util.Map;

public class HW3_2 extends ApplicationFrame {

    public HW3_2(String title) {
        super(title);
    }

    static XYSeries Ln1Series = new XYSeries("cmp(n) points");
    static XYSeries Ln2Series = new XYSeries("s(n) points");

    public static void main(String[] args) {
        int repetitions = 50;


        Map<Integer, Map<String, Double>> averages = new HashMap<>();
        for (int i = 100;i<=10000;i+=100) {
            averages.put(i, runSimulations(i, repetitions));
        }




        XYSeriesCollection dots = new XYSeriesCollection();
        //dots.addSeries(Ln1Series);
        //dots.addSeries(Ln2Series);
        XYDataset dotsdataset = dots;
        XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer(false, true);
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
        XYSeries series = null;
        //dataset.addSeries(Ln1Series);
        //dataset.addSeries(Ln2Series);

        //cmp, s

//        series = new XYSeries("cmp");
//        for (Map.Entry<Integer, Map<String, Double>> entry : averages.entrySet()) {
//            int n = entry.getKey();
//            double value = entry.getValue().get("cmp");
//            series.add(n, value);
//        }
//        dataset.addSeries(series);

//        series = new XYSeries("s");
//        for (Map.Entry<Integer, Map<String, Double>> entry : averages.entrySet()) {
//            int n = entry.getKey();
//            double value = entry.getValue().get("s");
//            series.add(n, value);
//        }
//        dataset.addSeries(series);

//        series = new XYSeries("s(n)/n");
//        for (Map.Entry<Integer, Map<String, Double>> entry : averages.entrySet()) {
//            int n = entry.getKey();
//            double value = entry.getValue().get("s");
//            series.add(n, value/n);
//        }
//        dataset.addSeries(series);

        series = new XYSeries("s(n)/n^2");
        for (Map.Entry<Integer, Map<String, Double>> entry : averages.entrySet()) {
            int n = entry.getKey();
            double value = entry.getValue().get("s");
            series.add(n, value/(n*n));
        }
        dataset.addSeries(series);

//        series = new XYSeries("cmp(n)/n");
//        for (Map.Entry<Integer, Map<String, Double>> entry : averages.entrySet()) {
//            int n = entry.getKey();
//            double value = entry.getValue().get("cmp");
//            series.add(n, value/n);
//        }
//        dataset.addSeries(series);

//        series = new XYSeries("cmp(n)/n^2");
//        for (Map.Entry<Integer, Map<String, Double>> entry : averages.entrySet()) {
//            int n = entry.getKey();
//            double value = entry.getValue().get("cmp");
//            series.add(n, value/(n*n));
//        }
//        dataset.addSeries(series);




        HW3_2 app = new HW3_2("Balls and Bins Simulation");

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
        double totalCMP = 0, totalS = 0;

        for (int i = 0; i < repetitions; i++) {
            int[] results = runExperiment(n);
            //Ln1Series.add(n, results[0]);
            totalCMP += results[0];
            //Ln2Series.add(n, results[1]);
            totalS += results[1];

        }

        Map<String, Double> averages = new HashMap<>();
        averages.put("cmp", totalCMP / repetitions);
        averages.put("s", totalS / repetitions);

        return averages;
    }


    private static int[] runExperiment(int n) {
        MersenneTwister random = new MersenneTwister();

        int[] A = new int[n];

        for(int i = 1; i <= n; i++) {
            if(A[random.nextInt(n)] == 0) {
                A[random.nextInt(n)] = i;
            }else{
                i--;
            }
        }

        int cmp=0;
        int s=0;

        for(int j = 1;j<n;j++){
            int key = A[j];

            int i = j-1;
            do{
                cmp++;
                if(A[i]>key){
                A[i+1] = A[i];
                s++;
                i = i-1;
                }
            }while(i>=0 && A[i]>key);
            A[i+1] = key;
        }
        int[] results = new int[2];
        results[0] = cmp;
        results[1] = s;

        return results;
    }
}


