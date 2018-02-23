package com.txbdc.upperlimbathomerehabilitationsystem;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListDevices extends AppCompatActivity {
    private ListView btDevices;
    private ArrayList<String> nearbyDevices=new ArrayList<String>();
    private BluetoothAdapter btFinder;
    private Button cancel;
    private Button select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_devices);

        BindViews();
        btFinder = BluetoothAdapter.getDefaultAdapter();
        btFinder.startDiscovery();

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);

    }

    private void BindViews() {
        btDevices=findViewById(R.id.available_bt_devices);
        cancel=findViewById(R.id.cancel_bt_connection_btn);
        select=findViewById(R.id.select_bt_connection_btn);

    }


    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent
                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                nearbyDevices.add(device.getName() + "\n" + device.getAddress());
                Log.i("BT", device.getName() + "\n" + device.getAddress());
                btDevices.setAdapter(new ArrayAdapter<String>(context,
                        android.R.layout.simple_list_item_1, nearbyDevices));
            }
        }
    };
}
