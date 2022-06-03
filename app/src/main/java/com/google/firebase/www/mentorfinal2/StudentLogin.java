package com.google.firebase.www.mentorfinal2;

import android.app.ProgressDialog;
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
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class StudentLogin extends AppCompatActivity implements View.OnClickListener{
    private EditText editTextEmail;
    private Button studentLog;
    private EditText editTextPassword;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        firebaseAuth = FirebaseAuth.getInstance();
        studentLog = (Button) findViewById(R.id.studentLog);
        progressDialog = new ProgressDialog(this);
        studentLog.setOnClickListener(this);
        editTextEmail =(EditText) findViewById(R.id.editTextEmail);
        editTextPassword =(EditText) findViewById(R.id.editTextPassword);
    }

    private void StudentLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Loging in Please Wait...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()) {
                    //starting profile Activity
                    finish();
                    Intent intent = new Intent(StudentLogin.this, StudentPage.class);
                    startActivity(intent);

                }

                if(!task.isSuccessful()) {
                    Toast.makeText(StudentLogin.this, "Login Error", Toast.LENGTH_LONG).show();
                }



            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v==studentLog){
            StudentLogin();
        }
    }

}
