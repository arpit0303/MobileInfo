package in.jaaga.mobileinfo;

import java.io.IOException;
import java.io.InputStream;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CPUFragment extends Fragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_cpu, container, false);
		
		AdView mAdView = (AdView) rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        
		TextView cpu = (TextView) rootView.findViewById(R.id.textView1);
		cpu.setText(ReadCPUinfo());
		
		return rootView;
		
	}
	
	private String ReadCPUinfo(){
		  ProcessBuilder cmd;
		  String result = "";
		  
		  try{
			   String[] args = {"/system/bin/cat", "/proc/cpuinfo"};
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
