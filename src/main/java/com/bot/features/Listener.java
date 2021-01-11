package com.bot.features;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static com.bot.features.TopPostsOfTheWeek.*;
import static net.dv8tion.jda.api.utils.TimeUtil.getDiscordTimestamp;

public class Listener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event){
        Message message = event.getMessage();
        MessageChannel channel = message.getChannel();

        long DAY_IN_MS = 1000 * 60 * 60 * 24;
        Date date = new Date(System.currentTimeMillis() - (7*DAY_IN_MS));
        OffsetDateTime offsetDateTime = date.toInstant().atOffset(message.getTimeCreated().getOffset());

            if(message.getContentRaw().equals("!help")){
                channel.sendMessage("For feature requests, contact <@145717043289784320>! Current features include: \n" +
                        "- Birthday Announcer\n" +
                        "- Top posts of the week (!top)\n" +
                        "- Most controversial post of the week (!controversial)\n" +
                        "- Most downvoted post of the week (!worst)\n" +
                        "- Posts of the month (!monthly)\n" +
                        "- Grab profile image (!image)\n" +
                        "- Message accolades for the week (!weekly)").queue();

            }else if(message.getContentRaw().equals("!top")) {
                //Deprecated
                List<Message> messageList = new ArrayList<Message>();

                for(Message currentMessage : channel.getIterableHistory()){
                    if(currentMessage.getTimeCreated().isAfter(offsetDateTime)){
                        messageList.add(currentMessage);
                    }else if (messageList.size() > 500){
                        break;
                    }else{
                        break;
                    }
                }

                Message topMessage = returnMostUpvoted(messageList);
                System.out.print("for loop done wowzers");
                channel.sendMessage("The top message of the past week is: " + topMessage.getJumpUrl()).queue();

            }else if(message.getContentRaw().equals("!controversial")){
                //Deprecated
                List<Message> messageList = new ArrayList<Message>();

                for(Message currentMessage : channel.getIterableHistory()){
                    if(currentMessage.getTimeCreated().isAfter(offsetDateTime)){
                        messageList.add(currentMessage);
                    }else if (messageList.size() > 500){
                        break;
                    }else{
                        break;
                    }
                }

                Message topMessage = returnMostReacted(messageList);
                System.out.print("for loop done wowzers");
                channel.sendMessage("The most controversial message of the past week is: " + topMessage.getJumpUrl()).queue();

            }else if(message.getContentRaw().equals("!worst")){
                //Deprecated
                List<Message> messageList = new ArrayList<Message>();

                for(Message currentMessage : channel.getIterableHistory()){
                    if(currentMessage.getTimeCreated().isAfter(offsetDateTime)){
                        messageList.add(currentMessage);
                    }else if (messageList.size() > 500){
                        break;
                    }else{
                        break;
                    }
                }

                Message topMessage = returnMostDownvoted(messageList);
                System.out.print("for loop done wowzers");
                channel.sendMessage("The WORST message of the past week is: " + topMessage.getJumpUrl()).queue();
            }else if(message.getContentRaw().equals("!monthly")) {
                Date date2 = new Date(System.currentTimeMillis() - (30*DAY_IN_MS));
                OffsetDateTime offsetDateTime2 = date2.toInstant().atOffset(message.getTimeCreated().getOffset());
                List<Message> messageList = new ArrayList<Message>();

                for(Message currentMessage : channel.getIterableHistory()){
                    if(currentMessage.getTimeCreated().isAfter(offsetDateTime2)){
                        messageList.add(currentMessage);
                    }else if (messageList.size() > 2000){
                        break;
                    }else{
                        break;
                    }
                }

                Message topMessage = returnMostUpvoted(messageList);
                Message controversialMessage = returnMostReacted(messageList);
                Message bottomMessage = returnMostDownvoted(messageList);
                System.out.print("for loop done wowzers");
                channel.sendMessage("Here's the roundup for the month: \n" +
                        "The top message of the past month is: " + topMessage.getJumpUrl() + "\n" +
                        "The most controversial message of the past month is: " + controversialMessage.getJumpUrl() + "\n" +
                        "The worst message of the past month is: " + bottomMessage.getJumpUrl()).queue();

            }else if(message.getContentRaw().startsWith("!image")){

                if(message.getMentionedMembers().isEmpty()) {
                    channel.sendMessage(event.getMessage().getAuthor().getAvatarUrl()).queue();
                }else{
                    for(Member member : message.getMentionedMembers()){
                        channel.sendMessage(member.getUser().getAvatarUrl()).queue();
                    }
                }

            }else if(message.getContentRaw().startsWith("!weekly")){
                Timer weeklyTimer = new Timer();
                weeklyTimer.scheduleAtFixedRate(new WeeklyTask(channel), 1000, 604800000);
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
