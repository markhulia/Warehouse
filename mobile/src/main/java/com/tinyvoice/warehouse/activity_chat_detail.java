package com.tinyvoice.warehouse;

import android.annotation.TargetApi;
import android.support.v4.app.RemoteInput;
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
        //IMPORTANT
        setContentView(R.layout.activity_chat_detail_activity);

        // Displays  a TextView that displays voice reply for feedback
        CharSequence replyText = getMessageText(getIntent());
        if (replyText != null) {
            TextView replyTextView = (TextView) findViewById(R.id.reply);
            replyTextView.setText("You repliedL: " + replyText);
        }
        String chattingWith = getIntent().getStringExtra(EXTRA_CHATTING_WITH);
        if (chattingWith != null) {
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