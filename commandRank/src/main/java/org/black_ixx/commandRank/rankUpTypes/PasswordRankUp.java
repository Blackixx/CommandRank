/*    */ package org.black_ixx.commandRank.rankUpTypes;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.black_ixx.commandRank.helpers.RankUpHelper;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class PasswordRankUp
/*    */ {
/*    */   private List<String> rankUpList;
/*    */   
/*    */   public PasswordRankUp(List<String> list)
/*    */   {
/* 13 */     this.rankUpList = list;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean tryRankUp(Player p, String s)
/*    */   {
/* 19 */     for (String a : this.rankUpList) {
/* 20 */       String[] al = a.split(":");
/* 21 */       String pw = al[0].trim();
/* 22 */       if (pw.equalsIgnoreCase(s)) {
/* 23 */         RankUpHelper.rankUp(p, al[1].trim(), org.black_ixx.commandRank.helpers.RankUpHelper.RankUpType.PasswordCommand);
/* 24 */         return true;
/*    */       }
/*    */     }
/* 27 */     return false;
/*    */   }
/*    */ }

