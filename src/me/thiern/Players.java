package me.thiern;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
	
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		Player player = (Player) sender;	
    	if(cmd.getName().equalsIgnoreCase("party") && sender instanceof Player){
			
    		if(args.length == 0){
    			player.sendMessage(ChatColor.RED + "Usage: /Party invite <player Name>");
				player.sendMessage(ChatColor.RED + "Usage: /Party invite <player Name> <player Name> <player Name>...");
				player.sendMessage(ChatColor.RED + "/party join");
				player.sendMessage(ChatColor.RED + "/party leave");
				return true;
    		}
    		
    		String arguments = "";
    		String users = "";
    		
    		for (int x = 0; x<args.length; x++){   			
    			String temp = args[x] + ",";
    			arguments = arguments +  temp;
    		}
    		for (int i = 1; i<args.length; i++){   			
    			String temp = args[i] + " ";
    			//check if the user that was passed in is online
    			if(Bukkit.getPlayerExact(args[i]) != null){
        			player.sendMessage(ChatColor.DARK_PURPLE + args[i] + "is online");
        			users = users +  temp;
    			}else{
        			player.sendMessage(ChatColor.RED + args[i] + "is offline");

    			}
    			//Player aplayer = Bukkit.getPlayerExact(args[0]);

    		}
    		//if the second argument is invite
    		//users at this point only contains online users 
    		String inv = "invite";
    		
    		String[] party = new String[6];
    		
    		if( arguments.toLowerCase().indexOf(inv.toLowerCase()) != -1 ){
    			player.sendMessage(ChatColor.GREEN + "/party invite " + users);
    			party = users.split(",");
    			getLogger().info("Party Created" +party +" "+ party[0]);
    		}
    	
			//player.sendMessage(ChatColor.GREEN + arguments);
			return true;
    		
    	}


		return false;
	}
	
	
}
