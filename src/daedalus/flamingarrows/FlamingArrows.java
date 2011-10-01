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
	public void onEnable() { 
		log.info("[FlamingArrows] v1.0 has been enabled!");
		log.info("[FlamingArrows] Developed by Daedalus`");
		setupConfiguration();
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
}
