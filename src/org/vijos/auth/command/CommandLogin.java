package org.vijos.auth.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.vijos.auth.VijosLogin;
import org.vijos.auth.lib.API;

public class CommandLogin {
	
	public boolean onCommand(CommandSender sender, Player player, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You must be a player!");
			return false;
		}
		
		if (player == null) {
			sender.sendMessage("You may not do this on the server side!");
			return false;
		}
		
		if (args[0] == null || args[1] == null) {
			sender.sendMessage("Missing username or password.");
		} 
		
		String username = args[0];
		String password = args[1];
		
		if (username == null || password == null || username.trim().equals("") || password.trim().equals("")) {
			VijosLogin.i().sendLoginMessage(player);
			return false;
		}
		
		API.i().login(player, password, username);
		
		return true;
	}
	
}