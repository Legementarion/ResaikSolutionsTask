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

    private Switch changePic,showAll,randPic,animPic;
    private NumberPicker numberPicker;

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
        changePic = (Switch) view.findViewById(R.id.paramSwitch1);
        changePic.setOnCheckedChangeListener(mySwitch);
        showAll = (Switch) view.findViewById(R.id.paramSwitch2);
        showAll.setOnCheckedChangeListener(mySwitch);
        randPic = (Switch) view.findViewById(R.id.paramSwitch3);
        randPic.setOnCheckedChangeListener(mySwitch);
        animPic = (Switch) view.findViewById(R.id.paramSwitch4);
        animPic.setOnCheckedChangeListener(mySwitch);
        numberPicker = (NumberPicker) view.findViewById(R.id.numberPicker);

        defaultSettings();
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

    private void defaultSettings(){
        numberPicker.setMaxValue(9);
        numberPicker.setMinValue(1);
        numberPicker.setValue(1);
        Settings.intervalPickChange = numberPicker.getValue();

        Settings.autoPickChange = changePic.isChecked();
        Settings.showAll = showAll.isChecked();
        Settings.orderPickChange = randPic.isChecked();
        Settings.animationPickChange = animPic.isChecked();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
