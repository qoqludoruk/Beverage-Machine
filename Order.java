
public class Order {
	private String size;
	private String type;
	private int noGlass;

	public Order(String size, String type, int noGlass) {
		super();
		this.size = size;
		this.type = type;
		this.noGlass = noGlass;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getNoGlass() {
		return noGlass;
	}
	public void setNoGlass(int noGlass) {
		this.noGlass = noGlass;
	}
	public String toSring() {
		return type+" - "+getPrice()+" TL";
	}
	public double getPrice() {  // the method for prices
		double price=0;
		switch(type) {  // I use switch case for type and also for size
		case "Apple Juice":
			price=10;
			break;
		case "Orange Juice":
			price=12;
			break;
		case "Pineapple Juice":
			price=15;
			break;
		case "Water":
			price=3;
			break;
		case "Water with ice":
			price=4;
			break;
		case "Tea":
			price=5;
			break;
		case "Tea with sugar":
			price=6;
			break;
		case "Coffee":
			price=7;
			break;
		case "Coffee with milk":
			price=9;
			break;
		}
		switch(size) {
		case "small":
			price*=0.75;
			break;
		case "medium":
			break;
		case "large":
			price*=1.25;		
		}
		
		price*=noGlass;	
		return price;
	}
	
	
}
