package a.a.mobileinfo;

import android.app.usage.UsageStatsManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.UserManager;
import android.os.Vibrator;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.drive.UserMetadata;

import adapters.CommonAdapter;

/**
 * Created by Arpit on 24/04/15.
 */
public class SystemFragment extends ListFragment {

    String[] desc = {"OS Name", "OS Version", "OS Architecture", "System Class Path",
            "VM Name", "VM version", "VM Location", "VM Vendor", "VM Vendor's URL",
            "VM libraries name", "VM libraries version","Android Version", "Android API Level"};

    String[] property = {System.getProperty("os.name"), System.getProperty("os.version"), System.getProperty("os.arch"),
            System.getProperty("java.class.path"), System.getProperty("java.vm.name"), System.getProperty("java.vm.version"),
            System.getProperty("java.home"), System.getProperty("java.vendor"), System.getProperty("java.vendor.url"),
            System.getProperty("java.specification.name"), System.getProperty("java.specification.version"),
            Build.VERSION.RELEASE, Build.VERSION.SDK_INT + ""};

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_system,
                container, false);

        AdView mAdView = (AdView) rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        CommonAdapter adapter = new CommonAdapter(getActivity().getApplicationContext(), desc, property, "System");
        setListAdapter(adapter);

        return rootView;
    }
}
