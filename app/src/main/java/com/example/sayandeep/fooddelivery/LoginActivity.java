package com.example.sayandeep.fooddelivery;


import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {
    EditText _phoneNumber,_otpEnter;
    Button _requestOTP, _submitOtp;
    String phoneNumber = "";
    PhoneAuthProvider.OnVerificationStateChangedCallbacks changedCallbacks;
    FirebaseAuth firebaseAuth;
    private String CLASS_TAG = LoginActivity.class.getSimpleName();
    private String verificationID;
    LinearLayout login1,login2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login1 =findViewById(R.id.login1);
        login2=findViewById(R.id.login2);
        firebaseAuth = FirebaseAuth.getInstance();
        initializeViews();
        _requestOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneNumber = _phoneNumber.getText().toString();
                _phoneNumber.setEnabled(false);
                _requestOTP.setEnabled(false);
                requestOTP();
                login1.setVisibility(View.INVISIBLE);
                login2.setVisibility(View.VISIBLE);



            }
        });
        changedCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                signInWithPhone(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(String verifyCode, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                verificationID=verifyCode;
            }
        };
        _submitOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationID,_otpEnter.getText().toString());
                signInWithPhone(credential);
            }
        });
    }

    private void initializeViews() {

        _otpEnter = findViewById(R.id.etOtp);
        _phoneNumber = findViewById(R.id.etMobile);
        _requestOTP = findViewById(R.id.requestOtp);
        _submitOtp=findViewById(R.id.btnSendOtp);

    }

    private void requestOTP() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                this,
                changedCallbacks
        );
    }

    private void signInWithPhone(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(CLASS_TAG, "SignedIn.");
                            Toast.makeText(getApplicationContext(),"Logged in Successfully.",Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d(CLASS_TAG, "Failed SignIn.");
                            Toast.makeText(getApplicationContext(),"Log in failed.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
