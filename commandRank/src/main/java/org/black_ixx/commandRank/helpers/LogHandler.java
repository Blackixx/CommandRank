 package org.black_ixx.commandRank.helpers;
 
 import java.util.logging.Level;
 import java.util.logging.Logger;
 import org.bukkit.Bukkit;
 
 public class LogHandler
 {
   private static final Logger logger = Bukkit.getLogger();
   
   public static void info(String msg) {
     logger.log(Level.INFO, "[CommandRank] " + msg);
   }
   
   public static void warning(String msg) {
     logger.log(Level.WARNING, "[CommandRank] " + msg);
   }
   
   public static void severe(String msg) {
     logger.log(Level.SEVERE, "[CommandRank] " + msg);
   }
   
   public static void info(String msg, Throwable e) {
     logger.log(Level.INFO, "[CommandRank] " + msg, e);
   }
   
   public static void warning(String msg, Throwable e) {
     logger.log(Level.WARNING, "[CommandRank] " + msg, e);
   }
   
   public static void severe(String msg, Throwable e) {
     logger.log(Level.SEVERE, "[CommandRank] " + msg, e);
   }
 }

