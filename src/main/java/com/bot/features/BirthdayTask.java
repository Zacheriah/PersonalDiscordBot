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

    public BirthdayTask(JDA jda){
        jda = jda;
    }

    @Override
    public void run() {
        System.out.println("Running timer task");
        try {
            Date date = new Date();
            today.setTime(date);
            Calendar birthdayDay = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date birthDate = null;
            BirthdayUser birthdayUser;
            for (int i = 0; i < birthdayList.size() - 1; i++) {

                birthDate = dateFormat.parse(birthdayList.get(i).getBirthdayString());
                birthdayDay.setTime(birthDate);
                if (today.get(Calendar.MONTH) == birthdayDay.get(Calendar.MONTH) && today.get(Calendar.DATE) == birthdayDay.get(Calendar.DATE)) {
                    birthdayUser = birthdayList.get(i);
                    jda.getTextChannelById("158082957645578240").sendMessage("Happy birthday " + birthdayUser.getName() + "!").queue();
                }
            }
        }catch(ParseException e){
            System.out.println("error parsing birthday data");
        }
        System.out.println("finished timer task");
    }
}
