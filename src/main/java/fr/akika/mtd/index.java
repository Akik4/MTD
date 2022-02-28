package fr.akika.mtd;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.Yaml;
import fr.akika.mtd.commands.reload;
import fr.akika.mtd.commands.start;
import fr.akika.mtd.commands.stop;
import fr.akika.mtd.discord.main;
import fr.akika.mtd.event.join;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public final class index extends JavaPlugin {

    public String prefix = "§b[§1MTD§b] §f: ";
    Yaml yaml = new Yaml();
    public main main;

    @Override
    public void onEnable() {

        System.out.println(prefix + "§aLe plugin est activé !");

        if(Files.isDirectory(Paths.get("./plugins/MTD/"))){
            File file = new File("./plugins/MTD/discord.yaml");
            if(file.exists()){
                System.out.println(prefix + "§aLe fichier de discord à été chargé");
                try {
                    Reader read = new FileReader("./plugins/MTD/discord.yaml");
                    Map<String, Object> obj = yaml.load(read);
                    if(obj.get("discord").equals("true")){
                        main = new main();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            } else {
                System.out.println(prefix + "§6Création du fichier en cours...");
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            File folder = new File("./plugins//MTD//discord.yaml");
            String text = "#Tell if you want active discord\ndiscord: false\n#Here, provide the token of you bot then go to discord portal developper\ntoken: false\n#Here, provide channel id where you want your minecraft chat\nchannelid: null";
            try {
                Files.createDirectories(Paths.get("./plugins//MTD"));
                folder.createNewFile();
                Files.writeString(Paths.get("./plugins/MTD/discord.yaml"), text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        getCommand("dreload").setExecutor(new reload());
        getCommand("dstop").setExecutor(new stop());
        getCommand("dstart").setExecutor(new start());

        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new join(), this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        System.out.println(prefix + "§cLe plugin est désactivé !");

        // Plugin shutdown logic
    }
}
