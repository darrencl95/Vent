package com.pingus.vent.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.pingus.vent.Controller.SettingsFragment;
import com.pingus.vent.R;
/**
 * Created by Shantanu Mantri on 6/25/2017.
 * Handles the Listview with switches in the Settings Section
 */
public class SettingArrayAdapter extends ArrayAdapter<String> {
    private Context context;
    private String[] args;

    public SettingArrayAdapter(Context context, String[] args) {
        super(context, -1,args);
        this.context = context;
        this.args = args;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.setting_list_row, parent, false);
        Switch switcher = (Switch) rowView.findViewById(R.id.switch1);
        switcher.setText(args[position]);
        switcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(position == 0) { //Block Comments
                    if(isChecked) {
                        Toast.makeText(getContext(), "Clicked on Block!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Unclicked on Block!", Toast.LENGTH_SHORT).show();
                    }
                } else if(position == 1) { //Block Likes
                    if(isChecked) {
                        Toast.makeText(getContext(), "Clicked on Likes!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Unclicked on Likes!", Toast.LENGTH_SHORT).show();
                    }
                } else { //Block Friend Requests
                    if(isChecked) {
                        Toast.makeText(getContext(), "Clicked on Requests!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Unclicked on Requests!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return rowView;
    }
}
