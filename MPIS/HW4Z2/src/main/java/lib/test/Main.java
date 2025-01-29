package lib.test;

import org.apache.commons.math3.special.Erf;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
//import sun.tools.jconsole.InternalDialog;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.abs;

public class Main extends ApplicationFrame {
    public Main(String title) {
        super(title);
    }
    public static double normalCDF(double z) {
        return 0.5 * (1 + Erf.erf(z / Math.sqrt(2)));
    }

    public static int add(int n, int k) {
        int temp = 0;
        for (int i = 0; i <= n; i++) {
            temp = i * 1 + (n - i) * (-1);
            if (temp == k) {
                return i;
            }
        }
        return temp;
    }


    public static void main(String[] args) {
        int n =30;

        XYSeries series = new XYSeries("P(X=k)");
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeriesCollection normalDataset = new XYSeriesCollection();
        XYPlot plot = new XYPlot();
        XYSeries normal = new XYSeries("normalCDF");

        double p = 0;
            for(int k = n*-1; k<=n; k+=2) {
                long result = CombinatoricsUtils.binomialCoefficient(n, add(n, k));
                System.out.println("result = " + result+" k = "+k);
                p+=  (result*Math.pow(0.5, n));
                double PX = (result*Math.pow(0.5, n));
                System.out.println("k = " + k+" P(X=k) = "+ PX+ " p = "+p);
                //series.add(k, p);
                normal.add(k, normalCDF(k));
        }
        //dataset.addSeries(series);

        p = 0;
        n=5;
        series = new XYSeries("F"+n);
        for(int k = n*-1; k<=n; k+=2) {
            long result = CombinatoricsUtils.binomialCoefficient(n, add(n, k));
            System.out.println("result = " + result+" k = "+k);
            p+=  (result*Math.pow(0.5, n));
            //double PX = (result*Math.pow(0.5, n));
            //System.out.println("k = " + k+" P(X=k) = "+ PX+ " p = "+p);
            series.add(k, p);
            //normal.add(k, normalCDF(k));
        }

        dataset.addSeries(series);
        p = 0;
        n=10;
        series = new XYSeries("F"+n);
        for(int k = n*-1; k<=n; k+=2) {
            long result = CombinatoricsUtils.binomialCoefficient(n, add(n, k));
            System.out.println("result = " + result+" k = "+k);
            p+=  (result*Math.pow(0.5, n));
            //double PX = (result*Math.pow(0.5, n));
            //System.out.println("k = " + k+" P(X=k) = "+ PX+ " p = "+p);
            series.add(k, p);
            //normal.add(k, normalCDF(k));
        }
        dataset.addSeries(series);

        p = 0;
        n=15;
        series = new XYSeries("F"+n);
        for(int k = n*-1; k<=n; k+=2) {
            long result = CombinatoricsUtils.binomialCoefficient(n, add(n, k));
            System.out.println("result = " + result+" k = "+k);
            p+=  (result*Math.pow(0.5, n));
            //double PX = (result*Math.pow(0.5, n));
            //System.out.println("k = " + k+" P(X=k) = "+ PX+ " p = "+p);
            series.add(k, p);
            //normal.add(k, normalCDF(k));
        }
        dataset.addSeries(series);

        p = 0;
        n=20;
        series = new XYSeries("F"+n);
        for(int k = n*-1; k<=n; k+=2) {
            long result = CombinatoricsUtils.binomialCoefficient(n, add(n, k));
            System.out.println("result = " + result+" k = "+k);
            p+=  (result*Math.pow(0.5, n));
            //double PX = (result*Math.pow(0.5, n));
            //System.out.println("k = " + k+" P(X=k) = "+ PX+ " p = "+p);
            series.add(k, p);
            //normal.add(k, normalCDF(k));
        }
        dataset.addSeries(series);

        p = 0;
        n=25;
        series = new XYSeries("F"+n);
        for(int k = n*-1; k<=n; k+=2) {
            long result = CombinatoricsUtils.binomialCoefficient(n, add(n, k));
            System.out.println("result = " + result+" k = "+k);
            p+=  (result*Math.pow(0.5, n));
            //double PX = (result*Math.pow(0.5, n));
            //System.out.println("k = " + k+" P(X=k) = "+ PX+ " p = "+p);
            series.add(k, p);
            //normal.add(k, normalCDF(k));
        }
        dataset.addSeries(series);

        p = 0;
        n=30;
        series = new XYSeries("F"+n);
        for(int k = n*-1; k<=n; k+=2) {
            long result = CombinatoricsUtils.binomialCoefficient(n, add(n, k));
            System.out.println("result = " + result+" k = "+k);
            p+=  (result*Math.pow(0.5, n));
            //double PX = (result*Math.pow(0.5, n));
            //System.out.println("k = " + k+" P(X=k) = "+ PX+ " p = "+p);
            series.add(k, p);
            //normal.add(k, normalCDF(k));
        }
        dataset.addSeries(series);
        normalDataset.addSeries(normal);
        plot.setDataset(0,dataset);
        plot.setDataset(1,normalDataset);
        plot.setRenderer(0,new XYLineAndShapeRenderer(true, false));
        plot.setRenderer(1,new XYLineAndShapeRenderer(true, false));

        NumberAxis xAxis = new NumberAxis("k");
        //xAxis.setAutoRangeIncludesZero(false);
        NumberAxis yAxis = new NumberAxis("propability");
        //yAxis.setAutoRangeIncludesZero(false);

        plot.setDomainAxis(xAxis);
        plot.setRangeAxis(yAxis);

        Main app = new Main("HW4_2 dla n = "+n);
        JFreeChart chart = new JFreeChart(
                "HW4_2 n = "+n,  // chart title
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
}