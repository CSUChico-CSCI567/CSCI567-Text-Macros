package com.example.sterling.textmacro.Objects;

import com.orm.SugarRecord;

/**
 * Created by Nanaka on 5/15/16.
 */
public class TextMacros extends SugarRecord{

    String phoneNumber = ""; //For number specific if want to implement
    String up = "";
    String down = "";

    public String getUp() {
        return up;
    }

    public void setUp(String up) {
        this.up = up;
    }

    public String getDown() {
        return down;
    }

    public void setDown(String down) {
        this.down = down;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
