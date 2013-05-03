package org.vijos.auth.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.vijos.auth.data.Sessions;

public class CommandLogout {
	
	public boolean onCommand(CommandSender sender, Player player, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage("You must be a player!");
			return false;
		}
		
		if (player == null) {
			sender.sendMessage("You may not do this on the server side!");
			return false;
		}
		
		if (args.length != 0) return false; 
		
		if (Sessions.i().getLogin(sender) == false){
			sender.sendMessage("\u00A7a[GeoPalz]\u00A7f \u00A7cYou haven't logged in.\u00A7f");
			return true;
		}
		
		Sessions.i().delLogin(player);
		
		sender.sendMessage("\u00A7a[GeoPalz]\u00A7f \u00A7bLogged out successfully.\u00A7f");
		
		Sessions.i().locations.put(player.getName().toLowerCase(), player.getLocation());
		player.teleport(player.getWorld().getSpawnLocation());
		
		return true;
	}
	
}