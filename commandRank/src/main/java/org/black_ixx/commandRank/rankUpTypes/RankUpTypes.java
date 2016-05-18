/*    */ package org.black_ixx.commandRank.rankUpTypes;
/*    */ 
/*    */ import org.black_ixx.commandRank.CommandRank;
/*    */ 
/*    */ public class RankUpTypes {
/*    */   private PasswordRankUp pw;
/*    */   private CommandRankUp cmd;
/*    */   
/*    */   public RankUpTypes(CommandRank plugin) {
/* 10 */     if (plugin.getConfig().getBoolean("passwordrankuplist.enabled")) {
/* 11 */       this.pw = new PasswordRankUp(plugin.getConfig().getStringList("passwordrankuplist.list"));
/*    */     }
/* 13 */     if (plugin.getConfig().getBoolean("commandrankuplist.enabled")) {
/* 14 */       this.cmd = new CommandRankUp(plugin.getConfig().getStringList("commandrankuplist.list"));
/*    */     }
/*    */   }
/*    */   
/*    */   public PasswordRankUp getPasswordRankUp() {
/* 19 */     return this.pw;
/*    */   }
/*    */   
/* 22 */   public CommandRankUp getCommandRankUp() { return this.cmd; }
/*    */ }
