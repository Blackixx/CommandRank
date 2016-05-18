 package org.black_ixx.commandRank.helpers;
 
 import org.bukkit.ChatColor;
 
 
 public class TimeDisplayer
 {
   public static String getDisplayTime(long time)
   {
     long seconds2 = time;
     long minutes2 = seconds2 / 60L;
     long hours2 = minutes2 / 60L;
     long days2 = hours2 / 24L;
     long weeks2 = days2 / 7L;
     double weeks = Math.floor(weeks2);
     double days = Math.floor(days2 - weeks * 7.0D);
     double hours = Math.floor(hours2 - days * 24.0D - weeks * 7.0D * 24.0D);
     double minutes = Math.floor(minutes2 - hours * 60.0D - days * 60.0D * 24.0D - weeks * 7.0D * 24.0D * 60.0D);
     double seconds = Math.floor(seconds2 - minutes * 60.0D - hours * 60.0D * 60.0D - days * 60.0D * 24.0D * 60.0D - weeks * 7.0D * 24.0D * 60.0D * 60.0D);
     int w = (int)weeks;
     int d = (int)days;
     int h = (int)hours;
     int m = (int)minutes;
     int s = (int)seconds;
     
 
     return ""+ChatColor.YELLOW + w + ChatColor.RED + "W " + ChatColor.YELLOW + d + ChatColor.RED + "D " + ChatColor.YELLOW + h + ChatColor.RED + "H " + ChatColor.YELLOW + m + ChatColor.RED + "M " + ChatColor.YELLOW + s + ChatColor.RED + "S ";
   }
 }

