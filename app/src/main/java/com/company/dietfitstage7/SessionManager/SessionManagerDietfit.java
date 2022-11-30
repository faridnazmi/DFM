package com.company.dietfitstage7.SessionManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.company.dietfitstage7.BottomNavigationActivity.Dashboard;
import com.company.dietfitstage7.OuterActivity.login;

import java.util.HashMap;

public class SessionManagerDietfit {
    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_USER_EMAIL = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public static final String Username_user = "username_user";
    public static final String User_email = "user_email";
    public static final String User_age = "user_age";

    public SessionManagerDietfit (Context context) {

        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_USER_EMAIL,PRIVATE_MODE);
        editor = sharedPreferences.edit();

    }

    public void createSession(String user_email,String user_age,String username_user){

        editor.putBoolean(LOGIN,true);
        editor.putString(Username_user,username_user);
        editor.putString(User_email,user_email);
        editor.putString(User_age,user_age);
        editor.apply();

    }
    public boolean isLogin(){
        return sharedPreferences.getBoolean(LOGIN,false);
    }

    public void checkLogin(){
        if(!this.isLogin()){
            Intent i = new Intent(context, login.class);
            context.startActivity(i);
            ((Dashboard) context).finish();

        }
    }
    public HashMap<String, String> getUserDetail(){
        HashMap<String, String> user = new HashMap<>();
        user.put(User_email,sharedPreferences.getString(User_email,null));
        user.put(Username_user,sharedPreferences.getString(Username_user,null));
        user.put(User_age,sharedPreferences.getString(User_age,null));
        return user;
    }
    public void logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context,login.class);
        context.startActivity(i);
        ((Dashboard)context).finish();

    }
}
