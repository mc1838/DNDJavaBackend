import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DriverManager;

import android.util.Log;
import android.widget.Toast;

/**
 * @author Maxwell Crawford
 * Connect module for Java/JDBC to MSSQL backend.
 * Has support for basic executeQuery/execute statements.
 * ANDROID/JTDS version!
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
		// Allow all forms of Networking threading
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

		String[] arr = null;
		try
		{
			Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionString);
		}

		catch (Exception e)
		{
			Log.w("Error with connection: ", e.getMessage());
            Toast.makeText(this, "getC "+ e.getMessage(), Toast.LENGTH_SHORT).show();
		}

		try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQLQuery))
        {
            ResultSetMetaData rsmd = resultSet.getMetaData(); //needed for column data/indices
            int columns = rsmd.getColumnCount();
            arr = new String[columns];

            while (resultSet.next())
            {
                for (int i=1; i<=columns; i++)
                {
                	arr[i-1] = resultSet.getString(i); //store each item as elem, offset-1 because columns start at 1
                }

            }

            connection.close();
            statement.close();
            resultSet.close();
        }

        catch (Exception e)
		{
			Log.w("Error with connection: ", e.getMessage());
            Toast.makeText(this, "exec " + e.getMessage(), Toast.LENGTH_SHORT).show();
		}

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
		// Allow all forms of Networking threading
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

		try
		{
			Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionString);
		}

		catch (Exception e)
		{
			Log.w("Error with connection: ", e.getMessage());
            Toast.makeText(this, "getC "+ e.getMessage(), Toast.LENGTH_SHORT).show();
		}

		try (Statement exstatement = connection.createStatement())
		{
            exstatement.execute(SQLQuery);
            
            exstatement.close();
            connection.close();
		}
		
		catch (Exception e)
		{
			Log.w("Error with connection: ", e.getMessage());
            Toast.makeText(this, "getC "+ e.getMessage(), Toast.LENGTH_SHORT).show();
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

	/**
	 * getStringAtIndex - using the given String array and index number,
	 * return the string at the index.
	 * @param inputstr - array of strings retrived from database row
	 * @param index - integer location of target string
	 * @return strresult - the target string to parse
	 */
	public String getStringAtIndex(String[] inputstr, int index)
	{
		String strresult = inputstr[index];		
		return strresult;
	}
	
	/**
	 * parseIntsFromResult - With inputstr from database row,
	 * parse the specific integers from the String and place
	 * into an array of actual integers.
	 * @param inputstr - the database row, as String
	 * @return intresult - array of int
	 */
	public int[] parseIntsFromResult(String inputstr)
	{
		// 1) Initialize temp Int array to total length of String
		// and check each character in String if it is a valid number
		int[] inttemp = new int[inputstr.length()];
		int intcount = 0;
		for (int i=0; i<inputstr.length(); i++)
		{
			char temp = inputstr.charAt(i);
			boolean isNumber = Character.isDigit(temp);
			if (isNumber)
			{
				intcount +=1;
				inttemp[i] = Character.getNumericValue(temp);
			}
			
			else
			{
				inttemp[i] = -1;
			}
		}
		
		// 2) Initialize actual result Int array, from number of valid digits
		// recorded above, and place those ints in the new array, in correct order.
		int[] intresult = new int[intcount];
		int tempcount = 0;
		for (int i=0; i<inttemp.length; i++)
		{
			if (inttemp[i] < 0)
			{
				continue;
			}
			
			else
			{
				intresult[tempcount] = inttemp[i];
				tempcount +=1;
			}
		}
		return intresult;
	}
}
