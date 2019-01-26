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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText password;

    private Button signupbtn;

    private FirebaseAuth auth;

    private DatabaseReference databaseReference;

    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = (EditText) findViewById(R.id.Name);
        email = (EditText) findViewById(R.id.Email);
        password = (EditText) findViewById(R.id.Password);

        auth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");

        signupbtn = (Button) findViewById(R.id.Signupbtn);

        authStateListener = new FirebaseAuth.AuthStateListener(){

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser()!=null)
                {
                    startActivity(new Intent(SignupActivity.this, HomePageActivity.class));
                }

            }
        };

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startSignUp();

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }

    private void startSignUp()
    {
        final String mName = name.getText().toString();
        String mEmail = email.getText().toString();
        String mPassword = password.getText().toString();

        if (TextUtils.isEmpty(mEmail) || TextUtils.isEmpty(mPassword)) {
            Toast.makeText(SignupActivity.this, "One or more field is empty!!!", Toast.LENGTH_LONG).show();
        }

        else
        {

            auth.createUserWithEmailAndPassword(mEmail, mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (!task.isSuccessful()) {

                        Toast.makeText(SignupActivity.this, "Sign Up NOT Succesfull!!!", Toast.LENGTH_LONG).show();

                    }

                    else
                    {
                        String userId = auth.getCurrentUser().getUid();
                        DatabaseReference newDatabaseForName = databaseReference.child(userId);

                        newDatabaseForName.child("Name").setValue(mName);

                    }
                }
            });

        }

    }
}
