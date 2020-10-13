import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

//import org.knowm.xchart.*;

public class RunStore {

	public static void main(String[] args) throws FileNotFoundException {
		
//		System.out.println("Enter number of days to run the Store: "); // https://www.cis.upenn.edu/~matuszek/General/JavaSyntax/print-statements.html
//        Scanner scan = new Scanner(System.in); // https://stackoverflow.com/questions/5287538/how-to-get-the-user-input-in-java
//        int days = scan.nextInt();
//        scan.close(); // https://www.tutorialspoint.com/java/util/scanner_close.htm
		int days = 30;

        PrintStream out = new PrintStream(new File("output_Inventory_30.txt")); // https://www.geeksforgeeks.org/redirecting-system-out-println-output-to-a-file-in-java/
        System.setOut(out);
		//run the store for 30 days 
		Store store1 = new GourmetStore(30);
		store1.runStore(days);
		
		//run the store for 45 days 
		out = new PrintStream(new File("output_Inventory_45.txt"));
		System.setOut(out);
		Store store2 = new GourmetStore(45);
		store2.runStore(days);
		
		//run the store for 60 days 
		out = new PrintStream(new File("output_Inventory_60.txt"));
		System.setOut(out);
		Store store3 = new GourmetStore(60);
		store3.runStore(days);
		
		
		//EXTRA CREDIT WORK
		//NOTE IMPORT IS COMMENTED OUT AS WELL AS THIS
//		List<Store> list_stores = new ArrayList<Store>(Arrays.asList(store1, store2, store3));
//		for(int i =0; i < 3; i++) {
//			Store currentStore = list_stores.get(i);
//			HashMap<Integer,Integer> storeOutages = currentStore.getCashReigster().getTotalOutagesByDay();
//			HashMap<Integer, Double> storeSales = currentStore.getCashReigster().getTotalProfitByDay();
//			String chartTitle = "Store w/ Inventory Size of " + currentStore.getInventorySize() + " Daily Outages and Sales";
//			
//			double[] daysArray = new double[days];
//			double[] outagesArray = new double[days];
//			double[] salesArray = new double[days];
//			
//			int counter = 0;
//			for(Map.Entry<Integer, Integer> entry : storeOutages.entrySet()){ //https://www.java67.com/2016/05/keyset-vs-entryset-vs-values-in-java-map-example.html
//					double key = entry.getKey();
//					double value = entry.getValue();
//					daysArray[counter] = key;
//					outagesArray[counter] = value;
//					counter++;
//			}
//			
//			counter = 0;
//			for(Map.Entry<Integer, Double> entry : storeSales.entrySet()) {
//				double value = entry.getValue();
//				salesArray[counter] = value;
//				counter++;
//			}
//			XYChart chart = new XYChartBuilder().width(800).height(600).title(chartTitle).xAxisTitle("Day").yAxisTitle("Outages/Sales ($)").build();
//			
//			chart.addSeries("Sales", daysArray, salesArray); //https://knowm.org/open-source/xchart/xchart-example-code/
//			chart.addSeries("Outages", daysArray, outagesArray);
//			new SwingWrapper(chart).displayChart();
//			
//		}
//		
//		CategoryChart chartBar = new CategoryChartBuilder().width(800).height(600).title("Total Roll/Sales/Outages Comparisons").xAxisTitle("Max Inventory Level").yAxisTitle("Outages/Sales ($)/Rolls").build();
//		
//		Integer[] totalRollsArray = new Integer[]{store1.getCashReigster().getTotalRollsSold(), store2.getCashReigster().getTotalRollsSold(), store3.getCashReigster().getTotalRollsSold()};
//		Double[] totalSalesArray = new Double[] {store1.getCashReigster().getTotalProfit(), store2.getCashReigster().getTotalProfit(), store3.getCashReigster().getTotalProfit()};
//		Integer[] totalOutagesArray = new Integer[] {store1.getCashReigster().getTotalOutageImpacts(), store2.getCashReigster().getTotalOutageImpacts(), store3.getCashReigster().getTotalOutageImpacts()};
//		
//	    chartBar.addSeries("Total Rolls", Arrays.asList(new Integer[] { 30, 45, 60 }), Arrays.asList( totalRollsArray));
//	    chartBar.addSeries("Total Sales", Arrays.asList(new Integer[] { 30, 45, 60 }), Arrays.asList( totalSalesArray ));
//	    chartBar.addSeries("Total Outages", Arrays.asList(new Integer[] { 30, 45, 60 }), Arrays.asList( totalOutagesArray));
//		
//		
//	    new SwingWrapper(chartBar).displayChart(); //https://knowm.org/open-source/xchart/xchart-example-code/

		
	}

}
