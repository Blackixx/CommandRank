/*    */ package org.black_ixx.commandRank.event;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.HandlerList;
/*    */ 
/*    */ public class CommandRankAddKillEvent extends Event implements Cancellable
/*    */ {
/* 10 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private final Player killer;
/*    */   
/*    */   private final Player victim;
/*    */   
/*    */   private int currentKills;
/*    */   private boolean cancelled;
/*    */   
/*    */   public CommandRankAddKillEvent(Player k, Player v, int kills)
/*    */   {
/* 21 */     this.killer = k;
/* 22 */     this.victim = v;
/* 23 */     this.currentKills = kills;
/*    */   }
/*    */   
/*    */   public Player getKiller()
/*    */   {
/* 28 */     return this.killer;
/*    */   }
/*    */   
/* 31 */   public Player getVictim() { return this.victim; }
/*    */   
/*    */   public int getKillersCurrentKills() {
/* 34 */     return this.currentKills;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean isCancelled()
/*    */   {
/* 40 */     return this.cancelled;
/*    */   }
/*    */   
/*    */   public void setCancelled(boolean cancelled)
/*    */   {
/* 45 */     this.cancelled = cancelled;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList()
/*    */   {
/* 50 */     return handlers;
/*    */   }
/*    */   
/*    */   public HandlerList getHandlers()
/*    */   {
/* 55 */     return handlers;
/*    */   }
/*    */ }
