package fr.akika.mtd.commands;

import fr.akika.mtd.discord.main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class start implements CommandExecutor {
    public fr.akika.mtd.discord.main main;


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!fr.akika.mtd.discord.main.enable){
            try{
                main = new main();
                sender.sendMessage("Votre bot à été démarré avec succès");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            sender.sendMessage("Vous ne pouvez pas démarrer le bot car il est déjà en route.");
        }
        return false;
    }
}
