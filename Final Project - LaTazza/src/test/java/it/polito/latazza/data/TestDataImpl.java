package it.polito.latazza.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import it.polito.latazza.exceptions.*;


public class TestDataImpl  {
	
	DataImpl dI= new DataImpl();
	int[] employeeIds = new int[2];
	int[] beverageIds = new int[2];
	
	@BeforeEach
	 protected void setUp() throws Exception {
			dI.reset();
	    	employeeIds[0] = dI.createEmployee("mario", "rossi");
	    	employeeIds[1] = dI.createEmployee("giuseppe", "verdi");
			beverageIds[0] = dI.createBeverage("coffee", 20, 50);
			beverageIds[1] = dI.createBeverage("milk", 100, 200);
	    }
	
	
	    @Test
		public void testgetEmployeeId() throws Exception{
	    	int size,id;
	    	id=employeeIds[0];
	        size= dI.getEmployeesId().size();
	        assertEquals(2,size);
	        assertEquals("mario",dI.getEmployeeName(id));
	        assertEquals("rossi",dI.getEmployeeSurname(id));
	              
	    }
	    @Test
	   	public void testgetBeverageId() throws Exception{
	       	int size,id;
	       	id=beverageIds[0];
	           size= dI.getBeveragesId().size();
	           assertEquals(2,size);
	           assertEquals("coffee",dI.getBeverageName(id));
	           assertEquals(new Integer(20),dI.getBeverageCapsulesPerBox(id));
	           assertEquals(new Integer(50),dI.getBeverageBoxPrice(id));
	          
	                 
	       }
		@Test
		public void testSellCapsule() throws Exception{
		
	        try {
	        	int empAccount = dI.rechargeAccount(1,100);
	        	dI.buyBoxes(1,1);
	        	assertEquals(new Integer(20),dI.getBeverageCapsules(1));
	        	int newEmpAccount = dI.sellCapsules(1, 1, 10, false);
	        	assertEquals(new Integer(10),dI.getBeverageCapsules(1));
	            assertEquals(100,empAccount);
	            assertEquals(100,newEmpAccount);
	        } catch (Exception r) {
	           fail();
	        } }
		@Test
		public void testSellCapsuleToNonCapsules() throws Exception{
		
	        try {
	        	dI.rechargeAccount(1,100);
	        	dI.sellCapsules(1, 1, 10, false);
	        	fail();
	        } catch (NotEnoughCapsules r) {
	           assertTrue(true);
	        } }
		
		@Test
		public void testSellCapsuleToNonBeverage() throws Exception{
		
	        try {
	        	dI.rechargeAccount(1,100);
	        	dI.buyBoxes(1,1);
	        	assertEquals(new Integer(20),dI.getBeverageCapsules(1));
	        	dI.sellCapsules(1, 3, 10, false);
	        	fail();
	        } catch (BeverageException r) {
	           assertTrue(true);
	        } }
		
		@Test
	  	    public void testSellCapsulesEmployeeNotEnoughBalance() throws Exception{
	  	    	
	  	    	try{
	  	    		dI.sellCapsules(1, 0, 10, true);
	  	    		if (dI.getEmployeeBalance(1) < 0){
	  	    			fail("Not enough balance");
	  	    		}
	  	    	}catch(Exception r){
	  	    		
	  	    	}
	  	    }
		
		
		@Test
		public void testSellCapsuleToNonEmployee() throws Exception{
		
	        try {
	        	dI.rechargeAccount(1,100);
	        	dI.buyBoxes(1,1);
	        	assertEquals(new Integer(20),dI.getBeverageCapsules(1));
	        	dI.sellCapsules(3, 1, 10, false);
	        	fail();
	        } catch (EmployeeException r) {
	           assertTrue(true);
	        } }
	    	@Test
	    	public void testsellCapsuleAccount() throws Exception{
	            try {
	            	int empAccount = dI.rechargeAccount(1,100);
	            	assertEquals(new Integer(100), dI.getBalance());
		        	dI.buyBoxes(1,1);
		        	assertEquals(new Integer(50), dI.getBalance());
		        	assertEquals(new Integer(20),dI.getBeverageCapsules(1));
		        	int newEmpAccount = dI.sellCapsules(1, 1, 10, true);
		        	assertEquals(new Integer(50), dI.getBalance());
		        	assertEquals(new Integer(10),dI.getBeverageCapsules(1));
		            assertEquals(100,empAccount);
		            assertEquals(75,newEmpAccount);
		            assertEquals(new Integer(75),dI.getEmployeeBalance(1));
	            } catch (Exception r) {
	               fail();
	            }   
	    }
	    	@Test
	   	public void testsellCapsuleToVisitor() throws Exception{
	    	try {
	    		dI.rechargeAccount(1,100);
	    		assertEquals(new Integer(100), dI.getBalance());
	    		dI.buyBoxes(1,1);
	    		assertEquals(new Integer(20),dI.getBeverageCapsules(1));
	    		assertEquals(new Integer(50), dI.getBalance());
	    		dI.sellCapsulesToVisitor(1,10);
	    		assertEquals(new Integer(10),dI.getBeverageCapsules(1));
	    		assertEquals(new Integer(75),dI.getBalance());
	            } catch (Exception r) {
	               fail();
	            }   
	    }
	    	
