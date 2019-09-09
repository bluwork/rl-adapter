package net.ltslab.ai;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class RealtimeChart {

        private final XYChart chart;
        private final ArrayList<LinkedList<Double>> input;
        private final SwingWrapper<XYChart> sw;
        private final String seriesName;
        private int screenshotAfter;

        public RealtimeChart(String charTitle, String xTitle, String yTitle, String seriesName, int screenshotAfter) {
            this.seriesName = seriesName;
            this.screenshotAfter = screenshotAfter;
            input = new ArrayList<>();
            input.add(new LinkedList<>());
            input.add(new LinkedList<>());
            input.get(0).add(0.0);
            input.get(1).add(0.0);
            // Create Chart
            chart = QuickChart.getChart(charTitle, xTitle, yTitle, seriesName, input.get(0), input.get(1));

            // Show it
            sw = new SwingWrapper<XYChart>(chart);
            sw.displayChart();



        }

        public void updateChart(double xData, double yData) {

            if (xData == 100) {
                saveBitmap(xData);
            }
            if (xData % screenshotAfter == 0) {
                saveBitmap(xData);
            }
            if (input.get(0).size() > 2000) {
                input.get(0).remove();
            }
            if (input.get(1).size() > 2000) {
                input.get(1).remove();
            }

            this.input.get(0).add(xData);
            this.input.get(1).add(yData);
            javax.swing.SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {

                    chart.updateXYSeries(seriesName, input.get(0), input.get(1), null);
                    sw.repaintChart();
                }
            });
        }

        private void saveBitmap(double epoch) {
            try {
                BitmapEncoder.saveBitmap(chart, "./Breakout_" + seriesName + "_" + epoch, BitmapEncoder.BitmapFormat.PNG);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
