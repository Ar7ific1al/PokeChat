package com.github.ar7ific1al.pokechat.commands;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.github.ar7ific1al.pokechat.Log;
import com.github.ar7ific1al.pokechat.Main;

public class ChatChannelCommands implements CommandExecutor {
	
	Main plugin;
	
	public ChatChannelCommands(Main instance){
		plugin = instance;
		Log.LogMessage(Log.ColorMessage("&7[&cPoke&fChat&7]&f ChatChannelCommands executor registered."), plugin.getServer().getConsoleSender());
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if (sender instanceof Player){
			try{
				if (args.length == 0){
					if (label.equalsIgnoreCase("ac")) {
						if (sender.hasPermission("pokechat.chat.adminchat")) {
							if (!Main.AdminChannel.containsKey((Player) sender)){
								Main.AdminChannel.put((Player)sender, "");
								sender.sendMessage(Log.ColorMessage("&7[&cPoke&fChat&7]&f Admin Chat toggled &2ON"));
							}
							else if (Main.AdminChannel.containsKey((Player) sender))	{
								Main.AdminChannel.remove((Player) sender);
								sender.sendMessage(Log.ColorMessage("&7[&cPoke&fChat&7]&f Admin Chat toggled &4OFF"));
							}
							if (Main.GymChannel.containsKey((Player) sender)){
								Main.GymChannel.remove((Player) sender);
								sender.sendMessage(Log.ColorMessage("&7[&cPoke&fChat&7]&f Gym Leader Chat toggled &4OFF"));
							}
							return true;
						}
					}
					else if (label.equalsIgnoreCase("gc"))	{
						if (sender.hasPermission("pokechat.chat.gymleaderchat"))	{
							if (!Main.GymChannel.containsKey((Player) sender)){
								Main.GymChannel.put((Player)sender, "");
								sender.sendMessage(Log.ColorMessage("&7[&cPoke&fChat&7]&f Gym Leader Chat toggled &2ON"));
							}
							else if (Main.GymChannel.containsKey((Player)sender))	{
								Main.GymChannel.remove((Player)sender);
								sender.sendMessage(Log.ColorMessage("&7[&cPoke&fChat&7]&f Gym Leader Chat toggled &4OFF"));
							}
							if (Main.AdminChannel.containsKey((Player)sender)){
								Main.AdminChannel.remove((Player)sender);
								sender.sendMessage(Log.ColorMessage("&7[&cPoke&fChat&7]&f Admin Chat toggled &4OFF"));
							}
							return true;
						}
					}
				}
				else if (args.length >= 1)	{
					if (label.equalsIgnoreCase("ac"))	{
						if (sender.hasPermission("pokechat.chat.adminchat"))	{
							FileConfiguration tmpfc = new YamlConfiguration();
							tmpfc.load(Main.SettingsFile);
							String message = "";
							for (int i = 0; i < args.length; i++){
								message += args[i];
								if (i != args.length)
									message += " ";
							}
							if (tmpfc.getBoolean("ChatChannels.Admin.AllowColors"))	{
								message = Log.ColorMessage(message);
							}
							for (Player p : Bukkit.getServer().getOnlinePlayers()){
								if (p.hasPermission("pokechat.chat.adminchat")){
									p.sendMessage((Log.ColorMessage("&4[&7AC&4] " + sender.getName() + ": &f")) + message);
								}
							}
						}
						return true;
					}
					else if (label.equalsIgnoreCase("gc")){
						if (sender.hasPermission("pokechat.chat.gymleaderchat"))	{
							FileConfiguration tmpfc = new YamlConfiguration();
							tmpfc.load(Main.SettingsFile);
							String message = "";
							for (int i = 0; i < args.length; i++){
								message += args[i];
								if (i != args.length)
									message += " ";
							}
							if (tmpfc.getBoolean("ChatChannels.GymLeader.AllowColors"))	{
								message = Log.ColorMessage(message);
							}
							for (Player p : Bukkit.getServer().getOnlinePlayers()){
								if (p.hasPermission("pokechat.chat.gymleaderchat")){
									p.sendMessage((Log.ColorMessage("&b[&7GC&b] " + sender.getName() + ": &f")) + message);
								}
							}
						}
						return true;
					}
				}
			}catch(ArrayIndexOutOfBoundsException ex){
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidConfigurationException e) {
				e.printStackTrace();
			}
		}
		else if (sender instanceof ConsoleCommandSender){
			try{
				if (args.length >= 1){
					if (label.equalsIgnoreCase("ac"))	{
						FileConfiguration tmpfc = new YamlConfiguration();
						tmpfc.load(Main.SettingsFile);
						String message = "";
						for (int i = 0; i < args.length; i++){
							message += args[i];
							if (i != args.length)
								message += " ";
						}
						if (tmpfc.getBoolean("ChatChannels.Admin.AllowColors"))	{
							message = Log.ColorMessage(message);
						}
						for (Player p : Bukkit.getServer().getOnlinePlayers()){
							if (p.hasPermission("pokechat.chat.adminchat")){
								p.sendMessage((Log.ColorMessage("&4[&7AC&4] " + sender.getName() + ": &f")) + message);
							}
						}
						Bukkit.getServer().getConsoleSender().sendMessage((Log.ColorMessage("&4[&7AC&4] " + sender.getName() + ": &f")) + message);
						return true;
					}
					else if (label.equalsIgnoreCase("gc")){
						FileConfiguration tmpfc = new YamlConfiguration();
						tmpfc.load(Main.SettingsFile);
						String message = "";
						for (int i = 0; i < args.length; i++){
							message += args[i];
							if (i != args.length)
								message += " ";
						}
						if (tmpfc.getBoolean("ChatChannels.GymLeader.AllowColors"))	{
							message = Log.ColorMessage(message);
						}
						for (Player p : Bukkit.getServer().getOnlinePlayers()){
							if (p.hasPermission("pokechat.chat.gymleaderchat")){
								p.sendMessage((Log.ColorMessage("&b[&7GC&b] " + sender.getName() + ": &f")) + message);
							}
						}
						Bukkit.getServer().getConsoleSender().sendMessage((Log.ColorMessage("&b[&7GC&b] " + sender.getName() + ": &f")) + message);
						return true;
					}
				}
			}catch(ArrayIndexOutOfBoundsException | IOException | InvalidConfigurationException ex){
				
			}
		}
		return false;
	}
}
