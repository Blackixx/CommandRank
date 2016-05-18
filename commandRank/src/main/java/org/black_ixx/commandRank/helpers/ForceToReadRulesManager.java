/*    */ package org.black_ixx.commandRank.helpers;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class ForceToReadRulesManager {
/*    */   private List<ReadRules> l;
/*    */   
/*    */   public ForceToReadRulesManager(int i) {
/*  9 */     this.l = new java.util.ArrayList<ReadRules>();
/* 10 */     int a = 0;
/* 11 */     while (a < i) {
/* 12 */       a++;
/* 13 */       this.l.add(new ReadRules(a));
/*    */     }
/*    */   }
/*    */   
/*    */   public void playerReadRules(String name, int i) {
/* 18 */     if (this.l.size() < i) {
/* 19 */       return;
/*    */     }
/* 21 */     if (((ReadRules)this.l.get(i - 1)).containsPlayer(name)) {
/* 22 */       return;
/*    */     }
/* 24 */     ((ReadRules)this.l.get(i - 1)).addPlayer(name);
/*    */   }
/*    */   
/*    */   public boolean playerHasReadRules(String name) {
/* 28 */     for (ReadRules r : this.l) {
/* 29 */       if (!r.containsPlayer(name)) {
/* 30 */         return false;
/*    */       }
/*    */     }
/* 33 */     return true;
/*    */   }
/*    */   
/* 36 */   public void removePlayer(String name) { for (ReadRules r : this.l) {
/* 37 */       r.removePlayer(name);
/*    */     }
/*    */   }
/*    */ }

