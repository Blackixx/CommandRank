package org.black_ixx.commandRank;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigHandler {
	
	
	public void setupConfig(FileConfiguration config, CommandRank plugin){
		config.addDefault("settings.permissionsdebug.enabled",	Boolean.valueOf(false));
		config.addDefault("settings.declinerules.enabled",		Boolean.valueOf(true));
		config.addDefault("settings.declinerules.rankup", "prisoner");
		config.addDefault("settings.acceptrules.enabled", Boolean.valueOf(true));
		config.addDefault("settings.acceptrules.rankup", "user");
		config.addDefault("settings.signtext", "[RankUp]");
		config.addDefault("settings.acceptrules.rankup", "user");
		config.addDefault("settings.acceptrules.rulepages", Integer.valueOf(3));
		config.addDefault("settings.acceptrules.playersneedtoreadrules", Boolean.valueOf(true));
		config.addDefault("settings.vault.enabled", Boolean.valueOf(true));
		config.addDefault("settings.tellplayerstoreadrules.scheduletime", Integer.valueOf(30));
		config.addDefault("settings.tellplayerstoreadrules.enabled", Boolean.valueOf(true));
		config.addDefault("settings.tellplayerstoreadrules.antipermission", "CommandRank.DoNotToReadRules");
		config.addDefault("settings.rankupinformation.enabled",		Boolean.valueOf(true));

		config.addDefault("autorankups.main.enabled", Boolean.valueOf(false));
		config.addDefault("autorankups.main.scheduletime", Integer.valueOf(600));

		config.addDefault("autorankups.explevel.enabled", Boolean.valueOf(false));
		config.addDefault("autorankups.explevel.list", new String[] { "5:user",	"30:builder" });

		config.addDefault("autorankups.points.enabled", Boolean.valueOf(false));
		config.addDefault("autorankups.points.list", new String[] { "500:user",	"2500:builder" });

		config.addDefault("autorankups.kills.enabled", Boolean.valueOf(true));
		config.addDefault("autorankups.kills.list",		new String[] { "10:prisoner" });

		config.addDefault("autorankups.onlinetime.enabled",
				Boolean.valueOf(true));
		config.addDefault("autorankups.onlinetime.list", new String[] {	"3600:user", "36000:builder" });

		config.addDefault("commandrankuplist.enabled", Boolean.valueOf(false));

		config.addDefault("commandrankuplist.list", new String[] { "CommandRank.isGroup.user:builder", "CommandRank.isGroup.guest:user" });

		config.addDefault("passwordrankuplist.enabled", Boolean.valueOf(false));
		config.addDefault("passwordrankuplist.list", new String[] {	"password:user", "9f8zfsio:builder" });

		if (!config.getBoolean("settings.dontadddefaults")) {
			config.addDefault("settings.dontadddefaults", Boolean.valueOf(true));

			config.addDefault("rankups.user.message", "&4You are now user!");
			config.addDefault("rankups.user.economy.price",	Integer.valueOf(500));
			config.addDefault("rankups.user.economy.enabled", Boolean.valueOf(false));
			config.addDefault("rankups.user.onlinetime.time", Integer.valueOf(3600));
			config.addDefault("rankups.user.onlinetime.enabled", Boolean.valueOf(true));
			config.addDefault("rankups.user.needxplevel.amount", Integer.valueOf(10));
			config.addDefault("rankups.user.needxplevel.enabled", Boolean.valueOf(false));
			config.addDefault("rankups.user.needitems.list", new String[] {	"LOG:10", "DIRT:60" });
			config.addDefault("rankups.user.needitems.enabled",	Boolean.valueOf(false));
			config.addDefault("rankups.user.needkills.amount", Integer.valueOf(0));
			config.addDefault("rankups.user.needkills.enabled", Boolean.valueOf(false));
			config.addDefault("rankups.user.commands.byplayer.list", new String[] { "warp user", "me is now user" });
			config.addDefault("rankups.user.commands.byplayer.enabled", Boolean.valueOf(true));
			config.addDefault("rankups.user.commands.byconsole.list", new String[] { "permissions player setgroup %name% user", "heal %name%", "feed %name%" });
			config.addDefault("rankups.user.commands.byconsole.enabled", Boolean.valueOf(true));
			config.addDefault("rankups.user.giveitems.list", new String[] { "LOG:10", "TORCH:15" });
			config.addDefault("rankups.user.giveitems.equiparmor", Boolean.valueOf(false));
			config.addDefault("rankups.user.giveitems.enabled", Boolean.valueOf(true));

			config.addDefault("rankups.builder.message", "&4You are now builder!");
			config.addDefault("rankups.builder.economy.price", Integer.valueOf(1500));
			config.addDefault("rankups.builder.economy.enabled", Boolean.valueOf(false));
			config.addDefault("rankups.builder.onlinetime.time", Integer.valueOf(36000));
			config.addDefault("rankups.builder.onlinetime.enabled", Boolean.valueOf(false));
			config.addDefault("rankups.builder.needxplevel.amount", Integer.valueOf(40));
			config.addDefault("rankups.builder.needxplevel.enabled", Boolean.valueOf(true));
			config.addDefault("rankups.builder.needkills.amount", Integer.valueOf(50));
			config.addDefault("rankups.builder.needkills.enabled", Boolean.valueOf(false));
			config.addDefault("rankups.builder.needitems.list", new String[] { "DIAMOND_BLOCK:2", "IRON_INGOT:40" });
			config.addDefault("rankups.builder.needitems.enabled", Boolean.valueOf(false));
			config.addDefault("rankups.builder.commands.byplayer.list", new String[] { "warp builder", "me is now builder" });
			config.addDefault("rankups.builder.commands.byplayer.enabled", Boolean.valueOf(true));
			config.addDefault("rankups.builder.commands.byconsole.list", new String[] { "permissions player setgroup %name% builder", "heal %name%", "feed %name%" });
			config.addDefault("rankups.builder.commands.byconsole.enabled", Boolean.valueOf(true));
			config.addDefault("rankups.builder.giveitems.list", new String[] { "WOOL:64", "GLASS:64", "OBSIDIAN:10" });
			config.addDefault("rankups.builder.giveitems.equiparmor", 	Boolean.valueOf(false));
			config.addDefault("rankups.builder.giveitems.enabled", Boolean.valueOf(false));

			config.addDefault("rankups.prisoner.message", "&4You are now prisoner!");
			config.addDefault("rankups.prisoner.economy.price", Integer.valueOf(0));
			config.addDefault("rankups.prisoner.economy.enabled", Boolean.valueOf(false));
			config.addDefault("rankups.prisoner.onlinetime.time", Integer.valueOf(0));
			config.addDefault("rankups.prisoner.onlinetime.enabled", Boolean.valueOf(false));
			config.addDefault("rankups.prisoner.needxplevel.amount", Integer.valueOf(0));
			config.addDefault("rankups.prisoner.needxplevel.enabled", Boolean.valueOf(false));
			config.addDefault("rankups.prisoner.needkills.amount", Integer.valueOf(0));
			config.addDefault("rankups.prisoner.needkills.enabled", Boolean.valueOf(false));
			config.addDefault("rankups.prisoner.needitems.list", new String[] { "DIRT:1" });
			config.addDefault("rankups.prisoner.needitems.enabled", Boolean.valueOf(false));
			config.addDefault("rankups.prisoner.commands.byplayer.list", new String[] { "warp prison", "me is now prisoner..." });
			config.addDefault("rankups.prisoner.commands.byplayer.enabled", Boolean.valueOf(true));
			config.addDefault( "rankups.prisoner.commands.byconsole.list", new String[] { "permissions player setgroup %name% prisoner" });
			config.addDefault("rankups.prisoner.commands.byconsole.enabled", Boolean.valueOf(true));
			config.addDefault("rankups.prisoner.giveitems.list", new String[] { "DIRT:1" });
			config.addDefault("rankups.prisoner.giveitems.equiparmor", Boolean.valueOf(false));
			config.addDefault("rankups.prisoner.giveitems.enabled", Boolean.valueOf(false));
			config.options().copyDefaults(true);
			plugin.saveConfig();
		}
	}

}
