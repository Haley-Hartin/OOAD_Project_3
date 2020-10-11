import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class GourmetStore extends Store{

	public GourmetStore(int inventorySize) {
		super(inventorySize, Arrays.asList("Egg Roll", "Jelly Roll", "Pastry Roll", "Sausage Roll", "Spring Roll"));
		//https://stackoverflow.com/questions/13395114/how-to-initialize-liststring-object-in-java
	}
	
	public Roll orderFromMenu(String order) {
		if(order == "Egg Roll") {
			return new EggRoll(1.00);
		}
		else if(order == "Jelly Roll") {
			return new JellyRoll(1.50);
		}
		else if(order == "Pastry Roll") {
			return new PastryRoll(2.00);
		}
		else if(order == "Sausage Roll") {
			return new SausageRoll(2.50);
		}
		else if(order == "Spring Roll") {
			return new SpringRoll(3.00);
		}
		
		return null;
	}
	
	public List<Customer> newCustomerLine(){
		Random rand = new Random(); ////https://www.geeksforgeeks.org/java-util-random-nextint-java/)
		int numCasual = rand.nextInt(12) + 1; //1-12 casual customers
		int numBusiness = rand.nextInt(3) + 1; //1-3 business customers
		int numCatering = rand.nextInt(3) + 1; //1-3 catering customers
		int totalCustomers = numCasual + numBusiness + numCatering;
		
		List<Customer> line = new ArrayList<Customer>(); 
		while(totalCustomers != 0) {
			int customerType = rand.nextInt(3);
			if(customerType == 0 && numCasual != 0) {
				totalCustomers -= 1;
				numCasual -= 1;
				line.add(new CasualCustomer());
			}
			else if(customerType == 1 && numBusiness != 0) {
				totalCustomers -= 1;
				numBusiness -= 1;
				line.add(new BusinessCustomer());
			}
			else if(customerType == 2 && numCatering != 0){
				totalCustomers -= 1;
				numCatering -= 1;
				line.add(new CateringCustomer());
			}
		}
		
		return line;
	}
	
}
