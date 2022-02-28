package fr.akika.mtd.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.yaml.snakeyaml.Yaml;

import javax.security.auth.login.LoginException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Map;

public class main extends ListenerAdapter {

    static Yaml yaml = new Yaml();
    public static JDA jda;
    static String token;
    private static JDA bot;
    public static boolean enable = false;


    public main() {

        try {
            Reader read = new FileReader("./plugins/MTD/discord.yaml");
            Map<String, Object> obj = yaml.load(read);
            token = String.valueOf(obj.get("token"));

            if(token == "true" | token == "false" | token == "null"){
                System.out.println("Please, provide a good token");
            }else {
                this.start();
            }
        } catch (FileNotFoundException | LoginException e) {
            e.printStackTrace();
        }

    }

    public static void start() throws LoginException {

        // args[0] should be the token
        // We only need 2 intents in this bot. We only respond to messages in guilds and private channels.
        // All other events will be disabled.


        bot = null;
        try {
            bot = JDABuilder.createLight(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                    .setActivity(Activity.playing("En ligne"))
                    .build()
                    .awaitReady();
            enable = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void shutdown(){
       bot.shutdown();
       enable = false;
    }


}
