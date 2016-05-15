package com.example.sterling.textmacro;

import android.Manifest;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, 123);
        Intent intent = new Intent(this, SmsActivity.class);
        startActivity(intent);
    }

}