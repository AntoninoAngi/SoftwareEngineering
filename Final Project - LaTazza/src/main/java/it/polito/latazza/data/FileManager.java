package it.polito.latazza.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.EmployeeException;


public class FileManager {
	
	public static Map<Integer,Employee> readEmployees()
	{
		FileInputStream fstream;
		try 
		{
			fstream = new FileInputStream("employees.txt");
		Map<Integer,Employee> employees = new HashMap<>();
			DataInputStream in =new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line;
			String [] fields;
			while((line=br.readLine()) != null)
			{
				fields=line.split(",");
				int id=Integer.parseInt(fields[0]);
				int balance=Integer.parseInt(fields[3]);
				Employee e = new Employee(id,fields[1],fields[2],balance);
				employees.put(id, e);
			}
			br.close();
			in.close();
			fstream.close();
			return employees;
		}
		catch (Exception e)
		{
			return null;
		}		
	}
	
	public static Map<Integer,Beverage> readBeverages()
	{
		FileInputStream fstream;
		try 
		{
			fstream = new FileInputStream("beverages.txt");
		Map<Integer,Beverage> beverages = new HashMap<>();
			DataInputStream in =new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line;
			String [] fields;
			while((line=br.readLine()) != null)
			{
				fields=line.split(",");
				int id=Integer.parseInt(fields[0]);
				int capsulesPerBox=Integer.parseInt(fields[2]);
				int boxPrice =Integer.parseInt(fields[3]);
				int available = Integer.parseInt(fields[4]);
				Beverage b = new Beverage(id,fields[1],capsulesPerBox,boxPrice,available);
				beverages.put(id, b);
			}
			br.close();
			in.close();
			fstream.close();
			return beverages;
		}
		catch (Exception e)
		{
			return null;
		}		
	}
	
