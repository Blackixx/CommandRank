/*    */ package org.black_ixx.commandRank.event;
/*    */ 
/*    */ import org.black_ixx.commandRank.helpers.RankUpHelper.RankUpType;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.HandlerList;
/*    */ 
/*    */ public class CommandRankRankUpCheckEvent extends Event implements Cancellable
/*    */ {
/* 11 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private final Player player;
/*    */   
/*    */   private String rankup;
/*    */   
/*    */   private RankUpType rankupType;
/*    */   private boolean cancelled;
/*    */   
/*    */   public CommandRankRankUpCheckEvent(Player p, String rankup, RankUpType type)
/*    */   {
/* 22 */     this.player = p;
/* 23 */     this.rankup = rankup;
/* 24 */     this.rankupType = type;
/*    */   }
/*    */   
/*    */   public Player getPlayer()
/*    */   {
/* 29 */     return this.player;
/*    */   }
/*    */   
/* 32 */   public String getRankUpName() { return this.rankup; }
/*    */   
/*    */   public RankUpType getRankUpType()
/*    */   {
/* 36 */     return this.rankupType;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean isCancelled()
/*    */   {
/* 42 */     return this.cancelled;
/*    */   }
/*    */   
/*    */   public void setCancelled(boolean cancelled)
/*    */   {
/* 47 */     this.cancelled = cancelled;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList()
/*    */   {
/* 52 */     return handlers;
/*    */   }
/*    */   
/*    */   public HandlerList getHandlers()
/*    */   {
/* 57 */     return handlers;
/*    */   }
/*    */ }
