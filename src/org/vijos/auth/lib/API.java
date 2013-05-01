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
	
	private String loginURI;
	
	public static API i() {
		return API.instance;
	}
	
	public API () {
		API.instance = this;
		
		this.loginURI = "https://192.155.87.155/api/login.php";
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

	public int getLogin(String username, String password) {
		int line = 10;
		
		try {
			String dataPost = "username=" + URLEncoder.encode(username, "UTF-8") + "&hash=" + password;
			
			line = Integer.parseInt(Sender.Post(this.loginURI, dataPost));
		} catch (IOException exception) {}
		
		return line;
	}

	public String sendTime(String username, String password, Integer played_time) {
		String response = "No response.";
		
		Date d1 = new Date();
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/YYYY HH:mm");
		String formattedDate = df.format(d1);
		
		try {
			String dataPost = "game_token=nike%2baccelerator&target_game_token=1&function=put&par=time_played&timestamp=" + URLEncoder.encode(formattedDate,"UTF-8") + "&value=" + URLEncoder.encode(String.valueOf(played_time),"UTF-8") + "&user_name=" + URLEncoder.encode(username, "UTF-8") + "&user_pw=" + URLEncoder.encode(password, "UTF-8");
			
			response = String.valueOf(Sender.Post("https://geopalz.com/games/request.php", dataPost));
		} catch (IOException exception) {}
		
		ConsoleLogger.i().info(response);
		
		return response;
	}
}
