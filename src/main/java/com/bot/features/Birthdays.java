package com.bot.features;

import com.bot.entities.BirthdayUser;
import com.sombrainc.excelorm.e2.EReader;
import com.sombrainc.excelorm.e2.impl.Bind;
import net.dv8tion.jda.api.JDA;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        new EReader("src/main/resources/birthdays.xlsx", "Sheet1")
                .listOf(BirthdayUser.class)
                .binds(
                        new Bind("id", "A2"),
                        new Bind("name", "B2"),
                        new Bind("birthdayString", "C2"),
                        new Bind("discord_id", "D2"))
                .pick("A2:A12")
                .go();
    }

    public BirthdayUser checkBirthdays() throws ParseException {
        Date date = new Date();
        today.setTime(date);
        Calendar birthdayDay = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date birthDate = null;
        for(int i = 0; i < birthdayList.size() - 1; i++){

            birthDate = dateFormat.parse(birthdayList.get(i).getBirthdayString());
            birthdayDay.setTime(birthDate);
            if(today.get(Calendar.MONTH) == birthdayDay.get(Calendar.MONTH) && today.get(Calendar.DATE) == birthdayDay.get(Calendar.DATE)){
                return birthdayList.get(i);
            }
        }
        return null;
    }

    public List<BirthdayUser> getBirthdayList() {
        return birthdayList;
    }

    public void setBirthdayList(List<BirthdayUser> birthdayList) {
        this.birthdayList = birthdayList;
    }

}
