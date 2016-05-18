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
import android.widget.EditText;

import com.example.sterling.textmacro.Objects.TextMacros;
import com.orm.SugarContext;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @Bind(R.id.negTxt)
    EditText negTxt;

    @Bind(R.id.posTxt)
    EditText posTxt;

    @Bind(R.id.number)
    EditText number;

    @OnClick(R.id.phone)
    public void loadNumber(View v){
        Intent intent = new Intent(this, CustomNumberActivity.class);
        intent.putExtra("phone", number.getText());
        startActivity(intent);
    }

    @OnClick(R.id.insertBtn)
    public void insert(View v){
        TextMacros txt = TextMacros.findById(TextMacros.class, 1);
        if(txt == null){
            txt = new TextMacros();
        }
        txt.setUp(posTxt.getText().toString());
        txt.setDown(negTxt.getText().toString());
        txt.save();
        Intent intent = new Intent(this, SmsActivity.class);
        startActivity(intent);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SugarContext.init(this);
        ButterKnife.bind(this);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, 123);
    }

    @Override
    protected void onDestroy() {
        SugarContext.terminate();
        super.onDestroy();
    }
}