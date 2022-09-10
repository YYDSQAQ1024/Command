package me.cmd.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Command extends JavaPlugin implements Listener {
    Plugin plugin = this;


    @Override
    public void onEnable() {

        getCommand("cmd").setExecutor((CommandExecutor) new cmd());
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        Bukkit.getConsoleSender().sendMessage("[Command]" + ChatColor.GREEN + "插件指令注册成功！");
        Bukkit.getConsoleSender().sendMessage("[Command]" + ChatColor.GREEN + "插件指令监听器注册成功！");
        Bukkit.getConsoleSender().sendMessage("[Command]" + ChatColor.GREEN + "插件TAB补全数据获取成功！");
        Bukkit.getConsoleSender().sendMessage("[Command]" + ChatColor.YELLOW + "当前已注册的指令：" + plugin.getConfig().getStringList("command"));
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            /*
             * We register the EventListener here, when PlaceholderAPI is installed.
             * Since all events are in the main class (this class), we simply use "this"
             */
            Bukkit.getPluginManager().registerEvents(this, this);
        } else {
            /*
             * We inform about the fact that PlaceholderAPI isn't installed and then
             * disable this plugin to prevent issues.
             */
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("[Command]" + ChatColor.GREEN + "感谢使用Command插件！");
        // Plugin shutdown logic
    }
}
