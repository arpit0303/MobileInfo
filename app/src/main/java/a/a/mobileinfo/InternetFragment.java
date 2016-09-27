package a.a.mobileinfo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.unity3d.ads.android.UnityAds;

import java.lang.reflect.Method;

import adapters.CommonAdapter;

/**
 * Created by Arpit on 24/04/15.
 */
public class InternetFragment extends ListFragment implements CompoundButton.OnCheckedChangeListener {

    Switch mobileSwitch, wifiSwitch;
    NetworkInfo nf;
    WifiInfo wf;
    WifiManager wm;
    ConnectivityManager cm;
    SwipeRefreshLayout swipeRefreshLayout;

    private String TAG = "InternetFragment";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_internet,
                container, false);
//        mobileSwitch = (Switch) rootView.findViewById(R.id.mobile_switch);
        wifiSwitch = (Switch) rootView.findViewById(R.id.wifi_switch);

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                connectionDetails();
            }
        });

        wm = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
        cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

//        mobileSwitch.setOnCheckedChangeListener(this);
        wifiSwitch.setOnCheckedChangeListener(this);

        try {
            if(UnityAds.canShow()){
                UnityAds.show();
            }
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }

        connectionDetails();

        return rootView;
    }

    private void connectionDetails() {

        wifiSwitch.setChecked(wm.isWifiEnabled());
//        mobileSwitch.setEnabled(checkMobileData());

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }

        nf = cm.getActiveNetworkInfo();
        wf = wm.getConnectionInfo();


        if (nf != null && nf.isConnected()) {
            if (nf.getType() == ConnectivityManager.TYPE_WIFI) {

                float wifiFrequency = 0;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    wifiFrequency = (wf.getFrequency() / 1000);
                }
                String wifiIpAddress = Formatter.formatIpAddress(wf.getIpAddress());

                String[] desc = {"Internet Type", "Internet Name", "Internet State",
                        "Wifi Speed", "Wifi Frequency", "Wifi BSSID", "Wifi Ip Address", "Wifi Network ID"};
                String[] property = {nf.getTypeName(), nf.getExtraInfo(), nf.getState().name(),
                        wf.getLinkSpeed() + " Mbps", wifiFrequency + " GHz", wf.getBSSID(), wifiIpAddress, wf.getNetworkId() + ""};

                CommonAdapter adapter = new CommonAdapter(getActivity(), desc, property, "Network");
                setListAdapter(adapter);
            } else if (nf.getType() == ConnectivityManager.TYPE_MOBILE) {
                String roaming;
                if (nf.isRoaming()) {
                    roaming = "In Roaming";
                } else {
                    roaming = "Not in Roaming";
                }

                String[] desc = {"Internet Type", "Internet Name", "Internet State", "Roaming State"};
                String[] property = {nf.getTypeName(), nf.getExtraInfo(), nf.getState().name(), roaming};

                CommonAdapter adapter = new CommonAdapter(getActivity(), desc, property, "Network");
                setListAdapter(adapter);
            } else {
                String[] desc = {"Internet Type", "Internet Name", "Internet State"};
                String[] property = {nf.getTypeName(), nf.getExtraInfo(), nf.getState().name()};

                CommonAdapter adapter = new CommonAdapter(getActivity(), desc, property, "Network");
                setListAdapter(adapter);
            }
        } else {
            String[] desc = {"Network State"};
            String[] property = {"Network Not Connected\n OR \nTurn On Data Services"};
            CommonAdapter adapter = new CommonAdapter(getActivity(), desc, property, "Network");
            setListAdapter(adapter);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
//            case R.id.mobile_switch:
//                if (b) {
//                    setMobileData(true);
//                } else {
//                    setMobileData(false);
//                }
//                connectionDetails();
//                break;
            case R.id.wifi_switch:
                if (b) {
                    wm.setWifiEnabled(true);
                } else {
                    wm.setWifiEnabled(false);
                }
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        connectionDetails();
                    }
                }, 5000);
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        connectionDetails();
    }

    public void setMobileData(boolean ON) {
        try {
            TelephonyManager telephonyService = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);

            Method setMobileDataEnabledMethod = telephonyService.getClass().getDeclaredMethod("setDataEnabled", boolean.class);

            if (null != setMobileDataEnabledMethod)
            {
                setMobileDataEnabledMethod.invoke(telephonyService, ON);
            }

//            Class cmClass = Class.forName(cm.getClass().getName());
//            Method method = cmClass.getDeclaredMethod("setMobileDataEnabled");
//            method.setAccessible(true); // Make the method callable
//            // get the setting for "mobile data"
//            method.invoke(cm, ON);
        } catch (Exception e) {
            Log.e(TAG, "error turning on/off data");
        }
    }

    public boolean checkMobileData() {
        try {
            Class cmClass = Class.forName(cm.getClass().getName());
            Method method = cmClass.getDeclaredMethod("getMobileDataEnabled");
            method.setAccessible(true); // Make the method callable
            // get the setting for "mobile data"
            boolean mobileDataEnabled = (Boolean) method.invoke(cm);
            return mobileDataEnabled;
        } catch (Exception e) {
            // Some problem accessible private API
            return false;
        }
    }

    private void enforceModifyPermission() {
               //mApp.enforceCallingOrSelfPermission(android.Manifest.permission.MODIFY_PHONE_STATE, null);
    }

}
