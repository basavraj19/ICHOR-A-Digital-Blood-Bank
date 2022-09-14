package com.example.ichor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateData extends AppCompatActivity {

    private TextView username,fullname,phonenumber,age,gender,bloodgroup;
    private Button update;
    private String u_name,fulll_name, phone_number,age_1,gender_1,bloood_group;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        username=findViewById(R.id.username);
        fullname=findViewById(R.id.Fname);
        phonenumber=findViewById(R.id.phoneno_profile);
        age=findViewById(R.id.Page);
        gender=findViewById(R.id.Pgender);
        bloodgroup=findViewById(R.id.Pbloodgroup);
        update=findViewById(R.id.update);

        reference= FirebaseDatabase.getInstance().getReference("User");

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
                if(updateusername()||updatefullname()||updateage()||updategender()||updatephoneno()||updatebloodgroup())
                {
                    Toast.makeText(UpdateData.this, "Data is changed !!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LoginScreen.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    return;
                }
                Toast.makeText(UpdateData.this, "Data is not changed so Upadte process can not be done!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), LoginScreen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    private boolean updatebloodgroup() {
        if(!bloood_group.equals(bloodgroup.getText().toString()))
        {
            reference.child(phone_number).child("username").setValue(bloodgroup.getText().toString());
            bloood_group=bloodgroup.getText().toString();
            return true;
        }
        return false;
    }

    private boolean updatephoneno() {
        if(!phone_number.equals(phonenumber.getText().toString()))
        {
            reference.child(phone_number).child("phoneno").setValue(phonenumber.getText().toString());
            phone_number=phonenumber.getText().toString();
            return true;
        }
        return false;
    }

    private boolean updategender() {
        if(!gender_1.equals(gender.getText().toString()))
        {
            reference.child(phone_number).child("gender").setValue(gender.getText().toString());
            gender_1=gender.getText().toString();
            return true;
        }
        return false;
    }

    private boolean updateage() {
        if(!age_1.equals(age.getText().toString()))
        {
            reference.child(phone_number).child("age").setValue(age.getText().toString());
            age_1=age.getText().toString();
            return true;
        }
        return false;
    }

    private boolean updatefullname() {
        if(!fulll_name.equals(fullname.getText().toString()))
        {
            reference.child(phone_number).child("fullname").setValue(fullname.getText().toString());
            fulll_name=fullname.getText().toString();
            return true;
        }
        return false;
    }

    private boolean updateusername() {
        if(!u_name.equals(username.getText().toString()))
        {
         reference.child(phone_number).child("username").setValue(username.getText().toString());
            u_name=username.getText().toString();
         return true;
        }
        return false;
    }

}