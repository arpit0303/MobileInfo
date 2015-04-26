package a.a.mobileinfo;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import adapters.CommonAdapter;

/**
 * Created by Arpit on 24/04/15.
 */
public class DisplayFragment extends ListFragment {

    String[] desc = {"Screen Width","Screen Height","Screen Density","Density Type"};
    String[] property = new String[5];
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main,
                container, false);

        AdView mAdView = (AdView) rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        property[0] = dm.widthPixels + " Pixels";
        property[1] = dm.heightPixels + " Pixels";
        property[2] = dm.densityDpi + " dpi";

        switch (dm.densityDpi) {
            case 120:
                property[3] = "ldpi";
                break;
            case 160:
                property[3] = "mdpi";
                break;
            case 240:
                property[3] = "hdpi";
                break;
            case 320:
                property[3] = "xhdpi";
                break;
            case 480:
                property[3] = "xxhdpi";
                break;
            case 640:
                property[3] = "xxxhdpi";
                break;
            case 213:
                property[3] = "tvdpi";
                break;

            default:
                property[3] = "nodpi";
                break;
        }


        CommonAdapter adapter = new CommonAdapter(getActivity().getApplicationContext(),desc,property,"Screen");
        setListAdapter(adapter);

        return rootView;
    }

}
