import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DriverManager;

/**
 * @author Maxwell Crawford
 * Connect module for Java/JDBC to MSSQL backend.
 * Has support for basic executeQuery/execute statements.
 */
public class Connect 
{
	/**
	 * General variables
	 */
	String connectionString = "";
	Connection connection = null;
	
	/**
	 * executeReturn - used for Select statements and 
	 * queries which return some 'ResultSet'.
	 * 
	 * Can use the ResultSet to retrieve and store data!
	 * @param SQLQuery - the specified SQL Select statement
	 */
	public String[] executeReturn(String SQLQuery, String[] arr)
	{
		try
		{
			connection = DriverManager.getConnection(connectionString);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SQLQuery);
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			ResultSetMetaData rsmd = resultSet.getMetaData(); //needed for column data/indices
			
			//perform actions, store data...
			arr = new String[]{"1","2","3"}; //TEST
			
			connection.close();
            statement.close();
            resultSet.close();
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return arr;
	}
	
	public int[] executeReturn(String SQLQuery, int[] arr)
	{
		try
		{
			connection = DriverManager.getConnection(connectionString);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SQLQuery);
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			ResultSetMetaData rsmd = resultSet.getMetaData(); //needed for column data/indices
			
			//perform actions, store data...
			arr = new int[]{1,2,3}; //TEST
			
			connection.close();
            statement.close();
            resultSet.close();
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return arr;
	}
	
	public int executeReturn(String SQLQuery, int arr)
	{
		try
		{
			connection = DriverManager.getConnection(connectionString);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SQLQuery);
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			ResultSetMetaData rsmd = resultSet.getMetaData(); //needed for column data/indices
			
			//perform actions, store data...
			arr = 2; //TEST
			
			connection.close();
            statement.close();
            resultSet.close();
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return arr;
	}
	
	/**
	 * execute - Used for Insert/Update/Delete and other
	 * statements which execute an action, but don't return data.
	 * 
	 * @param SQLQuery - the specified SQL statement for execution
	 */
	public void execute(String SQLQuery)
	{
		try
		{
			connection = DriverManager.getConnection(connectionString); //for testdb
            Statement exstatement = connection.createStatement(); //NOTE: need execute type stmt for non-selects
            exstatement.execute(SQLQuery);
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            exstatement.close();
            connection.close();
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @return the connectionString
	 */
	public String getConnectionString() {
		return connectionString;
	}

	/**
	 * @param connectionString the connectionString to set
	 */
	public void setConnectionString(String connectionString) {
		this.connectionString = connectionString;
	}
}
