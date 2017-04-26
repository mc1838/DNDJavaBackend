/**
 * Author: Maxwell Crawford
 * Date changed: 4-25-17 9:03pm
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
                String SQLb_sel = "SELECT * FROM [dbo].[Game]";
                
                String test2SQLc = "INSERT INTO [dbo].[Player]"
                		+ " ([Game_ID]"
                		+ " ,[DisplayName]"
                		+ " ,[Role]"
                		+ " ,[Health]"
                		+ " ,[Mana]"
                		+ " ,[Dexterity]"
                		+ " ,[Intelligence]"
                		+ " ,[Agility]"
                		+ " ,[Defense]"
                		+ " ,[Inventory]"
                		+ " ,[Successes]"
                		+ " ,[Fails]"
                		+ " ,[Score])"
                		+ " VALUES "
                		+ "(1"
                		+ " ,'Thargoid'"
                		+ " ,'Warrior'"
                		+ " ,'(1250,1250)'"
                		+ " ,'(900,1000)'"
                		+ " ,25"
                		+ " ,5"
                		+ " ,7"
                		+ " ,29"
                		+ " ,'Stuff1, Stuff2'"
                		+ " ,5"
                		+ " ,7"
                		+ " ,0)";
                String SQLc_sel = "SELECT * FROM [dbo].[Player]";
                
                
                System.out.println("\n\n\t -- Test 2: Insert INTO");
                /**
                 * TRY 2: Perform action with DB, using tests
                 */
                
                connection = DriverManager.getConnection(connectionString); //for testdb
                Statement exstatement = connection.createStatement(); //NOTE: need execute type stmt for non-selects
                exstatement.execute(test2SQLa);
                try
                {
                		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        exstatement.close();
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
                
                connection = DriverManager.getConnection(connectionString); //for testdb
                exstatement = connection.createStatement(); //NOTE: need execute type stmt for non-selects
                exstatement.execute(test2SQLb);
                try
                {
                		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        exstatement.close();
                }
                
                catch (Exception e) 
                {
                        e.printStackTrace();
                }
                
                try (Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(SQLb_sel)) 
                    {
                    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    		
                            // Print results from SQL statement
                            System.out.println("Insert Stmt Result - Game Table:");
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
                
                connection = DriverManager.getConnection(connectionString); //for testdb
                exstatement = connection.createStatement(); //NOTE: need execute type stmt for non-selects
                exstatement.execute(test2SQLc);
                try
                {
                		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        exstatement.close();
                }
                
                catch (Exception e) 
                {
                        e.printStackTrace();
                }
                
                try (Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(SQLc_sel)) 
                    {
                    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    		
                            // Print results from SQL statement
                            System.out.println("Insert Stmt Result - Player Table:");
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
                		+ " WHERE [Game_IDs] = '1,2,3';";
                
                String test3SQLb = "UPDATE [dbo].[Game]"
                		+ " SET [Player_IDs] = '1,4,5,6', [PlayerCount] = 4"
                		+ " WHERE [Player_IDs] = '1,2,3';";
                
                String test3SQLc = "UPDATE [dbo].[Player]"
                		+ " SET [Health] = '(400,1250)', [Successes] = 101"
                		+ " WHERE [Successes] = 5;";
                
                System.out.println("\n\n\t -- Test 3: Update");
                /**
                 * TRY 2: Perform action with DB, using tests
                 */
                
                connection = DriverManager.getConnection(connectionString); //for testdb
                exstatement = connection.createStatement(); //NOTE: may need execute type stmt for non-selects
                exstatement.execute(test3SQLa);
                try
                {
                		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        exstatement.close();
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
                
                System.out.println("\n\n\t* Waiting for input and ENTER... *");
                s = scan.next();
                System.out.println("====================================");
                
                connection = DriverManager.getConnection(connectionString); //for testdb
                exstatement = connection.createStatement(); //NOTE: may need execute type stmt for non-selects
                exstatement.execute(test3SQLb);
                try
                {
                		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        exstatement.close();
                }
                
                catch (Exception e) 
                {
                        e.printStackTrace();
                }
                
                try (Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(SQLb_sel)) 
                    {
                    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    		
                            // Print results from SQL statement
                            System.out.println("Update Stmt Result - Game Table:");
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
                
                connection = DriverManager.getConnection(connectionString); //for testdb
                exstatement = connection.createStatement(); //NOTE: may need execute type stmt for non-selects
                exstatement.execute(test3SQLc);
                try
                {
                		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        exstatement.close();
                }
                
                catch (Exception e) 
                {
                        e.printStackTrace();
                }
                
                try (Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(SQLc_sel)) 
                    {
                    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    		
                            // Print results from SQL statement
                            System.out.println("Update Stmt Result - Player Table:");
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
                 * Test 4: Delete existing row from tables
                 * a) Player
                 * b) Game
                 * c) DungeonMaster
                 */
                String test4SQLa = "DELETE FROM [dbo].[DungeonMaster] "
                		+ "WHERE [Game_IDs] = '4,5,6'";
                
                String test4SQLb = "DELETE FROM [dbo].[Game] "
                		+ "WHERE [Player_IDs] = '1,4,5,6'";
                
                String test4SQLc = "DELETE FROM [dbo].[Player] "
                		+ "WHERE [Successes] = 101";
                
                //SQLa_sel
                
                System.out.println("\n\n\t -- Test 4: Delete");
                
                /**
                 * TRY 2: Perform action with DB, using tests
                 */
                connection = DriverManager.getConnection(connectionString); //for testdb
                exstatement = connection.createStatement(); //NOTE: may need execute type stmt for non-selects
                exstatement.execute(test4SQLa);
                try
                {
                		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        exstatement.close();
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
                
                System.out.println("\n\n\t* Waiting for input and ENTER... *");
                s = scan.next();
                System.out.println("====================================");
                
                connection = DriverManager.getConnection(connectionString); //for testdb
                exstatement = connection.createStatement(); //NOTE: may need execute type stmt for non-selects
                exstatement.execute(test4SQLb);
                try
                {
                		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        exstatement.close();
                }
                
                catch (Exception e) 
                {
                        e.printStackTrace();
                }
                
                try (Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(SQLb_sel)) 
                    {
                    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    		
                            // Print results from SQL statement
                            System.out.println("Delete Stmt Result - Game Table:");
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
                
                connection = DriverManager.getConnection(connectionString); //for testdb
                exstatement = connection.createStatement(); //NOTE: may need execute type stmt for non-selects
                exstatement.execute(test4SQLc);
                try
                {
                		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        exstatement.close();
                }
                
                catch (Exception e) 
                {
                        e.printStackTrace();
                }
                
                try (Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(SQLc_sel)) 
                    {
                    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    		
                            // Print results from SQL statement
                            System.out.println("Delete Stmt Result - Player Table:");
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
                
                scan.close();
            }
        catch (Exception e) 
        {
                e.printStackTrace();
        }
        
    }
}