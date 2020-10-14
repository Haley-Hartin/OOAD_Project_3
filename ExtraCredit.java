//import org.knowm.xchart.*; 
import java.util.*;

public class ExtraCredit {
	
	public ExtraCredit() {
		
	}
	
	//EXTRA CREDIT WORK
	//NOTE IMPORT IS COMMENTED OUT AS WELL AS THIS

//	public void createStoreLineChart(Store store, int days) {
//		//create and display a line chart for the daily profits and daily outages of a store
//		HashMap<Integer,Integer> storeOutages = store.getCashReigster().getTotalOutagesByDay();
//		HashMap<Integer, Double> storeSales = store.getCashReigster().getTotalProfitByDay();
//		String chartTitle = "Store w/ Inventory Size of " + store.getInventorySize() + " Daily Outages and Sales";
//			
//		double[] daysArray = new double[days];
//		double[] outagesArray = new double[days];
//		double[] salesArray = new double[days];
//			
//		int counter = 0;
//		for(Map.Entry<Integer, Integer> entry : storeOutages.entrySet()){ //https://www.java67.com/2016/05/keyset-vs-entryset-vs-values-in-java-map-example.html
//				double key = entry.getKey();
//				double value = entry.getValue();
//				daysArray[counter] = key;
//				outagesArray[counter] = value;
//				counter++;
//		}
//			
//		counter = 0;
//		for(Map.Entry<Integer, Double> entry : storeSales.entrySet()) {
//			double value = entry.getValue();
//			salesArray[counter] = value;
//			counter++;
//		}
//		XYChart chart = new XYChartBuilder().width(800).height(600).title(chartTitle).xAxisTitle("Day").yAxisTitle("Outages/Sales ($)").build();
//			
//		chart.addSeries("Sales", daysArray, salesArray); //https://knowm.org/open-source/xchart/xchart-example-code/
//		chart.addSeries("Outages", daysArray, outagesArray);
//		new SwingWrapper(chart).displayChart();
//	}
//	
//	public void createStoreComparisonBarChart(List<Store> storeList) {
//		//Create Extra Credit Bar Graph that Compares Total Rolls Sold/Sales/Outages that occurred for each Inventory Level (30, 45, 60)
//		CategoryChart chartBar = new CategoryChartBuilder().width(800).height(600).title("Total Roll/Sales/Outages Comparisons").xAxisTitle("Max Inventory Level").yAxisTitle("Outages/Sales ($)/Rolls").build();
//		
//		Integer[] totalRollsArray = new Integer[storeList.size()];
//		Double[] totalSalesArray = new Double[storeList.size()];
//		Integer[] totalOutagesArray = new Integer[storeList.size()];
//		Integer[] inventoryLevels = new Integer[storeList.size()];
//		
//		for(int i = 0; i < storeList.size(); i++) {
//			Store store = storeList.get(i);
//			inventoryLevels[i] = store.getInventorySize();
//			totalRollsArray[i] = store.getCashReigster().getTotalRollsSold();
//			totalSalesArray[i] = store.getCashReigster().getTotalProfit();
//			totalOutagesArray[i] = store.getCashReigster().getTotalOutageImpacts();
//		}
//		
//	    chartBar.addSeries("Total Rolls", Arrays.asList(inventoryLevels), Arrays.asList( totalRollsArray));
//	    chartBar.addSeries("Total Sales", Arrays.asList(inventoryLevels), Arrays.asList( totalSalesArray ));
//	    chartBar.addSeries("Total Outages", Arrays.asList(inventoryLevels), Arrays.asList( totalOutagesArray));
//		
//	    new SwingWrapper(chartBar).displayChart(); //https://knowm.org/open-source/xchart/xchart-example-code/
//	}


}
