package com.bot.features;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static com.bot.features.TopPostsOfTheWeek.*;

public class Listener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event){
        Message message = event.getMessage();
        MessageChannel channel = message.getChannel();

        switch(message.getContentDisplay()){
            case "!help": {
                channel.sendMessage("For feature requests, contact <@145717043289784320>! Current features include: \n" +
                        "- Birthday Announcer\n" +
                        "- Top posts of the week (!top)\n" +
                        "- Most controversial post of the week (!controversial)\n" +
                        "- Most downvoted post of the week (!worst)").queue();
                break;
            }

            case "!top": {

                CountDownLatch latch = new CountDownLatch(1);
                List<Message> messageList = new ArrayList<Message>();
                try {
                    channel.getHistory().retrievePast(100).queue(messages -> messageList.addAll(messages));
                    latch.await(1, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message topMessage = returnMostUpvoted(messageList);
                channel.sendMessage("The top message of the past 100 messages is: " + topMessage.getJumpUrl()).queue();
                break;
            }

            case "!controversial": {
                CountDownLatch latch = new CountDownLatch(1);
                List<Message> messageList = new ArrayList<Message>();
                try {
                    channel.getHistory().retrievePast(100).queue(messages -> messageList.addAll(messages));
                    latch.await(1, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message topMessage = returnMostReacted(messageList);
                channel.sendMessage("The most reacted message of the past 100 messages is: " + topMessage.getJumpUrl()).queue();
                break;
            }

            case "!worst": {
                CountDownLatch latch = new CountDownLatch(1);
                List<Message> messageList = new ArrayList<Message>();
                try {
                    channel.getHistory().retrievePast(100).queue(messages -> messageList.addAll(messages));
                    latch.await(1, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message topMessage = returnMostDownvoted(messageList);
                channel.sendMessage("The most downvoted message of the past 100 messages is: " + topMessage.getJumpUrl()).queue();
                break;
            }
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
