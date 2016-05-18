package com.example.sterling.textmacro;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.telephony.SmsMessage;

import java.util.Random;

/**
 * Created by sterling on 5/15/16.
 */
public class SmsBroadcastReceiver extends BroadcastReceiver {

    public static final String SMS_BUNDLE = "pdus";

    public void onReceive(Context context, Intent intent) {
        Bundle intentExtras = intent.getExtras();
        if (intentExtras != null) {
            Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
            String smsMessageStr = "";
            String smsBody = "";
            String address = "";
            for (int i = 0; i < sms.length; ++i) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);

                smsBody = smsMessage.getMessageBody().toString();
                address = smsMessage.getOriginatingAddress();

                smsMessageStr += "YOU GOTS SOME MESSAGES BRO: " + address + "\n";
                smsMessageStr += smsBody + "\n";
            }

            Random generator = new Random();
            Intent intent2 = new Intent(context,SmsActivity.class);
            PendingIntent i = PendingIntent.getActivity(context, generator.nextInt(), intent2, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder mBuilder =
                    (NotificationCompat.Builder) new NotificationCompat.Builder(context.getApplicationContext())
                            .setSmallIcon(R.drawable.ic_action_name)
                            .setContentTitle(address)
                            .setContentText(smsBody)
                            .addAction(new android.support.v4.app.NotificationCompat.Action(R.drawable.ic_action_name, "", i))
                            .addAction(new android.support.v4.app.NotificationCompat.Action(R.drawable.ic_action_name, "", i))
                            .addAction(new android.support.v4.app.NotificationCompat.Action(R.drawable.ic_action_name, "", i))
                            .addAction(new android.support.v4.app.NotificationCompat.Action(R.drawable.ic_action_name, "", i));
            //.setContentIntent(i);
            int mNotificationId = 001;
// Gets an instance of the NotificationManager service
            NotificationManager mNotifyMgr = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
// Builds the notification and issues it.
            mNotifyMgr.notify(mNotificationId, mBuilder.build());

            //this will update the UI with message
            SmsActivity inst = SmsActivity.instance();
            inst.updateList(smsMessageStr);
        }
    }
}