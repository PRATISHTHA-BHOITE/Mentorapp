package com.google.firebase.www.mentorfinal2;


import android.content.Intent;
import android.icu.text.DateFormat;
import android.graphics.ColorSpace;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.android.firebase.ui.database.FirebaseListadapter;
import com.google.android.gms.tasks.OnCompleteListener;



//import java.text.DateFormat;

import static android.app.Activity.RESULT_OK;

public class chat extends AppCompatActivity {
    private static int SIGN_IN_REQUEST_CODE=1;
    private FirebaseListAdapter<chatmessage> adapter;
    RelativeLayout activity_chat;

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==SIGN_IN_REQUEST_CODE)
        {
            if(resultCode==RESULT_OK)
            {
                Snackbar.make(activity_chat,"Successfully signed in",Snackbar.LENGTH_SHORT).show();
                displaychatmessage();
            }
            else {
                Snackbar.make(activity_chat, "We couldnt sign you in ", Snackbar.LENGTH_SHORT).show();

                finish();

            }
        }
    }
    @Override
   /* public boolean onOptionsItemSelected(MenuItem item)
    {if(item.getItemId()==R.id.menu_sign_Out
        {
            AuthUI.getInstance().signOut(this).addOnCompleteListener(now OnCompleteListener<void>)
            {
                @Override
                        public void onComplete(Task)
            }
        }

        return true;
    }*/

    @Override
    public Boolean onCreateOptionMenu(Menu menu)
    {
        getMenuFlater().inflate(R.menu.main_menu,menu);
        return true;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        activity_chat= (RelativeLayout)findViewById(R.id.activity_chat);
        fab =(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
                    public void onClick(View view)
            {
                EditText input=(EditText)findViewById(R.id.input);
                FirebaseDatabase.getInstance().getReference().push().setValue(chatmessage(input.getText().toString().FirebaseAuth.getInstance().getCurrentUser().getEmail()));
                input.setText("");
            }
        });

        if(FirebaseAuth.getInstance().getCurrentUser()==null)
        {
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(),SIGN_IN_REQUEST_CODE);
        }
        else
        {
        Snackbar.make(activity_chat,"welcome"+FirebaseAuth.getInstance().getCurrentUser().getEmail(),Snackbar.LENGHT_SHORT).show();
            displaychatmessage();
        }

    }
    private void displaychatmessage()
    {
        ListView listofMessage=(ListView)findViewById(R.id.list_of_message);
        adapter= new FirebaseListAdapter<chatmessage>(this,chatmessage.class,R.layout.list_item,FirebaseDatabase.getInstance().getReference())
        {
            @Override
                    protected void populateView(View v,chatmessage model,int position)
            {
                TextView messageText,messageUser,messageTime;
                messageText=(TextView)findViewById(R.id.message_text);
                messageUser=(TextView)findViewById(R.id.message_user);
                messageTime=(TextView)findViewById(R.id.message_time);

                messageText.setText(ColorSpace.Model.getMessageText());
                messageUser.setText(ColorSpace.Model.getMessageUser());
                messageTime.setText(DateFormat.Format("dd-MM-yyyy(HH:mm:ss)",model.getMessageTime()));


            }
        };
        listofMessage.setAdapter(adapter);
    }

    public View getMenuFlater() {
        return menuFlater;
    }
}
