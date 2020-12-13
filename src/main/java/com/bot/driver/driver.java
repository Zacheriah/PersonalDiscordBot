package com.bot.driver;

import com.bot.features.Birthdays;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import com.bot.features.Listener;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class driver extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {
        System.out.println("hello world");

        JDA jda = JDABuilder.createDefault("Mjg0NjIwNDc4OTczMzQ1ODAy.WK__eg.ibdUuq1f2AESEslkf75kx6x_A3I")
                .setActivity(Activity.watching("my elo drop"))
                .addEventListeners(new Listener())
                .build();

        Birthdays birthdays = new Birthdays(jda);
        if(birthdays.checkBirthdays() != null){

        }

    }
}
