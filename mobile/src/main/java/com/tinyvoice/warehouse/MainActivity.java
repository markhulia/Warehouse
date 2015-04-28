package com.tinyvoice.warehouse;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    public static final int NOTIFICATION_ID = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private PendingIntent getActivityPendingIntent() {

        Intent activityIntent = new Intent(this, MainActivity.class);
        activityIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return PendingIntent.getActivity(this, 0, activityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public void onStandardNotificationButtonClick(View view) {
        PendingIntent activityPendingIntent = getActivityPendingIntent();
        Notification standardNotification = new NotificationCompat.Builder(this)
                .setContentTitle("Item ID")
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

    public void onUpdateStandardNotificationButtonClick(View view) {
        PendingIntent activityPendingIntent = getActivityPendingIntent();
        Notification updateNotification = new NotificationCompat.Builder(this)
                .setContentTitle("Update Title")
                .setContentText("Update Text")
                .setSmallIcon(R.drawable.ic_action_good)
                .setContentIntent(activityPendingIntent)
                .build();
        Toast.makeText(this, "Update Notification", Toast.LENGTH_SHORT).show();
        //issue the notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, updateNotification);
    }


    public void onBigTextStyleNotificationButtonClick(View view) {

        String longText = "Without BigTextStyle, only a single line of text would be visible. " +
                "Any additional text would not appear directly on the notification. " +
                "The entire first line would not even be on the notification if it were too long! " +
                "Text that doesn't fit in a standard notification becomes ellipsized. " +
                "That is, the characters that don't fit are removed and replaced by ellipsis.";

        PendingIntent activityPendingIntent = getActivityPendingIntent();
        Toast.makeText(this, "BIG Text Style notification", Toast.LENGTH_SHORT).show();
//        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle()
//                .bigText(longText);


        Notification bigTextStyleNotification = new Notification.Builder(this)
                .setContentTitle("Aaaw yiis Big Text")
                .setContentText(longText)
                .setSmallIcon(R.drawable.ic_action_good)
                .setContentIntent(activityPendingIntent)
                .setStyle(new Notification.BigTextStyle()
                        .bigText(longText))
                .build();


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, bigTextStyleNotification);

    }

    private PendingIntent getConversationPendingIntent(String chattingWith, int requestCode) {
        Intent conversationIntent = new Intent(this, ChatDetailActivity.class);

        if (chattingWith != null) {
            conversationIntent.putExtra(ChatDetailActivity.EXTRA_CHATTING_WITH, chattingWith);
        }

        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(ChatDetailActivity.class);
        taskStackBuilder.addNextIntent(conversationIntent);

        return taskStackBuilder.getPendingIntent(requestCode, PendingIntent.FLAG_CANCEL_CURRENT);
    }

    public void onBuildTaskStackContentIntentClick(View view) {
        PendingIntent conversationPendingIntent = getConversationPendingIntent("Preppy Rabbit", 0);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Preppy Rabbit")
                .setContentText("I like carrots")
                .setSmallIcon(R.drawable.ic_plusone_standard_off_client)
                .setContentIntent(conversationPendingIntent)
                .setPriority(Notification.PRIORITY_HIGH)
                .setDefaults(Notification.DEFAULT_ALL)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, notification);

    }

    private PendingIntent getActionFeedbackPendingIntent(String actionFeedback, int requestCode) {

        Intent actionFeedbackIntent = new Intent(this, ActionFeedbackActivity.class);
        actionFeedbackIntent.putExtra(ActionFeedbackActivity.EXTRA_ACTION_FEEDBACK, actionFeedback);

        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this)
                .addParentStack(ActionFeedbackActivity.class)
                .addNextIntent(actionFeedbackIntent);

        return taskStackBuilder.getPendingIntent(requestCode, PendingIntent.FLAG_CANCEL_CURRENT);
    }

    private PendingIntent getMainActivityPendingIntent() {
        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return PendingIntent.getActivity(this, 0,
                mainActivityIntent, PendingIntent.FLAG_CANCEL_CURRENT);
    }

    //Perform actions on notifications from wearable only, or wearable and handheld
    public void onWearableOnlyActionsClick(View view) {
        PendingIntent handheldActionFeedbackPendingIntent =
                getActionFeedbackPendingIntent("You invoked the handheld only action", 0);

        PendingIntent wearableActionFeedbackPendingIntent =
                getActionFeedbackPendingIntent("You invoked the wearable only action", 1);

        //##################################################################################
        //##################################################################################
        //##################################################################################

        //Create a broadcast Intent

        //If receiver is in the STOP state app will not be executed if below line
        //is not included
       // notificationSendIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        //Send broadcast
       // this.sendBroadcast(notificationSendIntent);


        //##################################################################################
        //##################################################################################
        //##################################################################################

        PendingIntent bothActionFeedbackPendingIntent =
                getActionFeedbackPendingIntent("You invoked the action that appears on both devices", 2);

        NotificationCompat.Action handheldOnlyAction = new NotificationCompat.Action(
                android.R.drawable.ic_media_previous, "Handheld",
                handheldActionFeedbackPendingIntent);

        NotificationCompat.Action wearableOnlyAction = new NotificationCompat.Action(
                android.R.drawable.ic_media_next, "Wearable",
                wearableActionFeedbackPendingIntent);

        NotificationCompat.Action bothAction = new NotificationCompat.Action(
                android.R.drawable.ic_media_pause, "Both",
                bothActionFeedbackPendingIntent);


        NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.
                WearableExtender()
                .addAction(wearableOnlyAction)
                .addAction(bothAction);
        PendingIntent mainActivityPendingIntent = getMainActivityPendingIntent();
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Title")
                .setContentText("Text")
                .setSmallIcon(android.R.drawable.ic_media_previous)
                .setContentIntent(mainActivityPendingIntent)
                .addAction(bothAction)
                .extend(wearableExtender)
                .addAction(handheldOnlyAction)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, notification);

    }

    //Scale image to the size required by notification
    private Bitmap getScaledLargeIconFromResource(int resource) {
        Resources res = getResources();
        int height = (int) res.getDimension(android.R.dimen.notification_large_icon_height);
        int width = (int) res.getDimension(android.R.dimen.notification_large_icon_width);
        Bitmap largeIcon = BitmapFactory.decodeResource(res, resource);
        return Bitmap.createScaledBitmap(largeIcon, width, height, false);
    }

    //Pending activity passes the context of the app. On wearable,
    // it adds "open Application" action button
    @TargetApi(20)
    public void onVoiceReplyClick(View view) {
        // Intent replyIntent = new Intent(this, activity_chat_detail.class);
        String[] choices = GenerateNumbers.getNumbers();
        RemoteInput remoteInput = new RemoteInput.Builder(ChatDetailActivity.EXTRA_VOICE_REPLY)
                .setLabel("Reply")
                .setChoices(choices)
                .setChoices(choices)
                        //Set false if voice input option should be excluded
                .setAllowFreeFormInput(true)
                .build();

        PendingIntent replyPendingIntent = getConversationPendingIntent("Preppy Rabbit", 0);
        NotificationCompat.Action replyAction =
                new NotificationCompat.Action.Builder(R.drawable.ic_action_favorite, "Reply", replyPendingIntent)
                        .addRemoteInput(remoteInput)
                        .build();

        NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender()
                .addAction(replyAction);

        Bitmap prettyAvatar = getScaledLargeIconFromResource(R.drawable.apple55);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Ugly Rabbit")
                .setContentText("Hey Fox")
                .setSmallIcon(R.drawable.ic_action_good)
                .setContentIntent(getConversationPendingIntent("Pretty Rabbit", 20))
                .setPriority(Notification.PRIORITY_HIGH)
                .setDefaults(Notification.DEFAULT_ALL)
                .setLargeIcon(prettyAvatar)
                .extend(wearableExtender)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, notification);

    }

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
