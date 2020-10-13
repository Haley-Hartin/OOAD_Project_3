import java.util.*;
import java.math.*;
import java.text.DecimalFormat;

public class CashRegister {
	
	private List<String> customerTypes;
	private List<String> menu;
	
	private double totalProfit;
	private int totalRollsSold;
	private int totalOutageImpacts;
	private HashMap<String, Integer> totalRollsSoldByType;
	private HashMap<Integer, Double> totalProfitByDay;
	private HashMap<Integer, Integer> totalOutagesByDay;
	
	private HashMap<String, Integer> dailyRollsSoldByType;
	private HashMap<String, Integer> dailyCustomersTypesImpactedByOutages;
	private HashMap<String, Double> dailyCustomerTypeProfit;
	private double dailyProfit;
	private int dailyOutages;
	private int dailyRollsSold;
	

	public CashRegister(List<String> customerTypes, List<String> menu) {
		this.totalProfit = 0;
		this.totalRollsSold = 0;
		this.totalOutageImpacts = 0;
		this.totalProfitByDay = new HashMap<Integer, Double>();
		this.totalOutagesByDay = new HashMap<Integer, Integer>();
		this.totalRollsSoldByType = new HashMap<String, Integer>();
		
		this.customerTypes = customerTypes;
		this.menu = menu;
		
		resetDailyStats();
	}
	
	private String formatMoney(double money) {
		DecimalFormat df = new DecimalFormat("0.00"); //https://mkyong.com/java/how-to-round-double-float-value-to-2-decimal-points-in-java/
		return "$" + df.format(money);
	}
	
	public void ringUpCustomer(Customer customer, List<Roll> purchasedRolls) {
		if(customer.didCustomerChangeOrder()) {
			int increaseNum = this.dailyCustomersTypesImpactedByOutages.get(customer.getCustomerType())+1;
			this.dailyCustomersTypesImpactedByOutages.put(customer.getCustomerType(), increaseNum);
			this.dailyOutages += 1;
		}
		if(purchasedRolls != null) {
			printCustomerOrderSummary(customer, purchasedRolls);
			double costOfOrder = getCostOfOrder(purchasedRolls);
			double totalCustomerProfit = this.dailyCustomerTypeProfit.get(customer.getCustomerType()) + costOfOrder;
			this.dailyCustomerTypeProfit.put(customer.getCustomerType(), totalCustomerProfit);
			this.dailyProfit += costOfOrder;
			this.dailyRollsSold += purchasedRolls.size();
			
			for(int i = 0; i < purchasedRolls.size(); i++) {
				String key = purchasedRolls.get(i).getRollType();
				this.dailyRollsSoldByType.put(key, this.dailyRollsSoldByType.get(key) + 1);
			}
			
			printIndividualRolls(purchasedRolls);
		}
	}
	
	private void printCustomerOrderSummary(Customer customer, List<Roll> purchasedRolls) {
		String message = customer.getCustomerType() + " order";
		message = message + " cost " + formatMoney(getCostOfOrder(purchasedRolls));
		message = message + " with ";
		HashMap<String, Integer> customerOrder = customer.getHashMapOrder();
		
		for(int i = 0; i < this.menu.size(); i++) {
			String key = this.menu.get(i);
			if(customerOrder.containsKey(key)) {
				message = message +  " "+ customerOrder.get(key) + " " + key + "(s),";
			}
		}
		
		System.out.print(message + "  Receipt:\n" );
	}
	
	private void printIndividualRolls(List<Roll> purchasedRolls) {
		System.out.print("\t");
		for(int i = 0; i < purchasedRolls.size(); i++) {
			
			System.out.print("[" + purchasedRolls.get(i).getOrderDescription() + "], ");
			if((i+1) % 4 == 0) {
				System.out.print("\n\t");
			}
			
		}
		System.out.println("\n");
		
	}
	
	private double getCostOfOrder(List<Roll> rolls) {
		double total = 0;
		for(int i = 0; i < rolls.size(); i++) {
			Roll currentRoll = rolls.get(i);
			total += currentRoll.cost();
		}
		
		return total;
	}
	
	private void resetDailyStats() {
		this.dailyRollsSoldByType = new HashMap<String, Integer>();
		this.dailyCustomerTypeProfit = new HashMap<String, Double>();
		this.dailyCustomersTypesImpactedByOutages = new HashMap<String, Integer>();
		
		for(int i = 0; i < this.menu.size(); i++) {
			this.dailyRollsSoldByType.put(this.menu.get(i), 0);
		}
		for(int i = 0; i < this.customerTypes.size(); i++) {
			this.dailyCustomerTypeProfit.put(this.customerTypes.get(i), 0.00);
			this.dailyCustomersTypesImpactedByOutages.put(this.customerTypes.get(i), 0);
		}
		this.dailyProfit = 0;
		this.dailyOutages = 0;
		this.dailyRollsSold = 0;
	}
	