	    	@Test
	    	public void testSellCapsulesEqualNumberOfAvailable()
	    	{
	    		try {
		        	int empAccount = dI.rechargeAccount(1,100);
		        	dI.buyBoxes(1,1);
		        	assertEquals(new Integer(20),dI.getBeverageCapsules(1));
		        	int newEmpAccount = dI.sellCapsules(1, 1, 20, false);
		        	assertEquals(new Integer(0),dI.getBeverageCapsules(1));
		            assertEquals(100,empAccount);
		            assertEquals(100,newEmpAccount);
		        } catch (Exception r) {
		           fail();
		        }
	    	}

			@Test
			public void testSellCapsuleVisitorToNonCapsules() throws Exception{
			
		        try {
		        	dI.rechargeAccount(1,100);
		        	dI.sellCapsulesToVisitor(1, 10);
		        	fail();
		        } catch (NotEnoughCapsules r) {
		           assertTrue(true);
		        } }
			
			@Test
			public void testSellCapsuleVisitorToNonBeverage() throws Exception{
			
		        try {
		        	dI.rechargeAccount(1,100);
		        	dI.buyBoxes(1,1);
		        	assertEquals(new Integer(20),dI.getBeverageCapsules(1));
		        	dI.sellCapsulesToVisitor(3, 10);
		        	fail();
		        } catch (BeverageException r) {
		           assertTrue(true);
		        } }
	    		    	
	    	@Test
	    	public void testSellCapsulesVisitorEqualNumberOfAvailable()
	    	{
	    		try {
		        	int empAccount = dI.rechargeAccount(1,100);
		        	dI.buyBoxes(1,1);
		        	assertEquals(new Integer(20),dI.getBeverageCapsules(1));
		        	dI.sellCapsulesToVisitor( 1, 20);
		        	assertEquals(new Integer(0),dI.getBeverageCapsules(1));
		            assertEquals(100,empAccount);
		        } catch (Exception r) {
		           fail();
		        }
	    	}
	  	    
	  	    @Test
	  	    public void testupdateBeverageNotExistBev() throws Exception{
	  	    	
	  	    	try{
	  	    	dI.updateBeverage(3, "milk", 10, 5);
	  	    	fail("Beverages not existing");
	  	    	}catch(BeverageException r){
	  	    		assertTrue(true);
	  	    	}
	  	    }
	  	    
	  	  @Test
	  	    public void testupdateBeverageWrongBoxPrice() throws Exception{
	  	    	
	  	    	try{
	  	    	dI.updateBeverage(3, "milk", 10, -5);
	  	    	fail();
	  	    	}catch(BeverageException r){
	  	    		assertTrue(true);
	  	    	}
	  	    }
	  	  
	  	 @Test
	  	    public void testupdateBeverageWrongCapsules() throws Exception{
	  	    	
	  	    	try{
	  	    	dI.updateBeverage(3, "milk", -10, 5);
	  	    	fail();
	  	    	}catch(BeverageException r){
	  	    		assertTrue(true);
	  	    	}
	  	    }
	  	    
	  		@Test
	    	public void testcreateBeverage() throws Exception{
	  	    	try {
					int id =dI.createBeverage("tea", 5, 30);
					int k = dI.getBeveragesId().size();
					assertEquals(3, k);
					assertEquals("tea",dI.getBeverageName(id));
					assertEquals(new Integer(5),dI.getBeverageCapsulesPerBox(id));
					assertEquals(new Integer(30),dI.getBeverageBoxPrice(id));
				} catch (BeverageException e) {
					fail();
				}
	  	    }
	  		
