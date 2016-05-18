package org.black_ixx.commandRank.helpers;

import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemChecker {
	public boolean inventoryContainsItem(Player p, ItemStack i) {
		if (getAmountOfSameItems(p, i) >= i.getAmount()) {
			return true;
		}
		return false;
	}

	public void takeItem(ItemStack istack, Player p) {
		int a = 0;
		ItemStack[] arrayOfItemStack;
		int j;
		int i;
		if ((istack.getEnchantments().isEmpty())
				&& (istack.getDurability() == 0)) {
			j = (arrayOfItemStack = p.getInventory().getContents()).length;
			for (i = 0; i < j; i++) {
				ItemStack s = arrayOfItemStack[i];
				if (s != null) {
					if (canSell(s)) {

						if (s.getType() == istack.getType()) {
							a += s.getAmount();

							remove(p, s);
						}
					}
				}
			}
			a -= istack.getAmount();
			if (a > 0) {
				addItem(istack, p, a);
			}
			return;
		}

		if ((!istack.getEnchantments().isEmpty())
				&& (istack.getDurability() != 0)) {
			j = (arrayOfItemStack = p.getInventory().getContents()).length;
			for (i = 0; i < j; i++) {
				ItemStack s = arrayOfItemStack[i];
				if (s != null) {
					if (canSell(s)) {

						if ((s.getType() == istack.getType())
								&& (sameDurability(istack, s))
								&& (!s.getEnchantments().isEmpty())
								&& (containsSameEnchantments(istack, s))) {
							a += s.getAmount();

							remove(p, s);
						}
					}
				}
			}

			a -= istack.getAmount();
			if (a > 0) {
				addItem(istack, p, a);
			}
			return;
		}

		if (!istack.getEnchantments().isEmpty()) {
			j = (arrayOfItemStack = p.getInventory().getContents()).length;
			for (i = 0; i < j; i++) {
				ItemStack s = arrayOfItemStack[i];
				if (s != null) {
					if (canSell(s)) {

						if ((s.getType() == istack.getType())
								&& (!s.getEnchantments().isEmpty())
								&& (containsSameEnchantments(istack, s))) {
							a += s.getAmount();

							remove(p, s);
						}
					}
				}
			}
			a -= istack.getAmount();
			if (a > 0) {
				addItem(istack, p, a);
			}
			return;
		}

		if (istack.getDurability() != 0) {
			j = (arrayOfItemStack = p.getInventory().getContents()).length;
			for (i = 0; i < j; i++) {
				ItemStack s = arrayOfItemStack[i];
				if (s != null) {
					if (canSell(s)) {

						if ((s.getType() == istack.getType())
								&& (istack.getDurability() == s.getDurability())) {
							a += s.getAmount();

							remove(p, s);
						}
					}
				}
			}
			a -= istack.getAmount();
			if (a > 0) {
				addItem(istack, p, a);
			}
			return;
		}
	}

	private void remove(Player p, ItemStack toR) {
		if (toR.hasItemMeta()) {
			ItemMeta m = toR.getItemMeta();
			m.setDisplayName(null);
			toR.setItemMeta(m);
			toR.setItemMeta(null);
		}

		if (toR.getDurability() != 0) {
			p.getInventory().removeItem(
					new ItemStack[] { new ItemStack(toR.getType(), toR
							.getAmount(), toR.getDurability()) });
			return;
		}

		p.getInventory()
				.removeItem(
						new ItemStack[] { new ItemStack(toR.getType(), toR
								.getAmount()) });
	}

	private boolean containsSameEnchantments(ItemStack i, ItemStack s) {
		for (Enchantment e : i.getEnchantments().keySet()) {
			if (!s.containsEnchantment(e)) {
				return false;
			}
			if (s.getEnchantmentLevel(e) < i.getEnchantmentLevel(e)) {
				return false;
			}
		}

		return true;
	}

	private boolean sameDurability(ItemStack i, ItemStack s) {
		if (i.getDurability() == s.getDurability()) {
			return true;
		}
		return false;
	}

	private int getAmountOfSameItems(Player p, ItemStack istack) {
		int a = 0;
		ItemStack[] arrayOfItemStack;
		if ((istack.getEnchantments().isEmpty())
				&& (istack.getDurability() == 0)) {
			int j = (arrayOfItemStack = p.getInventory().getContents()).length;
			for (int i = 0; i < j; i++) {
				ItemStack s = arrayOfItemStack[i];
				if (s != null) {
					if (canSell(s)) {

						if (s.getType() == istack.getType())
							a += s.getAmount();
					}
				}
			}
			return a;
		}

		if ((!istack.getEnchantments().isEmpty())
				&& (istack.getDurability() != 0)) {
			int j = (arrayOfItemStack = p.getInventory().getContents()).length;
			for (int i = 0; i < j; i++) {
				ItemStack s = arrayOfItemStack[i];
				if (s != null) {
					if (canSell(s)) {

						if ((s.getType() == istack.getType())
								&& (sameDurability(istack, s))
								&& (!s.getEnchantments().isEmpty())
								&& (containsSameEnchantments(istack, s))) {
							a += s.getAmount();
						}
					}
				}
			}

			return a;
		}

		if (!istack.getEnchantments().isEmpty()) {
			int j = (arrayOfItemStack = p.getInventory().getContents()).length;
			for (int i = 0; i < j; i++) {
				ItemStack s = arrayOfItemStack[i];
				if (s != null) {
					if (canSell(s)) {

						if ((s.getType() == istack.getType())
								&& (!s.getEnchantments().isEmpty())
								&& (containsSameEnchantments(istack, s))) {
							a += s.getAmount();
						}
					}
				}
			}
			return a;
		}

		if (istack.getDurability() != 0) {
			int j = (arrayOfItemStack = p.getInventory().getContents()).length;
			for (int i = 0; i < j; i++) {
				ItemStack s = arrayOfItemStack[i];
				if (s != null) {
					if (canSell(s)) {

						if ((s.getType() == istack.getType())
								&& (istack.getDurability() == s.getDurability())) {
							a += s.getAmount();
						}
					}
				}
			}
			return a;
		}

		int j = (arrayOfItemStack = p.getInventory().getContents()).length;
		for (int i = 0; i < j; i++) {
			ItemStack s = arrayOfItemStack[i];
			if (s != null) {
				if (canSell(s)) {

					if (s.getType() == istack.getType())
						a += s.getAmount();
				}
			}
		}
		return a;
	}

	private void addItem(ItemStack i, Player p, int amount) {
		ItemStack s = new ItemStack(i.getType(), amount, i.getDurability());
		if (!i.getEnchantments().isEmpty()) {
			s.addEnchantments(i.getEnchantments());
		}
		p.getInventory().addItem(new ItemStack[] { s });
	}

	public void tellPlayerItemsNeeded(List<ItemStack> items, Player p) {
		for (ItemStack i : items) {
			if (!inventoryContainsItem(p, i)) {
				p.sendMessage(ChatColor.RED + "- " + i.getAmount() + " "
						+ i.getType().name());
			} else {
				p.sendMessage(ChatColor.GREEN + "- " + i.getAmount() + " "
						+ i.getType().name());
			}
		}
	}

	private boolean isTool(ItemStack i) {
		String n = i.getType().name().toLowerCase();
		if ((n.contains("steal")) || (n.contains("wood"))
				|| (n.contains("stone")) || (n.contains("iron"))
				|| (n.contains("gold")) || (n.contains("diamond"))
				|| (n.contains("chain")) || (n.contains("leather"))) {
			return true;
		}
		return false;
	}

	private boolean canSell(ItemStack i) {
		return i.getDurability() == 0;
	}
}
