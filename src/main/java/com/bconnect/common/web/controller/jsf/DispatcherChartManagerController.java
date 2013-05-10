package com.bconnect.common.web.controller.jsf;

import com.bconnect.common.dispatch.implementation.DefaultRecordDispatcher;
import com.bconnect.common.web.controller.jsf.util.ChartUtilBean;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author jorge.rodriguez
 */
public class DispatcherChartManagerController extends DispatcherManagerController {

    List<ChartUtilBean> chartsData;

    public DispatcherChartManagerController() {
        super();
        initializeChartsData();
    }

    protected void initializeChartsData() {
        chartsData = new ArrayList<ChartUtilBean>();
        List<DefaultRecordDispatcher> activeDispatchers = super.getActiveDispatchers();
        for (DefaultRecordDispatcher dispatcher : activeDispatchers) {
            chartsData.add(new ChartUtilBean(createCategoryModel(dispatcher), dispatcher));
        }
    }

    private CartesianChartModel createCategoryModel(DefaultRecordDispatcher dispatcher) {
        CartesianChartModel categoryModel;
        categoryModel = new CartesianChartModel();

        ChartSeries idleRecords = new ChartSeries();
        idleRecords.setLabel("Max");

        idleRecords.set("Idle", dispatcher.getPoolMaxIdle());
        idleRecords.set("Active", dispatcher.getPoolMaxActive());

        ChartSeries activeRecords = new ChartSeries();
        activeRecords.setLabel("Current");

        activeRecords.set("Idle", dispatcher.getPoolNumIdle());
        activeRecords.set("Active", dispatcher.getPoolNumActive() >= 0 ? dispatcher.getPoolNumActive() : 0);

        categoryModel.addSeries(idleRecords);
        categoryModel.addSeries(activeRecords);

        return categoryModel;
    }

    public List<ChartUtilBean> getChartsData() {
        return chartsData;
    }
}