	  		@Test
	    	public void testCreateBeverageWrongBoxPrice() throws Exception{
	  	    	try {
					dI.createBeverage("tea", 5, -30);
					fail();
				} catch (BeverageException e) {
					assertTrue(true);
				}
	  	    }
	  		
	  		@Test
	    	public void testCreateBeverageWrongCapsules() throws Exception{
	  	    	try {
					dI.createBeverage("tea", -5, 30);
					fail();
				} catch (BeverageException e) {
					assertTrue(true);
				}
	  	    }
	  		
	  	    @Test
	  	    public void testRechargeAccount() {
	  	    	try 
	  	    	{
	  	    		int balance = dI.getEmployeeBalance(1);
	  	    		int newBalance = dI.rechargeAccount(1, 100);
	  	    		assertEquals(balance+100,newBalance);
	  	    	}
	  	    	catch(EmployeeException e)
	  	    	{
	  	    		fail();
	  	    	}
	  	    }
	  	    
	  	  @Test
	  	    public void testRechargeAccountToNonEmployee() {
	  	    	try 
	  	    	{
	  	    		dI.rechargeAccount(3, 100);
	  	    		fail();
	  	    	}
	  	    	catch(EmployeeException e)
	  	    	{
	  	    		assertTrue(true);
	  	    	}
	  	    }
	  	  
	  	  @Test
	  	    public void testRechargeAccountInvalidAmount() {
	  	    	try 
	  	    	{
	  	    		dI.rechargeAccount(3, -100);
	  	    		fail();
	  	    	}
	  	    	catch(EmployeeException e)
	  	    	{
	  	    		assertTrue(true);
	  	    	}
	  	    	try 
	  	    	{
	  	    		dI.rechargeAccount(3, 0);
	  	    		fail();
	  	    	}
	  	    	catch(EmployeeException e)
	  	    	{
	  	    		assertTrue(true);
	  	    	}
	  	    }
	  	    @Test
	  	    public void testupdateBeverageUpdating() throws Exception{
	  	    	
	  	    	dI.updateBeverage(2, "tea", 10, 5);
	  	    	int k = dI.getBeverageBoxPrice(2);
	  	    	assertEquals(k, 5);
	  	    	k = dI.getBeverageCapsulesPerBox(2);
	  	    	assertEquals(k, 10);
	  	    }
	  	    
	  	    @Test
	  	    public void testgetBeverageNameNotExistBev() throws Exception{
	  	    	
	  	    	try{
	  	    		dI.getBeverageName(5);
	  	    		fail("Beverage does not exist");
	  	    	}catch(BeverageException r){
	  	    		assertTrue(true);
	  	    	}
	  	    }
	  	    
	  	    @Test
	  	    public void testgetBeverageNameCheck() throws Exception{
	  	    	try {
	  	    	String name = dI.getBeverageName(1);
	  	    	assertEquals(name, "coffee");
	  	    	}
	  	    	catch(BeverageException e)
	  	    	{
	  	    		fail();
	  	    	}
	  	    }
	  	    
	  	    @Test
	  	    public void testgetBeverageCapsulesPerBoxNotExist() throws Exception{
	  	    	try{
	  		    	dI.getBeverageCapsulesPerBox(100);
	  		    	fail("Beverage not existing");
	  	    	}catch(BeverageException e){
	  	    		assertTrue(true);
	  	    	}
	  	    }
	  	    
	  	    @Test
	  	    public void testgetBeverageCapsulesPerBox() throws Exception{
	  	    	try
	  	    	{
	  	    		dI.updateBeverage(2, "tea", 10, 15);
	  	    		int k = dI.getBeverageCapsulesPerBox(2);
	  	    		assertEquals(k, 10);
	  	    	}
	  	    	catch(BeverageException e)
	  	    	{
	  	    		fail();
	  	    	}
	  	    }
	  	    
	  	    @Test
	  	    public void testgetBeverageBoxPrice() throws Exception{
	  	    	dI.updateBeverage(1, "tae", 10, 15);
	  	    	int k = dI.getBeverageBoxPrice(1);
	  	    	assertEquals(k, 15);
	  	    }
	  	    
	  	    @Test
	  	    public void testgetBeverageBoxPriceNotExist() throws Exception{
	  	    	try{
	  	    		dI.getBeverageBoxPrice(1000);
	  		    	fail("Beverage not existing");
	  	    	}catch(BeverageException e){
	  	    		assertTrue(true);
	  	    	}
	  	    }
	  	    
	  	    @Test
	  	    public void testgetBeveragesId()
	  	    {
	  	    	List<Integer> ids = dI.getBeveragesId();
	  	    	List<Integer> exp = new LinkedList<Integer>();
	  	    	exp.add(new Integer(1));
	  	    	exp.add(new Integer(2));
	  	    	assertEquals(2,ids.size());
	  	    	assertEquals(exp.get(0),ids.get(0));
	  	    	assertEquals(exp.get(1),ids.get(1));
	  	    	assertEquals(exp,ids);
	  	    }
	  	    
	  	  @Test
	  	    public void testgetBeveragesIdEmpty()
	  	    {
	  		  	dI.reset();
	  	    	List<Integer> ids = dI.getBeveragesId();
	  	    	assertNotNull(ids);
	  	    	assertEquals(0,ids.size());
	  	    }
	  	    
	  	    @Test
	  	    public void testgetBeverages() throws Exception{
	  	    	
		        Map<Integer, String> bev = dI.getBeverages();
		        assertTrue(bev.containsKey(1));
		        assertEquals(bev.get(1), "coffee");
		        assertEquals(bev.get(2), "milk");
		        assertEquals(bev.size(), 2);	
	  	    }
	  	    
	  	  @Test
	  	    public void testgetBeveragesEmpty() throws Exception{
	  	    	dI.reset();
		        Map<Integer, String> bev = dI.getBeverages();
		        assertNotNull(bev);
		        assertEquals(0,bev.size());
	  	    }
	  	  
	  	    @Test
	  	    public void testgetBeverageCapsulesNotExis() throws Exception{
	  	    	try{
	  	    		dI.getBeverageCapsules(3);
	  	    		fail("Beverage not existing");
	  	    	}catch(BeverageException e){
	  	    		assertTrue(true);
	  	    	}
	  	    }
	  	    
	  	    @Test
	  	    public void testgetBeverageCapsules() throws Exception{
	  	    	try
	  	    	{
	  	    		int k = dI.getBeverageCapsules(1);
	  	    		assertEquals(k, 0);
	  	    	}
	  	    	catch(Exception e)
	  	    	{
	  	    		fail();
	  	    	}
	  	    }
	  	    
	  	    
	  	    @Test
	    	@SuppressWarnings("deprecation")
			public void testGetReportUnvalidDates() {
	    		
			    	//check start date null
			    	Date now = new Date();
			    	try {  //
						dI.getReport(null,now);
						fail("start date is null so an exception should be thrown");
					} catch (DateException e) {
						assertTrue(true);
					}
			    	
			    	//check end date null
			    	try {
						dI.getReport(now,null);
						fail("end date is null so an exception should be thrown");
					} catch (DateException e) {
						assertTrue(true);
					}
			    	//creo una data futura
			    	Date d1 = new Date();
			    	d1.setYear(d1.getYear()+1);
			    	try {
						dI.getReport(d1,now);
						fail("start date is after the current day so an exception should be thrown");
					} catch (DateException e) {
						assertTrue(true);
					}
			    	//check inverted dates (start date after end date)
			    	Date d= new Date(2000-1900,0,1,0,0);
			    	try {
						dI.getReport(now,d);
						fail("start date is after end date so an exception should be thrown");
					} catch (DateException e) {
						assertTrue(true);
					}
	    	}
	  	    @Test
	    	@SuppressWarnings("deprecation")
			public void testDateEmployeeException() {
	    		
			    	
			    	Date now = new Date();
			    	try {  //
			    		dI.getEmployeeReport(employeeIds[0], null, now);
						fail("start date is null so an exception should be thrown");
					} catch (DateException e) {
						assertTrue(true);
					} catch (EmployeeException e) {
						fail("Employee Id is correct");
					}
			    	
			    	
			    	try {
						dI.getEmployeeReport(employeeIds[0], now, null);
						fail("end date is null so an exception should be thrown");
					} catch (DateException e) {
						assertTrue(true);
					} catch (EmployeeException e) {
					
						fail("Employee Id is correct");
					}
			    	
			    	Date d= new Date(2000-1900,0,1,0,0);
			    	try {
						dI.getEmployeeReport(employeeIds[0], now,d);
						fail("start date is after and date so an exception should be thrown");
					} catch (DateException e) {
						assertTrue(true);
					} catch (EmployeeException e) {
						fail("Employee Ids is correct");
					
					}
			    	Date date= new Date();
			    	try {
						dI.getEmployeeReport(5, now,date);
						fail("Employee Ids not correct");
					} catch (DateException e) {
						fail("Date is correct");
					} catch (EmployeeException e) {
						assertTrue(true);
					}
			    	Date d1 = new Date();
			    	d1.setYear(d1.getYear()+1);
			    	try {
						dI.getEmployeeReport(employeeIds[0],d1,now);
						fail("start date is after the current day so an exception should be thrown");
					} catch (DateException e) {
						assertTrue(true);
					} catch (EmployeeException e) {
						fail();
					}
	    	}
	  	    
