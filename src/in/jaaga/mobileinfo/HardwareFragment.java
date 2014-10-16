package in.jaaga.mobileinfo;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HardwareFragment extends ListFragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_hardware,
				container, false);
		
		//CommonAdapter adapter = new CommonAdapter(getActivity().getApplicationContext(),desc,property);
		//setListAdapter(adapter);
		return rootView;
	}
}
