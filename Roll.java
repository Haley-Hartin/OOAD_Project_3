
public abstract class Roll {
	
	
	private double price; //all roll prices should be different
	private String order;
	private String rollType;
	
	public Roll() {
		
	}

	public Roll(double price, String type) { //set roll info
		this.price = price;
		this.order = type;
		this.rollType = type;
	}
	
	public double cost() {
		return this.price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getOrderDescription() { //return the name of the roll 
		return this.order;
	}
	
	public String getRollType() {
		return this.rollType;
	}

}
