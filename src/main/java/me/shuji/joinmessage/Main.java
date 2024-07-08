package me.shuji.joinmessage;

import me.shuji.joinmessage.commands.ReloadCommand;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Logger;


public final class Main extends JavaPlugin implements Listener {

	public static FileConfiguration config;
	public static Logger console;
	private static Main instance;

	@Override
	public void onEnable() {
		console = getLogger();
		setConfig();
		instance = this;
		if (config.getBoolean("enabled")) {
			onDisable();
		}
		try {
			Objects.requireNonNull(getCommand("jmreload")).setExecutor(new ReloadCommand());
		} catch (NullPointerException e) {
			console.severe("Error registering command: " + e.getMessage());
			Arrays.stream(e.getStackTrace()).forEach(line -> Main.console.severe(line.toString()));
		}
		getServer().getPluginManager().registerEvents(this, this);
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		e.joinMessage(Component.text(trans(String.format(config.getString("joinMessage").replace("{player}", player.getName())))));
		String[] s = config.getString("messageToPlayer").split("\n");
		for (String m : s){
			player.sendMessage(trans(String.format(m.replace("{player}", player.getName()))));
		}
	}

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		e.quitMessage(Component.text(trans(String.format(config.getString("quitMessage").replace("{player}", player.getName())))));
		console.info(player.getName() + " logged off at " + " X: " + player.getLocation().x() + " Y: " + player.getLocation().y() + " Z: " + player.getLocation().z());
	}

	@SuppressWarnings("deprecation")
	public static String trans(String arg0) {
		return ChatColor.translateAlternateColorCodes('&', arg0);
	}

	public void setConfig() {
		config = getConfig();
		config.addDefault("enabled", true);
		config.addDefault("messageToPlayer", "Hello {player}");
		config.addDefault("joinMessage", "{player} joined. o/");
		config.addDefault("quitMessage", "{player} left. :c");
		config.addDefault("configReloadedMessage", "&2Config Succesfully Reloaded!");
		config.options().copyDefaults(true);
		saveConfig();
	}

	public static Main getInstance() {
		return instance;
	}
}
