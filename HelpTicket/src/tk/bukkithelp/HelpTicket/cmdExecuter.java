package tk.bukkithelp.HelpTicket;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.util.ArrayList;

public class cmdExecuter implements CommandExecutor {
    

	public settings cSettings = new settings();
	public ArrayList<String> plugins = new ArrayList<String>();
	
	@SuppressWarnings("unused")
	private HelpTicket plugin;
 
	public cmdExecuter(HelpTicket plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		Player player = null;
		if (sender instanceof Player) {
			player = (Player) sender;
			
		     if(command.getName().equalsIgnoreCase("newticket") || command.getName().equalsIgnoreCase("nticket")){
		    	 System.out.println("TEST");
		    	 player.sendMessage("Sender: " + player.getName());
		    	 player.sendMessage("Java Info: " + javaInfo());
		    	 player.sendMessage("OS Info: " + osInfo());
		    	 Plugin[] all = getPlugins();
	                //player.getWorld().playEffect(player.getLocation(), Effect.SMOKE, 1000, 1000);		    	 
	                for(Plugin plugin:all){
			    		 String s = plugin.getDescription().getName();
			    		 plugins.add(s);
			    	 }
	             player.sendMessage("Plugins: " + plugins);  		    			    	 
		    	 return true;
		     }else if(command.getName().equalsIgnoreCase("htreload") || command.getName().equalsIgnoreCase("htr")){
		    	 cSettings.reloadData();
		    	 return true;
		     }	
		     
		}else{
			System.out.println("[HelpTicket] Plaease run this command as a player");
			return true;
		}		
		return false;
}
	
	public String javaInfo(){
		String version = System.getProperty("java.version");
		String vendor = System.getProperty("java.vendor");
		return (vendor + " " + version);
	}
	
	public String osInfo(){
		String os = System.getProperty("os.name");
		String version = System.getProperty("os.version");
		String arch = System.getProperty("os.arch");
		return (os + " " + version + " " + arch);
	}
	
	public Plugin[] getPlugins(){
		Plugin[] list = plugin.getServer().getPluginManager().getPlugins();
		return list;
	}
	
}