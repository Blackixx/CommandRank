/*    */ package org.black_ixx.commandRank.commands;
/*    */ 
/*    */ import org.black_ixx.commandRank.CommandRank;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ public class CommandDeclineRules
/*    */   implements org.bukkit.command.CommandExecutor
/*    */ {
/*    */   private CommandRank plugin;
/*    */   
/* 14 */   public CommandDeclineRules(CommandRank plugin) { this.plugin = plugin; }
/*    */   
/*    */   public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
/* 17 */     if ((sender instanceof Player)) {
/* 18 */       Player p = (Player)sender;
/* 19 */       if (cmd.getName().equalsIgnoreCase("declinerules"))
/*    */       {
/* 21 */         if (!p.hasPermission("CommandRank.DeclineRules")) {
/* 22 */           p.sendMessage(this.plugin.getMessager().get("Main.NoPermissions"));
/* 23 */           this.plugin.playerFailedPermission(org.black_ixx.commandRank.helpers.PermissionsDebug.NoPermissionsCause.CommandRank_DeclineRules, p);
/* 24 */           return false;
/*    */         }
/*    */         
/* 27 */         if (p.hasPermission("CommandRank.exclude.command")) {
/* 28 */           this.plugin.getMessager().sendMessage("Main.Excluded", p);
/* 29 */           return false;
/*    */         }
/*    */         
/*    */ 
/* 33 */         this.plugin.getReadRulesManager().removePlayer(p.getName());
/*    */         
/* 35 */         org.black_ixx.commandRank.helpers.RankUpHelper.rankUp(p, this.plugin.getConfig().getString("settings.declinerules.rankup"), org.black_ixx.commandRank.helpers.RankUpHelper.RankUpType.DeclineRulesCommand);
/* 36 */         return true;
/*    */       }
/*    */       
/*    */ 
/*    */     }
/*    */     else
/*    */     {
/* 43 */       sender.sendMessage("Ingame command!");
/* 44 */       return false;
/*    */     }
/*    */     
/*    */ 
/* 48 */     return false;
/*    */   }
/*    */ }