	  	  @Test
		  	@SuppressWarnings("deprecation")
		  	    public void testEmployeeReportEqualDates() {
	  		Date d= new Date();
	  		Date d1= new Date();
	  		d.setHours(0);
	  		d.setMinutes(0);
	  		d.setSeconds(0);
	  		d1.setHours(0);
	  		d1.setMinutes(0);
	  		d1.setSeconds(0);
			List<String> reportResult = null;
	    	//doing transactions to test report
	    	try {
				dI.rechargeAccount(employeeIds[0], 5000);
				dI.rechargeAccount(employeeIds[1], 20000);
				dI.buyBoxes(beverageIds[0], new Integer(3));
				dI.buyBoxes(beverageIds[1], new Integer(1));
				dI.sellCapsules(employeeIds[0], beverageIds[0], new Integer(5), true);
				dI.rechargeAccount(employeeIds[0], 2000);
				dI.sellCapsules(employeeIds[1], beverageIds[0], new Integer(5), false);
				dI.sellCapsules(employeeIds[0], beverageIds[0], new Integer(5), false);
				dI.sellCapsules(employeeIds[0], beverageIds[1], new Integer(5), true);
				reportResult=dI.getEmployeeReport(employeeIds[0],d, d1);
				assertEquals(5,reportResult.size());
		    	assertEquals(1,reportResult.stream().filter(v -> v.contains("CASH")).count());
		    	assertEquals(2,reportResult.stream().filter(v -> v.contains("BALANCE")).count());
		    	assertEquals(2,reportResult.stream().filter(v -> v.contains("RECHARGE")).count());
			} catch (DateException e) {
				fail();
			}
	    	catch(EmployeeException e)
	    	{
	    		fail();
	    	}
	    	catch(NotEnoughCapsules e)
	    	{
	    		fail();
	    	} catch (BeverageException e) {
				fail();
			} catch (NotEnoughBalance e) {
			fail();
			}
	  		  
	  	  }
	  	    
	  	  @Test
	  	@SuppressWarnings("deprecation")
	  	    public void testEmployeeReport() {
	  		Date d= new Date(2000-1900,0,1,0,0);
			List<String> reportResult = null;
	    	//doing transactions to test report
	    	try {
				dI.rechargeAccount(employeeIds[0], 5000);
				dI.rechargeAccount(employeeIds[1], 20000);
				dI.buyBoxes(beverageIds[0], new Integer(3));
				dI.buyBoxes(beverageIds[1], new Integer(1));
				dI.sellCapsules(employeeIds[0], beverageIds[0], new Integer(5), true);
				dI.rechargeAccount(employeeIds[0], 2000);
				dI.sellCapsules(employeeIds[1], beverageIds[0], new Integer(5), false);
				dI.sellCapsules(employeeIds[0], beverageIds[0], new Integer(5), false);
				dI.sellCapsules(employeeIds[0], beverageIds[1], new Integer(5), true);
				Date now=new Date();
				reportResult=dI.getEmployeeReport(employeeIds[0],d, now);
				assertEquals(5,reportResult.size());
		    	assertEquals(1,reportResult.stream().filter(v -> v.contains("CASH")).count());
		    	assertEquals(2,reportResult.stream().filter(v -> v.contains("BALANCE")).count());
		    	assertEquals(2,reportResult.stream().filter(v -> v.contains("RECHARGE")).count());
			} catch (Exception e) {
				fail();
			}
	  	    }
	  	  
	  	  
		  	    
