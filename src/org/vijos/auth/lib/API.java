package org.vijos.auth.lib;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.text.SimpleDateFormat;

import org.bukkit.entity.Player;

import org.vijos.auth.VijosLogin;
import org.vijos.auth.data.Sessions;
import org.vijos.auth.thread.LoginThread;

public class API {
	
	private static API instance;
	
	public static API i() {
		return API.instance;
	}
	
	public API () {
		API.instance = this;
	}
	
	public void login(Player player, String password, String username) {
		if (Sessions.i().getLogin(player)) {
			player.sendMessage("\u00A7a[GeoPalz]\u00A7f \u00A7cYou have already logged in.\u00A7f");
			return;
		}
		
		
		if (Sessions.i().loging.containsKey(player.getName().toLowerCase())) {
			player.sendMessage("\u00A7a[GeoPalz]\u00A7f \u00A7cPlease wait...\u00A7f");
			return;
		}
		
		new LoginThread(username, password, VijosLogin.i(), player, true);
		player.sendMessage("\u00A7a[GeoPalz]\u00A7f \u00A7bLogging you in...\u00A7f");
	}
	
	public int getStatus(String username, Player player) {
		return 0;
	}

	public Integer getLogin(String username, String password) {
		int time_left = -1;
		
		try {
			String dataPost = "user_name=" + URLEncoder.encode(username, "UTF-8") + "&user_pw=" + URLEncoder.encode(password, "UTF-8");
			
			time_left = Integer.valueOf(Sender.Post("https://192.155.87.155/minecraft.geopalz.com/api.php", dataPost));
		} catch (IOException exception) {}
		
		return time_left;
	}

	public String sendTime(String username, String password, Integer played_time) {
		String response = "No response.";
		
		Date d1 = new Date();
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/YYYY HH:mm");
		String formattedDate = df.format(d1);
		
		try {
			String dataPost = "game_token=nikeplusaccelerator&target_game_token=nikeplusaccelerator&function=put&par=time_played&timestamp=" + URLEncoder.encode(formattedDate,"UTF-8") + "&value=" + URLEncoder.encode(String.valueOf(played_time),"UTF-8") + "&user_name=" + URLEncoder.encode(username, "UTF-8") + "&user_pw=" + URLEncoder.encode(password, "UTF-8");
			
			response = String.valueOf(Sender.Post("https://geopalz.com/games/request.php", dataPost));
		} catch (IOException exception) {}
		
		ConsoleLogger.i().info(response);
		
		return response;
	}
}
