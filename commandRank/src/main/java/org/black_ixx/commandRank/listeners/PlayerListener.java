package org.black_ixx.commandRank.listeners;

import org.black_ixx.commandRank.CommandRank;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerListener implements org.bukkit.event.Listener {
	private CommandRank plugin;
	public static boolean acceptRules;
	private String n;

	public PlayerListener(CommandRank plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onQuit(org.bukkit.event.player.PlayerQuitEvent event) {
		this.plugin.getManager().playerLeave(event.getPlayer().getUniqueId(),
				event.getPlayer().getName(), this.plugin);
		if (acceptRules) {
			this.plugin.getReadRulesManager().removePlayer(
					event.getPlayer().getName());
		}
	}

	@EventHandler
	public void onKick(org.bukkit.event.player.PlayerKickEvent event) {
		this.plugin.getManager().playerLeave(event.getPlayer().getUniqueId(),
				event.getPlayer().getName(), this.plugin);
	}

	@EventHandler
	public void onJoin(org.bukkit.event.player.PlayerJoinEvent event) {
		this.plugin.getManager().playerJoin(event.getPlayer().getUniqueId(),
				event.getPlayer().getName());
	}

	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event) {
		if ((acceptRules)
				&& (event.getMessage().toLowerCase().startsWith("/rules"))) {
			String s = event.getMessage().toLowerCase().replace("/rules", "");
			if ((s == null) || (s == "") || (s == " ")) {
				this.plugin.getReadRulesManager().playerReadRules(
						event.getPlayer().getName(), 1);
				return;
			}
			if ((s.length() < 2) || (s.charAt(1) == 0)) {
				this.plugin.getReadRulesManager().playerReadRules(
						event.getPlayer().getName(), 1);
				return;
			}
			s = String.valueOf(s.charAt(1));

			int i = 0;
			try {
				i = Integer.parseInt(s);
			} catch (NumberFormatException notnumber) {
				return;
			}
			this.plugin.getReadRulesManager().playerReadRules(
					event.getPlayer().getName(), i);
		}
	}

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {
		if ((event.getEntity() instanceof Player)) {
			if ((event.getDamager() instanceof Player)) {
				playerAttacksPlayer((Player) event.getEntity(),
						(Player) event.getDamager());
				return;
			}
			if ((event.getDamager() instanceof Arrow)) {
				Arrow a = (Arrow) event.getDamager();
				if ((a.getShooter() instanceof Player)) {
					playerAttacksPlayer((Player) event.getEntity(),
							(Player) a.getShooter());
					return;
				}
			}
			this.n = null;
		}
	}

	@EventHandler
	public void onDeath(EntityDeathEvent event) {
		if (((event.getEntity() instanceof Player)) && (this.n != null)) {
			if (org.bukkit.Bukkit.getPlayerExact(this.n) == null) {
				return;
			}
			this.plugin.getManager().playerKill(
					org.bukkit.Bukkit.getPlayerExact(this.n),
					(Player) event.getEntity());
			this.n = null;
		}
	}

	private void playerAttacksPlayer(Player opfer, Player damager) {
		this.n = damager.getName();
	}
}
