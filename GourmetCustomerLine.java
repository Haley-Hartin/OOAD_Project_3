import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GourmetCustomerLine extends CustomerLine{

	public GourmetCustomerLine(List<String> menu) {
		super(menu);
	}
	
	public List<Customer> getCustomerLine(){
		Random rand = new Random(); ////https://www.geeksforgeeks.org/java-util-random-nextint-java/)
		//randomlly determine aount of each customer
		int numCasual = rand.nextInt(12) + 1; //1-12 casual customers
		int numBusiness = rand.nextInt(3) + 1; //1-3 business customers
		int numCatering = rand.nextInt(3) + 1; //1-3 catering customers
		int totalCustomers = numCasual + numBusiness + numCatering;
		
		List<Customer> line = new ArrayList<Customer>(); 
		while(totalCustomers != 0) {
			int customerType = rand.nextInt(3); // choose which customer will be added to line
			if(customerType == 0 && numCasual != 0) {
				totalCustomers -= 1;
				numCasual -= 1;   
				line.add(new CasualCustomer(getStoreMenu()));
			}
			else if(customerType == 1 && numBusiness != 0) {
				totalCustomers -= 1;
				numBusiness -= 1;
				line.add(new BusinessCustomer(getStoreMenu()));
			}
			else if(customerType == 2 && numCatering != 0){
				totalCustomers -= 1;
				numCatering -= 1;
				line.add(new CateringCustomer(getStoreMenu()));
			}
		}
		
		return line;
	}

}
