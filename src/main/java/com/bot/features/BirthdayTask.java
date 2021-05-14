package com.bot.features;

import com.bot.entities.BirthdayUser;
import net.dv8tion.jda.api.JDA;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BirthdayTask extends TimerTask {
    Calendar today = Calendar.getInstance();
    private JDA jda;
    List<BirthdayUser> birthdayList = new ArrayList<>();

    public BirthdayTask(JDA jda, List<BirthdayUser> birthdayList){
        this.jda = jda;
        this.birthdayList = birthdayList;
    }

    @Override
    public void run() {
        System.out.println("Running daily Birthday task");
        try {
            System.out.println("Is it someone's birthday?");
            Date date = new Date();
            today.setTime(date);
            Calendar birthdayDay = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date birthDate = null;
            BirthdayUser birthdayUser;

            System.out.println(birthdayList.size());

            for (int i = 0; i < birthdayList.size() - 1; i++) {
                System.out.println("Is it " + birthdayList.get(i).getName() + "'s birthday?");
                birthDate = dateFormat.parse(birthdayList.get(i).getBirthdayString());
                birthdayDay.setTime(birthDate);
                if (today.get(Calendar.MONTH) == birthdayDay.get(Calendar.MONTH) && today.get(Calendar.DATE) == birthdayDay.get(Calendar.DATE)) {
                    System.out.println("It's someone's birthday!");
                    birthdayUser = birthdayList.get(i);
                    jda.getTextChannelById("158082957645578240").sendMessage("Happy birthday " + birthdayUser.getName() + "!").queue();
                }
            }
        }catch(ParseException e){
            System.out.println("error parsing birthday data");
        }
        System.out.println("finished Birthday task");
    }
}
