/*    */ package org.black_ixx.commandRank.rankUpTypes;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.black_ixx.commandRank.helpers.RankUpHelper;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class CommandRankUp
/*    */ {
/*    */   private List<String> rankUpList;
/*    */   
/*    */   public CommandRankUp(List<String> list)
/*    */   {
/* 13 */     this.rankUpList = list;
/*    */   }
/*    */   
/*    */   public boolean tryRankUp(Player p) {
/* 17 */     String s = getNextRankUp(p);
/* 18 */     if (s == null) {
/* 19 */       return false;
/*    */     }
/*    */     
/* 22 */     RankUpHelper.rankUp(p, s, org.black_ixx.commandRank.helpers.RankUpHelper.RankUpType.RankupCommand);
/* 23 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getNextRankUp(Player p)
/*    */   {
/* 31 */     int i = 0;
/* 32 */     for (String s : this.rankUpList) {
/* 33 */       String[] sl = s.split(":");
/* 34 */       String sa = sl[0].trim();
/*    */       
/* 36 */       if ((p.hasPermission(sa)) && 
/* 37 */         (this.rankUpList.get(i) != null)) {
/* 38 */         return sl[1].trim();
/*    */       }
/* 40 */       i++;
/*    */     }
/* 42 */     return null;
/*    */   }
/*    */ }

