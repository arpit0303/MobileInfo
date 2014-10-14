package in.jaaga.mobileinfo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SystemFragment extends Fragment{

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_system,
				container, false);
		
		//TextView Sys = (TextView) rootView.findViewById(R.id.sys);
		//Sys.setText(ReadSYSinfo());
		return rootView;
	}

	private static StringBuffer SYSinfoBuffer;

	 private String ReadSYSinfo()
	 {
	  SYSinfoBuffer = new StringBuffer();
	  
	  getProperty("os.name", "os.name", SYSinfoBuffer);
	  getProperty("os.version", "os.version", SYSinfoBuffer);
	  
	  getProperty("java.vendor.url", "java.vendor.url", SYSinfoBuffer);
	  getProperty("java.version", "java.version", SYSinfoBuffer);
	  getProperty("java.class.path", "java.class.path", SYSinfoBuffer);
	  getProperty("java.class.version", "java.class.version", SYSinfoBuffer);
	  getProperty("java.vendor", "java.vendor", SYSinfoBuffer);
	  getProperty("java.home", "java.home", SYSinfoBuffer);
	  
	  getProperty("user.name", "user.name", SYSinfoBuffer);
	  getProperty("user.home", "user.home", SYSinfoBuffer);
	  getProperty("user.dir", "user.dir", SYSinfoBuffer);
	  
	  return SYSinfoBuffer.toString();
	 }

	 private void getProperty(String desc, String property, StringBuffer tBuffer)
	 {
	  tBuffer.append(desc);
	  tBuffer.append(" : ");
	  tBuffer.append(System.getProperty(property));
	  tBuffer.append("\n");
	 }
}
