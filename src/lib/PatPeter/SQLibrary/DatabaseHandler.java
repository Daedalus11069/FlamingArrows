/**
 * Database Handler
 * Abstract superclass for all subclass database files.
 * 
 * Date Created: 2011-08-26 19:08
 * @author PatPeter
 */
package lib.PatPeter.SQLibrary;

/*
 *  MySQL
 */
import java.net.MalformedURLException;

/*
 *  SQLLite
 */
//import java.io.File;
//import java.sql.DatabaseMetaData;

/*
 *  Both
 */
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.util.logging.Logger;

public abstract class DatabaseHandler {
	protected Logger log;
	protected final String PREFIX;
	protected final String DATABASE_PREFIX;
	protected Connection connection;
	
	/*
	 *  MySQL, SQLLite
	 */
	
	public DatabaseHandler(Logger log, String prefix, String dp) {
		this.log = log;
		this.PREFIX = prefix;
		this.DATABASE_PREFIX = dp;
		this.connection = null;
	}
	
	/**
	 * <b>writeInfo</b><br>
	 * <br>
	 * &nbsp;&nbsp;Writes information to the console.
	 * <br>
	 * <br>
	 * @param toWrite - the <a href="http://download.oracle.com/javase/6/docs/api/java/lang/String.html">String</a>
	 * of content to write to the console.
	 */
	protected void writeInfo(String toWrite) {
		if (toWrite != null) {
			this.log.info(this.PREFIX + this.DATABASE_PREFIX + toWrite);
		}
	}
	
	/**
	 * <b>writeError</b><br>
	 * <br>
	 * &nbsp;&nbsp;Writes either errors or warnings to the console.
	 * <br>
	 * <br>
	 * @param toWrite - the <a href="http://download.oracle.com/javase/6/docs/api/java/lang/String.html">String</a>
	 * written to the console.
	 * @param severe - whether console output should appear as an error or warning.
	 */
	protected void writeError(String toWrite, boolean severe) {
		if (toWrite != null) {
			if (severe) {
				this.log.severe(this.PREFIX + this.DATABASE_PREFIX + toWrite);
			} else {
				this.log.warning(this.PREFIX + this.DATABASE_PREFIX + toWrite);
			}
		}
	}
	
	/**
	 * <b>initialize</b><br>
	 * <br>
	 * &nbsp;&nbsp;Used to check whether the class for the SQL engine is installed.
	 * <br>
	 * <br>
	 */
	abstract boolean initialize();
	
	/**
	 * <b>open</b><br>
	 * <br>
	 * &nbsp;&nbsp;Opens a connection with the database.
	 * <br>
	 * <br>
	 * @return the success of the method.
	 * @throws MalformedURLException - cannot access database because of a syntax error in the jdbc:// protocol.
	 * @throws InstantiationException - cannot instantiate an interface or abstract class.
	 * @throws IllegalAccessException - cannot access classes, fields, methods, or constructors that are private.
	 */
	abstract Connection open()
		throws MalformedURLException, InstantiationException, IllegalAccessException;
	
	/**
	 * <b>close</b><br>
	 * <br>
	 * &nbsp;&nbsp;Closes a connection with the database.
	 * <br>
	 * <br>
	 */
	abstract void close();
	
	/**
	 * <b>getConnection</b><br>
	 * <br>
	 * &nbsp;&nbsp;Gets the connection variable 
	 * <br>
	 * <br>
	 * @return the <a href="http://download.oracle.com/javase/6/docs/api/java/sql/Connection.html">Connection</a> variable.
	 * @throws MalformedURLException - cannot access database because of a syntax error in the jdbc:// protocol.
	 * @throws InstantiationException - cannot instantiate an interface or abstract class.
	 * @throws IllegalAccessException - cannot access classes, fields, methods, or constructors that are private.
	 */
	abstract Connection getConnection()
		throws MalformedURLException, InstantiationException, IllegalAccessException;
	
	/**
	 * <b>checkConnection</b><br>
	 * <br>
	 * Checks the connection between Java and the database engine.
	 * <br>
	 * <br>
	 * @return the status of the connection, true for up, false for down.
	 */
	abstract boolean checkConnection();
	
	/**
	 * <b>query</b><br>
	 * &nbsp;&nbsp;Sends a query to the SQL database.
	 * <br>
	 * <br>
	 * @param query - the SQL query to send to the database.
	 * @return the table of results from the query.
	 * @throws MalformedURLException - cannot access database because of a syntax error in the jdbc:// protocol.
	 * @throws InstantiationException - cannot instantiate an interface or abstract class.
	 * @throws IllegalAccessException - cannot access classes, fields, methods, or constructors that are private.
	 */
	abstract ResultSet query(String query)
		throws MalformedURLException, InstantiationException, IllegalAccessException;
	
}