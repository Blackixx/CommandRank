 package org.black_ixx.commandRank.helpers;
 
 import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.black_ixx.commandRank.CommandRank;
import org.black_ixx.playerpoints.PlayerPoints;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
 
 public class AutoRankUpCheck
 {
   private List<String> killList;
   private List<String> pointList;
   private List<String> timeList;
   private List<String> expList;
   private boolean points;
   private boolean kills;
   private boolean time;
   private boolean exp;
   private Manager m;
   private CommandRank plugin;
   private PlayerPoints pa;
   
   public AutoRankUpCheck(boolean p, boolean k, boolean t, boolean e, List<String> killList, List<String> timeList, List<String> pointList, List<String> expList, Manager man, CommandRank pp)
   {
     this.m = man;
     this.plugin = pp;
     this.points = p;
     this.kills = k;
     this.time = t;
     this.exp = e;
     if (k) {
       this.killList = killList;
     }
     if (t) {
       this.timeList = timeList;
     }
     if (p) {
       if (Bukkit.getPluginManager().getPlugin("PlayerPoints") == null) {
         LogHandler.severe("You need the plugin PlayerPoints to enable the AutoRankUp with points! Download it here: http://dev.bukkit.org/server-mods/playerpoints/");
         LogHandler.severe("Disabling CommandRank...");
         Bukkit.getPluginManager().disablePlugin(this.plugin);
       }
       Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("PlayerPoints");
       this.pa = ((PlayerPoints)PlayerPoints.class.cast(plugin));
       
       this.pointList = pointList;
     }
     if (e) {
       this.expList = expList;
     }
   }
   
   public void check()
   {
     if (this.kills) {
       killCheck();
     }
     if (this.points) {
       pointCheck();
     }
     if (this.time) {
       timeCheck();
     }
     if (this.exp) {
       expCheck();
     }
   }
   
 
   private void killCheck()
   {
	
		Collection<? extends Player> colOfPlayer = Bukkit.getOnlinePlayers();
		Player p;
		for (Iterator<? extends Player> iterator = colOfPlayer.iterator(); iterator.hasNext();) {
			p = iterator.next();
	
       if (!p.hasPermission("CommandRank.exclude.auto"))
       {
 
         int k = this.m.getKills(p.getUniqueId());
         int i = 0;
         String rankup = null;
         
         for (String s : this.killList) {
           String[] parts = s.split(":");
           int aI = Integer.parseInt(parts[0].trim());
           String aRankup = parts[1].trim();
           if ((k >= aI) && 
             (aI > i)) {
             i = aI;
             rankup = aRankup;
           }
         }
         
         if ((rankup != null) && 
           (!p.hasPermission("CommandRank.isGroup." + rankup)))
         {
 
           RankUpHelper.rankUp(p, rankup, RankUpHelper.RankUpType.AutoRankUp);
         }
       }
     }
   }
   
   private void pointCheck() {
    
		Collection<? extends Player> colOfPlayer = Bukkit.getOnlinePlayers();
		Player p;
		for (Iterator<? extends Player> iterator = colOfPlayer.iterator(); iterator.hasNext();) {
			p = iterator.next();
			
       if (!p.hasPermission("CommandRank.exclude.auto"))
       {
 
         int k = this.pa.getAPI().look(p.getName());
         int i = 0;
         String rankup = null;
         
         for (String s : this.pointList) {
           String[] parts = s.split(":");
           int aI = Integer.parseInt(parts[0].trim());
           String aRankup = parts[1].trim();
           if ((k >= aI) && 
             (aI > i)) {
             i = aI;
             rankup = aRankup;
           }
         }
         
         if ((rankup != null) && 
           (!p.hasPermission("CommandRank.isGroup." + rankup)))
         {
 
           RankUpHelper.rankUp(p, rankup, RankUpHelper.RankUpType.AutoRankUp); }
       }
     }
   }
   
   private void timeCheck() {
		Collection<? extends Player> colOfPlayer = Bukkit.getOnlinePlayers();
		Player p;
		for (Iterator<? extends Player> iterator = colOfPlayer.iterator(); iterator.hasNext();) {
			p = iterator.next();
		
       if (!p.hasPermission("CommandRank.exclude.auto"))
       {
 
         long k = this.m.getTime(p.getUniqueId());
         int i = 0;
         String rankup = null;
         
         for (String s : this.timeList) {
           String[] parts = s.split(":");
           int aI = Integer.parseInt(parts[0].trim());
           String aRankup = parts[1].trim();
           if ((k >= aI) && 
             (aI > i)) {
             i = aI;
             rankup = aRankup;
           }
         }
         
         if ((rankup != null) && 
           (!p.hasPermission("CommandRank.isGroup." + rankup)))
         {
 
           RankUpHelper.rankUp(p, rankup, RankUpHelper.RankUpType.AutoRankUp);
         }
       }
     }
   }
   
 
   private void expCheck()
   {
		Collection<? extends Player> colOfPlayer = Bukkit.getOnlinePlayers();
		Player p;
		for (Iterator<? extends Player> iterator = colOfPlayer.iterator(); iterator.hasNext();) {
			p = iterator.next();
		
       if (!p.hasPermission("CommandRank.exclude.auto"))
       {
 
         int l = p.getExpToLevel();
         int i = 0;
         String rankup = null;
         
         for (String s : this.expList) {
           String[] parts = s.split(":");
           int aI = Integer.parseInt(parts[0].trim());
           String aRankup = parts[1].trim();
           if ((l >= aI) && 
             (aI > i)) {
             i = aI;
             rankup = aRankup;
           }
         }
         
         if ((rankup != null) && 
           (!p.hasPermission("CommandRank.isGroup." + rankup)))
         {
 
           RankUpHelper.rankUp(p, rankup, RankUpHelper.RankUpType.AutoRankUp);
         }
       }
     }
   }
 }
