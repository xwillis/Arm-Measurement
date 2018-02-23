package com.txbdc.upperlimbathomerehabilitationsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ConnectDevices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_devices);
    }

    public void goToSelectBT(View view) {
        //when this method is run, tell it which screen to switch to
        Intent intent=new Intent(this, ListDevices.class);
        //switch screen
        startActivity(intent);
    }
}
