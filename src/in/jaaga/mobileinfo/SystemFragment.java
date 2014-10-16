package in.jaaga.mobileinfo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SystemFragment extends ListFragment{

	String[] desc = {"OS Name","OS Version","OS Architecture","System Class Path",
			"VM Name","VM version","VM Location","VM Vendor","VM Vendor's URL",
			"VM libraries name","VM libraries version"};
	
	String[] property = {"os.name","os.version","os.arch","java.class.path",
			"java.vm.name","java.vm.version","java.home","java.vendor","java.vendor.url",
			"java.specification.name","java.specification.version"};
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_system,
				container, false);
		
		CommonAdapter adapter = new CommonAdapter(getActivity().getApplicationContext(),desc,property);
		setListAdapter(adapter);
		return rootView;
	}
}
