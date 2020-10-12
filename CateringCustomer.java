import java.util.*;


public class CateringCustomer extends Customer{

	public CateringCustomer() {
		super("Catering Customer");
	}
	
	public void decideOrder() {
		System.out.println("A Catering Customer is deciding its order");
		Random rand = new Random();
		List<String> order = new ArrayList<String>(); 
		
		int numberOfTypes = 3; //https://www.baeldung.com/java-random-list-element
		int numOfEachType = 5;
		List<String> tempMenu = new ArrayList<String>(getMenu()); //https://stackoverflow.com/questions/7399482/java-lang-unsupportedoperationexception-at-java-util-abstractlist-removeunknown
	    for (int i = 0; i < numberOfTypes; i++) {
	        int randomIndex = rand.nextInt(tempMenu.size());
	        String rollType = tempMenu.get(randomIndex);
	        tempMenu.remove(randomIndex);
	        for(int j = 0; j < numOfEachType; j++) {
	        	order.add(rollType);
	        }
	        
	    }
		setOrder(order);
 	}

}
