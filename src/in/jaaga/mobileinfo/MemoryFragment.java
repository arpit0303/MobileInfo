package in.jaaga.mobileinfo;

import java.io.IOException;
import java.io.InputStream;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MemoryFragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_cpu, container, false);
		
		MemoryInfo mf = new MemoryInfo();
		ActivityManager am = (ActivityManager) getActivity().getSystemService(getActivity().ACTIVITY_SERVICE);
		am.getMemoryInfo(mf);
		long availablemem = mf.totalMem / (1024*1024);
		Log.i("a", availablemem+" GB");

		TextView cpu = (TextView) rootView.findViewById(R.id.textView1);
		cpu.setText(ReadCPUinfo());
		
		return rootView;
		
	}
	
	private String ReadCPUinfo(){
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

