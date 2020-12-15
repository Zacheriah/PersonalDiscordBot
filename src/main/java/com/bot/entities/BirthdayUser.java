package com.bot.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BirthdayUser {
    private int id;
    private String name;
    private Date birthday;
    private String birthdayString;
    private String discord_id;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    public BirthdayUser(){}

    public BirthdayUser(int id, String name, String birthdayString, String discord_id) throws ParseException {
        this.id = id;
        this.name = name;
        this.birthdayString = birthdayString;
        this.birthday = dateFormat.parse(birthdayString);
        System.out.println(dateFormat.parse(birthdayString));
        this.discord_id = discord_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {

        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiscordID() {
        return discord_id;
    }

    public void setDiscordID(String discordID) {
        this.discord_id = discordID;
    }


    public String getBirthdayString() {
        return birthdayString;
    }

    public void setBirthdayString(String birthdayString) throws ParseException {
        this.birthdayString = birthdayString;
        this.birthday =dateFormat.parse(birthdayString);
    }
}
