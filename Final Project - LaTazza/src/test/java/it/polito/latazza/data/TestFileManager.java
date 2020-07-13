package it.polito.latazza.data;


import java.util.List;
import java.util.Map;
import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.EmployeeException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TestFileManager {

	void set(){
		try {
			Employee e  = new Employee(1,"luigi","bianchi",0);
			FileManager.writeEmployee(e);
			e  = new Employee(2,"marco","carta",0);
			FileManager.writeEmployee(e);
			Beverage b  = new Beverage(1,"coffee",50,200,0);
			FileManager.writeBeverage(b);
			b = new Beverage(2,"tea",30,400,0);
			FileManager.writeBeverage(b);
			FileManager.updateBalance(30);
		} catch (BeverageException e) {
			fail();
		}
		catch(EmployeeException e)
		{
			fail();
		}
	}

	@AfterEach
	public void tearDown() {
		FileManager.deleteBeverages();
		FileManager.deleteEmployees();
		FileManager.deleteReports();
	}
	
	@Test
	public void testEmployeesNull()
	{
		Map<Integer,Employee> employees = FileManager.readEmployees();
		assertNull(employees);
	}
	
	@Test
	public void testReadOneEmployee()
	{
		try {
			Employee e  = new Employee(1,"luigi","bianchi",0);
			FileManager.writeEmployee(e);
			Map<Integer,Employee> employees = FileManager.readEmployees();
			assertEquals(1,employees.size());
			assertEquals(e,employees.get(1));
		} catch (EmployeeException e) {
			fail();
		}
	}

	@Test
	public void testReadOneBeverage()
	{
		try {
			Beverage b  = new Beverage(1,"coffee",50,200,0);
			FileManager.writeBeverage(b);
			Map<Integer,Beverage> beverages = FileManager.readBeverages();
			assertEquals(1,beverages.size());
			assertEquals(b,beverages.get(1));
		} catch (BeverageException e) {
			fail();
		}
	}
	@Test
	public void testBeverageNull()
	{
		Map<Integer,Beverage> beverages = FileManager.readBeverages();
		assertNull(beverages);
	}

	@Test
	public void testWriteEmployee() { //black box
		try {
			Employee e  = new Employee(3,"mario","rossi",0);
			FileManager.writeEmployee(e);
			Employee e1  = new Employee(4,"giuseppe","verdi",0);
			FileManager.writeEmployee(e1);
			Map<Integer,Employee> employees  = FileManager.readEmployees();
			assertEquals(2,employees.size(),"number of element is wrong");
			assertEquals(e,employees.get(3),"employee is wrong");
			assertEquals(e1,employees.get(4));
		} catch (EmployeeException e) {
			fail();
		}
	}
	
	@Test
	public void testWriteWrongEmployee() { //black box
		try {
			Employee e  = new Employee(-3,"mario","rossi",0);
			FileManager.writeEmployee(e);
			fail();
		} catch (EmployeeException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testWriteEmployeeAppend() { //
		try {
			set();
			Employee e  = new Employee(3,"mario","rossi",0);
			FileManager.writeEmployee(e);
			Employee e1  = new Employee(4,"giuseppe","verdi",0);
			FileManager.writeEmployee(e1);
			Map<Integer,Employee> employees  = FileManager.readEmployees();
			assertEquals(4,employees.size());
		} catch (EmployeeException e) {
			fail();
		}
	}
	
	@Test
	public void testWriteBeverage()
	{
		try
		{
			Beverage b = new Beverage(3,"milk",40,400,0);
			FileManager.writeBeverage(b);
			Beverage b1 = new Beverage(4,"camomille",50,300,1);
			FileManager.writeBeverage(b1);
			Map<Integer,Beverage> beverages = FileManager.readBeverages();
			assertEquals(2,beverages.size());
			assertEquals(b,beverages.get(3));
			assertEquals(b1,beverages.get(4));		
		}
		catch(BeverageException e)
		{
			fail();
		}
	}
	
	@Test
	public void testWriteWrongBeverage()
	{
		try
		{
			Beverage b = new Beverage(-3,"milk",40,400,0);
			FileManager.writeBeverage(b);
			fail();		
		}
		catch(BeverageException e)
		{
			assertTrue(true);
		}
	}
	
	
	
	@Test
	public void testWriteBeverageAppend()
	{
		
		try {
			set();
			Beverage b = new Beverage(3,"milk",40,400,0);
			FileManager.writeBeverage(b);
			Beverage b1 = new Beverage(4,"camomille",50,300,1);
			FileManager.writeBeverage(b1);
			Map<Integer,Beverage> beverages = FileManager.readBeverages();
			assertEquals(4,beverages.size());
		} catch (BeverageException e) {
			fail();
		}
	}
	
	
	@Test
	public void testReadBalanceNull()
	{
		FileManager.deleteReports();
		LaTazzaAccount account = FileManager.readBalance();
		assertEquals(0,account.getBalance());
	}
	
	@Test
	public void testReadBalance()
	{
		set();
		LaTazzaAccount account = FileManager.readBalance();
		assertEquals(30,account.getBalance());
	}
	
	@Test
	public void testUpdateBalanceNull()
	{
		FileManager.deleteReports();
		FileManager.updateBalance(50);
		assertEquals(50,FileManager.readBalance().getBalance());
	}
	
	@Test
	public void testUpdateBalance()
	{
		set();
		FileManager.updateBalance(50);
		assertEquals(50,FileManager.readBalance().getBalance());
	}
	
	
	@Test
	public void testUpdateFirstEmployee()
	{
		set();
		Map<Integer,Employee> employees = FileManager.readEmployees();
		Employee e = employees.get(1);
		e.setName("carlo");
		try {
			FileManager.updateEmployee(e);
		} catch (EmployeeException e2) {
			e2.printStackTrace();
		}
		Employee e1 = FileManager.readEmployees().get(1);
		assertEquals(2, FileManager.readEmployees().size());
		assertEquals(e,e1);
	}

	@Test
	public void testUpdateEmployee()
	{
		try {
			set();
			Employee e = new Employee(3, "mario", "rossi", 0);
			FileManager.writeEmployee(e);
			Employee e1 = new Employee(4, "giuseppe", "verdi", 0);
			FileManager.writeEmployee(e1);
			e.setName("giuseppe");
			FileManager.updateEmployee(e);
			Map<Integer, Employee> employees = FileManager.readEmployees();
			assertEquals(4, employees.size());
			assertEquals(e, employees.get(3));
			assertEquals(e1, employees.get(4));
		} catch (EmployeeException e) {
			fail();
		}
	}
	
	@Test
	public void testUpdateWrongEmployee()
	{
		set();
		try
		{
			Employee e = new Employee(-1,"luigi","bianchi",0);
			FileManager.updateEmployee(e);
			fail();
		}
		catch(EmployeeException e)
		{
			assertTrue(true);
		}
	}
	
	@Test
	public void testUpdateNullEmployees()
	{
		try {
		FileManager.deleteEmployees();
		Employee e  = new Employee(3,"mario","rossi",0);
		FileManager.updateEmployee(e);
		assertNull(FileManager.readEmployees());
		}
		catch(EmployeeException e)
		{
			fail();
		}
	}
	
	@Test
	public void testUpdateEmployeeNotExisting()
	{
		try
		{
			set();
			Employee e  = new Employee(3,"mario","rossi",0);
			FileManager.writeEmployee(e);
			Employee e1  = new Employee(4,"giuseppe","verdi",0);
			FileManager.writeEmployee(e1);
			Employee e2  = new Employee(5,"carlo","verdone",0);
			e2.setName("giuseppe");
			FileManager.updateEmployee(e2);
			Map<Integer,Employee> employees = FileManager.readEmployees();
			assertEquals(4,employees.size());
			assertEquals(e,employees.get(3));
			assertEquals(e1,employees.get(4));
		}
		catch(EmployeeException e)
		{
			fail();
		}
	}
	
	@Test
	public void testUpdateWrongBeverage()
	{
		try
		{
			set();
			Beverage b = new Beverage(-1,"tea",50,200,0);
			FileManager.updateBeverage(b);
			fail();
		}
		catch(BeverageException e)
		{
			assertTrue(true);
		}
	}
	
	@Test
	public void testUpdateBeverageNotExisting()
	{
		try
		{
			set();
			Beverage b = new Beverage(3,"tea",50,200,0);
			FileManager.updateBeverage(b);
			assertEquals(2,FileManager.readBeverages().size());
		}
		catch(BeverageException e)
		{
			fail();
		}
	}
	
	@Test
	public void testUpdateFirstBeverage()
	{
		try
		{
			set();
			Beverage b = FileManager.readBeverages().get(1);
			b.setName("tea");
			FileManager.updateBeverage(b);
			assertEquals(2,FileManager.readBeverages().size());
			assertEquals(b,FileManager.readBeverages().get(1));
		}
		catch(BeverageException e)
		{
			fail();
		}
	}
	
	@Test
	public void testUpdateNullBeverages()
	{
		try {
			Beverage b = new Beverage(5, "camomille", 30, 40, 0);
			FileManager.updateBeverage(b);
			assertNull(FileManager.readBeverages());
		} catch (BeverageException e) {
			fail();
		}
	}
	
	@Test
	public void testUpdateBeverage()
	{
		try
		{
			set();
			Beverage b = new Beverage(3,"milk",40,400,0);
			FileManager.writeBeverage(b);
			Beverage b1 = new Beverage(4,"camomille",50,300,1);
			FileManager.writeBeverage(b1);
			b.setBoxPrice(200);
			FileManager.updateBeverage(b);
			Map<Integer,Beverage> beverages = FileManager.readBeverages();
			assertEquals(4,beverages.size());
			assertEquals(b,beverages.get(3));
			assertEquals(b1,beverages.get(4));	
		}
		catch(BeverageException e)
		{
			fail();
		}
	}
	
	@Test
	public void testWrongEmployeeReport()
	{
		try
		{
			FileManager.writeSaleReportEmployee(-1, "luigi","bianchi","coffee",10,"CASH");
			fail();
		}
		catch(EmployeeException e)
		{
			assertTrue(true);
		}
	}
	
	@Test
	public void testEmployeeReport()
	{
		try
		{
			FileManager.writeSaleReportEmployee(1, "luigi","bianchi","coffee",10,"CASH");
			FileManager.writeSaleReportEmployee(1, "luigi","bianchi","coffee",30,"BALANCE");
			FileManager.writeSaleReportEmployee(1, "luigi","bianchi","",10,"RECHARGE");
			FileManager.writeSaleReportEmployee(2, "marco","carta","tea",10,"CASH");
			List<String> reports = FileManager.employeeReport(1);
			assertEquals(3,reports.size());
			String line = reports.get(0);
			String[] fields = line.split(" ");
			assertEquals("CASH",fields[3]);
			assertEquals("luigi",fields[4]);
			assertEquals("bianchi",fields[5]);
			assertEquals("coffee",fields[6]);
			assertEquals("10",fields[7]);
			line = reports.get(1);
			fields = line.split(" ");
			assertEquals("BALANCE",fields[3]);
			line = reports.get(2);
			fields = line.split(" ");
			assertEquals("RECHARGE",fields[3]);
			assertEquals("luigi",fields[4]);
			assertEquals("bianchi",fields[5]);
			assertTrue(line.contains(String.format("%.2f \u20ac", 0.10)));
		}
		catch(EmployeeException e)
		{
			fail();
		}
	}
	
	@Test
	public void testOneEmployeeReport()
	{
		try
		{
			FileManager.deleteReports();
			FileManager.writeSaleReportEmployee(1, "luigi","bianchi","coffee",10,"CASH");
			List<String> reports = FileManager.employeeReport(1);
			assertEquals(1,reports.size());
			String line = reports.get(0);
			String[] fields = line.split(" ");
			assertEquals("CASH",fields[3]);
			assertEquals("luigi",fields[4]);
			assertEquals("bianchi",fields[5]);
			assertEquals("coffee",fields[6]);
			assertEquals("10",fields[7]);
		}
		catch(EmployeeException e)
		{
			fail();
		}
	}
		
	@Test
	public void testEmployeeReportNull()
	{
		List<String> reports = FileManager.employeeReport(0);
		assertEquals(0,reports.size());
	}
	
	@Test
	public void testOneReport()
	{
		FileManager.deleteReports();
		boolean b1 = FileManager.writeSaleReportVisitor("coffee",10);
		assertTrue(b1);
		List<String> reports = FileManager.report();
		assertEquals(1,reports.size());
		String line = reports.get(0);
		String[] fields = line.split(" ");
		assertEquals("VISITOR",fields[2]);
		assertEquals("coffee",fields[3]);
		assertEquals("10",fields[4]);
	}
	@Test
	public void testReport()
	{
		boolean b1 = FileManager.writeSaleReportVisitor("coffee",10);
		boolean b2 = FileManager.writeSaleReportVisitor("tea",20);
		boolean b3 = FileManager.writeSaleReportVisitor("milk",15);
		boolean b4 = FileManager.writeSaleReportVisitor("coffee",3);
		boolean b5 = FileManager.writeBuyReport("coffee",1);
		boolean b6 = FileManager.writeBuyReport("milk",2);
		assertTrue(b1);
		assertTrue(b2);
		assertTrue(b3);
		assertTrue(b4);
		assertTrue(b5);
		assertTrue(b6);
		List<String> reports = FileManager.report();
		assertEquals(6,reports.size());
		String line = reports.get(0);
		String[] fields = line.split(" ");
		assertEquals("VISITOR",fields[2]);
		assertEquals("coffee",fields[3]);
		assertEquals("10",fields[4]);
		line = reports.get(1);
		fields = line.split(" ");
		assertEquals("VISITOR",fields[2]);
		assertEquals("tea",fields[3]);
		assertEquals("20",fields[4]);
		line = reports.get(2);
		fields = line.split(" ");
		assertEquals("VISITOR",fields[2]);
		assertEquals("milk",fields[3]);
		assertEquals("15",fields[4]);
		line = reports.get(3);
		fields = line.split(" ");
		assertEquals("VISITOR",fields[2]);
		assertEquals("coffee",fields[3]);
		assertEquals("3",fields[4]);
		line = reports.get(4);
		fields = line.split(" ");
		assertEquals("BUY",fields[2]);
		assertEquals("coffee",fields[3]);
		assertEquals("1",fields[4]);
	}
	
	
	@Test
	public void testReportNull()
	{
		List<String> reports = FileManager.report();
		assertEquals(0,reports.size());
	}
	
	@Test
	public void testDeleteReports()
	{
		try
		{
			FileManager.writeBuyReport("coffee", 1);
			FileManager.writeSaleReportEmployee(1, "mario", "rossi","coffee", 10, "CASH");
			FileManager.writeSaleReportVisitor("milk", 2);
			FileManager.updateBalance(30);
			int res = FileManager.deleteReports();
			assertEquals(1,res);
			List<String> reports = FileManager.report();
			int balance = FileManager.readBalance().getBalance();
			assertEquals(0,reports.size());
			assertEquals(0,balance);
		}
		catch(EmployeeException e)
		{
			fail();
		}
	}
	
	@Test
	public void testDeleteReportsNull()
	{
		int res  =FileManager.deleteReports();
		assertEquals(0,res);
	}
}
