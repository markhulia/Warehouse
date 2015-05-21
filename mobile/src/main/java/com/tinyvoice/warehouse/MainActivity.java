package com.tinyvoice.warehouse;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    public static final int NOTIFICATION_ID = 1;
    public static final String ItemID = "Item Id";
    public static final String ItemName = "Item Name";
    private static final String EXTRA_VOICE_REPLY = "extra_voice_reply";
//    public static final String numberOfPackages = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //Pending activity passes the context of the app. On wearable,
    // it adds "open Application" action button
    @TargetApi(20)
    public void onVoiceReplyClick(View view) {

       // String replyLabel = getResources().getString(R.string.reply_label);

        RemoteInput remoteInput = new RemoteInput.Builder(EXTRA_VOICE_REPLY)
                .setLabel("Reply")
                .build();





    }
        // Intent replyIntent = new Intent(this, activity_chat_detail.class);
//        String[] choices = GenerateNumbers.getNumbers();
//        RemoteInput remoteInput = new RemoteInput.Builder(ChatDetailActivity.EXTRA_VOICE_REPLY)
//                .setLabel("Reply")
//                .setChoices(choices)
//                .setChoices(choices)
//                //Set false if voice input option should be excluded
//                .setAllowFreeFormInput(true)
//                .build();
//
//        PendingIntent confirmActionPendingIntent =
//                getActionFeedbackPendingIntent("OK (entry updated)", 0);
//
//        PendingIntent replyPendingIntent = getConversationPendingIntent("Preppy Rabbit", 1);
//
//        NotificationCompat.Action confirmAction = new NotificationCompat.Action(
//                android.R.drawable.ic_media_previous, "OK",
//                confirmActionPendingIntent);
//
//        NotificationCompat.Action replyAction =
//                new NotificationCompat.Action.Builder(R.drawable.ic_action_favorite, ItemID, replyPendingIntent)
//                        .addRemoteInput(remoteInput)
//                        .build();
//
//        NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender()
//                .addAction(confirmAction)
//                .addAction(replyAction);
//
//        Bitmap prettyAvatar = getScaledLargeIconFromResource(R.drawable.apple55);
//
//        Notification notification = new NotificationCompat.Builder(this)
//                .setContentTitle(ItemID)
//                .setContentText(ItemName)
//                .setSmallIcon(R.drawable.ic_action_good)
//                .setContentIntent(getConversationPendingIntent("Pretty Rabbit", 20))
//                .setPriority(Notification.PRIORITY_HIGH)
//                .setDefaults(Notification.DEFAULT_ALL)
//                .setLargeIcon(prettyAvatar)
//                .extend(wearableExtender)
//                .build();
//
//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
//        notificationManager.notify(NOTIFICATION_ID, notification);
//
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
