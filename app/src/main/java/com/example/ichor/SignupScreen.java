package com.example.ichor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;

public class SignupScreen extends AppCompatActivity {
    private EditText name,uname,password,age,gender,bloodgroup,phonenumber;
    private MaterialButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        uname =findViewById(R.id.username);
        name =findViewById(R.id.fullname);
        password =findViewById(R.id.password_1);
        age =findViewById(R.id.age);
        gender =findViewById(R.id.gender);
        bloodgroup =findViewById(R.id.bloodgp);
        phonenumber =findViewById(R.id.phoneno);
        btn=findViewById(R.id.next);

    }
     private Boolean validatePassword() {
         String val = password.getText().toString();
         if (val.isEmpty()) {
             password.setError("Field cannot be empty");
             return false;
         }
         return true;
     }
        private Boolean validatename() {
             String val = name.getText().toString();
             if (val.isEmpty()) {
                 name.setError("Field cannot be empty");
                 return false;
             }
             return true;
         }

    private Boolean validateusername() {
        String val = uname.getText().toString();
        if (val.isEmpty()) {
            uname.setError("Field cannot be empty");
            return false;
        }
        return true;
    }
    private Boolean validatephoneno() {
        String val = phonenumber.getText().toString();
        if (val.isEmpty()) {
            phonenumber.setError("Field cannot be empty");
            return false;
        }
        return true;
    }
    private Boolean validategender() {
        String val = gender.getText().toString();
        if (val.isEmpty()) {
            gender.setError("Field cannot be empty");
            return false;
        }
        return true;
    }
    private Boolean validateage() {
        String val = age.getText().toString();
        if (val.isEmpty()) {
            age.setError("Field cannot be empty");
            return false;
        }
        return true;
    }
    private Boolean validatebloodgroup() {
        String val = bloodgroup.getText().toString();
        if (val.isEmpty()) {
            bloodgroup.setError("Field cannot be empty");
            return false;
        }
        return true;
    }
    public void status(View view)
    {
        if(!validatephoneno()|!validatename()|!validateusername()|!validategender()|!validateage()|!validatebloodgroup()|!validatePassword())
        {
            return;
        }
        String name_user=uname.getText().toString();
        String fullname=name.getText().toString();
        String pass =password.getText().toString();
        String phoneno=phonenumber.getText().toString();
        String blood=bloodgroup.getText().toString();
        String age1=age.getText().toString();
        String gender1=gender.getText().toString();

        Intent intent=new Intent(SignupScreen.this,VerifyPhonenumber.class);
        intent.putExtra("Username",name_user);
        intent.putExtra("Fullname",fullname);
        intent.putExtra("password",pass);
        intent.putExtra("Age",age1);
        intent.putExtra("Gender",gender1);
        intent.putExtra("BloodGroup",blood);
        intent.putExtra("PhoneNumber",phoneno);
        startActivity(intent);
    }

}