	    	@Test
	    	@SuppressWarnings("deprecation")
			public void testGetReport() {
	    			Date d= new Date(2000-1900,0,1,0,0);
	    			List<String> reportResult = null;
			    	//doing transactions to test report
			    	try {
						dI.rechargeAccount(employeeIds[0], 5000);
						dI.rechargeAccount(employeeIds[1], 20000);
						dI.buyBoxes(beverageIds[0], new Integer(3));
						dI.buyBoxes(beverageIds[1], new Integer(1));
						dI.sellCapsules(employeeIds[0], beverageIds[0], new Integer(5), true);
						dI.sellCapsules(employeeIds[1], beverageIds[0], new Integer(5), false);
						dI.sellCapsulesToVisitor(beverageIds[1], new Integer(5));
						Date now=new Date();
						reportResult=dI.getReport(d, now);
						assertEquals(7,reportResult.size());
				    	assertEquals(1,reportResult.stream().filter(v -> v.contains("CASH")).count());
				    	assertTrue(reportResult.stream().filter(v -> v.contains("RECHARGE")).count()==2);
				    	assertTrue(reportResult.stream().filter(v -> !dI.matchDates(v, d, new Date())).count()==0);
					} catch (Exception e) {
						fail();
					}
		    }
	    	
	    	@Test
	    	@SuppressWarnings("deprecation")
			public void testReportEqualDates() {
			    	try {
			    		Date d= new Date();
				  		Date d1= new Date();
				  		d.setHours(0);
				  		d.setMinutes(0);
				  		d.setSeconds(0);
				  		d1.setHours(0);
				  		d1.setMinutes(0);
				  		d1.setSeconds(0);
			    		List<String> reportResult = null;
						dI.rechargeAccount(employeeIds[0], 5000);
						dI.rechargeAccount(employeeIds[1], 20000);
						dI.buyBoxes(beverageIds[0], new Integer(3));
						dI.buyBoxes(beverageIds[1], new Integer(1));
						dI.sellCapsules(employeeIds[0], beverageIds[0], new Integer(5), true);
						dI.sellCapsules(employeeIds[1], beverageIds[0], new Integer(5), false);
						dI.sellCapsulesToVisitor(beverageIds[1], new Integer(5));
						Date now=new Date();
						reportResult=dI.getReport(d, d1);
						assertEquals(7,reportResult.size());
				    	assertEquals(1,reportResult.stream().filter(v -> v.contains("CASH")).count());
				    	assertTrue(reportResult.stream().filter(v -> v.contains("RECHARGE")).count()==2);
				    	assertTrue(reportResult.stream().filter(v -> !dI.matchDates(v, d, now)).count()==0);
					} catch (Exception e) {
						fail();
					}
		    }
	    	
	    	@Test
	    	public void testBuyBoxesNotValidBeverageId() {
	    		try {
	    			dI.rechargeAccount(employeeIds[0], 5000);
	    			dI.buyBoxes(15, 2);
	    			fail("id should not exist");
	    		}catch(BeverageException be) {
	    			assertTrue(true);
	    		}catch(NotEnoughBalance neb) {
	    			fail();
	    		}catch(EmployeeException ee) {
	    			fail();
	    		}
	    	}
	    	
	    	@Test
	    	public void testBuyBoxesNotValidBalance() {
	    		try {
	    			int liquidGold=dI.createBeverage("liquidGold", 20, 15000000);
	    			dI.rechargeAccount(employeeIds[0], 5000);
	    			dI.buyBoxes(liquidGold, 2);
	    			fail("balance should not be enought to buy liquid gold");
	    		}catch(BeverageException be) {
	    			fail();
	    		}catch(NotEnoughBalance neb) {
	    			assertTrue(true);
	    		}catch(EmployeeException ee) {
	    			fail();
	    		}
	    	}
	    	@Test
	    	public void testBuyBoxesValid() {
	    		try {
	    			dI.rechargeAccount(employeeIds[0], 500000);
	    			int hot_water=dI.createBeverage("hot_water",10, 1);
	    			int previousBalance=dI.getBalance();
	    			dI.buyBoxes(hot_water,1);
	    			assertEquals(dI.getBalance(), new Integer(previousBalance-1));
	    			assertEquals(dI.getBeverageCapsules(hot_water),new Integer(10));
	    		}catch(BeverageException be) {
	    			fail();
	    		}catch(NotEnoughBalance neb) {
	    			fail();
	    		}catch(EmployeeException ee) {
	    			fail();
	    		}
	    	}
	    	
