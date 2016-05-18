 package org.black_ixx.commandRank;
 
 import java.util.ArrayList;
 import java.util.List;
 import net.milkbowl.vault.economy.Economy;
 import org.black_ixx.commandRank.helpers.ItemStackTransformer;
 import org.black_ixx.commandRank.helpers.MessageHandler;
 import org.bukkit.Bukkit;
 import org.bukkit.Material;
 import org.bukkit.command.CommandSender;
 import org.bukkit.configuration.file.FileConfiguration;
 import org.bukkit.entity.Player;
 import org.bukkit.inventory.ItemStack;
 
 
 public class RankUp {
   private String msg;
   private String name;
   private boolean economy;
   private boolean onlineTime;
   private boolean needXP;
   private boolean needItems;
   private boolean needKills;
   private boolean playerCommand;
   private boolean consoleCommand;
   private boolean giveItems;
   private boolean equipArmor;
   private int price;
   private long time;
   private int xp;
   private List<ItemStack> itemsNeeded;
   private int kills;
   private List<String> playerCommands;
   private List<String> consoleCommands;
   private List<ItemStack> itemsGive;
   private List<String> information;
   
   public RankUp(CommandRank plugin, String name){
     FileConfiguration c = plugin.getConfig();
     String s = "rankups." + name + ".";
     
     this.name = name;
     this.msg = c.getString(s + "message");
     this.economy = c.getBoolean(s + "economy.enabled");
     this.onlineTime = c.getBoolean(s + "onlinetime.enabled");
     this.needXP = c.getBoolean(s + "needxplevel.enabled");
     this.needKills = c.getBoolean(s + "needkills.enabled");
     this.needItems = c.getBoolean(s + "needitems.enabled");
     this.giveItems = c.getBoolean(s + "giveitems.enabled");
     this.playerCommand = c.getBoolean(s + "commands.byplayer.enabled");
     this.consoleCommand = c.getBoolean(s + "commands.byconsole.enabled");
     
     this.price = c.getInt(s + "economy.price");
     this.time = c.getLong(s + "onlinetime.time");
     this.xp = c.getInt(s + "needxplevel.amount");
     
     this.itemsNeeded = new ArrayList<ItemStack>();
     for (String i : c.getStringList(s + "needitems.list")) {
       this.itemsNeeded.add(ItemStackTransformer.getItemStack(i));
     }
     
     this.kills = c.getInt(s + "needkills.amount");
     this.playerCommands = c.getStringList(s + "commands.byplayer.list");
     this.consoleCommands = c.getStringList(s + "commands.byconsole.list");
     
     this.itemsGive = new ArrayList<ItemStack>();
     this.equipArmor = c.getBoolean(s + "giveitems.equiparmor");
     for (String i : c.getStringList(s + "giveitems.list")) {
       this.itemsGive.add(ItemStackTransformer.getItemStack(i));
     }
     
 
     if (c.getBoolean("settings.rankupinformation.enabled")) {
       setInformation(plugin);
     }
   }
   
   public boolean checkEverything(Player p, CommandRank plugin)
   {
     double m;
     if (this.economy) {
       Economy e = plugin.getEconomy();
       if (!e.hasAccount(p)) {
         plugin.getMessager().sendMessage("Economy.AccountMissing", p);
         return false;
       }
       m = e.getBalance(p);
       if (m < this.price) {
         p.sendMessage(plugin.getMessager().get("Economy.NotEnoughMoney").replace("%money%", Double.toString(m)).replace("%needed%", Double.toString(this.price)));
         return false;
       }
     }
     if ((this.onlineTime) && 
       (plugin.getManager().getTime(p.getUniqueId()) < this.time)) {
       plugin.getMessager().sendMessage("OnlineTime.NotEnoughTime", p);
       return false;
     }
     
     if ((this.needXP) && 
       (p.getLevel() < this.xp)) {
       plugin.getMessager().sendMessage("XP.NotEnoughXP", p);
       return false;
     }
     
     if (this.needItems) {
       for (ItemStack i : this.itemsNeeded) {
         if (!plugin.getItemChecker().inventoryContainsItem(p, i)) {
           plugin.getMessager().sendMessage("NeedItems.NotEnoughItems." + this.name, p);
           
 
 
           return false;
         }
       }
     }
     if ((this.needKills) && 
       (plugin.getManager().getKills(p.getUniqueId()) < this.kills)) {
       plugin.getMessager().sendMessage("Kills.NotEnoughKills", p);
       return false;
     }
     
 
 
     return true;
   }
   
   public void actions(Player p, CommandRank plugin)
   {
     if (this.consoleCommand) {
       for (String s : this.consoleCommands) {
         Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s.replace("%name%", p.getName()));
       }
     }
     if (this.playerCommand) {
       for (String s : this.playerCommands) {
         p.performCommand(s.replace("%name%", p.getName()));
       }
     }
     if (this.giveItems) {
       for (ItemStack i : this.itemsGive) {
         if ((!this.equipArmor) || 
           (!equipAndTrueIfSuccess(i, p)))
         {
 
 
           p.getInventory().addItem(new ItemStack[] { i }); }
       }
     }
     if (this.economy) {
       Economy e = plugin.getEconomy();
       e.withdrawPlayer(p, this.price);
     }
     if (this.needXP) {
       p.setLevel(p.getLevel() - this.xp);
     }
     if (this.needItems) {
       for (ItemStack i : this.itemsNeeded) {
         plugin.getItemChecker().takeItem(i, p);
       }
       p.updateInventory();
     }
     p.sendMessage(this.msg.replace("%name%", p.getName()).replace("&", "§"));
   }
   
 
 
 
   public void goodActions(Player p, CommandRank plugin)
   {
     if (this.consoleCommand) {
       for (String s : this.consoleCommands) {
         Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s.replace("%name%", p.getName()));
       }
     }
     if (this.playerCommand) {
       for (String s : this.playerCommands) {
         p.performCommand(s.replace("%name%", p.getName()));
       }
     }
     if (this.giveItems) {
       for (ItemStack i : this.itemsGive) {
         if ((!this.equipArmor) || 
           (!equipAndTrueIfSuccess(i, p)))
         {
 
 
           p.getInventory().addItem(new ItemStack[] { i });
         }
       }
     }
   }
   
   public boolean getEconomyEnabled()
   {
     return this.economy;
   }
   
   public boolean getTimeEnabled() { return this.onlineTime; }
   
   public boolean getKillsEnabled() {
     return this.needKills;
   }
   
 
   private boolean equipAndTrueIfSuccess(ItemStack i, Player p) {
     Material m = i.getType();
     
     if ((m == Material.LEATHER_HELMET) || (m == Material.IRON_HELMET) || (m == Material.GOLD_HELMET) || (m == Material.CHAINMAIL_HELMET) || (m == Material.DIAMOND_HELMET)) {
       if (p.getInventory().getHelmet() == null) {
         p.getInventory().setHelmet(i);
         return true;
       }
       return false;
     }
     
     if ((m == Material.LEATHER_CHESTPLATE) || (m == Material.IRON_CHESTPLATE) || (m == Material.GOLD_CHESTPLATE) || (m == Material.CHAINMAIL_CHESTPLATE) || (m == Material.DIAMOND_CHESTPLATE)) {
       if (p.getInventory().getChestplate() == null) {
         p.getInventory().setChestplate(i);
         return true;
       }
       return false;
     }
     
     if ((m == Material.LEATHER_LEGGINGS) || (m == Material.IRON_LEGGINGS) || (m == Material.GOLD_LEGGINGS) || (m == Material.CHAINMAIL_LEGGINGS) || (m == Material.DIAMOND_LEGGINGS)) {
       if (p.getInventory().getLeggings() == null) {
         p.getInventory().setLeggings(i);
         return true;
       }
       return false;
     }
     
     if ((m == Material.LEATHER_BOOTS) || (m == Material.IRON_BOOTS) || (m == Material.GOLD_BOOTS) || (m == Material.CHAINMAIL_BOOTS) || (m == Material.DIAMOND_BOOTS)) {
       if (p.getInventory().getBoots() == null) {
         p.getInventory().setBoots(i);
         return true;
       }
       return false;
     }
     
 
     return false;
   }
   
   public void showInformation(CommandSender p, CommandRank plugin) {
     for (String s : this.information) {
       p.sendMessage(s);
     }
   }
   
   private void setInformation(CommandRank plugin) {
     MessageHandler m = plugin.getMessager();
     List<String> l = new ArrayList<String>();
     l.add(m.get("RankUpInformation.Template.RankUpName").replace("%name%", this.name));
     
     if (this.economy) {
       l.add(m.get("RankUpInformation.Template.EconomyPrice").replace("%price%", Double.toString(this.price)));
     }
     
     if (this.onlineTime) {
       l.add(m.get("RankUpInformation.Template.OnlineTime").replace("%time%", Long.toString(this.time)));
     }
     
     if (this.needXP) {
       l.add(m.get("RankUpInformation.Template.XPLevel").replace("%level%", Integer.toString(this.xp)));
     }
     
     if (this.needItems) {
       l.add(m.get("RankUpInformation.Template.Items.Title"));
       for (ItemStack i : this.itemsNeeded) {
         l.add(m.get("RankUpInformation.Template.Items.OneLine").replace("%itemName%", i.getType().name()).replace("%itemAmount%", Integer.toString(i.getAmount())));
       }
     }
     
     if (this.needKills) {
       l.add(m.get("RankUpInformation.Template.Kills").replace("%kills%", Integer.toString(this.kills)));
     }
     
     this.information = l;
   }
 }
