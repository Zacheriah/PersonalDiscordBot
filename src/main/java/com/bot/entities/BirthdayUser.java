package com.bot.entities;

import java.text.SimpleDateFormat;
import java.sql.Date;

public class BirthdayUser {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");
    private int id;
    private String name;
    private Date birthday;
    private String discord_id;

    public BirthdayUser(){}

    public BirthdayUser(int id, String name, Date birthday, String discord_id){
        this.id = id;
        this.name = name;
        this.birthday = birthday;
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




}