	private String prettyFormatHashMap(Iterator iterator) {
		String message = "";
	    while(iterator.hasNext()) { //https://www.geeksforgeeks.org/traverse-through-a-hashmap-in-java/
	    	Map.Entry mentry = (Map.Entry) iterator.next(); 
	    	if( !mentry.getValue().toString().equals("0")) {
	    		message = message + mentry.getValue() + " " + mentry.getKey() + "(s), ";
	    	}
	    }
	    if(message.length() != 0) {
	    	message = message.substring(0, message.length() - 2);
	    }
	    
	    return message;
	}
	
	private void printDailyCustomersImpacted(int day) {
		Iterator iterator = this.dailyCustomersTypesImpactedByOutages.entrySet().iterator();
		String message = prettyFormatHashMap(iterator);
		if(message.length() > 0) {
			System.out.println(message + " did not fill their original orders on day " + day + " due to roll outages.");
		}
		else {
			System.out.println("No customers were affected by roll outages on day " + day + ".");
		}
	}
	
	private void printDailyCustomerProfit(int day) {
		HashMap<String, String> dailyProfitMoneyFormat = new HashMap<String, String>();
		Iterator iterator2= this.dailyCustomerTypeProfit.entrySet().iterator();
		while(iterator2.hasNext()) {
			Map.Entry mentry = (Map.Entry) iterator2.next(); 
			String formatted = formatMoney(this.dailyCustomerTypeProfit.get(mentry.getKey()));
			dailyProfitMoneyFormat.put(mentry.getKey().toString(), formatted);
		}
		Iterator iterator = dailyProfitMoneyFormat.entrySet().iterator();
		String message = prettyFormatHashMap(iterator);
		System.out.println("On day " + day + " the store made " + formatMoney(this.dailyProfit) + ". Breakdown: "+ message  + ".");
	}
	
	private void printDailyRollsByType(int day) {
		Iterator iterator = this.dailyRollsSoldByType.entrySet().iterator();
		String message = prettyFormatHashMap(iterator);
		System.out.println("On day " + day + " the store sold " + this.dailyRollsSold + " rolls. Breakdown: "+ message +".");
	}
	
	public void reportDailyStats(int day) {
	
		printDailyCustomersImpacted(day);
		printDailyCustomerProfit(day);
		printDailyRollsByType(day);
		updateTotals(day);

		resetDailyStats();
	}
	
	private void updateTotals(int day) {
		this.totalProfitByDay.put(day, this.dailyProfit);
		this.totalOutagesByDay.put(day, this.dailyOutages);
		
		this.totalProfit += this.dailyProfit;
		this.totalRollsSold += this.dailyRollsSold;
		this.totalOutageImpacts += this.dailyOutages;
		Iterator it = this.dailyRollsSoldByType.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry mentry = (Map.Entry) it.next(); 
			String key = mentry.getKey().toString();
			if(this.totalRollsSoldByType.get(key) == null) {
				this.totalRollsSoldByType.put(key, 0);
			}
			this.totalRollsSoldByType.put(key, this.totalRollsSoldByType.get(key) + this.dailyRollsSoldByType.get(key));
		}
	}
	
	public void reportTotalStats() {
		System.out.println("Total Rolls Sold: " + this.totalRollsSold);
		Iterator iterator = this.totalRollsSoldByType.entrySet().iterator();
		System.out.println("Total Rolls Sold by Type: " + prettyFormatHashMap(iterator));
		System.out.println("Total Profit from Sales: " + formatMoney(this.totalProfit));
		System.out.println("Total Customer Orders Impacted by Roll Outages: " + this.totalOutageImpacts);
		
	}
	
	public HashMap<Integer, Double> getTotalProfitByDay(){
		return this.totalProfitByDay;
	}
	
	public HashMap<Integer, Integer> getTotalOutagesByDay(){
		return this.totalOutagesByDay;
	}
	
	public double getTotalProfit() {
		return this.totalProfit;
	}
	
	public int getTotalRollsSold() {
		return this.totalRollsSold;
	}
	
	public int getTotalOutageImpacts() {
		return this.totalOutageImpacts;
	}
}
