package com.company.dietfitstage7.Data;

public class ExerciseData {
    private String ex_name;
    private String ex_note;
    private String ex_time_taken;

    public String getEx_time_taken() {
        return ex_time_taken;
    }

    public void setEx_time_taken(String ex_time_taken) {
        this.ex_time_taken = ex_time_taken;
    }

    public String getEx_cal_info() {
        return ex_cal_info;
    }

    public void setEx_cal_info(String ex_cal_info) {
        this.ex_cal_info = ex_cal_info;
    }

    private String ex_cal_info;
    private String diary_id;
    private String diary_date;
    //private String ex_name,ex_new,ex_note,diary_id,diary_date;

    public String getDiary_date() {
        return diary_date;
    }

    public void setDiary_date(String diary_date) {
        this.diary_date = diary_date;
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
}
