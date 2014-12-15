package in.jaaga.mobileinfo;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BatteryFragment extends ListFragment {
	
	String[] desc = {"Battery Type","Battery Level","Battery Status","Voltage","Temperature"};
	String[] property = {};
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_network, container, false);
		
//		getActivity().registerReceiver(myBatteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
//		CommonAdapter adapter = new CommonAdapter(getActivity().getApplicationContext(),desc,property,"Battery");
//		setListAdapter(adapter);
		
		return rootView;
	}

//	BroadcastReceiver myBatteryReceiver = new BroadcastReceiver() {
//		
//		@Override
//		public void onReceive(Context context, Intent intent) {
//			// TODO Auto-generated method stub
//			if(intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)){
//				property[0] = String.valueOf(intent.getIntExtra("level", 0)) + "%";
//				property[1] = String.valueOf(intent.getIntExtra("level", 0)) + "%";
//				property[2] = String.valueOf(intent.getIntExtra("level", 0)) + "%";
//				property[3] = String.valueOf(intent.getIntExtra("level", 0)) + "%";
//				property[4] = String.valueOf(intent.getIntExtra("level", 0)) + "%";
//				
//			}
//		}
//	};
}
