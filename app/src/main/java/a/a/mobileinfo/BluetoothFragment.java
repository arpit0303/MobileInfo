package a.a.mobileinfo;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import adapters.CommonAdapter;

/**
 * Created by arpit on 01/12/15.
 */
public class BluetoothFragment extends ListFragment implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    BluetoothManager bluetoothManager;
    BluetoothAdapter bluetoothAdapter;
    Switch bluetoothSwitch;
    SwipeRefreshLayout swipeRefreshLayout;
    TextView changeBluetoothName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bluetooth,
                container, false);
        bluetoothSwitch = (Switch) rootView.findViewById(R.id.bluetooth_switch);
        changeBluetoothName = (TextView) rootView.findViewById(R.id.changeBluetoothName_text);

        operations(rootView);
        return rootView;
    }

    private void operations(View rootView){
        changeBluetoothName.setOnClickListener(this);

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                bluetoothDetails();
            }
        });

        bluetoothSwitch.setOnCheckedChangeListener(this);
    }

    private void bluetoothDetails() {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            bluetoothManager = (BluetoothManager) getActivity().getSystemService(Context.BLUETOOTH_SERVICE);
            bluetoothAdapter = bluetoothManager.getAdapter();
        }else {
            bluetoothAdapter = null;
        }

        if (bluetoothAdapter != null) {
            bluetoothSwitch.setChecked(bluetoothAdapter.isEnabled());
            String[] desc = {"Bluetooth Name", "Bluetooth MAC Address"};
            String[] property = {bluetoothAdapter.getName(), bluetoothAdapter.getAddress()};

            CommonAdapter adapter = new CommonAdapter(getActivity().getApplicationContext(), desc, property, "Bluetooth");
            setListAdapter(adapter);
        }
        else{
            bluetoothSwitch.setVisibility(View.GONE);
            changeBluetoothName.setText(getResources().getString(R.string.no_bluetooth));

        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2 && bluetoothAdapter != null) {
            if (b) {
                bluetoothAdapter.enable();
            } else {
                bluetoothAdapter.disable();
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.changeBluetoothName_text) {
            bluetoothSwitch.setChecked(true);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            final EditText bluetoothNameEditText = new EditText(getActivity());
            builder.setTitle("New Bluetooth Name")
                    .setMessage("Enter the bluetooth name")
                    .setView(bluetoothNameEditText)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String newBluetoothName = bluetoothNameEditText.getText().toString();
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2 && bluetoothAdapter != null) {
                                bluetoothAdapter.setName(newBluetoothName);
                                bluetoothDetails();
                            }
                        }
                    })
                    .setNegativeButton(android.R.string.cancel, null);

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        bluetoothDetails();
    }
}