	public static boolean writeSaleReportEmployee(Integer employeeId,String employeeName,String employeeSurname,String beverage,int number,String type)
	throws EmployeeException
	{
		if(employeeId <= 0) throw new EmployeeException();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		try(FileWriter fw = new FileWriter("reports.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
				if(type.equals("RECHARGE"))
				{
					double amount = number*1.0/100;
					String amountString = String.format("%.2f \u20ac", amount);
					out.println(dateFormat.format(date)+" "+employeeId+" "+type+" "+employeeName+" "+employeeSurname+" "+amountString);
				}
				else
					out.println(dateFormat.format(date)+" "+employeeId+" "+type+" "+employeeName+" "+employeeSurname+" "+beverage+" "+number);
			    out.flush();
			    bw.close();
			    fw.close();
			} 
		catch (IOException e) {
			    return false;
			}
		return true;
		
	}
	
	public static boolean writeSaleReportVisitor(String beverage,int number)
	{
		String type = new String("VISITOR");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		try(FileWriter fw = new FileWriter("reports.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println(dateFormat.format(date)+" "+type+" "+beverage+" "+number);
			    out.flush();
			    bw.close();
			    fw.close();
			} 
		catch (IOException e) {
			    return false;
			}
		return true;
		
	}
	
	public static boolean writeBuyReport(String beverage,int number)
	{
		String type = new String("BUY");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		try(FileWriter fw = new FileWriter("reports.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println(dateFormat.format(date)+" "+type+" "+beverage+" "+number);
			    out.flush();
			    bw.close();
			    fw.close();
			} 
		catch (IOException e) {
			    return false;
			}
		return true;
		
	}
	
	public static void updateEmployee(Employee e) throws EmployeeException
	{
		if(e.getId() <= 0) throw new EmployeeException();
		FileInputStream fstream = null; 
	      DataInputStream in = null;
	      BufferedWriter out = null;
	    
	      try {
	         // apro il file
	         fstream = new FileInputStream("employees.txt");
	     
	         // prendo l'inputStream
	         in = new DataInputStream(fstream);
	         BufferedReader br = new BufferedReader(new InputStreamReader(in));
	         String strLine;
	         String fields[];
	         StringBuilder fileContent = new StringBuilder();
	     
	         // Leggo il file riga per riga
	         while ((strLine = br.readLine()) != null) 
	         {
	        	 fields=strLine.split(",");
	        	 int id=Integer.parseInt(fields[0]);
	            if(id==e.getId())
	            {
	               // se la riga Ã¨ uguale a quella ricercata
	               fileContent.append(e.toString()+System.getProperty("line.separator"));
	            } else {
	               // ... altrimenti la trascrivo cosÃ¬ com'Ã¨
	               fileContent.append(strLine);
	               fileContent.append(System.getProperty("line.separator"));
	            }
	         }
	  
	         // Sovrascrivo il file con il nuovo contenuto (aggiornato)
	         FileWriter fstreamWrite = new FileWriter("employees.txt");
	         out = new BufferedWriter(fstreamWrite);
	         out.write(fileContent.toString());
	         fstream.close();
	         out.flush();
	         out.close();
	         in.close();
	      }
	      catch (Exception e2) 
	      {
	         return;
	      } 
	      return;
	}
	
	public static void updateBeverage(Beverage b) throws BeverageException
	{
		
		if(b.getId() <=0) throw new BeverageException();
		FileInputStream fstream = null; 
	      DataInputStream in = null;
	      BufferedWriter out = null;
	    
	      try {
	         // apro il file
	         fstream = new FileInputStream("beverages.txt");
	     
	         // prendo l'inputStream
	         in = new DataInputStream(fstream);
	         BufferedReader br = new BufferedReader(new InputStreamReader(in));
	         String strLine;
	         String fields[];
	         StringBuilder fileContent = new StringBuilder();
	     
	         // Leggo il file riga per riga
	         while ((strLine = br.readLine()) != null) 
	         {
	        	 fields=strLine.split(",");
	        	 int id=Integer.parseInt(fields[0]);
	            if(id==b.getId())
	            {
	               // se la riga è uguale a quella ricercata
	               fileContent.append(b.toString()+System.getProperty("line.separator"));
	            } else {
	               // ... altrimenti la trascrivo così com'è
	               fileContent.append(strLine);
	               fileContent.append(System.getProperty("line.separator"));
	            }
	         }
	  
	         // Sovrascrivo il file con il nuovo contenuto (aggiornato)
	         FileWriter fstreamWrite = new FileWriter("beverages.txt");
	         out = new BufferedWriter(fstreamWrite);
	         out.write(fileContent.toString());
	         fstream.close();
	         out.flush();
	         out.close();
	         in.close();
	      } catch (Exception e2) 
	      {
	         return;
	      } 
		return;
	}

	public static LaTazzaAccount readBalance() {
		LaTazzaAccount account = null;
		try
		{
			FileInputStream fstream=new FileInputStream("balance.txt");
			DataInputStream ds = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(ds));
			String line = br.readLine();
			int balance = Integer.parseInt(line.trim());
			br.close();
			account = new LaTazzaAccount(balance);
		}
		catch(Exception e)
		{
			account=new LaTazzaAccount(0);
		}
		return account;
	}

	public static void updateBalance(int balance) {
		
		FileWriter fstreamWrite;
		try {
			fstreamWrite = new FileWriter("balance.txt");
			BufferedWriter out = new BufferedWriter(fstreamWrite);
	        out.write(Integer.toString(balance));
	        out.close();
		} catch (Exception e) {
			return;
		}
	}
	
	public static int writeBeverage(Beverage b) throws BeverageException {
		
		if(b.getId() <= 0) throw new BeverageException();
		try{

		    BufferedWriter writer = new BufferedWriter(new FileWriter("beverages.txt", true)); 
  
		    writer.write(b.toString());
		    writer.newLine(); 
		    writer.close();
			}catch (Exception e2){
				return 0;
			}
			return 1;
	}

	// return a strings list about transactions of an identified employee
	public static List<String> employeeReport(Integer id) {
		
		FileInputStream fstream;
		try 
		{
			fstream = new FileInputStream("reports.txt");
			List<String> employeeReports = new ArrayList<String>();
			DataInputStream in =new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while((strLine=br.readLine()) != null)
			{
				String[] fields = strLine.split(" ");
				if( (fields[3].equals("CASH")) || (fields[3].equals("BALANCE")) || (fields[3].equals("RECHARGE")))
				{
					if(id == Integer.parseInt(fields[2]))
					{
					//add line to employeeReportt
						String line = removeId(strLine);
						employeeReports.add(line);
					}
				}
			}
			br.close();
			in.close();
			fstream.close();
			return employeeReports;
		}
		catch (IOException e)
		{
			return new ArrayList<String>();
		}		
	}

	// return list Strings about sales to visitors
	public static List<String> report() {
		
		FileInputStream fstream;
		try 
		{
			fstream = new FileInputStream("reports.txt");
			List<String> reports = new ArrayList<String>();
			DataInputStream in =new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while((strLine=br.readLine()) != null)
			{
				String[] fields = strLine.split(" ");
				if( (fields[3].equals("CASH")) || (fields[3].equals("BALANCE")) || (fields[3].equals("RECHARGE")))
				{
					//remove employee id and add line to report
						String line = removeId(strLine);
						reports.add(line);
				}
				else
					reports.add(strLine);
			}
			br.close();
			in.close();
			fstream.close();
			return reports;	
		}
		catch (IOException e)
		{
			return new ArrayList<String>();
		}
		
	}


	public static int writeEmployee(Employee e) throws EmployeeException {
		
		if(e.getId() <= 0) throw new EmployeeException();
		try{

	    BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt", true)); 

	    writer.write(e.toString());
	    writer.newLine();   
	    writer.close();
		}catch (Exception e2){
			return 0;
		}
		return 1;
	}

	public static int deleteEmployees() {
		try{
    		
    		File file = new File("employees.txt");
        	
    		if(file.delete()){
    			return 1;
    		}else{
    			return 0;
    		}
    	   
    	}catch(Exception e){
    		return 0;
    	}
	}
	
	public static int deleteBeverages() {
		try{
    		
    		File file = new File("beverages.txt");
        	
    		if(file.delete()){
    			return 1;
    		}else{
    			return 0;
    		}
    	   
    	}catch(Exception e){
    		return 0;
    	}
	}
	
	public static String removeId(String s) {
		String[] splitted = s.split(" ");
		splitted[2]="";
		s="";
		for(String s1 : splitted) {
			s+=s1+" ";
		}
		return s;
	}
	

	public static int deleteReports() {
		try{
			File file = new File ("reports.txt");
			File file2 = new File("balance.txt");
			boolean f1 = file.delete();
			boolean f2 = file2.delete();
        	
    		if(f1 && f2){
    			return 1;
    		}else{
    			return 0;
    		}
		}catch(Exception e2){
			return 0;
		}
	}
}
