/*    */ package org.black_ixx.commandRank.commands;
/*    */ 
/*    */ import org.black_ixx.commandRank.CommandRank;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ public class CommandPassword
/*    */   implements org.bukkit.command.CommandExecutor
/*    */ {
/*    */   private CommandRank plugin;
/*    */   
/* 12 */   public CommandPassword(CommandRank plugin) { this.plugin = plugin; }
/*    */   
/*    */   public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) {
/* 15 */     if ((sender instanceof Player)) {
/* 16 */       Player p = (Player)sender;
/* 17 */       if (cmd.getName().equalsIgnoreCase("password"))
/*    */       {
/* 19 */         if (!p.hasPermission("CommandRank.Password")) {
/* 20 */           p.sendMessage(this.plugin.getMessager().get("Main.NoPermissions"));
/* 21 */           this.plugin.playerFailedPermission(org.black_ixx.commandRank.helpers.PermissionsDebug.NoPermissionsCause.CommandRank_Password, p);
/* 22 */           return false;
/*    */         }
/*    */         
/* 25 */         if (p.hasPermission("CommandRank.exclude.command")) {
/* 26 */           this.plugin.getMessager().sendMessage("Main.Excluded", p);
/* 27 */           return false;
/*    */         }
/*    */         
/*    */ 
/* 31 */         if (args.length == 0) {
/* 32 */           p.sendMessage(this.plugin.getMessager().get("PasswordCommand.PlayerDidNotEnterAnyPassword"));
/* 33 */           return false;
/*    */         }
/*    */         
/* 36 */         if (this.plugin.getRankUpTypes().getPasswordRankUp().tryRankUp(p, args[0])) {
/* 37 */           return true;
/*    */         }
/*    */         
/* 40 */         p.sendMessage(this.plugin.getMessager().get("PasswordCommand.WrongPassword"));
/* 41 */         return true;
/*    */       }
/*    */       
/*    */ 
/*    */     }
/*    */     else
/*    */     {
/* 48 */       sender.sendMessage("Ingame command!");
/* 49 */       return false;
/*    */     }
/*    */     
/*    */ 
/* 53 */     return false;
/*    */   }
/*    */ }
