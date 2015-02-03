package in.jaaga.mobileinfo;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class SystemFragment extends ListFragment{

	String[] desc = {"OS Name","OS Version","OS Architecture","System Class Path",
			"VM Name","VM version","VM Location","VM Vendor","VM Vendor's URL",
			"VM libraries name","VM libraries version"};
	
	String[] property = {"os.name","os.version","os.arch","java.class.path",
			"java.vm.name","java.vm.version","java.home","java.vendor","java.vendor.url",
			"java.specification.name","java.specification.version"};
	TextToSpeech tts;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_system,
				container, false);
		
		AdView mAdView = (AdView) rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
		
		CommonAdapter adapter = new CommonAdapter(getActivity().getApplicationContext(),desc,property,"System");
		setListAdapter(adapter);
		
		return rootView;
	}
	
//	@Override
//	public void onListItemClick(ListView l, View v, final int position, long id) {
//		// TODO Auto-generated method stub
//		super.onListItemClick(l, v, position, id);
//		tts = new TextToSpeech(getActivity(), new OnInitListener() {
//			
//			@Override
//			public void onInit(int status) {
//				// TODO Auto-generated method stub
//				if(status == TextToSpeech.SUCCESS){
//					String mText = desc[position]  + " " + System.getProperty(property[position]);
//					tts.setSpeechRate((float) 0.8);
//					tts.speak(mText, TextToSpeech.QUEUE_FLUSH, null);
//				}
//			}
//		});
//	}
}
