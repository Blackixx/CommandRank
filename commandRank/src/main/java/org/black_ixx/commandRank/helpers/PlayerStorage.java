package org.black_ixx.commandRank.helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import org.black_ixx.commandRank.CommandRank;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class PlayerStorage {
	private static CommandRank plugin;
	private static File file;
	private static YamlConfiguration config;

	public static void init(CommandRank pp) {
		plugin = pp;
		file = new File(plugin.getDataFolder().getAbsolutePath()
				+ "/PlayerStorage.yml");
		config = YamlConfiguration.loadConfiguration(file);
	}

	public static void save() {
		if (config == null) {
			plugin.getLogger()
					.warning(
							"Was not able to save the storage.yml... (Dev Note: config==null)");
			return;
		}

		try {
			config.save(file);
		} catch (IOException e1) {
			plugin.getLogger().warning(
					"File I/O Exception on saving storage.yml");
			e1.printStackTrace();
		}
	}

	public static void reload() {
		try {
			config.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static void setKills(String name, int kills) {
		config.set("kills." + name, Integer.valueOf(kills));
	}

	public static void setKills(UUID uuid, int kills) {
		config.set("kills." + uuid.toString(), Integer.valueOf(kills));
	}
	
	public static void setTime(String name, long time) {
		config.set("time." + name, Long.valueOf(time));
	}
	
	public static void setTime(UUID uuid, long time) {
		config.set("time." + uuid.toString(), Long.valueOf(time));
	}

	public static long getTime(String name) {
		return config.getLong("time." + name);
	}

	public static long getTime(UUID uuid) {
		return config.getLong("time." + uuid.toString());
	}
	
	public static int getKills(String name) {
		return config.getInt("kills." + name);
	}
	
	public static int getKills(UUID uuid) {
		return config.getInt("kills." + uuid.toString());
	}
	
	public static void removeTime(String name) {
		config.set("time."+name, null);
	}

	public static void removeKills(String name) {
		config.set("kills."+name, null);
	}
	
	public static boolean oldTimeExist(String name) {
		return config.isSet("time."+name);
	}
	
	public static boolean oldKillsExist(String name) {
		return config.isSet("kills."+name);
	}
	
}