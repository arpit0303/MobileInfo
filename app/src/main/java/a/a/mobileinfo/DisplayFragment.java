package a.a.mobileinfo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import adapters.CommonAdapter;

/**
 * Created by Arpit on 24/04/15.
 */
public class DisplayFragment extends ListFragment {

    String[] desc = {"Display Name","Display Width","Display Height","Display Density","Density Type","Display fps"};
    String[] property = new String[6];
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list,
                container, false);

        AdView mAdView = (AdView) rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        WindowManager am = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display ds = am.getDefaultDisplay();
        property[0] = String.valueOf(ds.getName().toString());

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        property[1] = dm.widthPixels + " Pixels";
        property[2] = dm.heightPixels + " Pixels";
        property[3] = dm.densityDpi + " dpi";

        switch (dm.densityDpi) {
            case 120:
                property[4] = "ldpi";
                break;
            case 160:
                property[4] = "mdpi";
                break;
            case 240:
                property[4] = "hdpi";
                break;
            case 320:
                property[4] = "xhdpi";
                break;
            case 480:
                property[4] = "xxhdpi";
                break;
            case 640:
                property[4] = "xxxhdpi";
                break;
            case 213:
                property[4] = "tvdpi";
                break;

            default:
                property[4] = "nodpi";
                break;
        }

        property[5] = String.valueOf(ds.getRefreshRate());

        CommonAdapter adapter = new CommonAdapter(getActivity().getApplicationContext(),desc,property,"Screen");
        setListAdapter(adapter);

        return rootView;
    }

}