	    	@Test
	    	public void testBuyBoxesValidEqualNumberOfAvailable() {
	    		try {
	    			dI.rechargeAccount(employeeIds[0], 1);
	    			int hot_water=dI.createBeverage("hot_water",10, 1);
	    			int previousBalance=dI.getBalance();
	    			dI.buyBoxes(hot_water,1);
	    			assertEquals(dI.getBalance(), new Integer(previousBalance-1));
	    			assertEquals(dI.getBeverageCapsules(hot_water),new Integer(10));
	    		}catch(BeverageException be) {
	    			fail();
	    		}catch(NotEnoughBalance neb) {
	    			fail();
	    		}catch(EmployeeException ee) {
	    			fail();
	    		}
	    	}
	    	
	    	@Test
	    	public void testResetDataImplMapsCleared() {
	    		dI.reset();
	    		assertTrue(dI.getEmployees().isEmpty());
	    		assertTrue(dI.getEmployeesId().isEmpty());
	    		assertTrue(dI.getBeverages().isEmpty());
	    		assertTrue(dI.getBeveragesId().isEmpty());
	    		
	    		@SuppressWarnings("deprecation")
				Date d= new Date(2000-1900,0,1,0,0);
    			Date now = new Date();
	    		try {
					int employee = dI.createEmployee("ferdinando", "rossi");
					int beverage = dI.createBeverage("coffee", 15, 30);
					assertTrue(dI.getBeverages().size()==1);
					assertFalse(dI.getEmployees().size()!=1);
					assertEquals(dI.getEmployeeReport(employee,d,now).size(),0);
					assertEquals(dI.getBeverageName(beverage),"coffee");
					dI.reset();
					dI.getEmployeeName(employee);
					fail("should trow an employee exception, employee should not exist anymore");
				} catch (Exception e) {
				}
	    	}
	    	
	    	@Test
	    	public void testResetDataImplFilesClosed() {
	    		@SuppressWarnings("deprecation")
				Date d= new Date(2000-1900,0,1,0,0);
    			Date now = new Date();
		    	try {
					dI.rechargeAccount(employeeIds[0], 5000);
					dI.rechargeAccount(employeeIds[1], 20000);
					dI.buyBoxes(beverageIds[0], new Integer(3));
					dI.buyBoxes(beverageIds[1], new Integer(1));
					dI.sellCapsules(employeeIds[0], beverageIds[0], new Integer(5), true);
					dI.sellCapsules(employeeIds[1], beverageIds[0], new Integer(5), false);
					dI.sellCapsulesToVisitor(beverageIds[1], new Integer(5));
					now=new Date();
					
					dI.reset();
					assertFalse(dI.getBalance()!=0);
					assertEquals(dI.getReport(d,now).size(),0);
					assertEquals(dI.getEmployeesId().size(),0);
					assertEquals(dI.getBeveragesId().size(),0);
				} catch (Exception e) {
					fail();
				}
	    	}
	    	@Test
			public void testcreateEmployee() throws Exception{
		    	setUp();
		    	dI.createEmployee("Giacomo", "Blanco");
		    	dI.createEmployee("Erich", "Malan");
		    	dI.createEmployee("Klodiana", "Cika");
		    	dI.createEmployee("Antonino", "Angi");
		    	
		    	assertEquals(dI.getEmployeesId().size(),6);
		        assertEquals(FileManager.readEmployees().size(),6 );   
		    }
	     
