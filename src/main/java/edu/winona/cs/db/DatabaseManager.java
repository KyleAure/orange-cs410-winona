package edu.winona.cs.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import edu.winona.cs.log.Log;
import edu.winona.cs.log.Log.LogLevel;

/**
 * Database Manager Class
 * Singleton, only one database manager exists.
 * 
 * This class creates the puzzledb.
 * Defines the database Name, URL, driver, and creation status of the database.
 * 
 * @author Kyle Aure and Erika Tix
 */
public class DatabaseManager {
	private static final Log LOG = new Log(DatabaseManager.class.getName());
	public static final String DBNAME = ".puzzledb";
	public static final String DBURL = "jdbc:derby:" + DBNAME;
	public static final String JDBCDRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String DBCREATEURL = "jdbc:derby:" + DBNAME + ";create=true";
	private static DatabaseManager obj = null;
	private static HashMap<String,Table> tables = new HashMap<>();
	
	public static DatabaseManager getDatabaseManager() {
		if(obj==null){
		    obj= new DatabaseManager();
		}
	    return obj;
	}
	
	/**
	 * Private constructor only run once during program execution.
	 */
	public DatabaseManager() {
		createDatabase();
		loadTables();
	}
	
	/**
	 * Loads tables into HashMap if they already exist
	 */
	private void loadTables() {
		LOG.log(LogLevel.INFO,"Attempting to load tables.");
		Connection conn = null;
		try {
			LOG.log(LogLevel.INFO,"Getting Database Connection...");
			conn = DriverManager.getConnection(DBURL);
			LOG.log(LogLevel.INFO,"Getting Metadata...");
			DatabaseMetaData md = conn.getMetaData();
			LOG.log(LogLevel.INFO,"Getting tables results....");
			ResultSet rs = md.getTables(null, null, "%", null);
			while(rs.next()) {
				switch(rs.getString(3)) {
				case UserTable.NAME:
					LOG.log(LogLevel.INFO,"UserTable found...");
					UserTable ut = new UserTable();
					ut.setCreated(true);
					tables.put(UserTable.NAME, ut);
					break;
				case SaveStateTable.NAME:
					LOG.log(LogLevel.INFO,"SaveStateTable found...");
					SaveStateTable sst = new SaveStateTable();
					sst.setCreated(true);
					tables.put(SaveStateTable.NAME, sst);
					break;
				case HighScoreTable.NAME:
					LOG.log(LogLevel.INFO,"HighScoreTable found...");
					HighScoreTable hst = new HighScoreTable();
					hst.setCreated(true);
					tables.put(HighScoreTable.NAME, hst);
					break;
				case SettingsTable.NAME:
					LOG.log(LogLevel.INFO,"GameSettingsTable found...");
					SettingsTable gst = new SettingsTable();
					gst.setCreated(true);
					tables.put(SettingsTable.NAME, gst);
					break;
				}
			}
		} catch (SQLException e) {
			LOG.log(e, LogLevel.WARNING, "");
		} finally {
			try{
				if(conn!=null)
		            conn.close();
			}catch(SQLException e){
		         LOG.log(e, LogLevel.WARNING, "");
			}//end finally try
		}
		LOG.log(LogLevel.WARNING,tables.toString());
		LOG.log(LogLevel.INFO,"Done loading tables.\n");
		
	}
	
