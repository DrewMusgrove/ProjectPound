/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.drewmusgrove.projectpound;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import org.h2.tools.Csv;
import org.h2.tools.SimpleResultSet;



/**
 *
 * @author drew.musgrove
 */
public class Customer {
    String csvFile = "C:\\Users\\drew.musgrove\\Documents\\NetBeansProjects\\ProjectPound\\src\\main\\resources\\excel\\Database.csv";
        
    private String customerID;
    private String customerName;
    private String customerAccountNumber;
    private String customerBalance;
    
    public String getName(String customerID){
        String line;
        String cvsSplitBy = ",";
        System.out.println("customerID:" + customerID); 
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
        {

            while ((line = br.readLine()) != null) 
            {

                // use comma as separator
                String[] Row = line.split(cvsSplitBy);

                String customerIDcsv = Row[0];
                String customerNamecsv = Row[2];
                if (customerIDcsv.equals(customerID))
                {
                    return customerNamecsv;
                }    

            }            
        } 
        catch (IOException e) 
        {
            
            String error = "Error found : '" + e.toString() + "'";
            System.out.println("NO FILE FOUND!!");
            System.out.println(error);
            //e.printStackTrace();
        }
        
        return "Unknown!";
    }
    
    public String getAccountNumber(String customerID){
        String line;
        String cvsSplitBy = ",";
        System.out.println("customerID:" + customerID); 
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
        {

            while ((line = br.readLine()) != null) 
            {

                // use comma as separator
                String[] Row = line.split(cvsSplitBy);

                String customerIDcsv = Row[0];
                String customerAccountNumbercsv = Row[1];
                System.out.println("customerAccountNumbercsv:" + customerAccountNumbercsv); 
                if (customerIDcsv.equals(customerID))
                {
                    return customerAccountNumbercsv;
                }    

            }            
        } 
        catch (IOException e) 
        {
            
            String error = "Error found : '" + e.toString() + "'";
            System.out.println("NO FILE FOUND!!");
            System.out.println(error);
            //e.printStackTrace();
        }
        
        return "Unknown!";
    }
    
    public String getBalance(String customerID){
        
        String line;
        String cvsSplitBy = ",";
        System.out.println("customerID:" + customerID); 
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
        {

            while ((line = br.readLine()) != null) 
            {

                // use comma as separator
                String[] Row = line.split(cvsSplitBy);

                String customerIDcsv = Row[0];
                String customerBalancecsv = Row[3];
                System.out.println("customerAccountNumbercsv:" + customerBalancecsv); 
                if (customerIDcsv.equals(customerID))
                {
                    return "£" + customerBalancecsv;
                }    

            }            
        } 
        catch (IOException e) 
        {
            
            String error = "Error found : '" + e.toString() + "'";
            System.out.println("NO FILE FOUND!!");
            System.out.println(error);
            //e.printStackTrace();
        }
        
        return "0.00";
    }    
    
    public void connectDB()   
    {
       Connection c = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:test.db");
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("Opened database successfully");
    }
    
     public void createTable()   
    {
      Connection c = null;
      Statement stmt = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:test.db");
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         String sql = "CREATE TABLE CUSTOMERS " +
                        "(ID INT PRIMARY KEY     NOT NULL," +
                        " NAME           TEXT    NOT NULL, " + 
                        " AGE            INT     NOT NULL, " + 
                        " ACCOUNTNO        INT, " + 
                        " BALANCE         REAL)"; 
         stmt.executeUpdate(sql);
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("Table created successfully");
    }
    
    public void insertTable()      
    {
     Connection c = null;
      Statement stmt = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:test.db");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         String sql = "INSERT INTO CUSTOMERS (ID,NAME,AGE,ACCOUNTNO,BALANCE) " +
                        "VALUES (1, 'Drew', 23, '000001', 20000.00 );"; 
         stmt.executeUpdate(sql);

         sql = "INSERT INTO CUSTOMERS (ID,NAME,AGE,ACCOUNTNO,BALANCE) " +
                  "VALUES (2, 'Allen', 25, '000002', 15000.00 );"; 
         stmt.executeUpdate(sql);

         sql = "INSERT INTO CUSTOMERS (ID,NAME,AGE,ACCOUNTNO,BALANCE) " +
                  "VALUES (3, 'Teddy', 23, '000003', 20000.00 );"; 
         stmt.executeUpdate(sql);

         sql = "INSERT INTO CUSTOMERS (ID,NAME,AGE,ACCOUNTNO,BALANCE) " +
                  "VALUES (4, 'Mark', 25, '000004 ', 65000.00 );"; 
         stmt.executeUpdate(sql);

         stmt.close();
         c.commit();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("Records created successfully");
    }
    
    @SuppressWarnings("empty-statement")
    public String[] selectCustomer(String customerID) 
    {
      Connection c = null;
      Statement stmt = null;
      String[] data = {"","","",""};
      try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:test.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");

        stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery( "SELECT * FROM CUSTOMERS WHERE ID = " + customerID + ";" );
        
        while ( rs.next() ) {
            int id = rs.getInt("id");
            String  name = rs.getString("name");
            int age  = rs.getInt("age");
            float balance = rs.getFloat("balance");
            int accountNumber = rs.getInt("accountno");
            System.out.println( "ID = " + id );
            System.out.println( "NAME = " + name );
            System.out.println( "AGE = " + age );
            System.out.println( "SALARY = " + balance );
            System.out.println( "ACCOUNTNO = " + accountNumber );
            System.out.println();
            data[0] = name;
            data[1] = Integer.toString(age);
            data[2] = Float.toString(balance);
            data[3] = Integer.toString(accountNumber);
            
         }
        rs.close();
        stmt.close();
        c.close();
        return data;
     } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
     }
     System.out.println("Operation done successfully");
     return data;   
     }
    
    public void updateFromTable() 
    {
      Connection c = null;
   Statement stmt = null;
   
   try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:test.db");
      c.setAutoCommit(false);
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      String sql = "UPDATE COMPANY set SALARY = 25000.00 where ID=1;";
      stmt.executeUpdate(sql);
      c.commit();

      ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
      
      while ( rs.next() ) {
         int id = rs.getInt("id");
         String  name = rs.getString("name");
         int age  = rs.getInt("age");
         String  address = rs.getString("address");
         float salary = rs.getFloat("salary");
         
         System.out.println( "ID = " + id );
         System.out.println( "NAME = " + name );
         System.out.println( "AGE = " + age );
         System.out.println( "ADDRESS = " + address );
         System.out.println( "SALARY = " + salary );
         System.out.println();
      }
      rs.close();
      stmt.close();
      c.close();
   } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
   }
    System.out.println("Operation done successfully");
    }
    
    public void deleteFromTable() 
    {
      Connection c = null;
      Statement stmt = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:test.db");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         String sql = "DELETE from COMPANY where ID=2;";
         stmt.executeUpdate(sql);
         c.commit();

         ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
         
         while ( rs.next() ) {
         int id = rs.getInt("id");
         String  name = rs.getString("name");
         int age  = rs.getInt("age");
         String  address = rs.getString("address");
         float salary = rs.getFloat("salary");
         
         System.out.println( "ID = " + id );
         System.out.println( "NAME = " + name );
         System.out.println( "AGE = " + age );
         System.out.println( "ADDRESS = " + address );
         System.out.println( "SALARY = " + salary );
         System.out.println();
      }
      rs.close();
      stmt.close();
      c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("Operation done successfully");
    }
      

}
