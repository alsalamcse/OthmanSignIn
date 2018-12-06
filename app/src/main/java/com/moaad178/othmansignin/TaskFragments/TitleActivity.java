package com.moaad178.othmansignin.TaskFragments;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moaad178.othmansignin.R;

public class TitleActivity extends AppCompatActivity {
    private EditText title,text12;
    private SeekBar seekBar;
    private TextView tv;
    private Button btnDatePicker,btnSave;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        title =  (EditText)findViewById(R.id.Title);
        text12 = (EditText)findViewById(R.id.Title);
        seekBar = (SeekBar)findViewById(R.id.seek_bar);
        btnSave =  (Button)findViewById(R.id.Save);
       tv=(TextView)findViewById(R.id.textView2);
       seekBar=(SeekBar)findViewById(R.id.seekBar);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("ssss");

       btnSave.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


           }
       });


    }

    public void dataHandler() {
        boolean isok = true;
        String myTitle = title.getText().toString();
        String myText = text12.getText().toString();
        int seek=seekBar.getProgress();

        if (myTitle.length()<1){
            title.setError("please enter title");
            isok=false;
        }

        if (myText.length()<0){
            text12.setError("please enter text");
        }

        if (isok==true){
            final MyTask myTask=new MyTask();
            myTask.setText(myText);
            myTask.setTitle(myTitle);


            FirebaseAuth auth = FirebaseAuth.getInstance();
            myTask.setOwner(auth.getCurrentUser().getEmail());

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            String key = reference.child("MyTasks").push().getKey();
            myTask.setKey(key);
            reference.child("MyTasks").child(key).setValue(myTask).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> mytask) {
                    if(mytask.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "succesful", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(TitleActivity.this,TabsActivity.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "not succesful", Toast.LENGTH_SHORT).show();
                    }
                }




            });



    }}}
