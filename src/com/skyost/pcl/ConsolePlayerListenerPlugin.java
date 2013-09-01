package com.skyost.pcl;

import java.util.logging.Handler;
import java.util.logging.LogRecord;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ConsolePlayerListenerPlugin extends JavaPlugin {
	Handler han;
	
	public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args){
        if(cmd.getName().equalsIgnoreCase("listen")) {
        	if(sender instanceof Player) {
                    if(sender.hasPermission("console.playerlistener.listen")) {
                    	if(args[0].equalsIgnoreCase("start") || args[0].equalsIgnoreCase("st")) {
                    		sender.sendMessage(ChatColor.GREEN + "Getting every messages from the console...");
                    		han = new Handler() {
                                @Override
                                public void close() throws SecurityException { /* Do nothing...  */ }
                                @Override
                                public void flush() { /* Do nothing...  */ }
                                @Override
                                public void publish(LogRecord logRecord) { sender.sendMessage("[" + logRecord.getLevel().toString() + "]" + " - " + logRecord.getMessage()); }
                            };
                            Bukkit.getServer().getLogger().addHandler(han);
                    	}
                    	else if(args[0].equalsIgnoreCase("stop") || args[0].equalsIgnoreCase("sp")) {
                    		sender.sendMessage(ChatColor.RED + "Stop getting every messages from the console...");
                    		Bukkit.getServer().getLogger().removeHandler(han);
                    	}
                    	else {
                    		return false;
                    	}
                    }
            	}
               	else {
                    sender.sendMessage(ChatColor.RED + "You don't have permission to do this !");
               	}
        	}
        	else {
        		sender.sendMessage(ChatColor.RED + "[ConsolePlayerListener] You can't do this from the console !");
        	}
        return true;
    }
}