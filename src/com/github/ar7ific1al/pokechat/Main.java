package com.github.ar7ific1al.pokechat;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.ar7ific1al.pokechat.commands.ChatChannelCommands;
import com.github.ar7ific1al.pokechat.listeners.ChatListener;

public class Main extends JavaPlugin {
	
	public static Log logger;
	
	public String version;
	public List<String> authors;
	
	public static File PartiesDir;
	public static File SettingsDir;
	public static File SettingsFile;
	
	public static HashMap<Player, String> AdminChannel = new HashMap<Player, String>();
	public static HashMap<Player, String> GymChannel = new HashMap<Player, String>();
	
	@Override
	public void onEnable(){
		logger = new Log();
		PluginManager pm = Bukkit.getServer().getPluginManager();
		PluginDescriptionFile pdFile = this.getDescription();
		version = pdFile.getVersion();
		authors = pdFile.getAuthors();
		Log.LogMessage(Log.ColorMessage("&7[&cPoke&fChat&7]&f v." + version + " enabled."), getServer().getConsoleSender());
		
		if (!getDataFolder().exists()){
			getDataFolder().mkdir();
		}
		PartiesDir = new File(getDataFolder() + "/Parties");
		SettingsDir = new File(getDataFolder() + "/Settings");
		SettingsFile = new File(SettingsDir, "settings.yml");
		if (!SettingsFile.exists()){
			saveResource("Settings/settings.yml", false);
		}
		ChatChannelCommands channelCommands = new ChatChannelCommands(this);
		getCommand("ac").setExecutor(channelCommands);
		getCommand("gc").setExecutor(channelCommands);
		
		pm.registerEvents(new ChatListener(this), this);
	}
	
	@Override
	public void onDisable(){
		
	}

}
