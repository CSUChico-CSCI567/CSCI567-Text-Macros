package com.example.sterling.textmacro;

/**
 * Created by sterling on 5/15/16.
 */
import android.Manifest;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SmsActivity extends Activity{

    private static SmsActivity inst;

    public static SmsActivity instance() {
        return inst;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);


        Intent i = getIntent();
        String address = i.getStringExtra("address");
        String smsBody = i.getStringExtra("message");
        int id = i.getExtras().getInt("id");


        Log.i("sms", "id " + id);
        Log.i("sms", "address " + address);
        Log.i("sms", "message " + smsBody);

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(id);

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(address, null, smsBody, null, null);
    }

}
