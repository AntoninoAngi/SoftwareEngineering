package it.polito.latazza.data;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.DateException;
import it.polito.latazza.exceptions.EmployeeException;
import it.polito.latazza.exceptions.NotEnoughBalance;
import it.polito.latazza.exceptions.NotEnoughCapsules;

public class DataImpl implements DataInterface {
	
	private Map<Integer,Employee> employees;
	private Map<Integer,Beverage> beverages;
	private LaTazzaAccount account;

	public DataImpl () {
		employees=FileManager.readEmployees();
		beverages=FileManager.readBeverages();
		account=FileManager.readBalance();
		if(account == null) account = new LaTazzaAccount(0);
		if(employees == null) employees = new HashMap<Integer,Employee>();
		if(beverages == null) beverages = new HashMap<Integer,Beverage>();
	}
	
	@Override
	public Integer sellCapsules(Integer employeeId, Integer beverageId, Integer numberOfCapsules, Boolean fromAccount)
			throws EmployeeException, BeverageException, NotEnoughCapsules {
		
		if(IntegerNullOrNegative(employeeId)) throw new EmployeeException();
		if(IntegerNullOrNegative(beverageId)) throw new BeverageException();
		if(IntegerNullOrNegative(numberOfCapsules)) throw new NotEnoughCapsules();
		Employee e = employees.get(employeeId);
		if(e == null) throw new EmployeeException();
		Beverage b = beverages.get(beverageId);
		if(b == null) throw new BeverageException();
		int available=b.getCapsules();
		if(available < numberOfCapsules) throw new NotEnoughCapsules();
		int price=(int)(b.getUnitPrice()*numberOfCapsules);
		String type;
		b.updateAvailable(-1*numberOfCapsules);
		FileManager.updateBeverage(b);
		if(fromAccount)
		{	
			type=new String("BALANCE");
			e.updateBalance(-1*price);
			FileManager.updateEmployee(e);
		}
		else 
		{
			type=new String("CASH");
			account.updateBalance(price);
			FileManager.updateBalance(account.getBalance());
		}
		FileManager.writeSaleReportEmployee(e.getId(),e.getName(), e.getSurname(), b.getName(), numberOfCapsules,type);		
		return e.getBalance();
	}

	@Override
	public void sellCapsulesToVisitor(Integer beverageId, Integer numberOfCapsules)
			throws BeverageException, NotEnoughCapsules {
		
		if(IntegerNullOrNegative(beverageId)) throw new BeverageException();
		if(IntegerNullOrNegative(numberOfCapsules)) throw new NotEnoughCapsules();
		Beverage b = beverages.get(beverageId);
		if(b == null) throw new BeverageException();
		int available=b.getCapsules();
		if(available < numberOfCapsules) throw new NotEnoughCapsules();
		int price=(int)(b.getUnitPrice()*numberOfCapsules);
		b.updateAvailable(-1*numberOfCapsules);
		FileManager.updateBeverage(b);
		account.updateBalance(price);
		FileManager.updateBalance(account.getBalance());
		FileManager.writeSaleReportVisitor(b.getName(), numberOfCapsules);	
		return;
	}

	@Override
	public Integer rechargeAccount(Integer id, Integer amountInCents) throws EmployeeException {
		
		if(IntegerNullOrNegative(id)) throw new EmployeeException();
		if(IntegerNullOrNegative(amountInCents)) throw new EmployeeException();
		if(amountInCents == 0) throw new EmployeeException();
		Employee e = employees.get(id);
		if(e == null) throw new EmployeeException();
		e.updateBalance(amountInCents);
		FileManager.updateEmployee(e);
		String type=new String("RECHARGE");
		FileManager.writeSaleReportEmployee(e.getId(),e.getName(), e.getSurname(), "", amountInCents,type);
		account.updateBalance(amountInCents);
		FileManager.updateBalance(account.getBalance());
		return e.getBalance();
	}

