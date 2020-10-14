import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class RunStore {

	public static void main(String[] args) throws FileNotFoundException {
		
//		System.out.println("Enter number of days to run the Store: "); // https://www.cis.upenn.edu/~matuszek/General/JavaSyntax/print-statements.html
//        Scanner scan = new Scanner(System.in); // https://stackoverflow.com/questions/5287538/how-to-get-the-user-input-in-java
//        int days = scan.nextInt();
//        scan.close(); // https://www.tutorialspoint.com/java/util/scanner_close.htm
		int days = 30;
		
		JUnitCore junit = new JUnitCore();
        junit.addListener(new CustomExecutionListener());

        //run the store for 30 days with an inventory level of 30
        PrintStream out = new PrintStream(new File("output_Inventory_30.txt")); // https://www.geeksforgeeks.org/redirecting-system-out-println-output-to-a-file-in-java/
        System.setOut(out);
		Store store1 = new GourmetStore(30);
		store1.runStore(days);
		
		//https://stackoverflow.com/questions/39097716/is-it-possible-to-get-test-status-in-junit-from-runlistener-testfinished
        //https://stackoverflow.com/questions/46536753/print-test-result-in-junit
        System.out.println("\nRUNNING JUNIT TESTS.");
        Result result = junit.run(MyUnitTest.class);
		for(Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println("Successful: " + result.wasSuccessful() + " ran " + result.getRunCount() + " tests");
		
		//run the store for 30 days with an inventory level of 45
		out = new PrintStream(new File("output_Inventory_45.txt"));
		System.setOut(out);
		Store store2 = new GourmetStore(45);
		store2.runStore(days);
		
		//https://stackoverflow.com/questions/39097716/is-it-possible-to-get-test-status-in-junit-from-runlistener-testfinished
        //https://stackoverflow.com/questions/46536753/print-test-result-in-junit
        System.out.println("\nRUNNING JUNIT TESTS.");
        Result result2 = junit.run(MyUnitTest.class);
		for(Failure failure : result2.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println("Successful: " + result2.wasSuccessful() + " ran " + result2.getRunCount() + " tests");
		
		//run the store for 30 days with an inventory level of 60
		out = new PrintStream(new File("output_Inventory_60.txt"));
		System.setOut(out);
		Store store3 = new GourmetStore(60);
		store3.runStore(days);
		
		//https://stackoverflow.com/questions/39097716/is-it-possible-to-get-test-status-in-junit-from-runlistener-testfinished
        //https://stackoverflow.com/questions/46536753/print-test-result-in-junit
        System.out.println("\nRUNNING JUNIT TESTS.");
        Result result3 = junit.run(MyUnitTest.class);
		for(Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println("Successful: " + result3.wasSuccessful() + " ran " + result3.getRunCount() + " tests");
		
		
		//EXTRA CREDIT COMMENTENTED OUT DUE TO XChart NOT BEING INSTALLED for average users
		//NOTE TO RUN EXTRA CREDIT, UNCOMMENT ExtraCredit.java and this
		//4 Graphs should display if all goes right
//		List<Store> list_stores = new ArrayList<Store>(Arrays.asList(store1, store2, store3));
//		ExtraCredit ec = new ExtraCredit();
//		for(int i =0; i < list_stores.size(); i++) {
//			Store s = list_stores.get(i);
//			ec.createStoreLineChart(s, days);
//		}
//		
//		ec.createStoreComparisonBarChart(list_stores);
		
	}

}
