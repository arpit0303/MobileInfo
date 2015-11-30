package a.a.mobileinfo;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import adapters.CommonAdapter;

/**
 * Created by Arpit on 24/04/15.
 */
public class SystemFragment extends ListFragment {

    String[] desc = {"OS Name", "OS Version", "OS Architecture", "System Class Path",
            "VM Name", "VM version", "VM Location", "VM Vendor", "VM Vendor's URL",
            "VM libraries name", "VM libraries version"};

    String[] property = {"os.name", "os.version", "os.arch", "java.class.path",
            "java.vm.name", "java.vm.version", "java.home", "java.vendor", "java.vendor.url",
            "java.specification.name", "java.specification.version"};

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
