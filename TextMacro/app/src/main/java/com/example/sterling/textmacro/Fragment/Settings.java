package com.example.sterling.textmacro.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.sterling.textmacro.Objects.TextMacros;
import com.example.sterling.textmacro.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Settings.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Settings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Settings extends Fragment {

    @Bind(R.id.posTxt)
    EditText posTxt;

    @Bind(R.id.negTxt)
    EditText negTxt;

    @OnClick(R.id.saveButton)
    public void save(View v){
        TextMacros txtM = TextMacros.findById(TextMacros.class, 1);
        if(txtM == null){
            txtM = new TextMacros();
        }
        txtM.setUp(posTxt.getText().toString());
        txtM.setDown(negTxt.getText().toString());
        txtM.save();
    }
    
    public Settings() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
