package lib.test;

import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.special.Erf;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.DefaultCategoryDataset;
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

    public static double function(int n){
        MersenneTwister random = new MersenneTwister();
        int position = 0;
        int positive = 0;
        double N = (double) n;
        for(int i = 0; i<=n; i++){
            if(position>0){
                positive++;
            }
            double temp = random.nextDouble();
            //System.out.println(temp);
            if(temp > 0.5) {
                position++;
            }else {
                position--;
            }
        }
        return positive/N;
    }

    public static void main(String[] args) {
        int n =100;

        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );

        double p = 0;
        double[] tab = new double[20];
        for(int k = 0; k<5000; k++) {
            //tab[k] = function(n);
            double result = function(n);
            if (result >= 0 && result < 0.05) {
                tab[0]++;
            } else if (result >= 0.05 && result < 0.10) {
                tab[1]++;
            } else if (result >= 0.10 && result < 0.15) {
                tab[2]++;
            } else if (result >= 0.15 && result < 0.20) {
                tab[3]++;
            } else if (result >= 0.20 && result < 0.25) {
                tab[4]++;
            } else if (result >= 0.25 && result < 0.30) {
                tab[5]++;
            } else if (result >= 0.30 && result < 0.35) {
                tab[6]++;
            } else if (result >= 0.35 && result < 0.40) {
                tab[7]++;
            } else if (result >= 0.40 && result < 0.45) {
                tab[8]++;
            } else if (result >= 0.45 && result < 0.50) {
                tab[9]++;
            } else if (result >= 0.50 && result < 0.55) {
                tab[10]++;
            } else if (result >= 0.55 && result < 0.60) {
                tab[11]++;
            } else if (result >= 0.60 && result < 0.65) {
                tab[12]++;
            } else if (result >= 0.65 && result < 0.70) {
                tab[13]++;
            } else if (result >= 0.70 && result < 0.75) {
                tab[14]++;
            } else if (result >= 0.75 && result < 0.80) {
                tab[15]++;
            } else if (result >= 0.80 && result < 0.85) {
                tab[16]++;
            } else if (result >= 0.85 && result < 0.90) {
                tab[17]++;
            } else if (result >= 0.90 && result < 0.95) {
                tab[18]++;
            } else if (result >= 0.95 && result <= 1.00) {
                tab[19]++;
            }
        }

        // Normalizacja - obliczamy PDF
        double[] pdf = new double[20];
        for (int i = 0; i < 20; i++) {
            pdf[i] = (double) tab[i] / (5000.0); // Normalizacja do PDF
        }

        dataset.addValue(pdf[0], "Pn", "0.05>Pn>=0");
        dataset.addValue(pdf[1], "Pn", "0.1>Pn>=0.05");
        dataset.addValue(pdf[2], "Pn", "0.15>Pn>=0.1");
        dataset.addValue(pdf[3], "Pn", "0.2>Pn>=0.15");
        dataset.addValue(pdf[4], "Pn", "0.25>Pn>=0.2");
        dataset.addValue(pdf[5], "Pn", "0.3>Pn>=0.25");
        dataset.addValue(pdf[6], "Pn", "0.35>Pn>=0.3");
        dataset.addValue(pdf[7], "Pn", "0.4>Pn>=0.35");
        dataset.addValue(pdf[8], "Pn", "0.45>Pn>=0.4");
        dataset.addValue(pdf[9], "Pn", "0.5>Pn>=0.45");
        dataset.addValue(pdf[10], "Pn", "0.55>Pn>=0.5");
        dataset.addValue(pdf[11], "Pn", "0.6>Pn>=0.55");
        dataset.addValue(pdf[12], "Pn", "0.65>Pn>=0.6");
        dataset.addValue(pdf[13], "Pn", "0.7>Pn>=0.65");
        dataset.addValue(pdf[14], "Pn", "0.75>Pn>=0.7");
        dataset.addValue(pdf[15], "Pn", "0.8>Pn>=0.75");
        dataset.addValue(pdf[16], "Pn", "0.85>Pn>=0.8");
        dataset.addValue(pdf[17], "Pn", "0.9>Pn>=0.85");
        dataset.addValue(pdf[18], "Pn", "0.95>Pn>=0.9");
        dataset.addValue(pdf[19], "Pn", "1.0>Pn>=0.95");

        double counter = 0;
        for(double i: pdf){
            counter+=i;
        }
        System.out.println(counter);

        Main app = new Main("HW4_3 Pn dla n = "+n);
        JFreeChart barChart = ChartFactory.createBarChart(
                "Pn dla n = "+n,
                "Pn categories",
                "Mean Pn",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        app.setContentPane(chartPanel);

        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setSize(800, 600);
        app.setLocationRelativeTo(null);
        app.setVisible(true);
    }
}