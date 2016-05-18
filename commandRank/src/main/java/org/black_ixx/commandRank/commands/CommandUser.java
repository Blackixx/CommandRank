 package org.black_ixx.commandRank.commands;
 

import org.black_ixx.commandRank.CommandRank;
import org.black_ixx.commandRank.RankUp;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
 
 public class CommandUser implements org.bukkit.command.CommandExecutor
 {
   private CommandRank plugin;
   private boolean inf;
   
   public CommandUser(CommandRank plugin)
   {
     this.plugin = plugin;
     this.inf = plugin.getConfig().getBoolean("settings.rankupinformation.enabled");
   }
   
   public boolean onCommand(CommandSender s, org.bukkit.command.Command cmd, String commandLabel, String[] args)
   {
     if (cmd.getName().equalsIgnoreCase("cr"))
     {
 
       if (!(s instanceof org.bukkit.entity.Player)) {
         if (!this.inf) {
           s.sendMessage("Ingame command!");
           return false;
         }
         if (args.length == 0) {
           showConsolewMenu(s);
           return false;
         }
         
         if ((args[0].equalsIgnoreCase("info")) || (args[0].equalsIgnoreCase("information"))) {
           if (args.length != 2) {
             s.sendMessage(ChatColor.DARK_PURPLE + "/cr info <name of rankup> " + ChatColor.GREEN + "Shows information about RankUp");
             return false;
           }
           String r = args[1];
           if (this.plugin.getRankUps().get(r.toLowerCase()) == null) {
             this.plugin.getMessager().sendMessage("Main.RankUpNotExisting", s);
             return false;
           }
           ((RankUp)this.plugin.getRankUps().get(r.toLowerCase())).showInformation(s, this.plugin);
           
           return true;
         }
         
         showConsolewMenu(s);
         
         return false;
       }
       
 
 
       if (!s.hasPermission("CommandRank.UserCommand")) {
         s.sendMessage(this.plugin.getMessager().get("Main.NoPermissions"));
         this.plugin.playerFailedPermission(org.black_ixx.commandRank.helpers.PermissionsDebug.NoPermissionsCause.CommandRank_UserCommand, s);
         return false;
       }
       if (args.length == 0) {
         showMenu(s);
         return false;
       }
       
       String a = args[0];
       
       if (a.equalsIgnoreCase("kills")) {
         s.sendMessage(this.plugin.getMessager().get("UserCommand.ShowKills").replace("%kills%", Integer.toString(this.plugin.getManager().getKills(((Player)s).getUniqueId() )) ));
         return true;
       }
       
       if (a.equalsIgnoreCase("time")) {
         s.sendMessage(this.plugin.getMessager().get("UserCommand.ShowTime").replace("%time%", org.black_ixx.commandRank.helpers.TimeDisplayer.getDisplayTime(this.plugin.getManager().getTime(((Player)s).getUniqueId()))));
         return true;
       }
       
       if (this.inf)
       {
         if ((a.equalsIgnoreCase("info")) || (a.equalsIgnoreCase("information"))) {
           if (args.length != 2) {
             s.sendMessage(ChatColor.DARK_PURPLE + "/cr info <name of rankup> " + ChatColor.GREEN + "Shows information about RankUp");
             return false;
           }
           String r = args[1];
           if (this.plugin.getRankUps().get(r.toLowerCase()) == null) {
             this.plugin.getMessager().sendMessage("Main.RankUpNotExisting", s);
             return false;
           }
           ((RankUp)this.plugin.getRankUps().get(r.toLowerCase())).showInformation(s, this.plugin);
           
           return true;
         }
       }
       
 
 
 
 
       showMenu(s);
       return true;
     }
     
 
 
 
 
 
     return false;
   }
   
   public void showMenu(CommandSender p) {
     p.sendMessage(ChatColor.BLACK + "------------" + ChatColor.RED + " CommandRanks " + ChatColor.BLACK + "------------");
     p.sendMessage(ChatColor.DARK_PURPLE + "/cr time " + ChatColor.GREEN + "Shows onlineTime ");
     p.sendMessage(ChatColor.DARK_PURPLE + "/cr kills " + ChatColor.GREEN + "Shows amount of kills");
     if (this.inf)
       p.sendMessage(ChatColor.DARK_PURPLE + "/cr info <name of rankup> " + ChatColor.GREEN + "Shows information about RankUp");
   }
   
   public void showConsolewMenu(CommandSender p) {
     p.sendMessage(ChatColor.BLACK + "------------" + ChatColor.RED + " CommandRanks " + ChatColor.BLACK + "------------");
     p.sendMessage(ChatColor.DARK_PURPLE + "/cr info <name of rankup> " + ChatColor.GREEN + "Shows information about RankUp");
   }
 }
