package a.a.mobileinfo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Arpit on 24/04/15.
 */
public class CPUFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_text, container, false);

        TextView cpu = (TextView) rootView.findViewById(R.id.info);
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
