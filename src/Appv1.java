/**
 * Author: Maxwell Crawford
 * Date changed: 4-24-17 1:21pm
 * Set of SQL tests, to Azure, from Java/JDBC
 */

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DriverManager;
import com.microsoft.sqlserver.jdbc.*;

public class App {

    public static void main(String[] args) {

        // Connect to database
        String hostName = "csc450.database.windows.net";
        String dbName = "testdb";
        String user = "mc1838";
        String password = "Project450";
        String connectionString = 
        		"jdbc:sqlserver://csc450.database.windows.net:1433;" + 
        		"database=testdb;" + 
        		"user=mc1838@csc450;password=Project450;" + 
        		"encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        String connectionString_GameDB = 
        		"jdbc:sqlserver://csc450.database.windows.net:1433;" + 
        		"database=GameDB;" + 
        		"user=mc1838@csc450;password=Project450;" + 
        		"encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        Connection connection = null;
        Scanner scan = new Scanner(System.in);
        String s = "";
        
        /**
         * TRY 1: Initial Connection to DB
         */
        try 
        {
//                //connection = DriverManager.getConnection(connectionString);
//                connection = DriverManager.getConnection(connectionString_GameDB);
//                String schema = connection.getSchema();
//                System.out.println("Successful connection - Schema: " + schema);

                System.out.println("Now to begin set of SQL tests...");
                System.out.println("=========================================");

                // Create and execute a SELECT SQL statement.
                /**
                 * Test 1: Select ALL from tables
                 */
                String test1SQL1 = "SELECT * FROM [dbo].[TestTable]";
                String test1SQL2 = "SELECT * FROM [dbo].[PlayerTest]";
                
                System.out.println("\n\t -- Test 1: Select ALL");
                /**
                 * TRY 2: Perform action with DB, using tests
                 */
                connection = DriverManager.getConnection(connectionString); //for testdb
                try (Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(test1SQL1)) 
                {
                		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                		
                        // Print results from SQL statement
                        System.out.println("Select Stmt Result for TestTable:");
                        ResultSetMetaData rsmd = resultSet.getMetaData(); //needed for column data/indices
                        int columns = rsmd.getColumnCount();
                        String columnNames = "";
                        for (int i=1; i<=columns; i++)
                        {
                        	columnNames += rsmd.getColumnLabel(i) + "\t";
                        }
                        
                        System.out.println(columnNames);
                        System.out.println("==================================================================================");
                        while (resultSet.next())
                        {
                        	for (int i=1; i<=columns; i++)
                        	{
                        		System.out.print(resultSet.getString(i) + "\t"); //each col. item, tabbed
                        	}
                        	
                        }
                        
                        connection.close();
                        statement.close();
                        resultSet.close();
                }
                
                catch (Exception e) 
                {
                        e.printStackTrace();
                }
                
                System.out.println("\n\n\t* Waiting for input and ENTER... *");
                s = scan.next();
                System.out.println("====================================");
                
                /**
                 * TRY 2: Perform action with DB, using tests
                 */
                connection = DriverManager.getConnection(connectionString_GameDB);
                try (Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(test1SQL2)) 
                {
                		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                		
                        // Print results from SQL statement
                        System.out.println("Select Stmt Result for PlayerTest:");
                        ResultSetMetaData rsmd = resultSet.getMetaData(); //needed for column data/indices
                        int columns = rsmd.getColumnCount();
                        String columnNames = "";
                        for (int i=1; i<=columns; i++)
                        {
                        	columnNames += rsmd.getColumnLabel(i) + "\t";
                        }
                        
                        System.out.println(columnNames);
                        System.out.println("==================================================================================");
                        while (resultSet.next())
                        {
                        	for (int i=1; i<=columns; i++)
                        	{
                        		System.out.print(resultSet.getString(i) + "\t"); //each col. item, tabbed
                        	}
                        	
                        }
                        
                        connection.close();
                        statement.close();
                        resultSet.close();
                }
                
                catch (Exception e) 
                {
                        e.printStackTrace();
                }
                
//                System.out.println("\n\n\t* Waiting for input and ENTER... *");
//                s = scan.next();
//                System.out.println("====================================");
                
                /**
                 * Test 2: Insert INTO tables
                 * a) Player
                 * b) Game
                 * c) DungeonMaster
                 */
//                String test2SQLa = "SELECT * FROM [dbo].[TestTable]";
//                String test2SQLb = "SELECT * FROM [dbo].[PlayerTest]";
//                String test2SQLc = "";
//                
//                System.out.println("\n\t -- Test 1: Insert INTO");
//                /**
//                 * TRY 2: Perform action with DB, using tests
//                 */
//                try (Statement statement = connection.createStatement();
//                    ResultSet resultSet = statement.executeQuery(test2SQLa)) 
//                {
//                		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//                		
//                        // Print results from SQL statement
//                        System.out.println("Insert Stmt Result:");
//                        ResultSetMetaData rsmd = resultSet.getMetaData(); //needed for column data/indices
//                        int columns = rsmd.getColumnCount();
//                        String columnNames = "";
//                        for (int i=1; i<=columns; i++)
//                        {
//                        	columnNames += rsmd.getColumnLabel(i) + "\t";
//                        }
//                        
//                        System.out.println(columnNames);
//                        System.out.println("==================================================================================");
//                        while (resultSet.next())
//                        {
//                        	for (int i=1; i<=columns; i++)
//                        	{
//                        		System.out.print(resultSet.getString(i) + "\t"); //each col. item, tabbed
//                        	}
//                        	
//                        }
//                        
//                        connection.close();
//                        statement.close();
//                        resultSet.close();
//                }
//                
//                catch (Exception e) 
//                {
//                        e.printStackTrace();
//                }
                
                /**
                 * Test 3: Update existing row in tables
                 * a) Player
                 * b) Game
                 * c) DungeonMaster
                 */
                
                /**
                 * Test 4: Delete existing row from tables
                 * a) Player
                 * b) Game
                 * c) DungeonMaster
                 */
                
                /**
                 * TRY 2: Perform action with DB, using tests
                 */
//                try (Statement statement = connection.createStatement();
//                    ResultSet resultSet = statement.executeQuery(selectSql2)) 
//                {
//                		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//                		
//                        // Print results from SQL statement
//                        System.out.println("Select Stmt Result:");
//                        ResultSetMetaData rsmd = resultSet.getMetaData(); //needed for column data/indices
//                        int columns = rsmd.getColumnCount();
//                        String columnNames = "";
//                        for (int i=1; i<=columns; i++)
//                        {
//                        	columnNames += rsmd.getColumnLabel(i) + "\t";
//                        }
//                        
//                        System.out.println(columnNames);
//                        System.out.println("==================================================================================");
//                        while (resultSet.next())
//                        {
//                        	for (int i=1; i<=columns; i++)
//                        	{
//                        		System.out.print(resultSet.getString(i) + "\t"); //each col. item, tabbed
//                        	}
//                        	
//                        }
//                        
//                        connection.close();
//                        statement.close();
//                        resultSet.close();
//                }
//                
//                catch (Exception e) 
//                {
//                        e.printStackTrace();
//                }
            }
        catch (Exception e) 
        {
                e.printStackTrace();
        }
        
    }
}