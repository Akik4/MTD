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

public class reload implements CommandExecutor {

    public main main;
    Yaml yaml = new Yaml();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
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
        return false;
    }
}
