package it.polito.latazza.data;

public class Beverage {

	private int Id;
	private String name;
	private int capsulesPerBox;
	private int boxPrice;
	private double unitPrice;
	private int availability;
	
	
	
	public Beverage(int id, String name, int capsulesPerBox, int boxPrice,int available) {
		super();
		Id = id;
		this.name = name;
		this.capsulesPerBox = capsulesPerBox;
		this.boxPrice = boxPrice;
		availability=available;
		unitPrice = 1.0*boxPrice/capsulesPerBox;
	}
	
	@Override
	public String toString()
	{
	return Id+","+name+","+capsulesPerBox+","+boxPrice+","+availability;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Beverage)
		{
			Beverage b = (Beverage) o;
			boolean checkName=name.equals(b.getName());
			return ( checkName && (Id == b.getId()) && (capsulesPerBox == b.getCapsulesPerBox()) && (boxPrice == b.getBoxPrice()) && (availability == b.getCapsules()));
		}
		return false;
	}
	public int getCapsules() {
		return availability;
	}
	public void updateAvailable(int availability) {
		this.availability += availability;
	}
	public int getId() {
		return Id;
	}
	public String getName() {
		return name;
	}
	public int getCapsulesPerBox() {
		return capsulesPerBox;
	}
	public int getBoxPrice() {
		return boxPrice;
	}
	public double getUnitPrice() {
		return unitPrice;
	}

	public void setName(String name2) {
		// TODO Auto-generated method stub
		name=name2;
	}

	public void setCapsulesPerBox(Integer capsulesPerBox2) {
		// TODO Auto-generated method stub
		capsulesPerBox=capsulesPerBox2;
		unitPrice = 1.0*boxPrice/capsulesPerBox;
	}

	public void setBoxPrice(Integer boxPrice2) {
		// TODO Auto-generated method stub
		boxPrice=boxPrice2;
		unitPrice = 1.0*boxPrice/capsulesPerBox;
	}	
	
}