	    	@Test
			public void testUpdateEmployee() throws Exception{
		    	setUp();
		    	dI.createEmployee("Giacomo", "Blanco");
		    	dI.createEmployee("Erich", "Malan");
		    	dI.createEmployee("Klodiana", "Cika");
		    	dI.createEmployee("Antonino", "Angi");
		    	dI.updateEmployee(5, "Umberto", "Maggio");
		    	
		    	assertEquals(dI.getEmployeesId().size(),6);
		        assertEquals(FileManager.readEmployees().size(),6 );
		        assertEquals(dI.getEmployeeName(5),"Umberto");
		        assertEquals(dI.getEmployeeSurname(5),"Maggio");
		        
		    }
	    	@Test
	  	    public void testUpdateEmployeeNotExisting() throws Exception{
	  	    	try{
	  	    		setUp();
	  	    		dI.updateEmployee(5, "Mario", "Rossi");
	  	    		fail("Non Existing Employee");
	  	    	}catch(EmployeeException e){
	  	    		assertTrue(true);
	  	    	}
	  	    }
	    	@Test
	  	    public void testgetEmployeeNameNonExisting() throws Exception{
	  	    	try{
	  	    		setUp();
	  	    		dI.getEmployeeName(6);
	  	    		fail("Non Existing Employee");
	  	    	}catch(EmployeeException e){
	  	    		assertTrue(true);
	  	    	}
	  	    }
	    	@Test
	  	    public void testgetEmployeeSurameNonExisting() throws Exception{
	  	    	try{
	  	    		setUp();
	  	    		dI.getEmployeeSurname(7);
	  	    		fail("Non Existing Employee");
	  	    	}catch(EmployeeException e){
	  	    		assertTrue(true);
	  	    	}
	  	    }
	    	@Test
			public void testEmployeeBalance() throws Exception{
		    	setUp();
		    	dI.rechargeAccount(employeeIds[0], 5000);
		    	dI.buyBoxes(beverageIds[0],  new Integer(5));
		    	int balance;
		    	balance=dI.sellCapsules(employeeIds[0], beverageIds[0], new Integer(20), true);
		    	
		    	
		    	assertEquals(dI.getEmployeeBalance(employeeIds[0]),new Integer(balance)); 
		    }
	    	@Test
	  	    public void testEmployeeBalanceNonExisting() throws Exception{
	  	    	try{
	  	    		
	  	    		dI.getEmployeeBalance(3);
	  	    		fail("Non Existing Employee");
	  	    	}catch(EmployeeException e){
	  	    		assertTrue(true);
	  	    	}
	  	    }
	    	@Test
			public void testEmployeesId() throws Exception{
	    		List<Integer> employeesId=new ArrayList<>();
	    		List<Integer> exp= dI.getEmployeesId();
		    	employeesId.add(new Integer(1));
		    	employeesId.add(new Integer(2));
		    	assertNotNull(exp);
		    	assertEquals(employeesId,exp);
		    	assertEquals(exp.size(),2);
		    	dI.createEmployee("Mario", "Rossi");
		    	dI.createEmployee("Marco", "MARCO");
		    	employeesId= dI.getEmployeesId();
		    	assertEquals(employeesId.size(),4);

		    }
	    	@Test
			public void testgetEmployeeName() throws Exception{
	    		
		    	dI.createEmployee("Mario", "Rossi");
		    	dI.createEmployee("Luca", "Luce");
		    	assertEquals(dI.getEmployeeName(3),"Mario");
		    	assertEquals(dI.getEmployeeName(1),"mario");
		    	assertEquals(dI.getEmployeeName(4),"Luca");
		    	assertEquals(dI.getEmployeeName(2),"giuseppe");
		    	assertEquals(dI.getEmployeesId().size(),4);
		    }
	    	@Test
			public void testgetEmployeeSurname() throws Exception{
	    		
		    	dI.createEmployee("Mario", "Rossi");
		    	dI.createEmployee("Luca", "Luce");
		    	assertEquals(dI.getEmployeeSurname(3),"Rossi");
		    	assertEquals(dI.getEmployeeSurname(1),"rossi");
		    	assertEquals(dI.getEmployeeSurname(4),"Luce");
		    	assertEquals(dI.getEmployeeSurname(2),"verdi");
		    	assertEquals(dI.getEmployeesId().size(),4);		
		    }
	    	
	    	@Test
	    	public void testEmployeesIdEmpty()
	    	{
	    		dI.reset();
	    		List<Integer> exp= dI.getEmployeesId();
	    		assertNotNull(exp);
	    		assertEquals(0,exp.size());
	    	}
	    	@Test
			public void testgetEmployees() throws Exception{
		    	dI.createEmployee("Mario", "Rossi");
		    	dI.createEmployee("Marco", "MARCO");
		    	Map<Integer, String> employeesId= dI.getEmployees();
		    	assertEquals(employeesId.size(),4);
		    	assertEquals(employeesId.get(3),"Mario Rossi");
		    	assertEquals(employeesId.get(4),"Marco MARCO");
		    	assertEquals(employeesId.get(1),"mario rossi");
		    	assertEquals(employeesId.get(2),"giuseppe verdi");
		    	
		    	List<Integer> keys = new LinkedList<>();
		    	keys.add(new Integer(1));
		    	keys.add(new Integer(2));
		    	keys.add(new Integer(3));
		    	keys.add(new Integer(4));
		    	assertEquals(keys,employeesId.keySet().stream().collect(Collectors.toList()));
		    }
	    	
	    	@Test
			public void testgetEmployeesEmpty() throws Exception{
	    		dI.reset();
	    		Map<Integer, String> employeesId= dI.getEmployees();
	    		assertNotNull(employeesId);
	    		assertEquals(0,employeesId.size());
	    	}
	    	
	     
}
