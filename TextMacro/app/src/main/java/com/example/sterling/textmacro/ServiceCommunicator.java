package com.example.sterling.textmacro;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.telephony.SmsMessage;

import java.util.Random;

public class ServiceCommunicator extends Service {
    private SmsBroadcastReceiver mSMSreceiver;
    private IntentFilter mIntentFilter;
    private boolean receiverAlive = false;

    /**
     * Class for clients to access.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with
     * IPC.
     */
    public class LocalBinder extends Binder {
        ServiceCommunicator getService() {
            return ServiceCommunicator.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    // This is the object that receives interactions from clients.  See
    // RemoteService for a more complete example.
    private final IBinder mBinder = new LocalBinder();

    @Override
    public void onCreate() {
        super.onCreate();

        //SMS event receiver
        mSMSreceiver = new SmsBroadcastReceiver();
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(mSMSreceiver, mIntentFilter);
    }


    public static boolean isRunning = false;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        isRunning = true;
        return START_STICKY;
    }


    public class SmsBroadcastReceiver extends BroadcastReceiver {

        public static final String SMS_BUNDLE = "pdus";

        public void sendNotification(Context context, String address, String smsBody){

            Random generator = new Random();

            Intent intentOne = new Intent(context,SmsActivity.class);
            Intent intentTwo = new Intent(context,SmsActivity.class);
            Intent intentThree = new Intent(context,SmsActivity.class);
            PendingIntent i1 = PendingIntent.getActivity(context, generator.nextInt(), intentOne, PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent i2 = PendingIntent.getActivity(context, generator.nextInt(), intentTwo, PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent i3 = PendingIntent.getActivity(context, generator.nextInt(), intentThree, PendingIntent.FLAG_UPDATE_CURRENT);

            intentOne.putExtra("message", "Of Course I still Love You");
            intentTwo.putExtra("message", "Never Talk To Strangers");
            intentThree.putExtra("message", "No More Mr. Nice Guy");
            intentOne.putExtra("address", address);
            intentTwo.putExtra("address", address);
            intentThree.putExtra("address", address);

            NotificationCompat.Builder mBuilder =
                    (NotificationCompat.Builder) new NotificationCompat.Builder(context.getApplicationContext())
                            .setSmallIcon(R.drawable.messages)
                            .setContentTitle(address)
                            .setContentText(smsBody)
                            .addAction(new android.support.v4.app.NotificationCompat.Action(R.drawable.heart, "", i1))
                            .addAction(new android.support.v4.app.NotificationCompat.Action(R.drawable.thumbs_up, "", i2))
                            .addAction(new android.support.v4.app.NotificationCompat.Action(R.drawable.thumbs_down, "", i3));


            //.setContentIntent(i);
            int mNotificationId = 001;
            // Gets an instance of the NotificationManager service
            NotificationManager mNotifyMgr = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
            // Builds the notification and issues it.
            //            mNotifyMgr.notify(mNotificationId, mBuilder.build());
            mNotifyMgr.notify(mNotificationId, mBuilder.build());
        }

        public void onReceive(Context context, Intent intent) {
            Bundle intentExtras = intent.getExtras();
            if (intentExtras != null) {
                Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
                String smsBody = "";
                String address = "";
                for (int i = 0; i < sms.length; ++i) {
                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);
                    smsBody = smsMessage.getMessageBody().toString();
                    address = smsMessage.getOriginatingAddress();
                }
                sendNotification(context, address, smsBody);

            }
        }
    }
}