package com.example.ichor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Forgetpassword_1 extends AppCompatActivity {

    private EditText phonenumber;
    private MaterialButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        phonenumber=findViewById(R.id.phoneno);
        btn=findViewById(R.id.sendOTP);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isUser();
            }
        });
    }

    private void isUser() {
        final String userEnteredphonenumber = phonenumber.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        Query checkUser = reference.orderByChild("phoneno").equalTo(userEnteredphonenumber);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    phonenumber.setError(null);
                    Intent intent=new Intent(getApplicationContext(),ForgetPasswordClass.class);
                    intent.putExtra("phoneno",phonenumber.getText().toString());
                    startActivity(intent);
                } else {
                    // progressBar.setVisibility(View.GONE);
                    phonenumber.setError("No such User exist");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Forgetpassword_1.this, "Invalid User", Toast.LENGTH_SHORT).show();
            }
        });
    }
}