 package org.black_ixx.commandRank.helpers;
 
 import java.util.HashMap;
import java.util.UUID;

import org.black_ixx.commandRank.CommandRank;
import org.black_ixx.commandRank.RankUp;
import org.black_ixx.commandRank.event.CommandRankAddKillEvent;
import org.black_ixx.commandRank.event.CommandRankPlayerLeaveEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
 
 public class Manager
 {
   private HashMap<String, Long> joinTime;
   private HashMap<String, Integer> kills;
   private boolean kE;
   private boolean tE;
   
   public Manager(CommandRank plugin)
   {
     this.tE = false;
     this.kE = false;
     for (String s : plugin.getRankUps().keySet()) {
       RankUp r = (RankUp)plugin.getRankUps().get(s);
       if (r.getKillsEnabled()) {
         this.kE = true;
       }
       if (r.getTimeEnabled()) {
         this.tE = true;
       }
     }
     
     if (plugin.getConfig().getBoolean("autorankups.kills.enabled")) {
       this.kE = true;
     }
     if (plugin.getConfig().getBoolean("autorankups.onlinetime.enabled")) {
       this.tE = true;
     }
     
     if (this.tE)
       this.joinTime = new HashMap<String, Long>();
     if (this.kE) {
       this.kills = new HashMap<String, Integer>();
     }
   }
   
   public HashMap<String, Integer> getKills() {
     return this.kills;
   }
   
   public HashMap<String, Long> getJoinTime() { return this.joinTime; }
   
   public void playerTimeRefresh(UUID uuid, String name)
   {
	   
     if (this.tE) {
       long newTime = System.currentTimeMillis() - ((Long)this.joinTime.get(name)).longValue();
       this.joinTime.put(name, Long.valueOf(System.currentTimeMillis()));
       long oldTime = 0;
       if(PlayerStorage.oldTimeExist(name)) {
    	   oldTime = PlayerStorage.getTime(name);
    	   PlayerStorage.removeTime(name);
       }
       else {
    	   oldTime = PlayerStorage.getTime(uuid.toString());
       }
       long time = oldTime + newTime;
       PlayerStorage.setTime(uuid.toString(), time);
     }
   }
   
 
   public void playerLeave(UUID uuid, String name, CommandRank plugin)
   {
     if ((this.kE) && 
       (this.kills.containsKey(name))) {
       PlayerStorage.setKills(name, ((Integer)this.kills.get(name)).intValue());
       this.kills.remove(name);
     }
     if ((this.tE) && 
       (this.joinTime.containsKey(name))) {
       long newTime = System.currentTimeMillis() - ((Long)this.joinTime.get(name)).longValue();
       this.joinTime.remove(name);
       long oldTime = 0;
				PlayerStorage.getTime(name);
       long time = oldTime + newTime;
       PlayerStorage.setTime(name, time);
     }
     
     CommandRankPlayerLeaveEvent event = new CommandRankPlayerLeaveEvent(plugin, name);
     Bukkit.getPluginManager().callEvent(event);
   }
   
   public void playerJoin(UUID uuid, String name) {
     
	   int kills;
	   long time;
	   if (this.kE) {
    	 if(PlayerStorage.oldKillsExist(name)) {
    		 kills = PlayerStorage.getKills(name);
    		 PlayerStorage.removeKills(name);
    		 PlayerStorage.setKills(uuid, kills);
    	 }
    	 
       this.kills.put(uuid.toString(), Integer.valueOf(PlayerStorage.getKills(uuid)));
     }
     if (this.tE) {
    	 if(PlayerStorage.oldTimeExist(name)) {
    		 time = PlayerStorage.getTime(name);
    		 PlayerStorage.removeTime(name);
    		 PlayerStorage.setTime(uuid, time);
    	 }
       this.joinTime.put(uuid.toString(), Long.valueOf(System.currentTimeMillis()));
     }
   }
   
   public void addKill(UUID uuid) {
     if (this.kE)
       this.kills.put(uuid.toString(), Integer.valueOf(getKills(uuid) + 1));
   }
   
   public int setKills(UUID uuid, int i) {
     if (this.kE) {
       this.kills.put(uuid.toString(), Integer.valueOf(i));
       return i;
     }
     return 0;
   }
   
   public int getKills(UUID uuid) { if (!this.kills.containsKey(uuid.toString())) {
       return 0;
     }
     return ((Integer)this.kills.get(uuid.toString())).intValue();
   }
   
   public long getTime(UUID uuid) { 
	 long newTime;
	 if(Bukkit.getOfflinePlayer(uuid).isOnline()) {
		 newTime = System.currentTimeMillis() - ((Long)this.joinTime.get(uuid.toString())).longValue();
     	this.joinTime.put(uuid.toString(), Long.valueOf(System.currentTimeMillis()));
	 }
	 else {
		 newTime = 0;
	 }
     long oldTime = PlayerStorage.getTime(uuid);
     long time = oldTime + newTime;
     PlayerStorage.setTime(uuid, time);
     return time / 1000L;
   }
   
   public void playerKill(Player killer, Player victim) {
     if (!this.kE) {
       return;
     }
     CommandRankAddKillEvent event = new CommandRankAddKillEvent(killer, victim, getKills(killer.getUniqueId()));
     Bukkit.getPluginManager().callEvent(event);
     if (event.isCancelled()) {
       return;
     }
     addKill(killer.getUniqueId());
   }
 }

