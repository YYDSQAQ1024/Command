package me.cmd.command;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class cmd implements CommandExecutor, TabExecutor {
    Plugin plugin = me.cmd.command.Command.getPlugin(me.cmd.command.Command.class);
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("reload")){
            if (sender.hasPermission("c.reload.cmd")){
                plugin.saveConfig();
                plugin.reloadConfig();
                sender.sendMessage(ChatColor.GREEN + "插件重载成功！");
                Bukkit.getConsoleSender().sendMessage("[Command]" + ChatColor.YELLOW + sender.getName() + "重载了插件！");
            }
        }

        if (sender instanceof Player){
            Player player = (Player) sender;
            for (int i=0;i < plugin.getConfig().getStringList("command").size();i++){
                String name = plugin.getConfig().getStringList("command").get(i);
                if (args[0].equalsIgnoreCase(plugin.getConfig().getStringList("command").get(i))){
                    for (int i1=0;i1 < plugin.getConfig().getStringList(name + ".cmd").size();i1++){
                        String c = plugin.getConfig().getStringList(name + ".cmd").get(i1);
                        c = PlaceholderAPI.setPlaceholders(player,c);
                        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(),c);
                    }




                }
            }

        }else {
            Bukkit.getConsoleSender().sendMessage("[Command]" + ChatColor.YELLOW + "该指令只能由玩家执行");
        }


        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1){
            List<String> list = plugin.getConfig().getStringList("command");
            list.add("reload");
            return list;
        }
        return null;
    }
}
