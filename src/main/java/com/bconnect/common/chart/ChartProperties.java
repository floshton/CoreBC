package com.bconnect.common.chart;

import org.jfree.chart.JFreeChart;

/**
 *
 * @author floshton
 */
public class ChartProperties {

    protected JFreeChart chart;
    protected int width;
    protected int height;

    public ChartProperties() {
    }

    public ChartProperties(JFreeChart chart, int width, int height) {
        this.chart = chart;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public JFreeChart getChart() {
        return chart;
    }

    public void setChart(JFreeChart chart) {
        this.chart = chart;
    }
}
