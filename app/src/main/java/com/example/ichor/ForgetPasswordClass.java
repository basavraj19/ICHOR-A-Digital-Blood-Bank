package com.example.ichor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class ForgetPasswordClass extends AppCompatActivity {

    String  VerificationCodebySystem;
    private String phnumber;
    private TextView textView;
    private EditText otpcode;
    private Button verify;
    private ProgressBar progressBar;

    FirebaseDatabase root;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password_class);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        textView=findViewById(R.id.textView);
        otpcode=findViewById(R.id.OTPcode);
        verify=findViewById(R.id.button);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        Intent i=getIntent();
        phnumber=i.getStringExtra("phoneno");

        Sendverificationcodetouser(phnumber);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code =otpcode.getText().toString();
                if(code.isEmpty()||code.length()<6)
                {
                    otpcode.setError("Wrong OTP!!");
                    otpcode.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                VerifyCode(code);
            }
        });
    }
    private void Sendverificationcodetouser(String phoneno) {
        // [START start_phone_auth]
        FirebaseAuth mAuth =FirebaseAuth.getInstance();
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91" + phoneno)       // Phone number to verify
                        .setTimeout(120L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
        // [END start_phone_auth]
    }
    private  PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            VerificationCodebySystem =s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if(code!=null)
            {
                progressBar.setVisibility(View.VISIBLE);
                VerifyCode(code);
            }

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(ForgetPasswordClass.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    private void VerifyCode(String code)
    {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(VerificationCodebySystem,code);
        SignInbyCredential(credential);
    }

    private void SignInbyCredential(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth =FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(ForgetPasswordClass.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            root=FirebaseDatabase.getInstance();
                            reference =root.getReference("User");
                            Intent intent = new Intent(getApplicationContext(), UpdatePassword.class);
                            intent.putExtra(phnumber,"phoneno");
                            startActivity(intent);
                            Toast.makeText(ForgetPasswordClass.this, "Password is updated", Toast.LENGTH_SHORT).show();
                        }else
                        {
                            Toast.makeText(ForgetPasswordClass.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}