package fr.akika.mtd.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.Yaml;
import fr.akika.mtd.discord.main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Map;

import static fr.akika.mtd.discord.main.enable;
import static fr.akika.mtd.index.prefix;

public class reload implements CommandExecutor {

    public main main;
    Yaml yaml = new Yaml();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!enable){
            Bukkit.getConsoleSender().sendMessage(prefix + "§6You need to start the bot before");
        } else {
            main.shutdown();
            try {
                Reader read = new FileReader("./plugins/MTD/discord.yaml");
                Map<String, Object> obj = yaml.load(read);
                if(obj.get("discord") == "true"){
                    main = new main();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Bukkit.broadcastMessage("The discord yaml file has been recharged");
            main = new main();
        }
        return false;
    }
}
