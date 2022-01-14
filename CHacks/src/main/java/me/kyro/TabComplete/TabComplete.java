package me.kyro.TabComplete;

import com.google.common.collect.Lists;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.List;

public class TabComplete implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        List<String> arguments = Arrays.asList("help","reload","setposizione");
        List<String> Flist = Lists.newArrayList();

        if (args.length == 1){
            for (String s : arguments){
                if(s.toLowerCase().startsWith(args[0].toLowerCase()))Flist.add(s);
            }
            return Flist;
        }
        return null;
    }
}
