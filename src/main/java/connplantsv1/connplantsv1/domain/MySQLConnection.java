package connplantsv1.connplantsv1.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection {

	//private static final String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	
	
	//private static final String dbURL = "jdbc:sqlserver://localhost:1433;databasename=SOMDB";
	//private static final String dbURL = "jdbc:sqlserver://192.168.1.102:1433;databasename=SOMDB";
	//private static final String dbURL = "jdbc:sqlserver://9.81.193.106:1433;databasename=SOMDB";
	//private static final String dbURL = "jdbc:sqlserver://10.116.65.81:1433;databasename=PlasticResin";
	private static final String dbURL = "jdbc:mysql://smmtconn:3306/smmtdb";
	//jdbc:mysql://mysql:3306/sampledb?user=root&password=hDgjFKdKO8avDhBt&useSSL=false
	
	
	//private static final String user = "som_sa"; 
	//private static final String pass = "password@12345";
	
	private static final String user = "root"; 
	private static final String pass = "IwfGUwTxbE1jwvVa";
	
	private static final String dbURLwithUserPswd = dbURL + "?user=" + user + "&password=" + pass + "&useSSL=false";
	
	private static final String ID = "X";
	private static final String FName = "A";
	private static final String LName = "B";
	
	
	private static final String SchID = "X";
	private static final String SchName = "A";
	private static final String BLSPath = "B";
	private static final String Pattern = "X";
	
	private static final String Status = "B";
	private static final String ErrMsg = "X";
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Program started");
		MySQLConnection myserverCon = new MySQLConnection();
		myserverCon.dbConn(user, pass);
		//msserverCon.insertNewEmp(ID,FName,LName);
		//msserverCon.insertMIISchData(SchID,SchName,BLSPath,Pattern);
		//msserverCon.insertDummyData(Status,ErrMsg);
	}


	public String insertDummyData(String status2, String errMsg2) {

		String returnMsg = "" ;
		
	    try
	    {
	    	Connection databaseConnection= null;

	      //Connect to the database
	      databaseConnection = DriverManager.getConnection(dbURLwithUserPswd);
	      
	      Statement stmt = databaseConnection.createStatement();
 
	      insertTableDummy(stmt,status2, errMsg2);
	      
	      System.out.println("Closing database connection");
	    }
	    catch (SQLException err)
	    {
	       System.err.println("Error connecting to the database");
	       returnMsg = "ERROR";
	       err.printStackTrace(System.err);
	       return returnMsg;
	      // System.exit(0);
	    }
	    returnMsg = "SUCCESS";
	    System.out.println("Program finished");
	    return returnMsg;

	}
	
	public String insertMIISchData(String site, String shoporder, String item, String qty_to_build, String qty_done, String qty_scrapped, String modified_dt) {
		String returnMsg = "" ;
		
	    try
	    {
	    	Connection databaseConnection= null;

	      //Connect to the database
	      databaseConnection = DriverManager.getConnection(dbURLwithUserPswd);
	      
	      Statement stmt = databaseConnection.createStatement();
 
	      insertTableMIISch(stmt, site, shoporder, item, qty_to_build, qty_done, qty_scrapped, modified_dt);
	      
	      System.out.println("Closing database connection");
	    }
	    catch (SQLException err)
	    {
	       System.err.println("Error connecting to the database");
	       returnMsg = "ERROR";
	       err.printStackTrace(System.err);
	       return returnMsg;
	      // System.exit(0);
	    }
	    returnMsg = "SUCCESS";
	    System.out.println("Program finished");
	    return returnMsg;

		
	}



	public String insertNewEmp(String id2, String fname2, String lname2) {
		String returnMsg = "" ;
		
	    try
	    {
	       

	       Connection databaseConnection= null;

	      //Connect to the database
	      databaseConnection = DriverManager.getConnection(dbURLwithUserPswd);
	      
	      Statement stmt = databaseConnection.createStatement();
 
	      insertTable(stmt,id2, fname2, lname2);
	      
	      System.out.println("Closing database connection");
	    }
	    catch (SQLException err)
	    {
	       System.err.println("Error connecting to the database");
	       returnMsg = "ERROR";
	       err.printStackTrace(System.err);
	       return returnMsg;
	      // System.exit(0);
	    }
	    returnMsg = "SUCCESS";
	    System.out.println("Program finished");
	    return returnMsg;
	}
	
	public String dbConn(String userName, String Password)
	{
	String returnMsg = "" ;
		System.out.println("Program started");
		returnMsg = returnMsg + "Program started"+ "/n";
	    try
	    {
	       Class.forName(jdbcDriver).newInstance();
	       System.out.println("JDBC driver loaded successfully 2!");
	       returnMsg = returnMsg + "JDBC driver loaded successfully!"+ "/n";
	    }
	    catch (Exception err)
	    {
	       System.err.println("Error loading JDBC driver");
	       returnMsg = returnMsg + "Error loading JDBC driver"+ "/n";
	       err.printStackTrace(System.err);
	       System.exit(0);
	    }
		
	    Connection databaseConnection= null;
	    try
	    {
	      //Connect to the database
	      databaseConnection = DriverManager.getConnection(dbURL,userName,Password);
	      System.out.println("Connected to the database successfully 2!");
	      System.out.println("Closing database connection");
	      returnMsg = returnMsg + "Error loading JDBC driver"+ "/n" + "Closing database connection";

	      //close the database connection
	      databaseConnection.close();
	    }
	    catch (SQLException err)
	    {
	       System.err.println("Error connecting to the database 1!");
	       returnMsg = "ERROR - " + returnMsg + "Error connecting to the database" + "/n";
	       err.printStackTrace(System.err);
	       return returnMsg;
	      // System.exit(0);
	    }
	    returnMsg = "SUCCESS";
	    System.out.println("Program finished");
	    return returnMsg;
	}
	
	private void insertTable(Statement stmt, String idIn, String fnameIn, String lnameIn) throws SQLException{
		
		String sql = "INSERT Employee VALUES ('"+idIn+"','"+fnameIn+" "+lnameIn+"','"+fnameIn+"','"+lnameIn+"')";
		
		stmt.execute(sql);
		
		System.out.println("A new record is recorded successfully!");
	}
	
	private void insertTableMIISch(Statement stmt,String site, String shoporder, String item, String qty_to_build, String qty_done, String qty_scrapped, String modified_dt) throws SQLException {
		
		String sql = "INSERT SHOP_ORDER VALUES ('"+site+"','"+shoporder+"','"+item+"','"+qty_to_build+"','"+qty_done+"','"+qty_scrapped+"','"+modified_dt+"')";
		
		stmt.execute(sql);
		
		System.out.println("A new record is recorded successfully!");
		
	}

	private void insertTableDummy(Statement stmt, String status2, String errMsg2) throws SQLException {
	
		String sql = "INSERT DUMMYDATA VALUES ('"+status2+"','"+errMsg2+"')";
		
		stmt.execute(sql);
		
		System.out.println("A new record is recorded successfully!");
		
	}

	
}
