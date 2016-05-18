 package org.black_ixx.commandRank.helpers;
 
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.IOException;
 import org.black_ixx.commandRank.CommandRank;
 import org.bukkit.command.CommandSender;
 import org.bukkit.configuration.file.YamlConfiguration;
 
 public class PermissionsDebug
 {
   private CommandRank plugin;
   private File file;
   private YamlConfiguration config;
   
   public PermissionsDebug(CommandRank plugin)
   {
     this.plugin = plugin;
     this.file = new File(plugin.getDataFolder().getAbsolutePath() +    "/PermissionsDebug.yml");
     this.config = YamlConfiguration.loadConfiguration(this.file);
   }
   
 
 
 
   public void save()
   {
     try
     {
       this.config.save(this.file);
     } catch (IOException e1) {
       this.plugin.getLogger().warning(
         "File I/O Exception on saving storage.yml");
       e1.printStackTrace();
     }
   }
   
   public void reload() {
     try {
       this.config.load(this.file);
     } catch (FileNotFoundException e) {
       e.printStackTrace();
     } catch (IOException e) {
       e.printStackTrace();
     } catch (org.bukkit.configuration.InvalidConfigurationException e) {
       e.printStackTrace();
     }
   }
   
 
   public static enum NoPermissionsCause
   {
     CommandRank_RankUp_Name("RankUpHelper.rankUp permission: CommandRank.RankUp.<name>"), 
     CommandRank_Acceptrules("CommandAcceptRules.onCommand permission: CommandRank.AcceptRules"), 
     CommandRank_DeclineRules("DeclineRules.onCommand permission: CommandRank.DeclineRules"), 
     CommandRank_Password("CommandPassword.onCommand permission: CommandRank.Password"), 
     CommandRank_RankUpCommand("CommandRankUp.onCommand permission: CommandRank.RankUpCommand"), 
     CommandRank_UserCommand("CommandUser.onCommand permission: CommandRank.UserCommand"), 
     CommandRank_AdminCommand("CommandAdmin.onCommand permission: CommandRank.AdminCommand"), 
     CommandRank_CreateSign("SignListener.onCreate permission: CommandRank.CreateSign"), 
     CommandRank_UseSign("SignListener.onClick permission: CommandRank.useSign");
     
 
 
     private String id;
     
 
     private NoPermissionsCause(String name)
     {
       this.id = name;
     }
     
     public String getCause() { return this.id; }
   }
   
 
 
   public void addPermissionFailed(CommandSender p, NoPermissionsCause cause)
   {
     String x = "The player " + p.getName() + " did not have a needed permission. The cause: " + cause.getCause() + ".";
     if (p.isOp()) {
       x = x + "Also he was OP! ";
     }
     if (p.hasPermission("CommandRank.User")) {
       x = x + "And he had the permission CommandRank.User! ";
     }
     if (p.hasPermission("CommandRank.admin")) {
       x = x + "And he had the permission CommandRank.admin";
     }
     org.bukkit.Bukkit.broadcastMessage(org.bukkit.ChatColor.RED + "[CR] Debug: " + org.bukkit.ChatColor.WHITE + x);
     this.config.set(Long.toString(System.currentTimeMillis()), x);
     save();
   }
 }

