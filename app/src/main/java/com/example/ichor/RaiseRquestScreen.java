package com.example.ichor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RaiseRquestScreen extends AppCompatActivity {

    private MaterialButton btn;
    private EditText fname, Rphoneno, Rage, Rgender, Rbloodgroup, Rnoofbags;

    FirebaseDatabase root;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raise_rquest_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        btn = findViewById(R.id.sendrequest);
        fname = findViewById(R.id.fullname);
        Rphoneno = findViewById(R.id.phoneno);
        Rage = findViewById(R.id.age);
        Rgender = findViewById(R.id.gender);
        Rbloodgroup = findViewById(R.id.bloodgp);
        Rnoofbags = findViewById(R.id.requirement);

    }

    private Boolean validatename() {
        String val = fname.getText().toString();
        if (val.isEmpty()) {
            fname.setError("Field cannot be empty");
            return false;
        }
        return true;
    }

    private Boolean validatephoneno() {
        String val = Rphoneno.getText().toString();
        if (val.isEmpty()) {
            Rphoneno.setError("Field cannot be empty");
            return false;
        }
        return true;
    }

    private Boolean validategender() {
        String val = Rgender.getText().toString();
        if (val.isEmpty()) {
            Rgender.setError("Field cannot be empty");
            return false;
        }
        return true;
    }

    private Boolean validateage() {
        String val = Rage.getText().toString();
        if (val.isEmpty()) {
            Rage.setError("Field cannot be empty");
            return false;
        }
        return true;
    }

    private Boolean validatebloodgroup() {
        String val = Rbloodgroup.getText().toString();
        if (val.isEmpty()) {
            Rbloodgroup.setError("Field cannot be empty");
            return false;
        }
        return true;
    }

    private Boolean validatebloodrequirement() {
        String val = Rnoofbags.getText().toString();
       // int n = Integer.parseInt(val);
        if (val.isEmpty()) {
            Rbloodgroup.setError("Field cannot be empty");
            return false;
        }
       /* if (n <= 0) {
            Rbloodgroup.setError("Enter Valid Number");
            return false;
        }*/
        return true;
    }
    public void result(View view)
    {
        if (!validatephoneno() | !validatename() | !validategender() | !validateage() | !validatebloodgroup() | !validatebloodrequirement()) {
            return;
        }
        String fullname=fname.getText().toString();
        String phoneno=Rphoneno.getText().toString();
        String blood=Rbloodgroup.getText().toString();
        String age1=Rage.getText().toString();
        String gender1=Rgender.getText().toString();
        String requirement=Rnoofbags.getText().toString();

        root= FirebaseDatabase.getInstance();
        reference =root.getReference("Request");
        RequestData newrequest=new RequestData(fullname,phoneno,blood,age1,gender1,requirement);
        reference.child(phoneno).setValue(newrequest);

        Intent intent = new Intent(RaiseRquestScreen.this, ThankYouScreen.class);
        startActivity(intent);
    }
}