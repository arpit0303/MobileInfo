package a.a.mobileinfo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import adapters.CommonAdapter;

/**
 * Created by arpit on 30/11/15.
 */
public class SimFragment extends ListFragment {

    SwipeRefreshLayout swipeRefreshLayout;
    TelephonyManager tm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sim,
                container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                simDetails();
            }
        });

        tm  = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);

        simDetails();

        return rootView;
    }

    public void simDetails() {
        if(swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }

        String mobileNumber = "";
        if(tm.getLine1Number() == null){
            mobileNumber = "NULL";
        }
        else{
            mobileNumber = tm.getLine1Number();
        }

        String[] desc = {"Sim Serial Number", "Sim Operator Name", "Sim Country ISO", "Subscriber ID","Network Operator", "Mobile Number"};
        String[] property = {tm.getSimSerialNumber(), tm.getSimOperatorName(),
                            tm.getSimCountryIso(), tm.getSubscriberId(), tm.getNetworkOperator(), mobileNumber};

        CommonAdapter adapter = new CommonAdapter(getActivity().getApplicationContext(),desc,property,"Sim");
        setListAdapter(adapter);
    }
}
