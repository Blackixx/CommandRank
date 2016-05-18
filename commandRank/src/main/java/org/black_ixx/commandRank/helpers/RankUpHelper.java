/*    */ package org.black_ixx.commandRank.helpers;
/*    */ 
/*    */ import org.black_ixx.commandRank.CommandRank;
/*    */ import org.black_ixx.commandRank.RankUp;
import org.black_ixx.commandRank.event.CommandRankRankUpActionsEvent;
/*    */ import org.black_ixx.commandRank.event.CommandRankRankUpCheckEvent;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class RankUpHelper
/*    */ {
/*    */   private static CommandRank plugin;
/*    */   
/*    */   public static void init(CommandRank pp)
/*    */   {
/* 14 */     plugin = pp;
/*    */   }
/*    */   
/*    */   public static enum RankUpType
/*    */   {
/* 19 */     Sign, 
/* 20 */     AcceptRulesCommand, 
/* 21 */     DeclineRulesCommand, 
/* 22 */     RankupCommand, 
/* 23 */     PasswordCommand, 
/* 24 */     AutoRankUp;
/*    */   }
/*    */   
/*    */   public static void rankUp(Player p, String rankup, RankUpType type)
/*    */   {
/* 29 */     if (!p.hasPermission("CommandRank.RankUp." + rankup)) {
/* 30 */       plugin.getMessager().sendMessage("Main.NoPermissions", p);
/* 31 */       plugin.playerFailedPermission(PermissionsDebug.NoPermissionsCause.CommandRank_RankUp_Name, p);
/* 32 */       return;
/*    */     }
/*    */     
/* 35 */     rankup = rankup.toLowerCase();
/* 36 */     if (!plugin.getRankUps().containsKey(rankup)) {
/* 37 */       plugin.getMessager().sendMessage("Main.RankUpNotExisting", p);
/* 38 */       return;
/*    */     }
/* 40 */     RankUp r = (RankUp)plugin.getRankUps().get(rankup);
/*    */     
/* 42 */     CommandRankRankUpCheckEvent ec = new CommandRankRankUpCheckEvent(p, rankup, type);
/* 43 */     plugin.getServer().getPluginManager().callEvent(ec);
/* 44 */     if (ec.isCancelled()) {
/* 45 */       return;
/*    */     }
/*    */     
/* 48 */     if (!r.checkEverything(p, plugin)) {
/* 49 */       return;
/*    */     }
/*    */     
/*    */ 
/* 53 */    CommandRankRankUpActionsEvent event = new CommandRankRankUpActionsEvent(p, rankup, type);
/* 54 */     plugin.getServer().getPluginManager().callEvent(event);
/* 55 */     if (event.isCancelled()) {
/* 56 */       return;
/*    */     }
/*    */     
/* 59 */     r.actions(p, plugin);
/*    */     
/* 61 */     LogHandler.info("RankUp successfully executed. Player: " + p.getName() + ". RankUp: " + rankup + ".");
/*    */   }
/*    */ }

