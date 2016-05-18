/*    */ package org.black_ixx.commandRank.event;
/*    */ 
/*    */ import org.black_ixx.commandRank.CommandRank;
/*    */ import org.black_ixx.commandRank.helpers.Manager;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.HandlerList;
/*    */ 
/*    */ public class CommandRankPlayerLeaveEvent extends Event
/*    */ {
/* 10 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private final String player;
/*    */   
/*    */   private final Manager manager;
/*    */   
/*    */   public CommandRankPlayerLeaveEvent(CommandRank plugin, String name)
/*    */   {
/* 18 */     this.player = name;
/* 19 */     this.manager = plugin.getManager();
/*    */   }
/*    */   
/*    */   public String getPlayerName()
/*    */   {
/* 24 */     return this.player;
/*    */   }
/*    */   
/* 27 */   public Manager getCommandRankManager() { return this.manager; }
/*    */   
/*    */   public static HandlerList getHandlerList()
/*    */   {
/* 31 */     return handlers;
/*    */   }
/*    */   
/*    */   public HandlerList getHandlers()
/*    */   {
/* 36 */     return handlers;
/*    */   }
/*    */ }

