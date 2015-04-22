package com.tinyvoice.warehouse;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.view.View;
import android.widget.Toast;

import java.security.PublicKey;


public class MainActivity extends ActionBarActivity {
public static final int NOTIFICATION_ID = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private PendingIntent getActivityPendingIntent(){

        Intent activityIntent = new Intent(this, MainActivity.class);
        activityIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return PendingIntent.getActivity(this, 0, activityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public void onStandardNotificationButtonClick(View view){
       PendingIntent activityPendingIntent = getActivityPendingIntent();
        Notification standardNotification = new NotificationCompat.Builder(this)
                .setContentTitle("Standard title")
                .setContentText("Standard Text")
                .setSmallIcon(R.drawable.ic_action_good)
                .setContentIntent(activityPendingIntent)
                .setPriority(Notification.PRIORITY_MAX)
                .build();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        //issue the notification
        Toast.makeText(this, "Standard Notification", Toast.LENGTH_SHORT).show();
        notificationManager.notify(NOTIFICATION_ID, standardNotification);
    }

    public void onUpdateStandardNotificationButtonClick(View view){
        PendingIntent activityPendingIntent = getActivityPendingIntent();
        Notification updateNotification = new NotificationCompat.Builder(this)
                .setContentTitle("Update Title")
                .setContentText("Update Text")
                .setSmallIcon(R.drawable.ic_action_good)
                .setContentIntent(activityPendingIntent)
                .build();
        Toast.makeText(this, "Update Notification",Toast.LENGTH_SHORT).show();
        //issue the notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, updateNotification);
    }


    public void onBigTextStyleNotificationButtonClick (View view){

        String longText = "Without BigTextStyle, only a single line of text would be visible. " +
                "Any additional text would not appear directly on the notification. " +
                "The entire first line would not even be on the notification if it were too long! " +
                "Text that doesn't fit in a standard notification becomes ellipsized. " +
                "That is, the characters that don't fit are removed and replaced by ellipsis.";

        PendingIntent activityPendingIntent = getActivityPendingIntent();
        Toast.makeText(this,"BIG Text Style notification", Toast.LENGTH_SHORT).show();
//        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle()
//                .bigText(longText);


//THIS IS NOT WORKING PROPERLY---------------------
//-------------------------------------------------
        //-----------------------------------------
        Notification bigTextStyleNotification = new Notification.Builder(this)
                .setContentTitle("Aaaw yiis Big Text")
                .setContentText(longText)
                .setSmallIcon(R.drawable.ic_action_good)
                .setContentIntent(activityPendingIntent)
                .setStyle(new Notification.BigTextStyle()
                .bigText(longText))
                .build();


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID,bigTextStyleNotification);

    }

    private PendingIntent getConversationPendingIntent(String chattingWith, int requestCode){
        Intent conversationIntent = new Intent(this, activity_chat_detail.class);

        if(chattingWith != null){
            conversationIntent.putExtra(activity_chat_detail.EXTRA_CHATTING_WITH, chattingWith);
        }

        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(activity_chat_detail.class);
        taskStackBuilder.addNextIntent(conversationIntent);

        return taskStackBuilder.getPendingIntent(requestCode, PendingIntent.FLAG_CANCEL_CURRENT);
    }

    public void onBuildTaskStackContentIntentClick(View view){

    }

    public void onWearableOnlyActionsClick(View view){

    }

    public void onInboxStyleNotificationButtonClick(View view){
        Toast.makeText(this,"Inbox Style notification", Toast.LENGTH_SHORT).show();
    }
    //Pending activity passes the context of the app. On weareble,
    // it adds "open Application" action button


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
