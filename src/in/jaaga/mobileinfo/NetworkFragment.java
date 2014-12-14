package in.jaaga.mobileinfo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NetworkFragment extends ListFragment{
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_network,
				container, false);
		
		ConnectivityManager cm = (ConnectivityManager) getActivity().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo nf = cm.getActiveNetworkInfo();
		
		if(nf != null && nf.isConnected()){
			String roaming;
			if(nf.isRoaming()){
				roaming = "In Roaming";
			}
			else{
				roaming = "Not in Roaming";
			}
			String[] desc = {"Network Type","Network Name","Network State","Roaming State"};
			String[] property = {nf.getTypeName(), nf.getExtraInfo(), nf.getState().name(), roaming};
			CommonAdapter adapter = new CommonAdapter(getActivity().getApplicationContext(),desc,property,"Network");
			setListAdapter(adapter);
		}
		else{
			String[] desc = {"Network State"};
			String[] property = {"Network Not Connected\n OR \nTurn On Data Services"};
			CommonAdapter adapter = new CommonAdapter(getActivity().getApplicationContext(),desc,property,"Network");
			setListAdapter(adapter);
		}
		
		return rootView;
	}

}
