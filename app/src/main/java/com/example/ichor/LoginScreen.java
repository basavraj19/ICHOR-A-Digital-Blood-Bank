package com.example.ichor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginScreen extends AppCompatActivity {

    private EditText phonenumber,password;
    private TextView forgetpassword;
    public MaterialButton lbtn,rbtn;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_screen);

        phonenumber =findViewById(R.id.Phoneno);
        password =findViewById(R.id.password_1);
        lbtn =findViewById(R.id.loginbtn);
        rbtn =findViewById(R.id.next);
        forgetpassword =findViewById(R.id.forgotpwd);

        rbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(LoginScreen.this,SignupScreen.class);
                startActivity(intent);
            }
        });
        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginScreen.this, Forgetpassword_1.class);
                startActivity(intent);
            }
        });
    }
    private Boolean validateUsername() {
        String val = phonenumber.getText().toString();
        if (val.isEmpty()) {
            phonenumber.setError("Field cannot be empty");
            return false;
        } else {
            phonenumber.setError(null);
            //user.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword() {
        String val = password.getText().toString();
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            //password.setErrorEnabled(false);
            return true;
        }
    }
    public void loginUser(View view) {
        //Validate Login Info
        if (!validateUsername() | !validatePassword()) {
            return;
        } else {
            isUser();
        }
    }

    private void isUser() {
        // progressBar.setVisibility(View.VISIBLE);
        final String userEnteredphonenumber = phonenumber.getText().toString().trim();
        final String userEnteredPassword = password.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        Query checkUser = reference.orderByChild("phoneno").equalTo(userEnteredphonenumber);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    phonenumber.setError(null);
                    //user.setErrorEnabled(false);
                    String passwordFromDB = dataSnapshot.child(userEnteredphonenumber).child("password").getValue(String.class);
                    if (passwordFromDB.equals(userEnteredPassword)) {
                        phonenumber.setError(null);
                        // user.setErrorEnabled(false);
                        String fullnameDB =dataSnapshot.child(userEnteredphonenumber).child("fullname").getValue(String.class);
                        String usernameDB =dataSnapshot.child(userEnteredphonenumber).child("username").getValue(String.class);
                        String genderDB =dataSnapshot.child(userEnteredphonenumber).child("gender").getValue(String.class);
                        String ageDB =dataSnapshot.child(userEnteredphonenumber).child("age").getValue(String.class);
                        String bloodgroupDB =dataSnapshot.child(userEnteredphonenumber).child("bloodgroup").getValue(String.class);

                        Intent intent = new Intent(LoginScreen.this, HomeScreen.class);

                        intent.putExtra("username",usernameDB);
                        intent.putExtra("fullname",fullnameDB);
                        intent.putExtra("gender",genderDB);
                        intent.putExtra("age",ageDB);
                        intent.putExtra("bloodgroup",bloodgroupDB);
                        intent.putExtra("phoneno",userEnteredphonenumber);

                        startActivity(intent);
                    } else {
                        // progressBar.setVisibility(View.GONE);
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                } else {
                    // progressBar.setVisibility(View.GONE);
                    phonenumber.setError("No such User exist");
                    phonenumber.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoginScreen.this, "Invalid User", Toast.LENGTH_SHORT).show();
            }
        });
    }
}