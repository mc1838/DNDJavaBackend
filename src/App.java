/**
 * Author: Maxwell Crawford
 * Date changed: 4-24-17 2:05pm
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
                 * a) testdb - TestTable
                 * b) GameDB - PlayerTest
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
                
                System.out.println("\n\n\t* Waiting for input and ENTER... *");
                s = scan.next();
                System.out.println("====================================");
                
                /**
                 * Test 2: Insert INTO tables
                 * a) DungeonMaster
                 * b) Game
                 * c) Player
                 */
                String test2SQLa = "INSERT INTO [dbo].[DungeonMaster] ([Game_IDs])"
                		+ " VALUES ('1,2,3')";
                String SQLa_sel = "SELECT * FROM [dbo].[DungeonMaster]";
                
                String test2SQLb = "INSERT INTO [dbo].[Game]"
                		+ " ([Player_IDs]"
                		+ " ,[PlayerCount]"
                		+ " ,[Difficulty]"
                		+ " ,[Size]"
                		+ " ,[Enemies]"
                		+ " ,[Quests]"
                		+ " ,[Complete]"
                		+ " ,[Rules])"
                		+ " VALUES "
                		+ "('1,2,3'"
                		+ " ,3"
                		+ " ,100"
                		+ " ,'250,250'"
                		+ " ,'(5,0),(1,1)'"
                		+ " ,'(1,0),(5,1)'"
                		+ " ,0"
                		+ " ,'There are no rules!')";
                String test2SQLb_sel = "SELECT * FROM [dbo].[Game]";
                
                String test2SQLc = "INSERT INTO [dbo].[Player]"
                		+ " ([Game_ID]"
                		+ " ,[DisplayName]"
                		+ " ,[Role]"
                		+ " ,[Health]"
                		+ " ,[Mana]"
                		+ " ,[Dexterity]"
                		+ " ,[Intelligence]"
                		+ " ,[Agility])"
                		+ " ,[Defense]"
                		+ " ,[Inventory]"
                		+ " ,[Successes]"
                		+ " ,[Fails]"
                		+ " ,[Score]"
                		+ " VALUES "
                		+ "(1"
                		+ " ,'Thargoid'"
                		+ " ,'Warrior'"
                		+ " ,1250"
                		+ " ,900"
                		+ " ,25"
                		+ " ,5"
                		+ " ,7"
                		+ " ,29"
                		+ " ,'Stuff1, Stuff2'"
                		+ " ,5"
                		+ " ,7"
                		+ " ,0)";
                String test2SQLc_sel = "SELECT * FROM [dbo].[Player]";
                
                connection = DriverManager.getConnection(connectionString); //for testdb
                System.out.println("\n\t -- Test 1: Insert INTO");
                /**
                 * TRY 2: Perform action with DB, using tests
                 */
                try (Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(test2SQLa)) 
                {
                		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        
                        connection.close();
                        statement.close();
                        resultSet.close();
                }
                
                catch (Exception e) 
                {
                        e.printStackTrace();
                }
                
                try (Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(SQLa_sel)) 
                    {
                    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    		
                            // Print results from SQL statement
                            System.out.println("Insert Stmt Result - DM Table:");
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
                 * Test 3: Update existing row in tables
                 * a) Player
                 * b) Game
                 * c) DungeonMaster
                 */
                String test3SQLa = "UPDATE [dbo].[DungeonMaster]"
                		+ " SET [Game_IDs] = '4,5,6'"
                		+ " WHERE [Game_IDs] '1,2,3';";
                
                System.out.println("\n\t -- Test 3: Update");
                /**
                 * TRY 2: Perform action with DB, using tests
                 */
                try (Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(test3SQLa)) 
                {
                		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        
                        connection.close();
                        statement.close();
                        resultSet.close();
                }
                
                catch (Exception e) 
                {
                        e.printStackTrace();
                }
                
                try (Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(SQLa_sel)) 
                    {
                    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    		
                            // Print results from SQL statement
                            System.out.println("Update Stmt Result - DM Table:");
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
                
                /**
                 * Test 4: Delete existing row from tables
                 * a) Player
                 * b) Game
                 * c) DungeonMaster
                 */
                String test4SQLa = "DELETE FROM [dbo].[DungeonMaster] "
                		+ "WHERE Game_IDs='1,2,3'";
                //SQLa_sel
                
                System.out.println("\n\t -- Test 4: Delete");
                
                /**
                 * TRY 2: Perform action with DB, using tests
                 */
                try (Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(test4SQLa)) 
                {
                		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        
                        connection.close();
                        statement.close();
                        resultSet.close();
                }
                
                catch (Exception e) 
                {
                        e.printStackTrace();
                }
                
                try (Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(SQLa_sel)) 
                    {
                    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    		
                            // Print results from SQL statement
                            System.out.println("Delete Stmt Result - DM Table:");
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