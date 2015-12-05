package a.a.mobileinfo;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import adapters.CommonAdapter;

/**
 * Created by arpit on 30/11/15.
 */
public class DeviceFragment extends ListFragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list,
                container, false);

        AdView mAdView = (AdView) rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        WifiManager wm = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wf = wm.getConnectionInfo();

        TelephonyManager tm = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);

        String[] desc = {"Device Brand", "Device Model", "Device Code Name", "Device ID (IMEI)", "Wifi MAC Address", "Device Hardware",
                         "Device Serial Number", "Device Build ID","Overall Device Name",  "Device Manufacturer", "Device BootLoader", "Device Board"};
        String[] property = {Build.BRAND, Build.MODEL, Build.DEVICE, tm.getDeviceId(), wf.getMacAddress(), Build.HARDWARE,
                            Build.SERIAL, Build.DISPLAY, Build.PRODUCT,  Build.MANUFACTURER, Build.BOOTLOADER, Build.BOARD};

        CommonAdapter adapter = new CommonAdapter(getActivity().getApplicationContext(),desc,property,"Device");
        setListAdapter(adapter);


        return rootView;
    }
}
