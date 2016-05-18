/*    */ package org.black_ixx.commandRank.helpers;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.enchantments.Enchantment;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ public class ItemStackTransformer
/*    */ {
/*    */   public static ItemStack getItemStack(String s)
/*    */   {
/* 12 */     String[] parts = s.split(":");
/* 13 */     parts[0] = parts[0].trim();
/* 14 */     parts[1] = parts[1].trim();
/*    */     
/* 16 */     String itemName = parts[0];
/* 17 */     int amount = Integer.parseInt(parts[1]);
/*    */     
/* 19 */     Material material = Material.getMaterial(itemName);
/* 20 */     if (material == null) {
/*    */       try {
/* 22 */         int m_id = Integer.parseInt(itemName);
/* 23 */         material = Material.getMaterial(m_id);
/*    */       }
/*    */       catch (Exception localException) {}
/*    */     }
/*    */     
/* 28 */     ItemStack i = new ItemStack(material, amount);
/*    */     
/*    */ 
/*    */ 
/* 42 */     if (parts.length == 3) {
/* 43 */       parts[2] = parts[2].trim();
/* 44 */       short damage = Short.parseShort(parts[2]);
/* 45 */       i.setDurability(damage);
/*    */     }
/*    */     
/* 48 */     if (parts.length == 4) {
/* 49 */       parts[2] = parts[2].trim();
/* 50 */       Enchantment enchantment = Enchantment.getByName(parts[2]);
/* 51 */       if (enchantment == null) {
/*    */         try {
/* 53 */           int e_id = Integer.parseInt(parts[2]);
/* 54 */           enchantment = Enchantment.getById(e_id);
/*    */         } catch (Exception localException1) {}
/*    */       }
/* 57 */       parts[3] = parts[3].trim();
/* 58 */       int level = Integer.parseInt(parts[3]);
/* 59 */       i.addUnsafeEnchantment(enchantment, level);
/*    */     }
/*    */     
/* 62 */     if (parts.length == 5) {
/* 63 */       parts[2] = parts[2].trim();
/* 64 */       short damage = Short.parseShort(parts[2]);
/* 65 */       parts[3] = parts[3].trim();
/* 66 */       Enchantment enchantment = Enchantment.getByName(parts[3]);
/* 67 */       if (enchantment == null) {
/*    */         try {
/* 69 */           int e_id = Integer.parseInt(parts[3]);
/* 70 */           enchantment = Enchantment.getById(e_id);
/*    */         } catch (Exception localException2) {}
/*    */       }
/* 73 */       parts[4] = parts[4].trim();
/* 74 */       int level = Integer.parseInt(parts[4]);
/* 75 */       i.setDurability(damage);
/* 76 */       i.addUnsafeEnchantment(enchantment, level);
/*    */     }
/*    */     
/*    */ 
/* 80 */     return i;
/*    */   }
/*    */ }

