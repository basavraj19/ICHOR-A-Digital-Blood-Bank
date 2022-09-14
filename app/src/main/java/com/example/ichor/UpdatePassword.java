package com.example.ichor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdatePassword extends AppCompatActivity {
   private EditText password,cpassword;
   private MaterialButton btn;
   DatabaseReference r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        btn=findViewById(R.id.update);
        password=findViewById(R.id.newpassword);
        cpassword=findViewById(R.id.conformpassword);

        r= FirebaseDatabase.getInstance().getReference("User");

        Intent intent=getIntent();
        String pnumber= intent.getStringExtra("phoneno");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validpassword()||!validconformpassword())
                {
                    return;
                }
                if(!password.getText().toString().equals(cpassword.getText().toString()))
                {
                    return;
                }
                r.child(pnumber).child("password").setValue(cpassword.getText().toString());
                Intent intent=new Intent(getApplicationContext(),LoginScreen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return;
            }
        });

    }

    private boolean validconformpassword() {
        String val = cpassword.getText().toString();
        if (val.isEmpty()) {
            cpassword.setError("Field cannot be empty");
            return false;
        } else {
            cpassword.setError(null);
            return true;
        }
    }

    private boolean validpassword() {
        String val = password.getText().toString();
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }
}