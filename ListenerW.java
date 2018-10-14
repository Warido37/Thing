package com.warido.bridge;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class ListenerW implements Listener {
	String tag = ChatColor.GRAY+"["+ChatColor.DARK_GREEN+"BridgeManager"+ChatColor.GRAY+"]"+ChatColor.WHITE+" ";
		 
	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent event) {
		Player player = (Player) event.getPlayer();
		if(Main.config.get(player.getName().toLowerCase())==null) {
			return;
		}
		//if(event.getPlayer().getWorld().getName()) {
			player.getInventory().setItem(4, new ItemStack(Material.WOOL, 16));
			if(player.getLocation().getBlockY() < 1) {
				Location loc = new Location(player.getWorld(), 24, 7, player.getLocation().getZ()-1);
				loc.setPitch(0);
				loc.setYaw(90);
				loc.getBlock().setType(Material.CONCRETE);
				loc.setY(8);
				player.setInvulnerable(true);
				player.teleport(loc);
				player.sendMessage(tag+ChatColor.GOLD+"You reached bridge "+ChatColor.AQUA+Math.round(player.getLocation().getZ()));
			}
		//}
	}
}