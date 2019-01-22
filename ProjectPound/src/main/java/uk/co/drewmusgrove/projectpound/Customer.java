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
    
    Connection c = null;  
    Statement stmt = null;
    private String customerID;
    private String customerName;
    private String customerAccountNumber;
    private String customerBalance;
    
    public String getName(String customerID)
    {
        return "Unknown!";
    }     

    public String getAccountNumber(String customerID)
    { 
        return "Unknown!";
    }
    
    public String getBalance(String customerID)
    {
        return "£0.00";
    }    
    
    public void connectDB()   
    {    
        try 
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
        }   
        catch ( Exception e ) 
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
    
    public void createTable()   
    {
        try 
        {
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
        } 
        catch ( Exception e ) 
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }
    
    public String createCustomer(String name, String age, String accountNo, String balance)      
    {
        try 
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT MAX(ID) AS [ID] FROM CUSTOMERS;" );
            int id = 1; 
            while ( rs.next() ) 
            {
              id = rs.getInt("ID");
              id = id + 1;
            }
            rs.close();
            System.out.println( "New ID = " + id); 
            String sql = "INSERT INTO CUSTOMERS (ID,NAME,AGE,ACCOUNTNO,BALANCE) " +
                        "VALUES (" + id + ", '"+ name +"', " + age + ", '" + accountNo + "', " + balance + " );"; 
            stmt.executeUpdate(sql);      

            stmt.close();
            c.commit();
            c.close();
        } 
        catch ( Exception e ) 
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
        return "Records created successfully - " + name;
    }
    
    public String[] selectCustomer(String customerID) 
    {
        String[] data = {"","","",""};
        try 
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM CUSTOMERS WHERE ID = " + customerID + ";" );
        
            while ( rs.next() ) 
            {
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
        } 
        catch ( Exception e ) 
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return data;   
     }
    
    public void updateFromTable(String customerID)
    {
        try 
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "UPDATE CUSTOMERS set SALARY = 25000.00 where ID = " + customerID + ";";
            stmt.executeUpdate(sql);
            c.commit();

            ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
      
            while ( rs.next() ) 
            {
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
        } 
        catch ( Exception e ) 
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
    
    public void deleteFromTable(String customerID) 
    {
        try 
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "DELETE from COMPANY where WHERE ID =" + customerID + ";";
            stmt.executeUpdate(sql);
            c.commit();

            ResultSet rs = stmt.executeQuery( "SELECT * FROM CUSTOMERS;" );
            
            while ( rs.next() ) 
            {
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
        } 
        catch ( Exception e ) 
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
}