	/**
	 * Creates database
	 */
	private void createDatabase() {
		LOG.log(LogLevel.INFO,"Attempting database connection from DatabaseManager:CreateDatabase");
		//STEP 1: Create JDBC variables
	   Connection conn = null;
	   try{
	      //STEP 2: Register JDBC driver
		  Class.forName(JDBCDRIVER);
	      //STEP 3: Open a connection and create database
	      LOG.log(LogLevel.INFO,"Connecting to database...");
	      conn = DriverManager.getConnection(DBCREATEURL);
	      LOG.log(LogLevel.INFO, "Database created successfully...");
	   }catch(SQLException e){
	      //Handle errors for JDBC
	      LOG.log(e, LogLevel.SEVERE, "");
	   }catch(Exception e){
	      //Handle errors for Class.forName
	     LOG.log(e, LogLevel.SEVERE, "");
	   }finally{
	      //finally block used to close resources
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException e){
	         LOG.log(e, LogLevel.WARNING, "");
	      }//end finally try
	   }//end try
	   LOG.log(LogLevel.INFO, "End database creation.\n");
	}
	
	/**
	 * Returns a string representation of the database by the
	 * tables that the database has included.
	 */
	public String getTablesString() {
		String results = "Printing Tables";
		results += "\n---------------------\n";
		Connection conn;
		try {
			conn = DriverManager.getConnection(DBURL);
			DatabaseMetaData dbmd = conn.getMetaData();
			ResultSet resultSet = dbmd.getTables(null, null, null, new String[]{"TABLE"});
			while(resultSet.next()) {
				results += resultSet.getString("TABLE_NAME") + "\n";
			}
		} catch (SQLException e) {
			LOG.log(e, LogLevel.WARNING, "");
		}
		return results;
	}
	
	/**
	 * Returns the UserTable object to be used for database connectivity.
	 * @return UserTable
	 */
	public UserTable getUserTable() {
		LOG.log(LogLevel.INFO, "Request for UserTable from DatabaseManager received.");
		if(tables.containsKey(UserTable.NAME) && tables.get(UserTable.NAME).isCreated()) {
			LOG.log(LogLevel.INFO, "Existing table found.\n");
			return (UserTable) tables.get(UserTable.NAME);
		} else {
			LOG.log(LogLevel.INFO, "DatabaseManager creating UserTable.\n");
			UserTable ut = new UserTable();
			ut.createTable();
			tables.put(UserTable.NAME, ut);
			return ut;
		}
	}
	
	/**
	 * Returns the HighScoreTable object to be used for database connectivity.
	 * @return HighScoreTable
	 */
	public HighScoreTable getScoreTable() {
		LOG.log(LogLevel.INFO, "Request for HighScoreTable from DatabaseManager received.");
		if(tables.containsKey(HighScoreTable.NAME) && tables.get(HighScoreTable.NAME).isCreated()) {
			LOG.log(LogLevel.INFO, "Existing table found.\n");
			return (HighScoreTable) tables.get(HighScoreTable.NAME);
		} else {
			LOG.log(LogLevel.INFO, "DatabaseManager creating HighScoreTable.\n");
			HighScoreTable hst = new HighScoreTable();
			hst.createTable();
			tables.put(HighScoreTable.NAME, hst);
			return hst;
		}
	}
	
	/**
	 * Returns the SaveStateTable object to be used for database connectivity.
	 * @return SaveStateTable
	 */
	public SaveStateTable getSaveStateTable() {
		LOG.log(LogLevel.INFO, "Request for SaveStateTable from DatabaseManager received.");
		if(tables.containsKey(SaveStateTable.NAME) && tables.get(SaveStateTable.NAME).isCreated()) {
			LOG.log(LogLevel.INFO, "Existing table found.\n");
			return (SaveStateTable) tables.get(SaveStateTable.NAME);
		} else {
			LOG.log(LogLevel.INFO, "DatabaseManager creating SaveStateTable.\n");
			SaveStateTable sst = new SaveStateTable();
			sst.createTable();
			tables.put(SaveStateTable.NAME, sst);
			return sst;
		}
	}
	
	/**
	 * Returns the SettingsTable object to be used for database connectivity.
	 * @return SettingsTable
	 */
	public SettingsTable getSettingsTable() {
		LOG.log(LogLevel.INFO, "Request for GameSettingsTable from DatabaseManager received.");
		if(tables.containsKey(SettingsTable.NAME) && tables.get(SettingsTable.NAME).isCreated()) {
			LOG.log(LogLevel.INFO, "Existing table found.\n");
			return (SettingsTable) tables.get(SettingsTable.NAME);
		} else {
			LOG.log(LogLevel.INFO, "DatabaseManager creating GameSettingsTable.\n");
			SettingsTable gst = new SettingsTable();
			gst.createTable();
			tables.put(SettingsTable.NAME, gst);
			return gst;
		}
	}
	
}
