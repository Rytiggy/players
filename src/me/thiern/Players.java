package me.thiern;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.JsonObject;


public class Players extends JavaPlugin{
	
	public static void main(String[] args) throws IOException {
		
		JSONArray party = new JSONArray();
		party.add("funcamdog");
		party.add("imdare");
		party.add("redeye3323");
		party.add("test");
		writeJson("players.json", "funcamdog",party, "party info");
		//readJson("players.json");
	}
	 
	 
	public static JSONArray readJson(String f) { 
		JSONParser parser = new JSONParser();
		JSONArray party = null;
		
		try {

			Object obj = parser.parse(new FileReader(f));
			
			party =  (JSONArray) obj;
		
			System.out.println("PARTY"  + party.size());
			
			for(int i = 0; i < party.size(); i++){
				System.out.println("Party Leader: "  + ((JSONObject) party.get(i)).get("leader"));
				System.out.println("Party members list: "  + ((JSONObject) party.get(i)).get("members"));
				System.out.println("Party status: "  + ((JSONObject) party.get(i)).get("status"));
				System.out.println("--------------------");
			}
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return party;

	}
	public static void writeJson(String f, String leader ,JSONArray party , String status) { 
		JSONObject obj = new JSONObject();

		obj.put("leader", leader);
		obj.put("status", status);
		obj.put("members", party);

		try {
		
			JSONArray aParty = readJson("players.json");
			
			aParty.add(obj);


			FileWriter file = new FileWriter(f);
			file.write(aParty.toJSONString());
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	


	
	
	
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
    		
    		String[] party = null;
    		
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
