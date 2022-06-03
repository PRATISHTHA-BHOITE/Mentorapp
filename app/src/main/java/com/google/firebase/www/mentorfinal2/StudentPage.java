package com.google.firebase.www.mentorfinal2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StudentPage extends AppCompatActivity implements View.OnClickListener{


    private FirebaseAuth firebaseAuth;
    private Button buttonout,Notif,profile,msg;

    private TextView textViewUseremail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_page);
        firebaseAuth =FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()==null){
            finish();
            //  startActivity(new Intent(this),MainActivity.class);
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        buttonout =(Button) findViewById(R.id.buttonout);
        textViewUseremail =(TextView) findViewById(R.id.textViewUseremail);
        Notif =(Button) findViewById(R.id.button3) ;
        profile =(Button) findViewById(R.id.button4);
msg=(Button)findViewById(R.id.button5);

        //profile=(Button) findViewById(R.id.profile);
        textViewUseremail.setText("Welcome"+user.getEmail());
        buttonout.setOnClickListener(this);
        profile.setOnClickListener(this);
        Notif.setOnClickListener(this);
    msg.setOnClickListener(this);
    }
    public void onClick(View v) {

        if(v == buttonout)
        {
            firebaseAuth.signOut();
            finish();
            //startActivity(new Intent(getApplicationContext(),MainActivity.class);
        }
        if(v == profile) {

            Intent intent = new Intent(StudentPage.this, StudentProfile.class);
            startActivity(intent);
        }
        if(v == Notif)
        {
            Toast.makeText(this,"Send Notif",Toast.LENGTH_SHORT).show();

        }

    if(v == msg)
    {
        Intent intent = new Intent(StudentPage.this, msg3.class);
        startActivity(intent);
    }

    }

    }

