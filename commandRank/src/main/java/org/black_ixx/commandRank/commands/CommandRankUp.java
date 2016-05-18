/*    */ package org.black_ixx.commandRank.commands;
/*    */ 
/*    */ import org.black_ixx.commandRank.CommandRank;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ public class CommandRankUp
/*    */   implements org.bukkit.command.CommandExecutor
/*    */ {
/*    */   private CommandRank plugin;
/*    */   
/* 12 */   public CommandRankUp(CommandRank plugin) { this.plugin = plugin; }
/*    */   
/*    */   public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) {
/* 15 */     if ((sender instanceof Player)) {
/* 16 */       Player p = (Player)sender;
/* 17 */       if (cmd.getName().equalsIgnoreCase("rankup"))
/*    */       {
/* 19 */         if (!p.hasPermission("CommandRank.RankUpCommand")) {
/* 20 */           p.sendMessage(this.plugin.getMessager().get("Main.NoPermissions"));
/* 21 */           this.plugin.playerFailedPermission(org.black_ixx.commandRank.helpers.PermissionsDebug.NoPermissionsCause.CommandRank_RankUpCommand, p);
/* 22 */           return false;
/*    */         }
/*    */         
/* 25 */         if (p.hasPermission("CommandRank.exclude.command")) {
/* 26 */           this.plugin.getMessager().sendMessage("Main.Excluded", p);
/* 27 */           return false;
/*    */         }
/*    */         
/*    */ 
/* 31 */         if (this.plugin.getRankUpTypes().getCommandRankUp().tryRankUp(p)) {
/* 32 */           return true;
/*    */         }
/*    */         
/* 35 */         p.sendMessage(this.plugin.getMessager().get("RankUpCommand.PlayerCantUseRankUp"));
/*    */       }
/*    */       
/*    */ 
/*    */     }
/*    */     else
/*    */     {
/* 42 */       sender.sendMessage("Ingame command!");
/* 43 */       return false;
/*    */     }
/*    */     
/*    */ 
/* 47 */     return false;
/*    */   }
/*    */ }
