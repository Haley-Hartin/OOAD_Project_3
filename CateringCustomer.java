import java.util.*;


public class CateringCustomer extends Customer{

	public CateringCustomer(List<String> menu) {
		super("Catering Customer", menu);
	}
	
	public void decideOrder() {
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
	
	public void changeOrder(int unfilledOrderIndex) {
		orderHasChanged();
		List<String> prevOrder = getOrder();
		List<String> newOrder = new ArrayList<String>();
		Random rand = new Random();
		
		for(int i = 0; i < prevOrder.size(); i++) {
			if(i == unfilledOrderIndex) {
				List<String> altMenu = new ArrayList<String>(getMenu());
				boolean wasRemoved = altMenu.remove(prevOrder.get(unfilledOrderIndex));
				if(wasRemoved) {
					setMenu(altMenu);
				}
				if(altMenu.size() == 0) {
					continue;
				}
				String newRoll = altMenu.get(rand.nextInt(altMenu.size()));
				newOrder.add(newRoll);
			}
			else {
				newOrder.add(prevOrder.get(i)); 
			}
		}
		
		setOrder(newOrder);
	}

}
