/*     */ package org.black_ixx.commandRank.helpers;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import org.black_ixx.commandRank.CommandRank;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ 
/*     */ public class MessageHandler
/*     */ {
/*     */   private final CommandRank plugin;
/*  14 */   private final String fileName = "messages.yml";
/*     */   private final File file;
/*  16 */   private FileConfiguration config = null;
/*     */   
/*     */   public MessageHandler(CommandRank plugin) {
/*  19 */     this.plugin = plugin;
/*  20 */     this.file = new File(plugin.getDataFolder().getAbsolutePath(), "messages.yml");
/*  21 */     this.config = YamlConfiguration.loadConfiguration(this.file);
/*  22 */     setDefaults();
/*  23 */     reloadConfig();
/*     */   }
/*     */   
/*     */   public FileConfiguration getConfig() {
/*  27 */     if (this.config == null) {
/*  28 */       reloadConfig();
/*     */     }
/*  30 */     return this.config;
/*     */   }
/*     */   
/*     */   public void reloadConfig() {
/*  34 */     this.config = YamlConfiguration.loadConfiguration(this.file);
/*  35 */     InputStream defConfigStream = this.plugin.getResource("messages.yml");
/*  36 */     if (defConfigStream != null) {
/*  37 */       YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
/*  38 */       this.config.setDefaults(defConfig);
/*     */     }
/*     */   }
/*     */   
/*     */   public void saveConfig() {
/*     */     try {
/*  44 */       getConfig().save(this.file);
/*     */     } catch (IOException e) {
/*  46 */       LogHandler.severe("Could not save message config to " + this.file, e);
/*     */     }
/*     */   }
/*     */   
/*     */   public void sendMessage(String node, CommandSender sender) {
/*  51 */     sendMessage(node, sender, null);
/*     */   }
/*     */   
/*     */   public void sendMessage(String node, CommandSender sender, String targetName) {
/*  55 */     if (sender != null) {
/*  56 */       String message = get(node, sender.getName(), targetName);
/*     */       String[] arrayOfString;
/*  58 */       int j = (arrayOfString = message.split("\n")).length; for (int i = 0; i < j; i++) { String line = arrayOfString[i];
/*  59 */         sender.sendMessage(line);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*  64 */   public String get(String node) { return get(node, null, null); }
/*     */   
/*     */   private String get(String node, String playerName, String targetName)
/*     */   {
/*  68 */     return replace(this.config.getString(node, node), playerName, targetName);
/*     */   }
/*     */   
/*     */   private String replace(String message, String playerName, String targetName) {
/*  72 */     message = message.replace("&", "§");
/*     */     
/*     */ 
/*     */ 
/*  76 */     if (playerName != null) {
/*  77 */       message = message.replace("%name%", playerName);
/*     */     }
/*     */     
/*  80 */     if (targetName != null) {
/*  81 */       message = message.replace("%target%", targetName);
/*     */     }
/*     */     
/*     */ 
/*  85 */     return message;
/*     */   }
/*     */   
/*     */   private void setDefaults()
/*     */   {
/*  90 */     this.config.addDefault("Main.NoPermissions", "&cYou are not allowed to do this!");
/*  91 */     this.config.addDefault("Main.RankUpNotExisting", "&cThe RankUp is not existing...");
/*  92 */     this.config.addDefault("Main.TellPlayersToReadRules", "&4Accept the rules and become &cUser&4! &c/AcceptRules");
/*  93 */     this.config.addDefault("Main.Excluded", "&c*Nothing happens*");
/*  94 */     this.config.addDefault("Economy.AccountMissing", "&cYou don't have an economy account...!");
/*  95 */     this.config.addDefault("Economy.NotEnoughMoney", "&cYou don't have enough money (%money%/%needed%)!");
/*  96 */     this.config.addDefault("Kills.NotEnoughKills", "&cYou don't have enough kills!");
/*  97 */     this.config.addDefault("OnlineTime.NotEnoughTime", "&cYou were not online long enough to use the &4RankUp&c!");
/*  98 */     this.config.addDefault("XP.NotEnoughXP", "&cYou don't have enough XP!");
/*  99 */     this.config.addDefault("NeedItems.NotEnoughItems.user", "&cYou need 10 Log and 60 Dirt to become User!");
/* 100 */     this.config.addDefault("NeedItems.NotEnoughItems.builder", "&cYou need 40 iron ingots and 2 diamond blocks to become Builder!");
/* 101 */     this.config.addDefault("NeedItems.NotEnoughItems.prisoner", "&cYou need nothing to become Prisoner xD");
/* 102 */     this.config.addDefault("AcceptRulesCommand.PlayerDidNotReadRules", "&cYou have to read the rules first!");
/* 103 */     this.config.addDefault("RankUpCommand.PlayerCantUseRankUp", "&cYou can not use a RankUp at the moment!");
/* 104 */     this.config.addDefault("PasswordCommand.WrongPassword", "&cThis is the wrong password...");
/* 105 */     this.config.addDefault("PasswordCommand.PlayerDidNotEnterAnyPassword", "&1/password &4<pw> &1!");
/* 106 */     this.config.addDefault("AdminCommand.TargetNotFound", "&4%target% &cis not online!");
/* 107 */     this.config.addDefault("AdminCommand.ShowKills", "&4%target% &chas &4%kills% &ckills!");
/* 108 */     this.config.addDefault("AdminCommand.ShowTime", "&4%target% &cwas &4%time% &conline!");
/* 109 */     this.config.addDefault("AdminCommand.PlayerRankUpExecuted", "&cSuccessfully executed rankup &4%rankup% &cfor player &4%target%!");
/* 110 */     this.config.addDefault("AdminCommand.PlayerRankUpFailed", "&cRankup &4%rankup% &cfor player &4%target% &cfailed!");
/* 111 */     this.config.addDefault("AdminCommand.Reload.Start", "&4CommandRank reload starting...");
/* 112 */     this.config.addDefault("AdminCommand.Reload.End", "&4...finished!!!");
/* 113 */     this.config.addDefault("AdminCommand.ArgumentIsNotInteger", "&4%argument% &cis not an integer!");
/* 114 */     this.config.addDefault("AdminCommand.SetTime", "&4%target%'s &cold time: &4%oldtime%&c. The time was set to &4%newtime%&c!");
/* 115 */     this.config.addDefault("AdminCommand.SetKills", "&4%target%'s &cold amount of kills: &4%oldkills%&c. The amount was set to &4%newkills%&c!");
/* 116 */     this.config.addDefault("UserCommand.ShowKills", "&cYour amount of kills: &4%kills%&c!");
/* 117 */     this.config.addDefault("UserCommand.ShowTime", "&cYour were already &4%time%&c online!");
/* 118 */     this.config.addDefault("RankUpInformation.Template.RankUpName", "&4%name%");
/* 119 */     this.config.addDefault("RankUpInformation.Template.EconomyPrice", "&4Price: &c%price%");
/* 120 */     this.config.addDefault("RankUpInformation.Template.OnlineTime", "&4Online time: &c%time%");
/* 121 */     this.config.addDefault("RankUpInformation.Template.XPLevel", "&4XP level: &c%level%");
/* 122 */     this.config.addDefault("RankUpInformation.Template.Items.Title", "&4Items:");
/* 123 */     this.config.addDefault("RankUpInformation.Template.Items.OneLine", "  &8- &6%itemAmount% &c%itemName%");
/* 124 */     this.config.addDefault("RankUpInformation.Template.Kills", "&4Kills: &c%kills%");
/* 125 */     this.config.options().copyDefaults(true);
/* 126 */     saveConfig();
/*     */   }
/*     */ }
