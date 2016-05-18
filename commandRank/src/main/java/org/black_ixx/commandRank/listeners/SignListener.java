/*    */ package org.black_ixx.commandRank.listeners;
/*    */ 
/*    */ import org.black_ixx.commandRank.CommandRank;
/*    */ import org.black_ixx.commandRank.helpers.PermissionsDebug.NoPermissionsCause;
import org.black_ixx.commandRank.helpers.RankUpHelper;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.Sign;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.block.Action;
/*    */ import org.bukkit.event.block.SignChangeEvent;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ 
/*    */ public class SignListener implements org.bukkit.event.Listener
/*    */ {
/*    */   private CommandRank plugin;
/*    */   
/*    */   public SignListener(CommandRank plugin)
/*    */   {
/* 21 */     this.plugin = plugin;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   @EventHandler
/*    */   public void onCreate(SignChangeEvent event)
/*    */   {
/* 29 */     if (event.getLine(0).toLowerCase().endsWith(this.plugin.getSignText().toLowerCase())) {
/* 30 */       if (event.getPlayer().hasPermission("CommandRank.CreateSign")) {
/* 31 */         return;
/*    */       }
/* 33 */       this.plugin.getMessager().sendMessage("Main.NoPermissions", event.getPlayer());
/* 34 */       this.plugin.playerFailedPermission(NoPermissionsCause.CommandRank_CreateSign, event.getPlayer());
/* 35 */       event.setLine(0, "Haha FAIL xD");
/* 36 */       event.setLine(1, org.bukkit.ChatColor.MAGIC + ":D");
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   @EventHandler
/*    */   public void onClick(PlayerInteractEvent event)
/*    */   {
/* 44 */     if ((event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) && 
/* 45 */       (event.getClickedBlock() != null)) {
/* 46 */       Block b = event.getClickedBlock();
/* 47 */       if ((b.getType().equals(Material.SIGN_POST)) || (b.getType().equals(Material.WALL_SIGN))) {
/* 48 */         Sign s = (Sign)event.getClickedBlock().getState();
/* 49 */         if (s.getLine(0).toLowerCase().endsWith(this.plugin.getSignText().toLowerCase()))
/*    */         {
/*    */ 
/* 52 */           if (!event.getPlayer().hasPermission("CommandRank.useSign")) {
/* 53 */             this.plugin.getMessager().sendMessage("Main.NoPermissions", event.getPlayer());
/* 54 */             this.plugin.playerFailedPermission(NoPermissionsCause.CommandRank_UseSign, event.getPlayer());
/* 55 */             return;
/*    */           }
/* 57 */           if (event.getPlayer().hasPermission("CommandRank.exclude.sign")) {
/* 58 */             this.plugin.getMessager().sendMessage("Main.Excluded", event.getPlayer());
/* 59 */             return;
/*    */           }
/*    */           
/*    */ 
/*    */ 
/* 64 */           String rankup = s.getLine(1);
/* 65 */           if (rankup != null) {
/* 66 */            RankUpHelper.rankUp(event.getPlayer(), rankup, RankUpHelper.RankUpType.Sign);
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }