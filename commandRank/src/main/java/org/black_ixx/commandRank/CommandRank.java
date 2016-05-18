package org.black_ixx.commandRank;

import java.util.Collection;
import java.util.HashMap;

import java.util.Iterator;

import net.milkbowl.vault.economy.Economy;

import org.black_ixx.commandRank.commands.CommandAcceptRules;

import org.black_ixx.commandRank.commands.CommandAdmin;

import org.black_ixx.commandRank.commands.CommandDeclineRules;

import org.black_ixx.commandRank.commands.CommandPassword;

import org.black_ixx.commandRank.commands.CommandRankUp;

import org.black_ixx.commandRank.commands.CommandUser;

import org.black_ixx.commandRank.event.CommandRankReloadEvent;

import org.black_ixx.commandRank.helpers.AutoRankUpCheck;

import org.black_ixx.commandRank.helpers.ForceToReadRulesManager;

import org.black_ixx.commandRank.helpers.ItemChecker;

import org.black_ixx.commandRank.helpers.LogHandler;

import org.black_ixx.commandRank.helpers.Manager;

import org.black_ixx.commandRank.helpers.MessageHandler;

import org.black_ixx.commandRank.helpers.PermissionsDebug;


import org.black_ixx.commandRank.helpers.PlayerStorage;

import org.black_ixx.commandRank.helpers.RankUpHelper;
import org.black_ixx.commandRank.listeners.PlayerListener;
import org.black_ixx.commandRank.rankUpTypes.RankUpTypes;

import org.bukkit.Bukkit;


import org.bukkit.command.CommandSender;


import org.bukkit.configuration.ConfigurationSection;

import org.bukkit.configuration.file.FileConfiguration;

import org.bukkit.entity.Player;


import org.bukkit.plugin.RegisteredServiceProvider;

import org.bukkit.plugin.java.JavaPlugin;


public class CommandRank extends JavaPlugin {
	private MessageHandler m;
	private HashMap<String, RankUp> r;
	private Economy economy;
	private Manager man;
	private String signText;
	private ForceToReadRulesManager fr;
	private AutoRankUpCheck check;
	private RankUpTypes rut;
	private String ttrr;
	private int task1;
	private int task2;
	private final String v = "v3.2.1";

	private ItemChecker checker;
	private PermissionsDebug debug;

	public void onDisable() {
		this.m = null;

		Collection<? extends Player> colOfPlayer = Bukkit.getOnlinePlayers();
		Player p;
		for (Iterator<? extends Player> iterator = colOfPlayer.iterator(); iterator
				.hasNext();) {
			p = iterator.next();
			this.man.playerLeave(p.getUniqueId(), p.getName(), this);
		}

		PlayerStorage.save();

		LogHandler.info("v3.2.1 was disabled.");
	}

	public void onEnable() {
		this.m = new MessageHandler(this);

		FileConfiguration config = getConfig();
		new ConfigHandler().setupConfig(config, this);

		this.signText = config.getString("settings.signtext");

		this.checker = new ItemChecker();

		this.r = new HashMap<String, RankUp>();

		ConfigurationSection rankups = config
				.getConfigurationSection("rankups");
		for (String rankup : rankups.getKeys(false)) {
			this.r.put(rankup, new RankUp(this, rankup));
		}

		PlayerStorage.init(this);
		RankUpHelper.init(this);

		this.man = new Manager(this);

		for(Player p : Bukkit.getOnlinePlayers()){
			this.man.playerJoin(p.getUniqueId(), p.getName());
		}

		if (config.getBoolean("settings.acceptrules.playersneedtoreadrules")) {
			org.black_ixx.commandRank.listeners.PlayerListener.acceptRules = true;
			this.fr = new ForceToReadRulesManager(
					config.getInt("settings.acceptrules.rulepages"));
		}

		if (!pluginChecks()) {
			LogHandler.severe("Disabling CommandRank...");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}

		if ((config.getBoolean("passwordrankuplist.enabled"))
				|| (config.getBoolean("commandrankuplist.enabled"))) {
			this.rut = new RankUpTypes(this);
		}

		getServer().getPluginManager().registerEvents(
				new org.black_ixx.commandRank.listeners.SignListener(this),
				this);
		getServer().getPluginManager().registerEvents(
				new org.black_ixx.commandRank.listeners.PlayerListener(this),
				this);

		if (config.getBoolean("settings.acceptrules.enabled")) {
			CommandAcceptRules acceptRules = new CommandAcceptRules(this);
			getCommand("acceptrules").setExecutor(acceptRules);
		}

		CommandAdmin cra = new CommandAdmin(this);
		getCommand("cra").setExecutor(cra);

		if (config.getBoolean("settings.declinerules.enabled")) {
			CommandDeclineRules declineRules = new CommandDeclineRules(this);
			getCommand("declinerules").setExecutor(declineRules);
		}

		if (config.getBoolean("passwordrankuplist.enabled")) {
			CommandPassword password = new CommandPassword(this);
			getCommand("password").setExecutor(password);
		}

		if (config.getBoolean("commandrankuplist.enabled")) {
			CommandRankUp ru = new CommandRankUp(this);
			getCommand("rankup").setExecutor(ru);
		}

		CommandUser cr = new CommandUser(this);
		getCommand("cr").setExecutor(cr);

		if (config.getBoolean("autorankups.main.enabled")) {
			this.check = new AutoRankUpCheck(
					config.getBoolean("autorankups.points.enabled"),
					config.getBoolean("autorankups.kills.enabled"),
					config.getBoolean("autorankups.onlinetime.enabled"),
					config.getBoolean("autorankups.explevel.enabled"),
					config.getStringList("autorankups.kills.list"),
					config.getStringList("autorankups.onlinetime.list"),
					config.getStringList("autorankups.points.list"),
					config.getStringList("autorankups.explevel.list"),
					this.man, this);

			int x = config.getInt("autorankups.main.scheduletime");

			this.task1 = getServer().getScheduler().scheduleSyncRepeatingTask(
					this, new Runnable() {
						public void run() {
							CommandRank.this.check.check();
						}

					}, 60L, 20 * x);
		}

		if (config.getBoolean("settings.tellplayerstoreadrules.enabled")) {
			this.ttrr = config
					.getString("settings.tellplayerstoreadrules.antipermission");
			int x = config
					.getInt("settings.tellplayerstoreadrules.scheduletime");

			this.task2 = getServer().getScheduler().scheduleSyncRepeatingTask(
					this, new Runnable() {
						public void run() {
							String s = CommandRank.this.m
									.get("Main.TellPlayersToReadRules");

							Collection<? extends Player> colOfPlayer = Bukkit
									.getOnlinePlayers();
							Player p;
							for (Iterator<? extends Player> iterator = colOfPlayer
									.iterator(); iterator.hasNext();) {
								p = iterator.next();
								if (!p.hasPermission(CommandRank.this.ttrr)) {
									p.sendMessage(s);
								}
							}

						}

					}, 60L, 20 * x);
		}

		setupPermissionsDebug();

		LogHandler.info("v3.2.1 was enabled.");
	}

	public MessageHandler getMessager() {
		return this.m;
	}

	public Manager getManager() {
		return this.man;
	}

	public boolean pluginChecks() {
		if (getConfig().getBoolean("settings.vault.enabled")) {
			org.bukkit.plugin.Plugin VaultPlugin = getServer()
					.getPluginManager().getPlugin("Vault");
			if (VaultPlugin == null) {
				LogHandler
				.severe("Vault not found!!! Download it there: http://dev.bukkit.org/server-mods/vault/ !!");
				return false;
			}
			LogHandler.info("Vault found!");

			Boolean b = Boolean.valueOf(false);
			for (String s : this.r.keySet()) {
				RankUp ru = (RankUp) this.r.get(s);
				if (ru.getEconomyEnabled()) {
					b = Boolean.valueOf(true);
				}
			}
			if (b.booleanValue()) {
				RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
				if (economyProvider != null)
					this.economy = ((Economy) economyProvider.getProvider());
				if (this.economy == null) {
					LogHandler.severe("If you want to use the economy part and enable Vault, you have to install an economy plugin...");
					return false;
				}
			} else {
				LogHandler.info("Hmm.. Vault was enabled but you are not using the economy part...");
			}
		}

		return true;
	}

	public Economy getEconomy() {
		return this.economy;
	}

	public HashMap<String, RankUp> getRankUps() {
		return this.r;
	}

	public String getSignText() {
		return this.signText;
	}

	public ForceToReadRulesManager getReadRulesManager() {
		return this.fr;
	}

	public RankUpTypes getRankUpTypes() {
		return this.rut;
	}

