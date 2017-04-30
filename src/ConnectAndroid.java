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
	 * Constructor, which sets up the connectionstring
	 * to use. Can be re-set again later.
	 * @param connectionString - the string which determines which server/db to use and how
	 */
	public Connect(String connectionString) {
		super();
		this.connectionString = connectionString;
	}
	
	/**
	 * connectSelect - used for Select statements and 
	 * queries which return some 'ResultSet'.
	 * 
	 * Can use the ResultSet to retrieve and store data!
	 * @param SQLQuery - the specified SQL Select statement
	 */
	public ArrayList<String[]> connectSelect(String SQLQuery)
	{
		// Allow all forms of Networking threading
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ArrayList<String[]> result = new ArrayList<String[]>();

		// 1) Establish initial connection
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

		// 2) Grab results from DB and store in 2D array
		try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQLQuery))
        {
            ResultSetMetaData rsmd = resultSet.getMetaData(); //needed for column data/indices
            int columns = rsmd.getColumnCount();

            while (resultSet.next())
            {
            	String[] row = new String[columns];
            	for (int i=1; i<=columns; i++)
            	{
            		row[i-1] = resultSet.getString(i);
            	}
            	result.add(row);
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

		return result;
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
	 * login - Parses int from Player ID string,
	 * checks DB IDs, and compares given ID to existing set.
	 * @param playerID - given Player_ID, as string
	 * @return isValid - true if ID is valid and exists; false if invalid/doesn't exist
	 */
	public boolean login(String playerID)
	{
      boolean isValid = false;
      int Player_ID = 0;
      int tempID = 0;
      
      // CHECK 1: See if input is a valid number
      while (!isValid)
      {
          try
          {
          	tempID = Integer.parseInt(playerID);
          }
          
          catch (Exception e)
          {
        	isValid = false;
        	return isValid;
//        	e.printStackTrace();
          }
          
          // Was valid number
          Player_ID = tempID;
          isValid = true;
      }
      
      // Get current IDs in DB:
      String LoginQuery = "SELECT [Player_ID] FROM [dbo].[Player]";
      
      ArrayList<String[]> result = this.connectSelect(LoginQuery);
      String[] currentIDs = new String[result.size()];
      for (int i=0; i<result.size(); i++)
      {
      	currentIDs[i] = result.get(i)[0]; //0th index since Player_ID has only 1 char.
      }
      
      isValid = false; //reset to check again
      
      // CHECK 2: See if given ID is actually in DB
      for (int i=0; i<currentIDs.length; i++)
      {
      	tempID = this.parseIntsFromResult(currentIDs[i])[0];
      	System.out.println("RESULT TEMPID: " + tempID); //TEST
      	if (tempID == Player_ID)
      	{
      		isValid = true;
      		break;
      	}
      	
      }
      
      return isValid;
	}
	
	/**
	 * register - Inserts a default row into Player table,
	 * then grabs the newest Player_ID for the player,
	 * for later usage.
	 * @return lastID - new Player_ID
	 */
	public int register()
	{
		//Insert new default row in Player
        String newPlayerQuery = "INSERT INTO [dbo].[Player] DEFAULT VALUES";
        this.connectExecute(newPlayerQuery);
        
        // Check current IDs in DB
        String LoginQuery = "SELECT [Player_ID] FROM [dbo].[Player]";
        ArrayList<String[]> result = this.connectSelect(LoginQuery);
        String[] currentIDs = new String[result.size()];
        for (int i=0; i<result.size(); i++)
        {
        	currentIDs[i] = result.get(i)[0]; //0th index since Player_ID has only 1 char.
        }
        
        // Grab only the last ID:
        int lastID = this.parseIntsFromResult(currentIDs[currentIDs.length-1])[0];
        return lastID;
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
	public String[] getRowAtIndex(ArrayList<String[]> inputstr, int index)
	{
		String strresult[] = inputstr.get(index);		
		return strresult;
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
