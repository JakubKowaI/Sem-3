package lib.test;

import org.apache.commons.math3.random.MersenneTwister;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.*;
import java.awt.*;

public class HW4Z3 extends JFrame {
    public HW4Z3(String title) {
        super(title);
    }

    public static double function(int n) {
        MersenneTwister random = new MersenneTwister();
        int position = 0;
        int positive = 0;
        for (int i = 0; i <= n; i++) {
            if (position > 0) {
                positive++;
            }
            double temp = random.nextDouble();
            position += (temp > 0.5) ? 1 : -1;
        }
        return (double) positive / n;
    }

    public static XYSeries arcSinPDFSeries(int points) {
        XYSeries series = new XYSeries("ArcSin PDF");
        for (int i = 1; i < points; i++) { // Avoid x = 0, x = 1 (division by zero)
            double x = i / (double) points;
            double y = 1.0 / (Math.PI * Math.sqrt(x * (1 - x)));
            series.add(x, y);
        }
        return series;
    }

    public static void main(String[] args) {
        int n = 10000;
        int samples = 5000;
        int bins = 20;

        double[] values = new double[samples];
        for (int k = 0; k < samples; k++) {
            values[k] = function(n);
        }

        // Create histogram dataset
        HistogramDataset dataset = new HistogramDataset();
        dataset.setType(HistogramType.SCALE_AREA_TO_1); // Normalized PDF
        dataset.addSeries("Pn", values, bins, 0, 1);

        // Create series for ArcSin PDF
        XYSeriesCollection datasetXY = new XYSeriesCollection();
        datasetXY.addSeries(arcSinPDFSeries(1000));

        // Create chart
        JFreeChart chart = ChartFactory.createHistogram(
                "Pn & ArcSin PDF for n = " + n,
                "Pn",
                "Density",
                datasetXY,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDataset(1, dataset);

        // Set renderers
        XYBarRenderer barRenderer = new XYBarRenderer();
        barRenderer.setSeriesPaint(1, new Color(0, 0, 255, 150)); // Transparent blue
        plot.setRenderer(1, barRenderer);

        XYLineAndShapeRenderer lineRenderer = new XYLineAndShapeRenderer(true, false);
        //lineRenderer.setSeriesPaint(1, Color.RED); // Red line for ArcSin PDF
        //lineRenderer.setSeriesShapesVisible(1, false);
        plot.setRenderer(0, lineRenderer);

        // Configure axes
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setRange(0, 1);

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setAutoRangeIncludesZero(false);

        // Display chart
        HW4Z3 app = new HW4Z3("HW4_3 Pn for n = " + n);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        app.setContentPane(chartPanel);

        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setSize(800, 600);
        app.setLocationRelativeTo(null);
        app.setVisible(true);
    }
}
