package com.moaad178.othmansignin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LogInActivity extends AppCompatActivity {
private Button login,signUp;
private EditText Email,password;
FirebaseAuth auth;
FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        login=(Button) findViewById(R.id.login);
        signUp=(Button)findViewById(R.id.signUp);
        Email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        auth=(FirebaseAuth)FirebaseAuth.getInstance();
        user=(FirebaseUser)auth.getCurrentUser();



        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signUp= new Intent(LogInActivity.this,SignUp.class);
            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=Email.getText().toString();
                String Password=password.getText().toString();
                CheckLogin(email,Password);

            }
        });




    }
    private void CheckLogin(String email,String Password){
        boolean x=true;

        if (email.length()>8&&password.length()>8){
            x=true;
        }

            else {
            Toast.makeText(this, "check your email or password", Toast.LENGTH_SHORT).show();
            x=false;

        }

        if (x==true){
                   auth .createUserWithEmailAndPassword(email,Password).addOnCompleteListener(LogInActivity.this, new OnCompleteListener<AuthResult>() {
                       @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){//chek if response arrive to server
                                Toast.makeText(LogInActivity.this, "Autheincation succesful", Toast.LENGTH_SHORT).show();
                           }
                           else {
                                Toast.makeText(LogInActivity.this, "Authnotificatio Failed "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                task.getException().printStackTrace();
                            }
                        }
                   });

        }


    }}





