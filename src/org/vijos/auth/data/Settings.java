package org.vijos.auth.data;

import org.bukkit.configuration.file.YamlConfiguration;

public class Settings {
	
	public static String HashMethod = "md5";
	
	private static Settings instance;
	
	private YamlConfiguration config;
	
	public static Settings i() {
		return Settings.instance;
	}
	
	public Settings() {
		Settings.instance = this;
	}
	
	public void loadConfig() {
		this.config.set("Login.AtSpawn", true);
		this.config.set("API.HashMethod", "md5");
		this.config.set("API.CheckCredentials", false);
		this.config.set("API.StatusURI", "https://192.155.87.155/api/status.php");
		this.config.set("API.LoginURI", "https://192.155.87.155/api/login.php");
		
		Settings.HashMethod = instance.getString("API.HashMethod").toLowerCase();
	}
	
	public boolean getBoolean(String path) {
		return this.config.getBoolean(path);
	}
	
	public String getString(String path) {
		return this.config.getString(path);
	}
	
}
