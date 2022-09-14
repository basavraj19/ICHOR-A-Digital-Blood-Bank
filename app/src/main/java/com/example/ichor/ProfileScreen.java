package com.example.ichor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class ProfileScreen extends AppCompatActivity {

    private TextView username,fullname,phonenumber,age,gender,bloodgroup;
    private ImageView update;
    private String u_name ,fulll_name, phone_number,age_1,gender_1,bloood_group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
         username=findViewById(R.id.username);
         fullname=findViewById(R.id.Fname);
         phonenumber=findViewById(R.id.phoneno_profile);
         age=findViewById(R.id.Page);
         gender=findViewById(R.id.Pgender);
         bloodgroup=findViewById(R.id.Pbloodgroup);
         update=findViewById(R.id.edit);

        Intent i =getIntent();
        u_name= i.getStringExtra("username");
        fulll_name= i.getStringExtra("fullname");
        phone_number=i.getStringExtra("phoneno");
        age_1=i.getStringExtra("age");
        gender_1=i.getStringExtra("gender");
        bloood_group=i.getStringExtra("bloodgroup");

        username.setText(u_name);
        fullname.setText(fulll_name);
        phonenumber.setText(phone_number);
        bloodgroup.setText(bloood_group);
        gender.setText(gender_1);
        age.setText(age_1);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),UpdateData.class);
                intent.putExtra("username",u_name);
                intent.putExtra("fullname",fulll_name);
                intent.putExtra("gender",gender_1);
                intent.putExtra("age",age_1);
                intent.putExtra("bloodgroup",bloood_group);
                intent.putExtra("phoneno",phone_number);
                startActivity(intent);
            }
        });
    }
}