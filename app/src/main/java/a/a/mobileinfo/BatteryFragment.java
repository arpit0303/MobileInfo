package a.a.mobileinfo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import adapters.CommonAdapter;

/**
 * Created by Arpit on 25/04/15.
 */
public class BatteryFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        readBattery();

        return rootView;
    }

    private void readBattery() {

        BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
                boolean present = intent.getExtras().getBoolean(BatteryManager.EXTRA_PRESENT);
                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0);
                String technology = intent.getExtras().getString(BatteryManager.EXTRA_TECHNOLOGY);
                float temperature = ((float) intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0)) / 10;
                float voltage = ((float) intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0)) / 1000;

                String healthText = "";
                if (health == 1) {
                    healthText = "Unknown";
                } else if (health == 2) {
                    healthText = "Good";
                } else if (health == 3) {
                    healthText = "Over Heat";
                } else if (health == 4) {
                    healthText = "Dead";
                } else if (health == 5) {
                    healthText = "Over Voltage";
                } else if (health == 6) {
                    healthText = "Unspecified Failure";
                } else if (health == 7) {
                    healthText = "Cold";
                } else {
                    healthText = "Unknown";
                }

                String pluggedText = "";
                if (plugged == 1) {
                    pluggedText = "AC";
                } else if (plugged == 2) {
                    pluggedText = "USB";
                } else if (plugged == 4) {
                    pluggedText = "Wireless";
                } else if (plugged == 0) {
                    pluggedText = "Not Plugged";
                } else {
                    pluggedText = "Unknown";
                }

                String presentText = "";
                if (present) {
                    presentText = "YES";
                } else {
                    presentText = "NO";
                }

                String statusText = "";
                if (status == 1) {
                    statusText = "Unknown";
                } else if (status == 2) {
                    statusText = "Charging";
                } else if (status == 3) {
                    statusText = "Discharging";
                } else if (status == 4) {
                    statusText = "Not Charging";
                } else if (status == 5) {
                    statusText = "Full";
                } else {
                    statusText = "Unknown";
                }

                String[] desc = {"Health", "Battery Level", "Device Plugged", "Battery Present", "Battery Status",
                        "Battery Technology", "Battery \nTemperature", "Battery Voltage"};

                String[] property = {healthText, level + "%", pluggedText, presentText, statusText,
                        technology, temperature + " Degrees Celcius", voltage + " Volt"};

                CommonAdapter adapter = new CommonAdapter(getActivity().getApplicationContext(), desc, property, "System");
                setListAdapter(adapter);
            }
        };
        getActivity().registerReceiver(batteryInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }
}
