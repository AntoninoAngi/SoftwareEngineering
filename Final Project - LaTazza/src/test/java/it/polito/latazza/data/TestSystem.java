package it.polito.latazza.data;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.DateException;
import it.polito.latazza.exceptions.EmployeeException;
import it.polito.latazza.exceptions.NotEnoughBalance;
import it.polito.latazza.exceptions.NotEnoughCapsules;

 

public class TestSystem {

	DataImpl dI= new DataImpl();
	int[] employeeIds = new int[2];
	int[] beverageIds = new int[2];
	
	
	@BeforeEach
	 protected void setUp() throws Exception {
			dI.reset();
	    	employeeIds[0] = dI.createEmployee("mario", "rossi");
	    	employeeIds[1] = dI.createEmployee("giuseppe", "verdi");
			beverageIds[0] = dI.createBeverage("coffee", 50, 1000);
			beverageIds[1] = dI.createBeverage("milk", 100, 200);
	    }
	
	
	@Test
	public void testScenario1() throws Exception{
        try {
        	int empAccount = dI.rechargeAccount(1,2000);
        	assertEquals(new Integer(2000), dI.getBalance());
        	dI.buyBoxes(1,1);
        	assertEquals(new Integer(1000), dI.getBalance());
        	assertEquals(new Integer(50),dI.getBeverageCapsules(1));
        	int newEmpAccount = dI.sellCapsules(1, 1, 1, true);
        	assertEquals(new Integer(1000), dI.getBalance());
        	assertEquals(new Integer(49),dI.getBeverageCapsules(1));
            assertEquals(2000,empAccount);
            assertEquals(1980,newEmpAccount);
            assertEquals(new Integer(1980),dI.getEmployeeBalance(1));
        } catch (Exception r) {
           fail();
        }   
}
	
