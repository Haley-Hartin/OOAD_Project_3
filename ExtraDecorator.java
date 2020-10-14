
public abstract class ExtraDecorator extends Roll { //decorator pattern //https://www.baeldung.com/java-decorator-pattern and Lecture 13
	
	private Roll roll; //decorator pattern
	private String extraName; //name of the extra
	private int numExtra; ////the number of extras being put on for the roll
	private double extraPrice; //the unique price determined based on roll type


	public ExtraDecorator(Roll roll, int numExtra) { 
		this.roll = roll; //decorator pattern
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
	
	public abstract double choosePrice(String rollType); //there is a different price for each roll type, and ideally the name of the extra will be set as well
	public abstract String getOrderDescription(); //decorator pattern //adds the extra to the overall roll description
	
	public double cost() { //add the extra cost of the wanted extras to the total cost of the roll
		return getRoll().cost() + (this.numExtra * this.extraPrice); //decorator pattern
	}
	
	

}