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
import android.widget.Toast;

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
        intent.putExtra("phone", number.getText().toString());
        startActivity(intent);
    }

    @OnClick(R.id.insertBtn)
    public void insert(View v){
        TextMacros txt = TextMacros.findById(TextMacros.class, 1);
        if(txt == null){
            txt = new TextMacros();
        }
        if(posTxt.getText().toString().equals("") || negTxt.getText().toString().equals("")){
            Toast.makeText(this, "Make Both Macros Not Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        txt.setUp(posTxt.getText().toString());
        txt.setDown(negTxt.getText().toString());
        txt.save();
        Toast.makeText(this, "Saved Macros", Toast.LENGTH_SHORT).show();
        this.finish();
    }
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SugarContext.init(this);
        ButterKnife.bind(this);

        TextMacros txt = TextMacros.findById(TextMacros.class, 1);
        if(txt != null){
            negTxt.setText(txt.getDown());
            posTxt.setText(txt.getUp());
        }
        //Show current saved if exist
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS, Manifest.permission.SEND_SMS}, 1337);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}