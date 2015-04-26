package com.tinyvoice.warehouse;

import android.annotation.TargetApi;
import android.app.RemoteInput;
import android.content.Intent;
import android.support.v7.app.*;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.*;

public class activity_chat_detail extends ActionBarActivity {
    public static final String EXTRA_CHATTING_WITH = "chatting_with";
    public static final String EXTRA_VOICE_REPLY = "extra_voice_reply";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    // Displays  a TextView that displays voice reply for feedback
    CharSequence replyText = getMessageText(getIntent());
    if(replyText != null){
        TextView replyTextView = (TextView)findViewById(R.id.reply);
        replyTextView.setText("You repliedL: "+replyText);
    }

        String chattingWith = getIntent().getStringExtra(EXTRA_CHATTING_WITH);
            if(chattingWith != null){
                getSupportActionBar().setTitle(chattingWith);


            }
    }

    // The getMessageText method shows hot to extract voice reply from Intent
    @TargetApi(20) //Suppressing compatibility errors between SDK18 adn SDK20
    private CharSequence getMessageText(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            return remoteInput.getCharSequence(EXTRA_VOICE_REPLY);
        }
        return null;
     }

}

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_activity_chat_detail, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }