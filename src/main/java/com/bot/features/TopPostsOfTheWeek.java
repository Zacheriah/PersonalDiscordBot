package com.bot.features;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageReaction;

import java.util.List;
public class TopPostsOfTheWeek {

    static public Message returnMostReacted(List<Message> messageList){
        Message topMessage = messageList.get(0);

        for(int i = 0; i < messageList.size(); i++){
            int reactions1 = numberOfReactions(messageList.get(i).getReactions());
            int reactions2 = numberOfReactions(topMessage.getReactions());
            if(reactions1 > reactions2){
                topMessage = messageList.get(i);
            }
        }
        return topMessage;
    }

    static public int numberOfReactions(List<MessageReaction> reactions){
        int totalReactions = 0;
        for(MessageReaction reaction : reactions){
            totalReactions += reaction.getCount();
        }
        return totalReactions;
    }

    static public int numberOfUpvotes(List<MessageReaction> reactions){
        int totalReactions = 0;
        for(MessageReaction reaction : reactions){
            if(reaction.getReactionEmote().getEmote().getName().equals("UpVote")){
                totalReactions += reaction.getCount();
            }else if(reaction.getReactionEmote().getEmote().getName().equals("DownVote")){
                totalReactions = totalReactions - reaction.getCount();
            }
        }
        return totalReactions;
    }

    static public Message returnMostUpvoted(List<Message> messageList){
        Message mostUpvoted = messageList.get(0);

        for(int i = 0; i < messageList.size(); i++){
            int reactions1 = numberOfUpvotes(messageList.get(i).getReactions());
            int reactions2 = numberOfUpvotes(mostUpvoted.getReactions());

            if(reactions1 > reactions2){
                mostUpvoted = messageList.get(i);
            }
        }
        return mostUpvoted;
    }

    static public int numberOfDownvotes(List<MessageReaction> reactions){
        int totalReactions = 0;
        for(MessageReaction reaction : reactions){
            if(reaction.getReactionEmote().getEmote().getName().equals("DownVote")){
                totalReactions += reaction.getCount();
            }else if(reaction.getReactionEmote().getEmote().getName().equals("UpVote")){
                totalReactions = totalReactions - reaction.getCount();
            }
        }
        return totalReactions;
    }

    static public Message returnMostDownvoted(List<Message> messageList){
        Message mostUpvoted = messageList.get(0);

        for(int i = 0; i < messageList.size(); i++){
            int reactions1 = numberOfDownvotes(messageList.get(i).getReactions());
            int reactions2 = numberOfDownvotes(mostUpvoted.getReactions());

            if(reactions1 > reactions2){
                mostUpvoted = messageList.get(i);
            }
        }
        return mostUpvoted;
    }
}
