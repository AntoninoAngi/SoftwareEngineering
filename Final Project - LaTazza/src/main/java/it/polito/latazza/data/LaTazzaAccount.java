package it.polito.latazza.data;

public class LaTazzaAccount {
	
	private int balance;
	

	public LaTazzaAccount(int balance) {
		super();
		this.balance = balance;
	}

	public int getBalance() {
		return balance;
	}

	public void updateBalance(int balance) {
		this.balance+= balance;
	}
	
	public void delete()
	{
		this.balance = 0;
	}
	

}
