package daedalus.flamingarrows;


import java.util.logging.Logger;

import org.bukkit.event.player.PlayerListener;

public class FlamingarrowsPlayerListener extends PlayerListener {
	Logger log = Logger.getLogger("Minecraft");
	public static FlamingArrows plugin; public FlamingarrowsPlayerListener(FlamingArrows instance) {
        plugin = instance;
	}
}
