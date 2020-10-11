
public abstract class ExtraDecorator extends Roll { //https://www.baeldung.com/java-decorator-pattern and Lecture 13
	
	private Roll roll;
	private String extraName;
	private int numExtra;
	private double extraPrice;


	public ExtraDecorator(Roll roll, int numExtra) { 
		this.roll = roll;
		this.numExtra = numExtra;
		this.extraName = "<unk>";
		double price = choosePrice(roll.getRollType());
		
		this.extraPrice = price;
	}

	
	public void setExtraName(String name) {
		this.extraName = name;
	}
	
	public String getExtraName() {
		return this.extraName;
	}
	
	public int getNumExtra() {
		return this.numExtra;
	}
	
	public double getExtraPrice() {
		return this.extraPrice;
	}
	
	public String getRollType() {
		return this.roll.getRollType();
	}
	
	public Roll getRoll() {
		return this.roll;
	}
	
	public abstract double choosePrice(String rollType);
	public abstract String getOrderDescription();
	
	public double cost() {
		return getRoll().cost() + (this.numExtra * this.extraPrice);
	}
	
	

}