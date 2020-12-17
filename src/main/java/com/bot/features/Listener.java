package com.bot.features;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import static net.dv8tion.jda.api.entities.MessageHistory.getHistoryAfter;

public class Listener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event){
        Message message = event.getMessage();
        switch(message.getContentDisplay()){
            case "!help":
                MessageChannel channel = message.getChannel();
                System.out.println("debug");
                channel.sendMessage("For feature requests, contact <@145717043289784320>! Current features include: \n" +
                        "- Birthday Announcer\n" +
                        "- Top posts of the week(WIP)").queue();
                System.out.println("debug");
                break;

            case "!top":
                MessageHistory messageHistory = getHistoryAfter(message.getChannel(), );
                break;
        }
        if (event.isFromType(ChannelType.PRIVATE))
        {
            System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(),
                    event.getMessage().getContentDisplay());
        }
        else
        {
            System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(),
                    event.getTextChannel().getName(), event.getMember().getEffectiveName(),
                    event.getMessage().getContentDisplay());
        }
    }
}
