import java.util.ArrayList;
import java.util.List;

public class BusinessCustomer extends Customer{

	public BusinessCustomer(List<String> menu) {
		super("Business Customer", menu);
	}
	
	public void decideOrder() {
		int numRolls = 10;
		List<String> order = new ArrayList<String>(); 
		int currentType = 0;
		int numCurrentType = 0;
		//the Business Customer will take 2 of each type of roll on the menu, for a total of 10 rolls
		while(numRolls != 0) {
			if(numCurrentType < 2) {
				order.add(getMenu().get(currentType));
				numRolls -= 1;
				numCurrentType += 1;
			}
			else {
				currentType += 1;
				numCurrentType = 0;
			}
			
		}
		
		setOrder(order);
 	}
	
	public void changeOrder(int unfilledOrderIndex) {
		orderHasChanged(); //raise flag that customer has changed thier order
		setOrder(null); //any roll outage that the business customer is informed of will cause them to drop their order and not make any purchase
	}

}
