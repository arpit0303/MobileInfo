package a.a.mobileinfo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import adapters.CommonAdapter;

/**
 * Created by Arpit on 24/04/15.
 */
public class NetworkFragment extends ListFragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list,
                container, false);

        AdView mAdView = (AdView) rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        connectionDetails();

        return rootView;
    }

    private void connectionDetails() {

        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf = cm.getActiveNetworkInfo();

        if(nf != null && nf.isConnected()){
            if(nf.getType() == ConnectivityManager.TYPE_MOBILE) {
                String roaming;
                if (nf.isRoaming()) {
                    roaming = "In Roaming";
                } else {
                    roaming = "Not in Roaming";
                }

                String[] desc = {"Internet Type","Internet Name","Internet State","Roaming State"};
                String[] property = {nf.getTypeName(), nf.getExtraInfo(), nf.getState().name(), roaming};

                CommonAdapter adapter = new CommonAdapter(getActivity().getApplicationContext(),desc,property,"Network");
                setListAdapter(adapter);
            }
            else if(nf.getType() == ConnectivityManager.TYPE_WIFI){
                WifiManager wm = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
                WifiInfo wf = wm.getConnectionInfo();

                float wifiFrequency = (wf.getFrequency()/1000) ;
                String wifiIpAddress = Formatter.formatIpAddress(wf.getIpAddress());

                String[] desc = {"Internet Type","Internet Name","Internet State",
                        "Wifi Speed", "Wifi Frequency", "Wifi BSSID", "Wifi Ip Address", "Wifi Network ID"};
                String[] property = {nf.getTypeName(), nf.getExtraInfo(), nf.getState().name(),
                        wf.getLinkSpeed() + " Mbps", wifiFrequency + " GHz", wf.getBSSID(), wifiIpAddress, wf.getNetworkId()+""};

                CommonAdapter adapter = new CommonAdapter(getActivity().getApplicationContext(),desc,property,"Network");
                setListAdapter(adapter);
            }
            else{
                String[] desc = {"Internet Type","Internet Name","Internet State"};
                String[] property = {nf.getTypeName(), nf.getExtraInfo(), nf.getState().name()};

                CommonAdapter adapter = new CommonAdapter(getActivity().getApplicationContext(),desc,property,"Network");
                setListAdapter(adapter);
            }
        }
        else{
            String[] desc = {"Network State"};
            String[] property = {"Network Not Connected\n OR \nTurn On Data Services"};
            CommonAdapter adapter = new CommonAdapter(getActivity().getApplicationContext(),desc,property,"Network");
            setListAdapter(adapter);
        }
    }

}