	@Override
	public void buyBoxes(Integer beverageId, Integer boxQuantity) throws BeverageException, NotEnoughBalance {
		if(IntegerNullOrNegative(beverageId)) throw new BeverageException();
		if(IntegerNullOrNegative(boxQuantity)) throw new BeverageException();
		if(!beverages.containsKey(beverageId)) throw new BeverageException();
		Beverage b = beverages.get(beverageId);
		if(b.getBoxPrice()*boxQuantity>account.getBalance()) throw new NotEnoughBalance();
		int price=b.getBoxPrice()*boxQuantity;
		account.updateBalance(-1*price);
		FileManager.updateBalance(account.getBalance());
		b.updateAvailable(boxQuantity*b.getCapsulesPerBox());
		FileManager.updateBeverage(b);
		FileManager.writeBuyReport(b.getName(), boxQuantity);
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<String> getEmployeeReport(Integer employeeId, Date startDate, Date endDate)
			throws EmployeeException, DateException{
		// return a list of employee transactions within a period throws exceptions in case of ghost id or ghost date
		// functions such as matchDates and matchId can be found at the bottom of this class
		if(!validDates(startDate,endDate)) throw new DateException();
		endDate.setHours(23);
		endDate.setMinutes(59);
		endDate.setSeconds(59);
		startDate.setHours(0);
		startDate.setMinutes(0);
		startDate.setSeconds(0);
		if(employees.get(employeeId)==null) throw new EmployeeException();
		List<String> employeeReport = FileManager.employeeReport(employeeId)
								.stream()
								.filter(s -> matchDates(s,startDate,endDate) )
								.collect(Collectors.toList());
		return employeeReport;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public List<String> getReport(Date startDate, Date endDate) throws DateException {
		// return a list of transactions within a period throws exceptions in case of ghost date
		// functions such as matchDates and matchId can be found at the bottom of this class
		List<String> Report = new ArrayList<String>();
		if(!validDates(startDate,endDate)) throw new DateException();
		startDate.setHours(0);
		startDate.setMinutes(0);
		startDate.setSeconds(0);
		endDate.setHours(23);
		endDate.setMinutes(59);
		endDate.setSeconds(59);
		Report = FileManager.report()
								.stream()
								.filter(s -> matchDates(s,startDate,endDate))
								.collect(Collectors.toList());
		return Report;
	}

	@Override
	public Integer createBeverage(String name, Integer capsulesPerBox, Integer boxPrice) throws BeverageException {
		if(IntegerNullOrNegative(capsulesPerBox)) throw new BeverageException();
		if(IntegerNullOrNegative(boxPrice)) throw new BeverageException();
		if(stringNullOrEmpty(name)) throw new BeverageException();
		int beveragesCounter = beverages.size(); 
		if(capsulesPerBox <= 0) throw new BeverageException();
		if(boxPrice <= 0) throw new BeverageException();
		Beverage b = new Beverage(++beveragesCounter,name,capsulesPerBox,boxPrice,0);
		if(FileManager.writeBeverage(b)==0) throw new BeverageException();
		beverages.put(beveragesCounter,b);
		return beverages.get(beveragesCounter).getId();
	}

	@Override
	public void updateBeverage(Integer id, String name, Integer capsulesPerBox, Integer boxPrice)
			throws BeverageException {
		if(IntegerNullOrNegative(id)) throw new BeverageException();
		if(IntegerNullOrNegative(capsulesPerBox)) throw new BeverageException();
		if(IntegerNullOrNegative(boxPrice)) throw new BeverageException();
		if(stringNullOrEmpty(name)) throw new BeverageException();
		if(!beverages.containsKey(id)) throw new BeverageException();
		if(capsulesPerBox <= 0) throw new BeverageException();
		if(boxPrice <= 0) throw new BeverageException();
		Beverage b = beverages.get(id);
		b.setName(name);
		b.setCapsulesPerBox(capsulesPerBox);
		b.setBoxPrice(boxPrice);
		FileManager.updateBeverage(b);
	}

	@Override
	public String getBeverageName(Integer id) throws BeverageException {
		if(IntegerNullOrNegative(id)) throw new BeverageException();
		Beverage b = beverages.get(id);
		if(b == null) throw new BeverageException();
		return b.getName();
	}

	@Override
	public Integer getBeverageCapsulesPerBox(Integer id) throws BeverageException {
		if(IntegerNullOrNegative(id)) throw new BeverageException();
		Beverage b = beverages.get(id);
		if(b == null) throw new BeverageException();
		return b.getCapsulesPerBox();
	}

	@Override
	public Integer getBeverageBoxPrice(Integer id) throws BeverageException {
		if(IntegerNullOrNegative(id)) throw new BeverageException();
		Beverage b = beverages.get(id);
		if(b == null) throw new BeverageException();
		return b.getBoxPrice();
	}

	@Override
	public List<Integer> getBeveragesId() {
		return beverages.keySet().stream().collect(Collectors.toList());
	}

	@Override
	public Map<Integer, String> getBeverages() {
		return beverages.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().getName()));
	}

