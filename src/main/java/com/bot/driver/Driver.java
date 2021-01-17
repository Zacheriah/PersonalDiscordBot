package com.bot.driver;
/*
Feature list:
- Top scoring posts of the week
- lowest scoring posts of the week
- most controversial posts of the week
- all of the above of the month
- Birthday announcer
 */
import com.bot.entities.BirthdayUser;
import com.bot.features.BirthdayTask;
import com.bot.features.Birthdays;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.*;
import com.bot.features.Listener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.EnumSet;
import java.util.Timer;

public class Driver extends ListenerAdapter {
    static JDA jda;
    public static void main(String[] args) throws LoginException, ParseException {
        String token = "";
        System.out.println(System.getenv("CLASSPATH"));
        //Path path = Paths.get("./src/main/resources/keystore.txt");
        //token = new String(Files.readAllBytes(path));
        token = System.getenv("DISCORD_KEY");
        jda = JDABuilder.createDefault(token, EnumSet.allOf(GatewayIntent.class))
                .setActivity(Activity.watching("my elo drop"))
                .addEventListeners(new Listener())
                .build();

        Birthdays birthdays = new Birthdays(jda);
        Timer dailyTimer = new Timer();
        dailyTimer.scheduleAtFixedRate(new BirthdayTask(jda), 10000, 86400000);
    }
}