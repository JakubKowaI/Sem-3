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

public class HW3_3 extends ApplicationFrame {

    public HW3_3(String title) {
        super(title);
    }

    static XYSeries Ln1Series = new XYSeries("Required rounds");
    static XYSeries Ln2Series = new XYSeries("s(n) points");

    public static void main(String[] args) {
        int repetitions = 50;
        double probability = 0.1;

        Map<Integer, Map<String, Double>> averages = new HashMap<>();
        for (int i = 100;i<=10000;i+=100) {
            averages.put(i, runSimulations(i, repetitions,probability));
        }

        XYSeriesCollection dots = new XYSeriesCollection();
        dots.addSeries(Ln1Series);
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

        series = new XYSeries("Rounds");
        for (Map.Entry<Integer, Map<String, Double>> entry : averages.entrySet()) {
            int n = entry.getKey();
            double value = entry.getValue().get("T");
            series.add(n, value/Math.log(n));
        }
        dataset.addSeries(series);

        HW3_3 app = new HW3_3("Communication");


        plot.setDataset(0, dataset);
        plot.setRenderer(0, new XYLineAndShapeRenderer(true, false));

        JFreeChart chart = new JFreeChart(
                "Rounds required for every client to get a message with "+probability*100+"% chance",
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

    private static Map<String, Double> runSimulations(int n, int repetitions,double p) {
        double totalT=0;

        for (int i = 0; i < repetitions; i++) {
            int[] results = runExperiment(n,p);
            Ln1Series.add(n, results[0]);


            totalT += results[0];

        }

        Map<String, Double> averages = new HashMap<>();
        averages.put("T", totalT / repetitions);


        return averages;
    }


    private static int[] runExperiment(int n,double p) {
        MersenneTwister random = new MersenneTwister();
        int[] table = new int[n];
        int counter = 0;
        int T = 0;

        do{
            for(int i = 0; i < n; i++) {
                if(table[i] == 0) {
                    if(random.nextDouble() < p) {
                        table[i] = 1;
                        counter++;
                    }
                }
            }
            T++;
        }while(counter!=n);

    int[] results = new int[1];
    results[0] = T;
        return results;
    }
}


