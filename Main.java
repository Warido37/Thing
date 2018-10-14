package com.warido.bridge;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class Main extends JavaPlugin {
	String tag = ChatColor.GRAY+"["+ChatColor.DARK_GREEN+"BridgeManager"+ChatColor.GRAY+"]"+ChatColor.WHITE+" ";
	static FileConfiguration config;
	
	@Override
	public void onEnable() {
		
		this.getLogger().info(ChatColor.DARK_BLUE+"[BridgeManager]"+ChatColor.GREEN+" The plugin has been started");
		Listener ll = new ListenerW();
		Bukkit.getServer().getPluginManager().registerEvents(ll  , this);
	    config = this.getConfig();
	    saveConfig();
	}
	
	@Override
	public void onDisable() {
		this.getLogger().info(ChatColor.DARK_BLUE+"[BridgeManager]"+ChatColor.RED+" The plugin has been stopped");
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		int bridgen = 0;
		if(command.getName().equalsIgnoreCase("help")) {
			sender.sendMessage(tag+"The command is not done yet.");
		}else if(command.getName().equalsIgnoreCase("bridge")) {
			String player = "";
			String syn = "";
			Bukkit.getConsoleSender().sendMessage(args);
			if(args.length == 0) {
				player = sender.getName().toLowerCase();
				syn = "You are";
			}else{
				@SuppressWarnings("deprecation")
				Player pl = Bukkit.getPlayer(args[0]);
				if(pl != null) {
					syn = ChatColor.YELLOW+pl.getName()+ChatColor.AQUA+" is";
					player = pl.getName().toLowerCase();
				}else {
					sender.sendMessage(tag+ChatColor.RED+"Player not found");
					return true;
				}
			}
			sender.sendMessage(tag+syn+" on bridge "+ChatColor.AQUA+bridgen);
			return true;
		}else if(command.getName().equalsIgnoreCase("start")) {
			String player = sender.getName().toLowerCase();
			if(config.get(player)==null) {
				config.set(player+".bridge", 0);
				config.set(player+".highscore", 0);
				config.set(player+".start", 0);
				saveConfig();
				sender.sendMessage(tag+ChatColor.GREEN+"You have started bridging.");
			}else {
				sender.sendMessage(tag+ChatColor.RED+"You have already started bridging.\nDo /bridge to get back to your progress.");
			}
			return true;
		}
		return false;
	}

	public void saveconfig() {
		saveConfig();
	}
}
