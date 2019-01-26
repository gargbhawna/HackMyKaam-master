package com.hackmykaam.hackmykaam;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;

    private Button loginbtn;

    private FirebaseAuth auth;

    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.Email);
        password = (EditText) findViewById(R.id.Password);

        auth = FirebaseAuth.getInstance();

        loginbtn = (Button) findViewById(R.id.Loginbtn);

        authStateListener = new FirebaseAuth.AuthStateListener(){

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser()!=null)
                {
                    startActivity(new Intent(LoginActivity.this, HomePageActivity.class));
                }

            }
        };


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startSignIn();

            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }

    private void startSignIn() {
        String mEmail = email.getText().toString();
        String mPassword = password.getText().toString();

        if (TextUtils.isEmpty(mEmail) || TextUtils.isEmpty(mPassword)) {
            Toast.makeText(LoginActivity.this, "One or more field is empty!!!", Toast.LENGTH_LONG).show();
        }

        else
        {
            auth.signInWithEmailAndPassword(mEmail, mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (!task.isSuccessful()) {

                        Toast.makeText(LoginActivity.this, "Sign In NOT Succesfull!!!", Toast.LENGTH_LONG).show();

                    }
                }
            });
        }

    }
}
