/**
 * 
 */
package com.chapman.webapp.action;

import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.PieChartModel;

/**
 * @author PMC
 *
 */

@ManagedBean(name = "checkingPieChart")
public class CheckingPieChart {
	public PieChartModel getPieModel() {
		PieChartModel pieModel = new PieChartModel();
		pieModel.set("Item 1", 10);
		pieModel.set("Item 2", 12.5);
		pieModel.set("Item 3", 30);
		pieModel.set("Item 4", 18);
		return pieModel;
	}
}
