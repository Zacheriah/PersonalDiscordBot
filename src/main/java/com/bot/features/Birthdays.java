package com.bot.features;

import com.bot.entities.BirthdayUser;
import com.sombrainc.excelorm.e2.EReader;
import com.sombrainc.excelorm.e2.impl.Bind;
import net.dv8tion.jda.api.JDA;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Birthdays {
    Calendar today = Calendar.getInstance();

    List<BirthdayUser> birthdayList;
    JDA jda;
    public Birthdays(JDA jda){
        this.jda = jda;
        birthdayList =
        new EReader("C:\\Users\\Proba\\IdeaProjects\\Jinseo_Bot\\src\\main\\resources\\birthdays.xlsx", "Sheet1")
                .listOf(BirthdayUser.class)
                .binds(
                        new Bind("id", "A2"),
                        new Bind("name", "B2"),
                        new Bind("birthday", "C2"),
                        new Bind("discord_id", "D2"))
                .pick("A2:A12")
                .go();
    }

    public BirthdayUser checkBirthdays(){
        Date date = new Date();
        today.setTime(date);
        Calendar birthdayDay = Calendar.getInstance();

        for(int i = 0; i < birthdayList.size(); i++){
            birthdayDay.setTime(birthdayList.get(i).getBirthday());
            if(today.get(Calendar.MONTH) == birthdayDay.get(Calendar.MONTH) && today.get(Calendar.DATE) == birthdayDay.get(Calendar.DATE)){
                jda.getTextChannelById("158082957645578240").sendMessage("Happy birthday " +  birthdayList.get(i).getName() + "!");
                return birthdayList.get(i);
            }
        }
        return null;
    }
}