	@Test
    public void testScenario2() throws Exception{
    	
    	try{
    		dI.sellCapsules(1, 0, 10, true);
    		if (dI.getEmployeeBalance(1) < 0){
    			fail("Not enough balance");
    		}
    	}catch(Exception r){
    		
    	}
    }


@Test
	public void testScenario3() throws Exception{
	try {
		dI.rechargeAccount(1,10000);
		assertEquals(new Integer(10000), dI.getBalance());
		dI.buyBoxes(1,1);
		assertEquals(new Integer(0),dI.getBeverageCapsules(2));
		assertEquals(new Integer(9000), dI.getBalance());
		dI.sellCapsulesToVisitor(1,10);
		assertEquals(new Integer(0),dI.getBeverageCapsules(2));
		assertEquals(new Integer(9200),dI.getBalance());
        } catch (Exception r) {
           fail();
        }   
}
	@Test
	public void testScenario4() throws Exception{
		
        try {
        	dI.rechargeAccount(1, 1000);
        	assertEquals(new Integer(1000), dI.getBalance());
        	dI.buyBoxes(1, 1);
        	assertEquals(new Integer(50),dI.getBeverageCapsules(1));
        	dI.sellCapsules(1, 1, 50, true);
        	dI.sellCapsulesToVisitor(1,10 );
        	fail();
        } catch (NotEnoughCapsules r) {
           assertTrue(true);
        } }
	 @Test
	    public void testScenario5() {
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
	public void testScenario6() {
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
	public void testScenario7() {
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
  	@SuppressWarnings("deprecation")
  	    public void testScenario8() {
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
		public void test1Scenario9() {
 		
		    	
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
	  	    public void test2Scenario9() {
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
		public void testScenario10() {
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
		public void test1Scenario11() {
 		
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
		public void test2Scenario11() {
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
	 @Test 	public void testPerformanceFR1() {
	    	try {
				long millisSum = 0;
				long millisStart = 0;
				long millisEnd = 0;
				
				for(int i=0; i<100; i++ ) {
					setUp();
					dI.rechargeAccount(employeeIds[0], 5000);
					dI.buyBoxes(beverageIds[0], new Integer(3));
					dI.buyBoxes(beverageIds[1], new Integer(1));
					millisStart = Calendar.getInstance().getTimeInMillis();
					dI.sellCapsules(employeeIds[0], beverageIds[0], new Integer(5), true);
					millisEnd = Calendar.getInstance().getTimeInMillis();
					millisSum += (millisEnd-millisStart); 
				}
				millisSum = millisSum / 100;
				assertTrue(millisSum < 500);
			} catch (Exception e) {
				fail();
			}
	 }

	 
	 @Test 	public void testPerformanceFR2() {
	    	try {
				long millisSum = 0;
				long millisStart = 0;
				long millisEnd = 0;
				
				for(int i=0; i<100; i++ ) {
					setUp();
					dI.rechargeAccount(employeeIds[0], 5000);
					dI.rechargeAccount(employeeIds[1], 20000);
					dI.buyBoxes(beverageIds[0], new Integer(3));
					dI.buyBoxes(beverageIds[1], new Integer(1));
					millisStart = Calendar.getInstance().getTimeInMillis();
					dI.sellCapsulesToVisitor(beverageIds[1], new Integer(5));
					millisEnd = Calendar.getInstance().getTimeInMillis();
					millisSum += (millisEnd-millisStart); 
				}
				millisSum = millisSum / 100;
				assertTrue(millisSum < 500);
			} catch (Exception e) {
				fail();
			}
	 }
	 
	 @Test 	public void testPerformanceFR3() {
	    	try {
				long millisSum = 0;
				long millisStart = 0;
				long millisEnd = 0;
				
				for(int i=0; i<100; i++ ) {
					setUp();
					millisStart = Calendar.getInstance().getTimeInMillis();
					dI.rechargeAccount(employeeIds[0], 5000);
					millisEnd = Calendar.getInstance().getTimeInMillis();
					millisSum += (millisEnd-millisStart); 
				}
				millisSum = millisSum / 100;
				assertTrue(millisSum < 500);
			} catch (Exception e) {
				fail();
			}
	 }
	 
	 @Test 	public void testPerformanceFR4() {
	    	try {
				long millisSum = 0;
				long millisStart = 0;
				long millisEnd = 0;
				
				for(int i=0; i<100; i++ ) {
					setUp();
					dI.rechargeAccount(employeeIds[0], 5000);
					millisStart = Calendar.getInstance().getTimeInMillis();
					dI.buyBoxes(beverageIds[0], new Integer(3));
					millisEnd = Calendar.getInstance().getTimeInMillis();
					millisSum += (millisEnd-millisStart); 
				}
				assertEquals(dI.getBeverageCapsules(beverageIds[0]), (Integer) (3*50));
				millisSum = millisSum / 100;
				assertTrue(millisSum < 500);
			} catch (Exception e) {
				fail();
			}
	 }
	 
	 @SuppressWarnings("deprecation")
	@Test 	
	 public void testPerformanceFR5() {
		 try {
				long millisSum = 0;
				long millisStart = 0;
				long millisEnd = 0;
				Date d= new Date();
		  		Date d1= new Date();
		  		d.setHours(0);
		  		d.setMinutes(0);
		  		d.setSeconds(0);
		  		d1.setHours(0);
		  		d1.setMinutes(0);
		  		d1.setSeconds(0);
		  		d1.setDate(d1.getDate()+1);
				for(int i=0; i<100; i++ ) {
					setUp();
					dI.rechargeAccount(employeeIds[0], 5000);
					dI.buyBoxes(beverageIds[0], new Integer(3));
					dI.sellCapsules(employeeIds[0], beverageIds[0], new Integer (3), true);
					dI.sellCapsules(employeeIds[0], beverageIds[0], new Integer (3), false);
					dI.rechargeAccount(employeeIds[0], 1500);
					millisStart = Calendar.getInstance().getTimeInMillis();
					dI.getEmployeeReport(employeeIds[0], d, d1);
					millisEnd = Calendar.getInstance().getTimeInMillis();
					millisSum += (millisEnd-millisStart);
				}
				millisSum = millisSum / 100;
				assertTrue(millisSum < 500);
			} catch (Exception e) {
				fail();
			}
	 }
	 
	 @SuppressWarnings("deprecation")
		@Test 	
		 public void testPerformanceFR6() {
			 try {
					long millisSum = 0;
					long millisStart = 0;
					long millisEnd = 0;
					Date d= new Date();
			  		Date d1= new Date();
			  		d.setHours(0);
			  		d.setMinutes(0);
			  		d.setSeconds(0);
			  		d1.setHours(0);
			  		d1.setMinutes(0);
			  		d1.setSeconds(0);
			  		d1.setDate(d1.getDate()+1);
					for(int i=0; i<100; i++ ) {
						setUp();
						dI.rechargeAccount(employeeIds[0], 5000);
						dI.buyBoxes(beverageIds[0], new Integer(3));
						dI.sellCapsules(employeeIds[0], beverageIds[0], new Integer (3), true);
						dI.sellCapsules(employeeIds[0], beverageIds[0], new Integer (3), false);
						dI.rechargeAccount(employeeIds[0], 1500);
						millisStart = Calendar.getInstance().getTimeInMillis();
						dI.getReport(d, d1);
						millisEnd = Calendar.getInstance().getTimeInMillis();
						millisSum += (millisEnd-millisStart);
					}
					millisSum = millisSum / 100;
					assertTrue(millisSum < 500);
				} catch (Exception e) {
					fail();
				}
		 }
	 
	 @Test
	 public void testPerformanceFR7() {
		 try {
				long millisSum = 0;
				long millisStart = 0;
				long millisEnd = 0;
				int i;
				
				for(i=0; i<100; i++) {
					setUp();
					millisStart = Calendar.getInstance().getTimeInMillis();
					dI.updateBeverage(beverageIds[0], "coffeee", 20, 500);
					millisEnd = Calendar.getInstance().getTimeInMillis();
					millisSum += (millisEnd-millisStart); 
				}
				millisSum = millisSum / 100;
				assertTrue(millisSum < 500);
				millisSum = 0;
				
				for(i=0; i<100; i++) {
					setUp();
					millisStart = Calendar.getInstance().getTimeInMillis();
					dI.createBeverage("tea", 30, 400);
					millisEnd = Calendar.getInstance().getTimeInMillis();
					millisSum += (millisEnd-millisStart); 
				}
				millisSum = millisSum / 100;
				assertTrue(millisSum < 500);
			} catch (Exception e) {
				fail();
			}
	 }
	 
	 @Test
	 public void testPerformanceFR8() {
		 try {
				long millisSum = 0;
				long millisStart = 0;
				long millisEnd = 0;
				int i;
				
				for(i=0; i<100; i++) {
					setUp();
					millisStart = Calendar.getInstance().getTimeInMillis();
					dI.updateEmployee(employeeIds[0], "alessandro", "manzoni");
					millisEnd = Calendar.getInstance().getTimeInMillis();
					millisSum += (millisEnd-millisStart); 
				}
				millisSum = millisSum / 100;
				assertTrue(millisSum < 500);
				millisSum = 0;
				
				for(i=0; i<100; i++) {
					setUp();
					millisStart = Calendar.getInstance().getTimeInMillis();
					dI.createEmployee("ugo", "foscolo");
					millisEnd = Calendar.getInstance().getTimeInMillis();
					millisSum += (millisEnd-millisStart); 
				}
				millisSum = millisSum / 100;
				assertTrue(millisSum < 500);
			} catch (Exception e) {
				fail();
			}
	 }
}
