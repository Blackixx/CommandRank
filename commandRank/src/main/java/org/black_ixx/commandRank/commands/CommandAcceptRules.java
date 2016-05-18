/*    */ package org.black_ixx.commandRank.commands;
/*    */ 
/*    */ import org.black_ixx.commandRank.CommandRank;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ public class CommandAcceptRules
/*    */   implements org.bukkit.command.CommandExecutor
/*    */ {
/*    */   private CommandRank plugin;
/*    */   
/* 15 */   public CommandAcceptRules(CommandRank plugin) { this.plugin = plugin; }
/*    */   
/*    */   public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
/* 18 */     if ((sender instanceof Player)) {
/* 19 */       Player p = (Player)sender;
/* 20 */       if (cmd.getName().equalsIgnoreCase("acceptrules"))
/*    */       {
/* 22 */         if (!p.hasPermission("CommandRank.AcceptRules")) {
/* 23 */           p.sendMessage(this.plugin.getMessager().get("Main.NoPermissions"));
/* 24 */           this.plugin.playerFailedPermission(org.black_ixx.commandRank.helpers.PermissionsDebug.NoPermissionsCause.CommandRank_Acceptrules, p);
/* 25 */           return false;
/*    */         }
/*    */         
/* 28 */         if (p.hasPermission("CommandRank.exclude.command")) {
/* 29 */           this.plugin.getMessager().sendMessage("Main.Excluded", p);
/* 30 */           return false;
/*    */         }
/*    */         
/*    */ 
/*    */ 
/* 35 */         if (org.black_ixx.commandRank.listeners.PlayerListener.acceptRules) {
/* 36 */           if (!this.plugin.getReadRulesManager().playerHasReadRules(p.getName())) {
/* 37 */             p.sendMessage(this.plugin.getMessager().get("AcceptRulesCommand.PlayerDidNotReadRules"));
/* 38 */             return false;
/*    */           }
/* 40 */           this.plugin.getReadRulesManager().removePlayer(p.getName());
/*    */         }
/* 42 */         org.black_ixx.commandRank.helpers.RankUpHelper.rankUp(p, this.plugin.getConfig().getString("settings.acceptrules.rankup"), org.black_ixx.commandRank.helpers.RankUpHelper.RankUpType.AcceptRulesCommand);
/*    */         
/* 44 */         return true;
/*    */       }
/*    */       
/*    */ 
/*    */     }
/*    */     else
/*    */     {
/* 51 */       sender.sendMessage("Ingame command!");
/* 52 */       return false;
/*    */     }
/*    */     
/*    */ 
/* 56 */     return false;
/*    */   }
/*    */ }

