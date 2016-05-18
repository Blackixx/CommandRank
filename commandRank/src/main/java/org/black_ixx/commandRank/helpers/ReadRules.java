/*    */ package org.black_ixx.commandRank.helpers;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class ReadRules
/*    */ {
/*    */   private int i;
/*    */   private List<String> players;
/*    */   
/*    */   public ReadRules(int amount) {
/* 11 */     this.i = amount;
/* 12 */     this.players = new java.util.ArrayList();
/*    */   }
/*    */   
/* 15 */   public void addPlayer(String name) { this.players.add(name); }
/*    */   
/*    */   public void removePlayer(String name) {
/* 18 */     this.players.remove(name);
/*    */   }
/*    */   
/* 21 */   public boolean containsPlayer(String name) { return this.players.contains(name); }
/*    */   
/*    */   public int getI() {
/* 24 */     return this.i;
/*    */   }
/*    */ }
