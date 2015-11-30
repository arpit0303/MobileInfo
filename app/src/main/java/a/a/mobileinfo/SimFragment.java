package a.a.mobileinfo;

import android.content.Context;
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
public class SimFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list,
                container, false);

        AdView mAdView = (AdView) rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        simDetails();

        return rootView;
    }

    private void simDetails() {
        TelephonyManager tm = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);

        String[] desc = {"Sim Serial Number", "Sim Operator Name", "Sim Country ISO", "Subscriber ID","Network Operator"};
        String[] property = {tm.getSimSerialNumber(), tm.getSimOperatorName(),
                            tm.getSimCountryIso(), tm.getSubscriberId(), tm.getNetworkOperator()};

        CommonAdapter adapter = new CommonAdapter(getActivity().getApplicationContext(),desc,property,"Sim");
        setListAdapter(adapter);
    }
}
