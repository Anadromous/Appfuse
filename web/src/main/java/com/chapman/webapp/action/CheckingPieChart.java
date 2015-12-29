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

	private PieChartModel model;

	public CheckingPieChart() {
		model = new PieChartModel();
		model.set("Item 1", 10);
		model.set("Item 2", 12.5);
		model.set("Item 3", 30);
		model.set("Item 4", 18);

		model.setTitle("Custom Pie");
		model.setLegendPosition("e");
		model.setFill(true);
		model.setShowDataLabels(true);
		model.setDiameter(150);

	}

	public PieChartModel getModel() {
		return model;
	}
}
