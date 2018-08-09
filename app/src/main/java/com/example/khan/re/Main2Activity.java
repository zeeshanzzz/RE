package com.example.khan.re;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
EditText t;
Button b2;
CountryCodePicker picker;
public PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        t=findViewById(R.id.editText);
        picker=findViewById(R.id.countryCodePicker);
        b2=findViewById(R.id.button3);
        b2.setOnClickListener(this);
        picker.registerCarrierNumberEditText(t);
    }

    @Override
    public void onClick(View view) {
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        String nb;
        nb = picker.getFullNumberWithPlus();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                nb,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.

            }


            @Override
            public void onVerificationFailed(FirebaseException e) {
                // ...
            }// OnVerificationStateChangedCallbacks

        };
    }
}
