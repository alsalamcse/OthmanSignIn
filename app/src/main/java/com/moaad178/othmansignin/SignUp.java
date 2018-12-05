package com.moaad178.othmansignin;

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

public class SignUp extends AppCompatActivity {
    private EditText firstName, LastName, Email, Password;
    private Button save;
    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firstName = (EditText) findViewById(R.id.firstName);
        LastName = (EditText) findViewById(R.id.Lastname);
        Email = (EditText) findViewById(R.id.Email);
        Password = (EditText) findViewById(R.id.password);
        save = (Button) findViewById(R.id.save);
        auth = (FirebaseAuth) FirebaseAuth.getInstance();
        user = (FirebaseUser) auth.getCurrentUser();//return if user signed in if user didn't sign in  return null


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataHandler();

            }

        });




    }
    private void dataHandler(){

        boolean isk = true;//if all the fields filled will
        String email = Email.getText().toString();
        String password = Password.getText().toString();
        String firstname = firstName.getText().toString();
        String lastname = LastName.getText().toString();
        if (email.length() > 8 || email.indexOf('@') < 0 || email.indexOf('.') < 0) {
            Email.setError("Wrong email");
            isk = false;
        }
        if (password.length() < 8) {
            isk = false;
        }
        if(firstname.length()<1&&lastname.length()<1){
            isk=false;
        }
        if (isk) {
            creatAcount(email, password);
        }



    }


    private void creatAcount(String email, String password) {

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>(){
                @Override
                public void onComplete(Task<AuthResult> task) {
                    if (task.isSuccessful()){//chek if response arrive to server
                        Toast.makeText(SignUp.this, "Auth notification Successful", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        Toast.makeText(SignUp.this, "auth in not completed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }
}