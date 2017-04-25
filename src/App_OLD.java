//package com.sqldbsamples;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DriverManager;
import com.microsoft.sqlserver.jdbc.*;

public class App {

    public static void main(String[] args) {

        // Connect to database
        String hostName = "csc450.database.windows.net"; //"yourserver";
        String dbName = "testdb";//"yourdatabase";
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

        try {
                //connection = DriverManager.getConnection(connectionString);
                connection = DriverManager.getConnection(connectionString_GameDB);
                String schema = connection.getSchema();
                System.out.println("Successful connection - Schema: " + schema);

                System.out.println("Query data example:");
                System.out.println("=========================================");

                // Create and execute a SELECT SQL statement.
                String selectSql = "SELECT * FROM [dbo].[TestTable]";
                String selectSql2 = "SELECT * FROM [dbo].[PlayerTest]";

                try (Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(selectSql2)) 
                {
                		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                		
                        // Print results from select statement
                        System.out.println("Select Stmt Result:");
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
            }
        catch (Exception e) {
                e.printStackTrace();
        }
        
    }
}