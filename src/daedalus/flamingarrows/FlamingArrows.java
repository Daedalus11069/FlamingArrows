package daedalus.flamingarrows;

import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;


public class FlamingArrows extends JavaPlugin {
	private final FlamingarrowsEntityListener entityListener = new FlamingarrowsEntityListener(this);
	Logger log = Logger.getLogger("Minecraft");
	public Configuration config;
	public static int ftdivider;
	protected MySQL sql;
        protected static Configuration config;
        private String mysqlServer,mysqlUsername,
                   mysqlPassword,mysqlPort;
        public static Logger log;
    protected String mysqlDatabase,mysqlTableInternal,mysqlTableInput;
	
	
	public void onEnable() { 
		log.info("[FlamingArrows] v1.0 has been enabled!");
		log.info("[FlamingArrows] Developed by Daedalus`");
		        // TODO: Place any custom enable code here, such as registering events
	        config = getConfiguration();
	        //Set up MySQL
	        mysqlServer = config.getString("MySQL.Hostname", "localhost");
	        mysqlDatabase = config.getString("MySQL.Database", "test");
	        mysqlTableInput = config.getString("MySQL.Tables.SiteInput", "testable");
	        mysqlTableInternal = config.getString("MySQL.Tables.Internal", "FlamingArrowsInternal");
	        mysqlUsername = config.getString("MySQL.Username", "root");
	        mysqlPassword = config.getString("MySQL.Password", "");
	        mysqlPort = config.getString("MySQL.PortNumber", "3306");
	        sql = new MySQL(log,"FlamingArrows ",mysqlServer,mysqlPort,mysqlDatabase,mysqlUsername,mysqlPassword);
	        
	        config.save();
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.PROJECTILE_HIT, entityListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener, Event.Priority.Normal, this);
		//if (player.hasPermission("flamingarrows.permission")) {
	 		   //Do something
		//}
		
	}
	private void setupConfiguration() {
		config = getConfiguration();
		if (config.getKeys(null).isEmpty()) {
			config.setProperty("Fire-ticks-divider", 2);
		}
		ftdivider = config.getInt("Fire-ticks-divider", 1);
		config.save();

	}
	 
	public void onDisable() { 
		config.save();
		log.info("Your plugin has been disabled.");
	 
	}
	
	
	void setProperty(String string, Object data)
    	{
	        org.bukkit.util.config.Configuration configuration = getConfig();
	        configuration.load();
	        configuration.setProperty(string.toLowerCase(), data);
	        configuration.save();
	    	return;
    	}
    	
    	
        int getInt(String string, int default)
    	{
		org.bukkit.util.config.Configuration configuration = getConfig();
        	configuration.load();
    		return configuration.getInt(string.toLowerCase(),default);
    	}
	
	String getString(String string, String default)
    	{
		org.bukkit.util.config.Configuration configuration = getConfig();
        	configuration.load();
    		return configuration.getInt(string.toLowerCase(),default);
    	}
	
	
}