	public void reload() {
		reloadConfig();

		for(Player p : Bukkit.getOnlinePlayers()){
			this.man.playerLeave(p.getUniqueId(), p.getName(), this);
		}

		PlayerStorage.save();

		if (this.task1 != 0) {
			Bukkit.getScheduler().cancelTask(this.task1);
		}
		if (this.task2 != 0) {
			Bukkit.getScheduler().cancelTask(this.task2);
		}

		this.m = new MessageHandler(this);

		FileConfiguration config = getConfig();

		this.signText = config.getString("settings.signtext");

		this.r = new HashMap<String, RankUp>();

		ConfigurationSection rankups = config.getConfigurationSection("rankups");
		for(String rankup : rankups.getKeys(false)){
			this.r.put(rankup, new RankUp(this, rankup));
		}

		PlayerStorage.init(this);
		RankUpHelper.init(this);

		this.man = new Manager(this);

		for(Player p : Bukkit.getOnlinePlayers()){
			this.man.playerJoin(p.getUniqueId(), p.getName());
		}

		if (config.getBoolean("settings.acceptrules.playersneedtoreadrules")) {
			PlayerListener.acceptRules = true;
			this.fr = new ForceToReadRulesManager(
					config.getInt("settings.acceptrules.rulepages"));
		}

		if (!pluginChecks()) {
			LogHandler.severe("Disabling CommandRank...");
			getServer().getPluginManager().disablePlugin(this);
		}

		if ((config.getBoolean("passwordrankuplist.enabled")) || (config.getBoolean("commandrankuplist.enabled"))) {
			this.rut = new RankUpTypes(this);
		}

		if (config.getBoolean("autorankups.main.enabled")) {
			this.check = new AutoRankUpCheck(
					config.getBoolean("autorankups.points.enabled"),
					config.getBoolean("autorankups.kills.enabled"),
					config.getBoolean("autorankups.onlinetime.enabled"),
					config.getBoolean("autorankups.explevel.enabled"),
					config.getStringList("autorankups.kills.list"),
					config.getStringList("autorankups.onlinetime.list"),
					config.getStringList("autorankups.points.list"),
					config.getStringList("autorankups.explevel.list"),
					this.man, this);

			int x = config.getInt("autorankups.main.scheduletime");

			this.task1 = getServer().getScheduler().scheduleSyncRepeatingTask(
					this, new Runnable() {
						public void run() {
							CommandRank.this.check.check();
						}
					}, 60L, 20 * x);
		}

		if (config.getBoolean("settings.tellplayerstoreadrules.enabled")) {
			this.ttrr = config
					.getString("settings.tellplayerstoreadrules.antipermission");
			int x = config
					.getInt("settings.tellplayerstoreadrules.scheduletime");

			this.task2 = getServer().getScheduler().scheduleSyncRepeatingTask(
					this, new Runnable() {
						public void run() {
							String s = CommandRank.this.m.get("Main.TellPlayersToReadRules");

							for(Player p : Bukkit.getOnlinePlayers()){
								if (!p.hasPermission(CommandRank.this.ttrr)) {
									p.sendMessage(s);
								}
							}

						}

					}, 60L, 20 * x);
		}

		setupPermissionsDebug();

		if (config.getBoolean("settings.acceptrules.enabled")) {
			CommandAcceptRules acceptRules = new CommandAcceptRules(this);
			getCommand("acceptrules").setExecutor(acceptRules);
		}

		CommandAdmin cra = new CommandAdmin(this);
		getCommand("cra").setExecutor(cra);

		if (config.getBoolean("settings.declinerules.enabled")) {
			CommandDeclineRules declineRules = new CommandDeclineRules(this);
			getCommand("declinerules").setExecutor(declineRules);
		}

		if (config.getBoolean("passwordrankuplist.enabled")) {
			CommandPassword password = new CommandPassword(this);
			getCommand("password").setExecutor(password);
		}

		if (config.getBoolean("commandrankuplist.enabled")) {
			CommandRankUp ru = new CommandRankUp(this);
			getCommand("rankup").setExecutor(ru);
		}

		CommandUser cr = new CommandUser(this);
		getCommand("cr").setExecutor(cr);

		CommandRankReloadEvent evReload = new CommandRankReloadEvent(this);
		Bukkit.getPluginManager().callEvent(evReload);

		LogHandler.info(v+" was reloaded.");
	}

	public ItemChecker getItemChecker() {
		return this.checker;
	}

	public void playerFailedPermission(
			PermissionsDebug.NoPermissionsCause cause, CommandSender p) {
		if (this.debug == null) {
			return;
		}
		this.debug.addPermissionFailed(p, cause);
	}

	public void setupPermissionsDebug() {
		if (getConfig().getBoolean("settings.permissionsdebug.enabled")) {
			this.debug = new PermissionsDebug(this);
		} else {
			this.debug = null;
		}
	}

}
