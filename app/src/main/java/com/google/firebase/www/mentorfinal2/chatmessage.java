package com.google.firebase.www.mentorfinal2;

import android.provider.ContactsContract;

import java.util.Date;

/**
 * Created by pratishtha on 27-09-2018.
 */

public class chatmessage {
    private String messageText;
    private String messageUser;
    private long messageTime;

    public chatmessage(String messageText,String messageUser)
    {
        this.messageText=messageText;
        this.messageUser=messageUser;

        messageTime=new Date().getTime();
    }
    public chatmessage() {
    }

    public String getMessageText(){
        return messageText;
    }

    public void setMessageText(String messageText){
        this.messageText=messageText;
    }

    public String getMessageUser(){
        return messageUser;
    }

    public void setMessageUser(String messageUser){
        this.messageUser=messageUser;
    }
}
