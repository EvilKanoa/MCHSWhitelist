package ca.kanoa.whitelist;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandExecutor implements org.bukkit.command.CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("reloadwhitelist") && args.length == 0) {
			sender.sendMessage(ChatColor.RED 
					+ "Reloading whitelist from \"" + Whitelist.whitelistFile + "\"...");
			if (Whitelist.getInstance().getWhitelist().load()) {
				sender.sendMessage(ChatColor.RED + "Whitelist reloaded!");
			} else {
				sender.sendMessage(ChatColor.RED + "The whitelist failed to load, check console for errors.");
			}
			return true;
		} else if (cmd.getName().equalsIgnoreCase("iswhitelisted") && args.length == 1) {
			sender.sendMessage("whitelist: " + Whitelist.getInstance().getWhitelist().getUserArray().length
					+ ", cache: " + Whitelist.getInstance().getWhitelist().getCache().size());
			sender.sendMessage("Whitelisted? " + Whitelist.getInstance().getWhitelist().isWhitelisted(args[0]));
			return true;
		} else {
			return false;
		}
	}

}
