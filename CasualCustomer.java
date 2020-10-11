import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CasualCustomer extends Customer{

	public CasualCustomer() {
		super("Casual Customer");
	}

	public void decideOrder() {
		Random rand = new Random(); ////https://www.geeksforgeeks.org/java-util-random-nextint-java/)
		int numRolls = rand.nextInt(3) + 1; //1-3 rolls
		List<String> order = new ArrayList<String>(); 
		for(int i = 0; i < numRolls; i++) { ////https://www.baeldung.com/java-random-list-element
			String type = getMenu().get(rand.nextInt(getMenu().size()));
			order.add(type);
		}
		
		setOrder(order);
 	}
}

