package com.bconnect.common.web.controller.jsf.util;

import org.primefaces.model.chart.CartesianChartModel;

/**
 *
 * @author rafael.ventura
 */
public class ChartUtilBean {

    protected CartesianChartModel chart;
    protected Object item;

    public ChartUtilBean(CartesianChartModel chart, Object item) {
        this.chart = chart;
        this.item = item;
    }

    public ChartUtilBean() {
    }

    public CartesianChartModel getChart() {
        return chart;
    }

    public Object getItem() {
        return item;
    }

    public void setChart(CartesianChartModel chart) {
        this.chart = chart;
    }

    public void setItem(Object item) {
        this.item = item;
    }
}
