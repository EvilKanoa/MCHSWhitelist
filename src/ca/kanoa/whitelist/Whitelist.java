package ca.kanoa.whitelist;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Whitelist extends JavaPlugin {

	public static final String whitelistFile = "mchs_whitelist.txt";
	
	private static Whitelist _instance = null;
	
	private CommandExecutor executor;
	private Listener listener;
	private WhitelistFile whitelist;
	
	@Override
	public void onEnable() {
		_instance = this;
		
		executor = new CommandExecutor();
		getCommand("reloadwhitelist").setExecutor(executor);
		getCommand("iswhitelisted").setExecutor(executor);
		
		listener = new EventListener();
		Bukkit.getPluginManager().registerEvents(listener, this);
		
		whitelist = new WhitelistFile(new File(
				getDataFolder().getParentFile().getParentFile(), whitelistFile));
		if (!whitelist.load()) {
			getLogger().severe("Unable to load the whitelist! (\"" + whitelistFile + "\")");
		}
	}
	
	public WhitelistFile getWhitelist() {
		return whitelist;
	}
	
	public static Whitelist getInstance() {
		return _instance;
	}
	
}
