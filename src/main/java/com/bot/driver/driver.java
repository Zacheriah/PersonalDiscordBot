package com.bot.driver;
/*
Feature list:

- Top scoring posts of the week
 */
import com.bot.entities.BirthdayUser;
import com.bot.features.Birthdays;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.*;
import com.bot.features.Listener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;

public class driver extends ListenerAdapter {

    static JDA jda;

    public static void main(String[] args) throws LoginException, ParseException {
        System.out.println("hello world");

        String token = "";

        try {
            Path path = Paths.get("./src/main/resources/keystore");
            token = new String(Files.readAllBytes(path));
            jda = JDABuilder.createDefault(token)
                    .setActivity(Activity.watching("my elo drop"))
                    .addEventListeners(new Listener())
                    .build();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Birthdays birthdays = new Birthdays(jda);
        BirthdayUser user = birthdays.checkBirthdays();
        if (user != null) {
            jda.getTextChannelById("158082957645578240").sendMessage("Happy birthday " + user.getName() + "!").queue();
        }
    }
}