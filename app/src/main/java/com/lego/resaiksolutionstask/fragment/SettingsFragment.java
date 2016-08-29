package com.lego.resaiksolutionstask.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.Switch;

import com.lego.resaiksolutionstask.R;
import com.lego.resaiksolutionstask.utils.Settings;


public class SettingsFragment extends Fragment {

    public SettingsFragment() {
    }

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        Switch changePic = (Switch) view.findViewById(R.id.paramSwitch1);
        changePic.setOnCheckedChangeListener(mySwitch);
        Switch showAll = (Switch) view.findViewById(R.id.paramSwitch2);
        showAll.setOnCheckedChangeListener(mySwitch);
        Switch randPic = (Switch) view.findViewById(R.id.paramSwitch3);
        randPic.setOnCheckedChangeListener(mySwitch);
        Switch animPic = (Switch) view.findViewById(R.id.paramSwitch4);
        animPic.setOnCheckedChangeListener(mySwitch);

        NumberPicker numberPicker = (NumberPicker) view.findViewById(R.id.numberPicker);
        numberPicker.setMaxValue(9);
        numberPicker.setMinValue(0);
        numberPicker.setValue(1);
        return view;
    }

    private CompoundButton.OnCheckedChangeListener mySwitch = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()){
                case R.id.paramSwitch1:
                    Settings.autoPickChange = isChecked;
                    break;
                case R.id.paramSwitch2:
                    Settings.showAll = isChecked;
                    break;
                case R.id.paramSwitch3:
                    Settings.orderPickChange = isChecked;
                    break;
                case R.id.paramSwitch4:
                    Settings.animationPickChange = isChecked;
                    break;
            }
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
