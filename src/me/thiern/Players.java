package me.thiern;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class Players extends JavaPlugin{
	@Override
	public void onEnable() {
		getLogger().info("Hello World, Players is alive!");
	}
	
	@Override
	public void onDisable(){
		getLogger().info("Good buy :( it was nice while it lasted.");

		
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		if(cmd.getName().equalsIgnoreCase("hello") && sender instanceof Player){
			Player player = (Player) sender;
			player.sendMessage("Hello , " + player.getName() + "!" );
			return true;
		}
		
		return false;
	}
	
	
}
