package fr.akika.mtd.commands;

import fr.akika.mtd.discord.main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;


public class stop implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(main.enable){
            try{
                main.shutdown();
                sender.sendMessage("Votre bot à été arrêté avec succès");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            sender.sendMessage("Vous ne pouvez pas arrêter le bot car il n'est pas en route.");
        }

        return false;
    }
}
