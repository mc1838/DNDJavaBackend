import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DriverManager;

/**
 * @author Maxwell Crawford
 * Connect module for Java/JDBC to MSSQL backend.
 * Has support for basic executeQuery/execute statements.
 * DESKTOP JAVA version!
 */
public class Connect 
{
	/**
	 * General variables
	 */
	String connectionString = "";
	Connection connection = null;
	
	/**
	 * connectSelect - used for Select statements and 
	 * queries which return some 'ResultSet'.
	 * 
	 * Can use the ResultSet to retrieve and store data!
	 * @param SQLQuery - the specified SQL Select statement
	 */
	public String[] connectSelect(String SQLQuery)
	{
		String[] arr;
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(connectionString);
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}

		try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQLQuery))
        {
            ResultSetMetaData rsmd = resultSet.getMetaData(); //needed for column data/indices
            int columns = rsmd.getColumnCount();
            arr = new String[columns];
            // result += "Select Stmt Result for Table:\n";
            // String columnNames = "";
            // for (int i=1; i<=columns; i++)
            // {
            //     columnNames += rsmd.getColumnLabel(i) + "\t";
            // }

            // arr[0] = columnNames;
            // result += columnNames + "\n";
            // result += "============================\n";

            String items = "";
            while (resultSet.next())
            {
                for (int i=1; i<=columns; i++)
                {
                	arr[i-1] = resultSet.getString(i); //store each item as elem, offset-1
                	// items += resultSet.getString(i) + "\t"; //each col. item, tabbed
                    // result += resultSet.getString(i) + "\t"; //each col. item, tabbed
                }

            }
            // arr[1] = items;

            connection.close();
            statement.close();
            resultSet.close();
        }

        catch (Exception e)
		{
			e.printStackTrace();
		}

		// try
		// {
		// 	connection = DriverManager.getConnection(connectionString);
		// 	Statement statement = connection.createStatement();
		// 	ResultSet resultSet = statement.executeQuery(SQLQuery);
		// 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		// 	ResultSetMetaData rsmd = resultSet.getMetaData(); //needed for column data/indices
			
		// 	//perform actions, store data...
		// 	arr = new String[]{"1","2","3"}; //TEST
			
		// 	connection.close();
  //           statement.close();
  //           resultSet.close();
		// }
		
		// catch (Exception e)
		// {
		// 	e.printStackTrace();
		// }
		
		return arr;
	}
	
	/**
	 * connectExecute - Used for Insert/Update/Delete and other
	 * statements which execute an action, but don't return data.
	 * 
	 * @param SQLQuery - the specified SQL statement for execution
	 */
	public void connectExecute(String SQLQuery)
	{
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(connectionString);
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}

		try (Statement exstatement = connection.createStatement())
		{
            exstatement.execute(SQLQuery);
            
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

	public int[] parseIntsFromResult(String inputstr)
	{
		int[] intresult = new int[]{1,2,3}; //TEST
		// NEED to be able to test set of ints from varchar,
		// such as '1,2,3' --> (1,2,3)
		return intresult;
	}

	public String parseStrFromResult(String[] inputstr, int index)
	{
		String strresult = inputstr[index];		
		return strresult;
	}
}
