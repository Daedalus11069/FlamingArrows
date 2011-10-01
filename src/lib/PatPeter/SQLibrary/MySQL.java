/**
 * MySQL
 * Inherited subclass for making a connection to a MySQL server.
 * 
 * Date Created: 2011-08-26 19:08
 * @author PatPeter
 */
package lib.PatPeter.SQLibrary;

/*
 * MySQL
 */
import java.net.MalformedURLException;

/*
 * Both
 */
//import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.logging.Logger;
import java.util.logging.Logger;

public class MySQL extends DatabaseHandler {
	private String hostname = "localhost";
	private String portnmbr = "3306";
	private String username = "minecraft";
	private String password = "";
	private String database = "minecraft";
	
	public MySQL(Logger log,
				 String prefix,
				 String hostname,
				 String portnmbr,
				 String database,
				 String username,
				 String password) {
		super(log,prefix,"[MySQL] ");
		this.hostname = hostname;
		this.portnmbr = portnmbr;
		this.database = database;
		this.username = username;
		this.password = password;
	}
	
	@Override
	protected boolean initialize() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // Check that server's Java has MySQL support.
			return true;
	    } catch (ClassNotFoundException e) {
	    	this.writeError("Class Not Found Exception: " + e.getMessage() + ".", true);
	    	return false;
	    }
	}
	
	@Override
	public Connection open() throws MalformedURLException, InstantiationException, IllegalAccessException {
		if (initialize()) {
			String url = "";
		    try {
				url = "jdbc:mysql://" + this.hostname + ":" + this.portnmbr + "/" + this.database;
				this.connection = DriverManager.getConnection(url, this.username, this.password);
				return this.connection;
		    } catch (SQLException e) {
		    	this.writeError(url,true);
		    	this.writeError("Could not be resolved because of an SQL Exception: " + e.getMessage() + ".", true);
		    }
		}
		return this.connection;
	}
	
	@Override
	public void close() {
		try {
			if (this.connection != null)
				this.connection.close();
		} catch (Exception e) {
			this.writeError("Failed to close database connection: " + e.getMessage(), true);
		}
	}
	
	@Override
	public Connection getConnection()
	throws MalformedURLException, InstantiationException, IllegalAccessException {
		if (this.connection == null)
			return open();
		return this.connection;
	}
	
	@Override
	public boolean checkConnection() {
		if (this.connection == null) {
			try {
				open();
				return true;
			} catch (MalformedURLException ex) {
				this.writeError("MalformedURLException: " + ex.getMessage(), true);
			} catch (InstantiationException ex) {
				this.writeError("InstantiationExceptioon: " + ex.getMessage(), true);
			} catch (IllegalAccessException ex) {
				this.writeError("IllegalAccessException: " + ex.getMessage(), true);
			}
			return false;
		}
		return true;
	}
	
	@Override
	public ResultSet query(String query)
	throws MalformedURLException, InstantiationException, IllegalAccessException {
		//Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		try {
                    //connection = getConnection();
                    this.connection = this.getConnection();
                    statement = this.connection.createStatement();

                    if(query.trim().substring(0,6).equals("SELECT"))
                    {
                        result = statement.executeQuery(query);
                    }else{    
                        statement.executeUpdate(query);
                    }
                    return result;
		} catch (SQLException ex) {
			this.writeError("Error in SQL query: " + ex.getMessage(), false);
		}
		return null;
	}
}