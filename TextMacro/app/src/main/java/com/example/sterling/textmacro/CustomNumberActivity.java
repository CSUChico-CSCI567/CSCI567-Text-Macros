package com.example.sterling.textmacro;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sterling.textmacro.Objects.TextMacros;
import com.example.sterling.textmacro.R;
import com.orm.SugarContext;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomNumberActivity extends AppCompatActivity {

    private String phone_number;

    @Bind(R.id.negTxt)
    EditText negTxt;

    @Bind(R.id.posTxt)
    EditText posTxt;

    @Bind(R.id.number)
    EditText number;

    @Bind(R.id.phone)
    Button phone;

    @OnClick(R.id.insertBtn)
    public void loadCust(View v){
        TextMacros txt;
        List<TextMacros> txts = TextMacros.find(TextMacros.class, "phone_number = ?", phone_number);
        if (txts == null || txts.isEmpty()){
            txt = new TextMacros();
        }
        else {
            txt = txts.get(0);
        }
        txt.setDown(negTxt.getText().toString());
        txt.setUp(posTxt.getText().toString());
        txt.setPhoneNumber(phone_number);
        txt.save();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_number);
        ButterKnife.bind(this);
        SugarContext.init(this);

        phone_number = getIntent().getExtras().getString("phone");

        number.setText(phone_number);
        TextMacros txt;
        List<TextMacros> txts = TextMacros.find(TextMacros.class, "phone_number = ?", phone_number);
        if (txts != null && !txts.isEmpty()){
            txt = txts.get(0);
            negTxt.setText(txt.getDown());
            posTxt.setText(txt.getUp());
        }
        phone.setVisibility(View.GONE);
        number.setClickable(false);
        number.setFocusable(false);
    }

    @Override
    protected void onDestroy() {
        SugarContext.terminate();
        super.onDestroy();
    }
}
