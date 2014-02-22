package com.github.ar7ific1al.pokechat.listeners;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.github.ar7ific1al.pokechat.Log;
import com.github.ar7ific1al.pokechat.Main;

public class ChatListener implements Listener	{
	
	Main plugin;
	
	public ChatListener(Main instance){
		plugin = instance;
		Log.LogMessage(Log.ColorMessage("&7[&cPoke&fChat&7]&f ChatListener registered"), plugin.getServer().getConsoleSender());
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event){
		if (Main.AdminChannel.containsKey(event.getPlayer())){
			String message = event.getMessage();
			FileConfiguration tmpfc = new YamlConfiguration();
			try {
				tmpfc.load(Main.SettingsFile);
				if (tmpfc.getBoolean("ChatChannels.Admin.AllowColors"))	{
					message = Log.ColorMessage(message);
				}
			} catch (IOException | InvalidConfigurationException e) {
				e.printStackTrace();
			}
			for (Player p : Bukkit.getOnlinePlayers()){
				if (p.hasPermission("pokechat.chat.adminchat")){
					p.sendMessage((Log.ColorMessage("&4[&7AC&4] " + event.getPlayer().getName() + ": &f") + message));
				}
			}
			plugin.getServer().getConsoleSender().sendMessage(Log.ColorMessage("&4[&7AC&4] " + event.getPlayer().getName() + ": &f" + message));
			event.setCancelled(true);
		}
		else if (Main.GymChannel.containsKey(event.getPlayer())){
			String message = event.getMessage();
			FileConfiguration tmpfc = new YamlConfiguration();
			try {
				tmpfc.load(Main.SettingsFile);
				if (tmpfc.getBoolean("ChatChannels.GymLeader.AllowColors"))	{
					message = Log.ColorMessage(message);
				}
			} catch (IOException | InvalidConfigurationException e) {
				e.printStackTrace();
			}
			for (Player p : Bukkit.getServer().getOnlinePlayers()){
				if (p.hasPermission("pokechat.chat.gymleaderchat")){
					p.sendMessage((Log.ColorMessage("&b[&7GC&b] " + event.getPlayer().getName() + ": &f") + message));
				}
			}
			plugin.getServer().getConsoleSender().sendMessage(Log.ColorMessage("&b[&7GC&b] " + event.getPlayer().getName() + ": &f" + message));
			event.setCancelled(true);
		}
	}

}
