package com.example.sterling.textmacro;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.example.sterling.textmacro.Objects.TextMacros;
import com.example.sterling.textmacro.R;
import com.orm.SugarContext;

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

    @OnClick(R.id.insertBtn)
    public void loadCust(View v){
        TextMacros txt = TextMacros.find(TextMacros.class, "phone_number = ?", phone_number).get(0);
        if (txt == null){
            txt = new TextMacros();
        }
        txt.setDown(negTxt.getText().toString());
        txt.setUp(posTxt.getText().toString());
        txt.setPhoneNumber(phone_number);
        txt.save();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_number);
        ButterKnife.bind(this);
        SugarContext.init(this);

        phone_number = "000-000-0000";

        number.setText(phone_number);
        number.setClickable(false);
        number.setFocusable(false);

        //service shit maybe here as well.
    }

    @Override
    protected void onDestroy() {
        SugarContext.terminate();
        super.onDestroy();
    }
}
