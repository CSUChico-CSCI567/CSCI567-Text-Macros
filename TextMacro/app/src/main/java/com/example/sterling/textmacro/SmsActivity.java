package com.example.sterling.textmacro;

/**
 * Created by sterling on 5/15/16.
 */
import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SmsActivity extends Activity/* implements OnItemClickListener*/ {

    private static SmsActivity inst;
//    ArrayList<String> smsMessagesList = new ArrayList<String>();
//    ListView smsListView;
//    ArrayAdapter arrayAdapter;

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
        //setContentView(R.layout.activity_sms);
        Intent i = getIntent();
        String address = i.getStringExtra("address");
        String msg = i.getStringExtra("message");
        try{
            SmsManager mgr = SmsManager.getDefault();
            mgr.sendTextMessage(address, null, msg, null, null);
        } catch(Exception e) {
            Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
//        smsListView = (ListView) findViewById(R.id.SMSList);
//        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, smsMessagesList);
//        smsListView.setAdapter(arrayAdapter);
//        smsListView.setOnItemClickListener(this);

        //refreshSmsInbox();
    }

//    public void refreshSmsInbox() {
//        ContentResolver contentResolver = getContentResolver();
//        Cursor smsInboxCursor = contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null);
//        int indexBody = smsInboxCursor.getColumnIndex("body");
//        int indexAddress = smsInboxCursor.getColumnIndex("address");
//        if (indexBody < 0 || !smsInboxCursor.moveToFirst()) return;
//        arrayAdapter.clear();
//        do {
//            String str = "SMS From: " + smsInboxCursor.getString(indexAddress) +
//                    "\n" + smsInboxCursor.getString(indexBody) + "\n";
//            arrayAdapter.add(str);
//        } while (smsInboxCursor.moveToNext());
//    }
//
//    public void updateList(final String smsMessage) {
//        arrayAdapter.insert(smsMessage, 0);
//        arrayAdapter.notifyDataSetChanged();
//    }
//
//    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
//        try {
//            String[] smsMessages = smsMessagesList.get(pos).split("\n");
//            String address = smsMessages[0];
//            String smsMessage = "";
//            for (int i = 1; i < smsMessages.length; ++i) {
//                smsMessage += smsMessages[i];
//            }
//
//            String smsMessageStr = address + "\n";
//            smsMessageStr += smsMessage;
//            Toast.makeText(this, smsMessageStr, Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
