package org.black_ixx.commandRank.commands;

import org.black_ixx.commandRank.CommandRank;
import org.black_ixx.commandRank.RankUp;
import org.black_ixx.commandRank.helpers.PermissionsDebug.NoPermissionsCause;
import org.black_ixx.commandRank.helpers.PlayerStorage;
import org.black_ixx.commandRank.helpers.TimeDisplayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAdmin implements org.bukkit.command.CommandExecutor {
	private CommandRank plugin;

	public CommandAdmin(CommandRank plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender s, org.bukkit.command.Command cmd,
			String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("cra")) {
			if (!s.hasPermission("CommandRank.AdminCommand")) {
				s.sendMessage(this.plugin.getMessager().get("Main.NoPermissions"));
				this.plugin.playerFailedPermission(NoPermissionsCause.CommandRank_AdminCommand, s);
				return false;
			}
			if (args.length == 0) {
				showMenu(s);
				return false;
			}

			String a = args[0];

			if (a.equalsIgnoreCase("rankup")) {
				if (args.length != 3) {
					showMenu(s);
					return false;
				}
				String player = args[2];
				String rankup = args[1];

				if (Bukkit.getPlayer(player) == null) {
					this.plugin.getMessager().sendMessage("AdminCommand.TargetNotFound", s, player);
					return false;
				}
				Player p = Bukkit.getPlayer(player);

				rankup = rankup.toLowerCase();
				if (!this.plugin.getRankUps().containsKey(rankup)) {
					this.plugin.getMessager().sendMessage("Main.RankUpNotExisting", s);
					return false;
				}
				RankUp r = (RankUp) this.plugin.getRankUps().get(rankup);
				r.goodActions(p, this.plugin);

				s.sendMessage(this.plugin.getMessager().get("AdminCommand.PlayerRankUpExecuted").replace("%target%", p.getName()).replace("%rankup%", rankup));

				return true;
			}

			if (a.equalsIgnoreCase("tryrankup")) {
				if (args.length != 3) {
					showMenu(s);
					return false;
				}
				String player = args[2];
				String rankup = args[1];

				if (Bukkit.getPlayer(player) == null) {
					this.plugin.getMessager().sendMessage("AdminCommand.TargetNotFound", s, player);
					return false;
				}
				Player p = Bukkit.getPlayer(player);

				rankup = rankup.toLowerCase();
				if (!this.plugin.getRankUps().containsKey(rankup)) {
					this.plugin.getMessager().sendMessage("Main.RankUpNotExisting", s);
					return false;
				}
				RankUp r = (RankUp) this.plugin.getRankUps().get(rankup);

				if (!r.checkEverything(p, this.plugin)) {
					s.sendMessage(this.plugin.getMessager().get("AdminCommand.PlayerRankUpFailed").replace("%target%", p.getName()).replace("%rankup%", rankup));
					return false;
				}

				r.actions(p, this.plugin);

				s.sendMessage(this.plugin.getMessager().get("AdminCommand.PlayerRankUpExecuted").replace("%target%", p.getName()).replace("%rankup%", rankup));

				return true;
			}

			if (a.equalsIgnoreCase("reload")) {
				this.plugin.getMessager().sendMessage("AdminCommand.Reload.Start", s);
				this.plugin.reload();
				this.plugin.getMessager().sendMessage("AdminCommand.Reload.End", s);

				return true;
			}

			if (a.equalsIgnoreCase("kills")) {
				if (args.length != 2) {
					showMenu(s);
					return false;
				}

				if (Bukkit.getOfflinePlayer(args[1]) == null) {
					this.plugin.getMessager().sendMessage("AdminCommand.TargetNotFound", s, args[1]);
					return false;
				}
				OfflinePlayer p = Bukkit.getOfflinePlayer(args[1]);
				s.sendMessage(this.plugin.getMessager().get("AdminCommand.ShowKills").replace("%target%", p.getName()).replace("%kills%", Integer.toString(this.plugin.getManager().getKills(p.getUniqueId()))));
				return true;
			}

			if (a.equalsIgnoreCase("time")) {

				if (args.length != 2) {
					showMenu(s);
					return false;
				}

				if (Bukkit.getOfflinePlayer(args[1]) == null) {
					this.plugin.getMessager().sendMessage("AdminCommand.TargetNotFound", s, args[1]);
					return false;
				}
				OfflinePlayer p = Bukkit.getOfflinePlayer(args[1]);
				s.sendMessage(this.plugin.getMessager().get("AdminCommand.ShowTime").replace("%target%", p.getName())
						.replace("%time%", TimeDisplayer.getDisplayTime(this.plugin.getManager().getTime(p.getUniqueId()))));
				return true;
			}

			if (a.equalsIgnoreCase("settime")) {

				if (args.length != 3) {
					showMenu(s);
					return false;
				}

				if (Bukkit.getOfflinePlayer(args[1]) == null) {
					this.plugin.getMessager().sendMessage("AdminCommand.TargetNotFound", s, args[1]);
					return false;
				}
				OfflinePlayer p = Bukkit.getOfflinePlayer(args[1]);
				long old = this.plugin.getManager().getTime(p.getUniqueId());
				long neu = 0L;

				try {
					neu = Long.parseLong(args[2]);
				} catch (NumberFormatException notnumber) {
					s.sendMessage(this.plugin.getMessager().get("AdminCommand.ArgumentIsNotInteger").replace("%argument%", args[2]));
					return true;
				}

				s.sendMessage(this.plugin.getMessager().get("AdminCommand.SetTime").replace("%oldtime%", TimeDisplayer.getDisplayTime(old))
						.replace("%newtime%", TimeDisplayer.getDisplayTime(neu)).replace("%target%", p.getName()));
				PlayerStorage.setTime(p.getName(), neu * 1000L);

				return true;
			}

			if (a.equalsIgnoreCase("setkills")) {

				if (args.length != 3) {
					showMenu(s);
					return false;
				}

				if (Bukkit.getOfflinePlayer(args[1]) == null) {
					this.plugin.getMessager().sendMessage("AdminCommand.TargetNotFound", s, args[1]);
					return false;
				}
				OfflinePlayer p = Bukkit.getOfflinePlayer(args[1]);
				long old = PlayerStorage.getKills(p.getName());
				int neu = 0;

				try {
					neu = Integer.parseInt(args[2]);
				} catch (NumberFormatException notnumber) {
					s.sendMessage(this.plugin.getMessager().get("AdminCommand.ArgumentIsNotInteger").replace("%argument%", args[2]));
					return true;
				}

				this.plugin.getManager().setKills(p.getUniqueId(), neu);
				s.sendMessage(this.plugin.getMessager().get("AdminCommand.SetKills")
						.replace("%oldkills%", Long.toString(old))
						.replace("%newkills%", Long.toString(neu))
						.replace("%target%", p.getName()));

				return true;
			}

			showMenu(s);
			return true;
		}

		return false;
	}

	public void showMenu(CommandSender p) {
		p.sendMessage(ChatColor.BLACK + "------------" + ChatColor.RED
				+ " Admin commands " + ChatColor.BLACK + "------------");
		p.sendMessage(ChatColor.DARK_PURPLE + "/cra time <name> "
				+ ChatColor.GREEN + "Shows onlineTime of <name>");
		p.sendMessage(ChatColor.DARK_PURPLE + "/cra kills <name> "
				+ ChatColor.GREEN + "Shows kills of <name>");
		p.sendMessage(ChatColor.DARK_PURPLE + "/cra setKills <name> <kill> "
				+ ChatColor.GREEN + "Sets the amount of kills of <name>");
		p.sendMessage(ChatColor.DARK_PURPLE + "/cra setTime <name> <time> "
				+ ChatColor.GREEN
				+ "Sets the onlineTime of <name> (Time in seconds)");
		p.sendMessage(ChatColor.DARK_PURPLE
				+ "/cra tryRankup <name of rankup> <player> " + ChatColor.GREEN
				+ "Executes a rankup");
		p.sendMessage(ChatColor.DARK_PURPLE
				+ "/cra rankup <name of rankup> <player> " + ChatColor.GREEN
				+ "Executes a rankup without checks");
		p.sendMessage(ChatColor.DARK_PURPLE + "/cra reload " + ChatColor.GREEN
				+ "Reloads plugin " + ChatColor.DARK_RED
				+ "(Not tested enough but should work)");
	}
}