	@Override
	public Integer getBeverageCapsules(Integer id) throws BeverageException {
		if(IntegerNullOrNegative(id)) throw new BeverageException();
		Beverage b = beverages.get(id);
		if(b == null) throw new BeverageException();
		return b.getCapsules();
	}

	@Override
	public Integer createEmployee(String name, String surname) throws EmployeeException {
		if(stringNullOrEmpty(name)||stringNullOrEmpty(surname)) throw new EmployeeException();
		int employeeCounter = (employees.size() +1 );
		Employee e = new Employee(employeeCounter,name,surname,0);
		if(FileManager.writeEmployee(e)==0) throw new EmployeeException();
		employees.put(employeeCounter,e);
		return employeeCounter;
	}

	@Override
	public void updateEmployee(Integer id, String name, String surname) throws EmployeeException {
		if(stringNullOrEmpty(name)||stringNullOrEmpty(surname)) throw new EmployeeException();
		if(IntegerNullOrNegative(id)) throw new EmployeeException();
		if(!employees.containsKey(id)) throw new EmployeeException();
		Employee e = employees.get(id);
		e.setName(name);
		e.setSurname(surname);
		FileManager.updateEmployee(e);
	}

	@Override
	public String getEmployeeName(Integer id) throws EmployeeException {
		if(IntegerNullOrNegative(id)) throw new EmployeeException();
		Employee e = employees.get(id);
		if(e == null) throw new EmployeeException();
		return e.getName();
	}

	@Override
	public String getEmployeeSurname(Integer id) throws EmployeeException {
		if(IntegerNullOrNegative(id)) throw new EmployeeException();
		Employee e = employees.get(id);
		if(e == null) throw new EmployeeException();
		return e.getSurname();
	}

	@Override
	public Integer getEmployeeBalance(Integer id) throws EmployeeException {
		if(IntegerNullOrNegative(id)) throw new EmployeeException();
		Employee e = employees.get(id);
		if(e == null) throw new EmployeeException();
		return e.getBalance();
	}

	@Override
	public List<Integer> getEmployeesId() {
		return employees.keySet().stream().collect(Collectors.toList());
	}

	@Override
	public Map<Integer, String> getEmployees() {
		return employees.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().getNameSurname()));
	}

	@Override
	public Integer getBalance() {
		return account.getBalance();
	}

	@Override
	public void reset() {
		destroyAll();
		clearFiles();
	}
	
	//invoke delete files, it tries 5 times in case of failures then it stops;
	private void clearFiles() {
		int i=0;
		while(i < 5) {
			if(FileManager.deleteReports()==1 && FileManager.deleteBeverages()==1 && FileManager.deleteEmployees()==1) break;
			i++;
		}
		return;
	}

	private void destroyAll() {
		employees.clear();
		beverages.clear();
		account.delete();
	}

	public boolean matchId(String wholeString, Integer id) {
		String[] elements = wholeString.split(" ");
		if(Integer.parseInt(elements[2])==id) return true;
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public boolean matchDates(String wholeString, Date startDate, Date endDate) {
		String[] elements = wholeString.split(" ");
		Date d = null;
		String[] times = elements[0].split("-");
		int year = Integer.parseInt(times[0]);
		int month = Integer.parseInt(times[1]);
		int date = Integer.parseInt(times[2]);
		times = elements[1].split(":");
		int hour = Integer.parseInt(times[0]);
		int min = Integer.parseInt(times[1]);
		int seconds = Integer.parseInt(times[2]);
		d = new Date(year-1900,month-1,date,hour,min,seconds);
		if( (d.after(startDate)) || (d.equals(startDate) ) )
		{
			if( (d.before(endDate)) || (d.equals(endDate)))
				return true;
		}
		return false;
	}
	
	public boolean validDates(Date start,Date end) {
		Date today = new Date();
		if(start==null||end==null) return false;
		if(start.equals(end))
		{
			if(start.before(today) || start.equals(today)) return true;
			else
			return false;
		}
		if(start.after(today))
		{
			return false;
		}
		/*if(end.after(today)) 
		{
			return false;
		}*/
		if(start.after(end)) return false;
		return true;
	}

	
	public boolean IntegerNullOrNegative(Integer i)
	{
		if(i== null) return true;
		if(i < 0 ) return true;
		return false;
	}
	
	public boolean stringNullOrEmpty(String s){
		if(s==null) return true;
		s=s.trim();
		if(s.isEmpty()) return true;
		return false;
	}
	
}
