package com.company.dietfitstage7.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.company.dietfitstage7.BottomNavigationActivity.Dashboard;
import com.company.dietfitstage7.BottomNavigationActivity.Setting;
import com.company.dietfitstage7.OuterActivity.login;
import com.company.dietfitstage7.R;
import com.company.dietfitstage7.SessionManager.SessionManagerDietfit;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import android.util.Base64;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class user_profile extends AppCompatActivity {

    SessionManagerDietfit sessionManagerDietfit;
    TextView TVuser_email,BtnChangeProfileImage;
    EditText ETUser_username,ETUser_age,ETUser_gender,ETUser_act_lvl,ETUser_bmi;
    String getEmail;
    ImageView backUserProfile;
    Button EditProfileBtn,SaveProfileBtn;
    CircleImageView UserProfileImage;
    ProgressBar PBUserProfile;
    private static final String TAG = user_profile.class.getSimpleName();
    private static String URL_READ = "http://192.168.100.196/Volley_Dietfit/read_detail.php";
    private static String URL_EDIT = "http://192.168.100.196/Volley_Dietfit/edit_detail.php";
    private static String URL_UPLOAD = "http://192.168.100.196/Volley_Dietfit/upload.php";
    private long backPressedTime;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        ETUser_username = findViewById(R.id.EditTextUsernameProfile);
        TVuser_email = findViewById(R.id.TVEmailProfile);
        ETUser_age = findViewById(R.id.EditTextAgeProfile);
        ETUser_gender = findViewById(R.id.EditTextGenderProfile);
        ETUser_bmi =findViewById(R.id.EditTextBmiProfile);
        ETUser_act_lvl = findViewById(R.id.EditTextActLvlProfile);
        backUserProfile = findViewById(R.id.BackUserProfile);
        BtnChangeProfileImage = findViewById(R.id.ButtonChangeProfilePic);
        UserProfileImage = findViewById(R.id.profile_photo);
        SaveProfileBtn = findViewById(R.id.ButtonSaveProfile);
        EditProfileBtn = findViewById(R.id.ButtonEditProfile);
        PBUserProfile = findViewById(R.id.progressBarProfile);



        ETUser_username.setFocusableInTouchMode(false);
        ETUser_age.setFocusableInTouchMode(false);
        ETUser_bmi.setFocusableInTouchMode(false);
        ETUser_act_lvl.setFocusableInTouchMode(false);
        ETUser_gender.setFocusableInTouchMode(false);



        EditProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ETUser_username.setFocusableInTouchMode(true);
                ETUser_age.setFocusableInTouchMode(true);
                ETUser_bmi.setFocusableInTouchMode(true);
                ETUser_act_lvl.setFocusableInTouchMode(true);
                ETUser_gender.setFocusableInTouchMode(true);

                EditProfileBtn.setVisibility(View.GONE);
                SaveProfileBtn.setVisibility(View.VISIBLE);

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(ETUser_username,InputMethodManager.SHOW_IMPLICIT);
            }
        });

        SaveProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveEditDetail();

                SaveProfileBtn.setVisibility(View.GONE);

                ETUser_username.setFocusableInTouchMode(false);
                ETUser_age.setFocusableInTouchMode(false);
                ETUser_bmi.setFocusableInTouchMode(false);
                ETUser_act_lvl.setFocusableInTouchMode(false);
                ETUser_gender.setFocusableInTouchMode(false);
            }
        });


        //imm.showSoftInput()


        backUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Setting.class));
            }
        });


        sessionManagerDietfit = new SessionManagerDietfit(this);
        sessionManagerDietfit.checkLogin();

        HashMap<String, String > user = sessionManagerDietfit.getUserDetail();
        getEmail = user.get(sessionManagerDietfit.User_email);


        BtnChangeProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFile();
            }
        });

    }


    private void getUserDetail() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("LOADING.....");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i(TAG, response.toString());
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");

                            if(success.equals("1")) {

                                for(int i=0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String user_email = object.getString("user_email").trim();
                                    String username_user = object.getString("username_user").trim();
                                    String user_bmi = object.getString("user_bmi").trim();
                                    String user_age = object.getString("user_age").trim();
                                    String user_gender = object.getString("user_gender").trim();
                                    String user_act_lvl = object.getString("user_act_lvl").trim();
                                    String user_pwd = object.getString("user_pwd").trim();
                                    //String user_profile_pic = object.getString("user_profile_pic").trim();
//                                    String url_image ="http://192.168.100.196/Volley_Dietfit/profile_image/"+getEmail+".jpeg";
//                                    Picasso.with(user_profile.this)
//                                            .load(url_image)
//                                            .placeholder(R.drawable.imageholder)
//                                            .into(UserProfileImage);

                                    TVuser_email.setText(user_email);
                                    ETUser_username.setText(username_user);
                                    ETUser_bmi.setText(user_bmi);
                                    ETUser_age.setText(user_age);
                                    ETUser_gender.setText(user_gender);
                                    ETUser_act_lvl.setText(user_act_lvl);

                                   // UserProfileImage.setImageURI(Uri.parse(user_profile_pic));
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(user_profile.this, "Your Profile Cannot Be Read "+e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(user_profile.this, "Error Malfunction "+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_email", getEmail);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onResume(){
        super.onResume();
        getUserDetail();
        //getImage();
    }

    //START CLICK BUTTON BACK TO EXIT <----------------------------------------------------------- //
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
        startActivity(intent);
        finish();
    }

    private void SaveEditDetail(){
        final String username_user = this.ETUser_username.getText().toString().trim();
        final String user_bmi = this.ETUser_bmi.getText().toString().trim();
        final String user_gender = this.ETUser_gender.getText().toString().trim();
        final String user_act_lvl = this.ETUser_act_lvl.getText().toString().trim();
        final String user_email = getEmail;
        final String user_age = this.ETUser_age.getText().toString().trim();

        //final ProgressDialog progressDialog = new ProgressDialog(this);
        //progressDialog.setMessage("Saving....");
        //progressDialog.show();
        PBUserProfile.setVisibility(View.VISIBLE);
        EditProfileBtn.setVisibility(View.GONE);
        SaveProfileBtn.setVisibility(View.GONE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_EDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if(success.equals("1")){
                                Toast.makeText(user_profile.this,"Profile Saved",Toast.LENGTH_SHORT).show();
                                sessionManagerDietfit.createSession(user_email,user_age,username_user);
                                PBUserProfile.setVisibility(View.GONE);
                                EditProfileBtn.setVisibility(View.VISIBLE);
                                SaveProfileBtn.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            PBUserProfile.setVisibility(View.GONE);
                            EditProfileBtn.setVisibility(View.VISIBLE);
                            SaveProfileBtn.setVisibility(View.GONE);
                            //progressDialog.dismiss();
                            Toast.makeText(user_profile.this,"Error Exception"+e.toString(),Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        PBUserProfile.setVisibility(View.GONE);
                        EditProfileBtn.setVisibility(View.VISIBLE);
                        SaveProfileBtn.setVisibility(View.GONE);
                        //progressDialog.dismiss();
                        Toast.makeText(user_profile.this,"Error Volley "+error.toString(),Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String,String> getParams() throws  AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("user_email",user_email);
                params.put("user_bmi",user_bmi);
                params.put("user_age",user_age);
                params.put("user_act_lvl",user_act_lvl);
                params.put("username_user",username_user);
                params.put("user_gender",user_gender);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void chooseFile(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),1);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                UserProfileImage.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }

            UploadPicture(getEmail,getStringImage(bitmap));
        }
    }

    private void UploadPicture(final String user_email,final String user_profile_pic) {
        //final ProgressDialog progressDialog = new ProgressDialog(this);
        //progressDialog.setMessage("Uploading....");
        //progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPLOAD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //progressDialog.dismiss();
                        Log.i(TAG,response.toString());
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if(success.equals("1")){
                                Toast.makeText(user_profile.this,"Profile Photo Uploaded",Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            //progressDialog.dismiss();
                            Toast.makeText(user_profile.this, "Try again!"+ e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(user_profile.this, "Try again!"+ error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_email",user_email);
                params.put("user_profile_pic",user_profile_pic);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public String getStringImage(Bitmap bitmap) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byte[] imageByteArray = byteArrayOutputStream.toByteArray();
        String encodeImage = Base64.encodeToString(imageByteArray, Base64.DEFAULT);
        return encodeImage;
    }

    /*private void getImage(){

        final String user_email = getEmail;
         String url_image ="http://192.168.100.196/Volley_Dietfit/profile_image/"+user_email+".jpeg";

        StringRequest stringRequest = new StringRequest(url_image, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                String user_email = "";

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray("result");
                    JSONObject collegeData = result.getJSONObject(2);
                    user_email = collegeData.getString("user_email");


                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(user_profile.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }*/

    }

