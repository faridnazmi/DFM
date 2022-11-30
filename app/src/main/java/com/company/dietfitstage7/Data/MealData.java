package com.company.dietfitstage7.Data;

public class MealData {

    private String meals_name;
    private String meals_note;
    private String meals_cal_info;
    private String diary_id;
    private String diary_date;
    //private String meals_name,meals_new,meals_note,diary_id,diary_date;

    public String getDiary_date() {
        return diary_date;
    }

    public String getMeals_cal_info() {
        return meals_cal_info;
    }
    public void setMeals_cal_info(String meals_cal_info) {
        this.meals_cal_info = meals_cal_info;
    }
    public void setDiary_date(String diary_date) {
        this.diary_date = diary_date;
    }

    public String getDiary_id() {
        return diary_id;
    }

    public void setDiary_id(String diary_id) {
        this.diary_id = diary_id;
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
