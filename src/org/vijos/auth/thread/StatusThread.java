package org.vijos.auth.thread;

import org.bukkit.entity.Player;

import org.vijos.auth.VijosLogin;

public class StatusThread extends LoginThread {
	
	public StatusThread(String username, VijosLogin plugin, Player player) {
		super(username, null, plugin, player, false);
	}
	
	public void run() {
		if (!this.player.isOnline())
			return;
		
		if (!this.player.hasPermission("vijoslogin.login") && player.isOnline()) {
			player.kickPlayer("\u00A7a[GeoPalz]\u00A7f \u00A7cIncorrect permissions!\u00A7f");
		}
		
		if (this.getLogin()) {
			VijosLogin.i().sendLoginMessage(this.player);
		}
	}
	
	public boolean getLogin() {
		return true;
	}
}