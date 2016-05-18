/*    */ package org.black_ixx.commandRank.event;
/*    */ 
/*    */ import org.black_ixx.commandRank.CommandRank;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.HandlerList;
/*    */ 
/*    */ public class CommandRankReloadEvent extends Event
/*    */ {
/*  9 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private final CommandRank plugin;
/*    */   
/*    */   public CommandRankReloadEvent(CommandRank p)
/*    */   {
/* 15 */     this.plugin = p;
/*    */   }
/*    */   
/*    */   public CommandRank getCommandRankPlugin()
/*    */   {
/* 20 */     return this.plugin;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 24 */     return handlers;
/*    */   }
/*    */   
/*    */   public HandlerList getHandlers()
/*    */   {
/* 29 */     return handlers;
/*    */   }
/*    */ }
