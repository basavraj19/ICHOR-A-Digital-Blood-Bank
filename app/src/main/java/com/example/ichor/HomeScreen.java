package com.example.ichor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class HomeScreen extends AppCompatActivity {

    private ImageView profile;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        profile=findViewById(R.id.Profile);
        btn=findViewById(R.id.Requestbtn);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=getIntent();

                String u_name= intent.getStringExtra("username");
                String fulll_name= intent.getStringExtra("fullname");
                String phone_number=intent.getStringExtra("phoneno");
                String age_1=intent.getStringExtra("age");
                String gender_1=intent.getStringExtra("gender");
                String bloood_group=intent.getStringExtra("bloodgroup");

                Intent i =new Intent(HomeScreen.this,ProfileScreen.class);

                i.putExtra("username",u_name);
                i.putExtra("fullname",fulll_name);
                i.putExtra("gender",gender_1);
                i.putExtra("age",age_1);
                i.putExtra("bloodgroup",bloood_group);
                i.putExtra("phoneno",phone_number);

                startActivity(i);

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(HomeScreen.this,RaiseRquestScreen.class);
                startActivity(intent);
            }
        });
    }
}