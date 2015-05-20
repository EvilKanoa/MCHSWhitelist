package ca.kanoa.whitelist;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class EventListener implements Listener {

	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerJoin(PlayerLoginEvent event) {
		if (!Whitelist.getInstance().getWhitelist().isWhitelisted(event.getPlayer())) {
			event.disallow(Result.KICK_WHITELIST, 
					"You are not whitelisted. If you believe this is in error, contact a staff member.");
		}
		
		/* PlayerJoinEvent
		if (!Whitelist.getInstance().getWhitelist().isWhitelisted(event.getPlayer())) {
			final Player player = event.getPlayer();
			Bukkit.getScheduler().scheduleSyncDelayedTask(Whitelist.getInstance(), new Runnable(){
				@Override
				public void run() {
					player.kickPlayer("You are not whitelisted. If you believe this is an error, contact a staff member");
				}}, 1);
		} */
	}
	
}
