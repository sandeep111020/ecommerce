package com.comapany.shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.comapany.shopping.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class otpscreen extends AppCompatActivity {

    PinView pinFromUser;
    String codeBySystem;
    FirebaseAuth firebaseAuth;
    TextView textView;
    String phoneNo,name,email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpscreen);

        pinFromUser= findViewById(R.id.pin_view);
         phoneNo = getIntent().getStringExtra("phoneNo");
         name = getIntent().getStringExtra("name");
         email = getIntent().getStringExtra("email");
         password = getIntent().getStringExtra("password");
        firebaseAuth = FirebaseAuth.getInstance();
        textView = findViewById(R.id.display);
        textView.setText(phoneNo);
        storeNewUserData();
        initiateotp();
        //sendVerificationCodeToUser(_phoneNo);
    }

    //otp code
    private void initiateotp()
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNo,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
                {
                    @Override
                    public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken)
                    {
                        codeBySystem=s;
                    }

                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential)
                    {
                        String code = phoneAuthCredential.getSmsCode();
                        if(code!=null){
                            pinFromUser.setText(code);
                            verifyCode(code);
                        }
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });        // OnVerificationStateChangedCallbacks

    }


    /*private void sendVerificationCodeToUser(String phoneNo){
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(phoneNo)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

                @Override
                public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                    super.onCodeAutoRetrievalTimeOut(s);
                    codeBySystem = s;
                }

                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                    String code = phoneAuthCredential.getSmsCode();
                    if(code!=null){
                        pinFromUser.setText(code);
                        verifyCode(code);
                    }
                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {

                    Toast.makeText(otpscreen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            };*/

    //otp code

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeBySystem,code);
        signInWithPhoneAuthCredential(credential);
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {



        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            storeNewUserData();
                            //Verification completed successfully here Either
                            // store the data or do whatever desire
                            Toast.makeText(otpscreen.this, "Verification is successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(otpscreen.this,HomeActivity2.class));
                            finish();

                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(otpscreen.this, "Verification Not Completed! Try again.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    private void storeNewUserData() {

                        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                        DatabaseReference reference = rootNode.getReference("Users");


                        User addnewUser = new User(name,email,password);
                        reference.child(phoneNo).setValue(addnewUser);
                    }
                });
    }

    /*public void onclicksubmit(View view) {
        String code = pinFromUser.getText().toString();
        if(!code.isEmpty()){
            storeNewUserData();
            //startActivity(new Intent(otpscreen.this,HomeActivity2.class));
           verifyCode(code);
        }
      *//*  Intent i = new Intent(this,HomeActivity2.class);
        startActivity(i);*//*
    }*/

    private void storeNewUserData() {
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference("Users");


        User addnewUser = new User(name,email,password);
        reference.child(phoneNo).setValue(addnewUser);
    }
}