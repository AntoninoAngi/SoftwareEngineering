package it.polito.latazza.data;

public class Employee {

	private int Id;
	private String name;
	private String surname;
	private int balance;
	
	
	
	public Employee(int id, String name, String surname, int balance) {
		super();
		Id = id;
		this.name = name;
		this.surname = surname;
		this.balance = balance;
	}
	
	
	public String getNameSurname()
	{
		return name+" "+surname;
	}
	public int getId() {
		return Id;
	}
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public int getBalance() {
		return balance;
	}
	public void updateBalance(int balance) {
		this.balance += balance;
	}
	
	@Override
	public String toString()
	{
		return Id+","+name+","+surname+","+balance;
	}



	public void setName(String name2) {
		// TODO Auto-generated method stub
		name=name2;
	}



	public void setSurname(String surname2) {
		// TODO Auto-generated method stub
		surname=surname2;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Employee)
		{
			Employee e  = (Employee) o;
			boolean checkName = name.equals(e.getName());
			boolean checkSurname = surname.equals(e.getSurname());
			return ( checkName  && (Id==e.getId()) && checkSurname && (balance == e.getBalance()) ); 
		}
		return false;
	}
}
