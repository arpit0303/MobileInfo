package a.a.mobileinfo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Arpit on 24/04/15.
 */
public class MemoryFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_a, container, false);

        AdView mAdView = (AdView) rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

//        ActivityManager.MemoryInfo mf = new ActivityManager.MemoryInfo();
//        ActivityManager am = (ActivityManager) getActivity().getSystemService(getActivity().ACTIVITY_SERVICE);
//        am.getMemoryInfo(mf);
//        float availableMemory = mf.availMem / (1024*1024);
//        float totalMemory = mf.totalMem / (1024*1024);
//
//        String[] desc = {"Total Memory","Available Memory"};
//        String[] property = {totalMemory + " GB", availableMemory + " GB"};
//        CommonAdapter adapter = new CommonAdapter(getActivity().getApplicationContext(),desc,property,"Memory");
//        setListAdapter(adapter);

        TextView info = (TextView) rootView.findViewById(R.id.info);
        info.setText(ReadMemoryinfo());

        return rootView;

    }

    private String ReadMemoryinfo(){
        ProcessBuilder cmd;
        String result = "";

        try{
            String[] args = {"/system/bin/cat", "/proc/meminfo"};
            //version, cpuinfo,meminfo
            cmd = new ProcessBuilder(args);

            Process process = cmd.start();

            InputStream in = process.getInputStream();
            byte[] re = new byte[1024];
            while(in.read(re) != -1){
                result = result + new String(re);
            }
            in.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        return result;
    }
}
