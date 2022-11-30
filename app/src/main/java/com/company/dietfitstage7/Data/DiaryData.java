package com.company.dietfitstage7.Data;

public class DiaryData {
    //private String ex_name,ex_new,ex_note,diary_id,diary_date,meals_name,meals_new,meals_note;
    private String ex_name,ex_note,diary_id,diary_date,meals_name,meals_note,user_email,meals_cal_info,ex_cal_info,ex_time_taken;

    public String getMeals_cal_info() {
        return meals_cal_info;
    }

    public void setMeals_cal_info(String meals_cal_info) {
        this.meals_cal_info = meals_cal_info;
    }

    public String getEx_cal_info() {
        return ex_cal_info;
    }

    public void setEx_cal_info(String ex_cal_info) {
        this.ex_cal_info = ex_cal_info;
    }

    public String getEx_time_taken() {
        return ex_time_taken;
    }

    public void setEx_time_taken(String ex_time_taken) {
        this.ex_time_taken = ex_time_taken;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getEx_name() {
        return ex_name;
    }

    public void setEx_name(String ex_name) {
        this.ex_name = ex_name;
    }

    public String getEx_note() {
        return ex_note;
    }

    public void setEx_note(String ex_note) {
        this.ex_note = ex_note;
    }

    public String getDiary_id() {
        return diary_id;
    }

    public void setDiary_id(String diary_id) {
        this.diary_id = diary_id;
    }

    public String getDiary_date() {
        return diary_date;
    }

    public void setDiary_date(String diary_date) {
        this.diary_date = diary_date;
    }

    public String getMeals_name() {
        return meals_name;
    }

    public void setMeals_name(String meals_name) {
        this.meals_name = meals_name;
    }

    public String getMeals_note() {
        return meals_note;
    }

    public void setMeals_note(String meals_note) {
        this.meals_note = meals_note;
    }
}
