package net.java_rin.moretaxaddon.command;

import net.java_rin.moretaxaddon.config.ConfigurationManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MoreTaxCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args[0].equalsIgnoreCase("reload")) {
            ConfigurationManager.load();
            sender.sendMessage(ChatColor.GREEN + "Reload complete.");
        } else {
            sender.sendMessage(ChatColor.RED + "Unknown command.");
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        List<String> argument = new ArrayList<>();
        if (args.length == 1) {
            argument.add("reload");
            Collections.sort(argument);
        }
        return argument.stream().filter(completion -> completion.startsWith(args[args.length - 1])).collect(Collectors.toList());
    }
}
