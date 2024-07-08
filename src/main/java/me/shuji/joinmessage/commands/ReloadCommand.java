package me.shuji.joinmessage.commands;

import me.shuji.joinmessage.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command _command, @NotNull String _label, @NotNull String[] _args) {
        Main plugin = Main.getInstance();
		plugin.reloadConfig();
		Main.config = plugin.getConfig();
		sender.sendMessage(Main.trans(Main.config.getString("configReloadedMessage")));
		plugin.getLogger().info("Configuration file has been reloaded by " + sender.getName());
        return true;
    }
}
