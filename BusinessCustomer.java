import java.util.ArrayList;
import java.util.List;

public class BusinessCustomer extends Customer{

	public BusinessCustomer() {
		super("Business Customer");
	}
	
	public void decideOrder() {
		System.out.println("A Business Customer is deciding its order");
		int numRolls = 10;
		List<String> order = new ArrayList<String>(); 
		int currentType = 0;
		int numCurrentType = 0;
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

}
