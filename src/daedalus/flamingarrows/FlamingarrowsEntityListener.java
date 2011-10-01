package daedalus.flamingarrows;

import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class FlamingarrowsEntityListener extends EntityListener {
	Logger log = Logger.getLogger("Minecraft");
	public static FlamingArrows plugin; public FlamingarrowsEntityListener(FlamingArrows instance) {
        plugin = instance;
	}
	int fireticks = 0;
	int ftdivd = FlamingArrows.ftdivider;
	
	public void onProjectileHit(ProjectileHitEvent event) {
		Entity arrow = event.getEntity();
		Block block = arrow.getLocation().getBlock();
		fireticks = arrow.getFireTicks();
		if (fireticks > 0) {
			//Fire spreads here
			if (block.getType().toString() == "AIR") {
				block.setType(Material.FIRE);
			}
		}
	}
	public void onEntityDamage(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player) {
			Entity entity = event.getEntity();
			DamageCause damager = event.getCause();
			if (damager.toString() == "PROJECTILE") {
				if (fireticks > 0) {
					int fireticksdiv = fireticks / 2;
					entity.setFireTicks(fireticksdiv);
					log.info("" + ftdivd);
				}
			}
			
		}
	}
}