package org.vijos.auth.data;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

public class Messages {
	
	private static Messages instance;
	
	private YamlConfiguration config;
	private File configFile;
	
	public static Messages i() {
		return Messages.instance;
	}
	
	public Messages() {
		Messages.instance = this;
	}
	
	public void loadConfig() {
		this.config = YamlConfiguration.loadConfiguration(this.configFile);
		this.config.addDefault("Message.Kick", "&a[GeoPalz]&f Please log in to continue.&f");
		this.config.addDefault("Message.Ban", "&a[GeoPalz]&f You have been banned.&f");
		this.config.addDefault("Message.UserNotExists", "&a[GeoPalz]&f Server down for maintainence.&f");
		this.config.addDefault("Message.Permission", "&a[GeoPalz]&f &cYou don't have the permission to run this command.&f");
		this.config.addDefault("Message.Login.Success", "&a[GeoPalz]&f &bLogged in successfully.&f");
		this.config.addDefault("Message.Login.Start", "&a[GeoPalz]&f &bLogging you in...&f");
		this.config.addDefault("Message.Login.Ing", "&a[GeoPalz]&f &cPlease wait...&f");
		this.config.addDefault("Message.Login.Fail", "&a[GeoPalz]&f &cWrong username or password!&f");
		this.config.addDefault("Message.Login.Tip", "&a[GeoPalz]&f &ePleaze use [/login username password] to login.&f");
		this.config.addDefault("Message.Login.Welcome", "&a[GeoPalz]&f &eWelcome back!&f");
		this.config.addDefault("Message.Login.In", "&a[GeoPalz]&f &cYou have already logged in.&f");
		this.config.addDefault("Message.Logout.Success", "&a[GeoPalz]&f &bLogged out successfully.&f");
		this.config.addDefault("Message.Logout.Fail", "&a[GeoPalz]&f &cYou haven't logged in.&f");
		this.config.options().copyDefaults(true);
	}
	
	public String getMessage(String action) {
		return this.config.getString("Message." + action).replaceAll("(&([a-f0-9]))", "\u00A7$2");
	}
	
}
