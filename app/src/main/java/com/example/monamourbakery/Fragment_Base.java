package com.example.monamourbakery;

import android.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Fragment_Base extends Fragment {
    private String selected;
    private Product product;
    protected List<String> readTextFile(int resourceFile) {
        List<String> data = new ArrayList<>();
        String string = "";
        InputStream is = this.getResources().openRawResource(resourceFile);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            while (true) {
                try {
                    if ((string = reader.readLine()) == null) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (!string.isEmpty()) {
                    data.add(string.replace('\n', '\0')); }
            }
            is.close();
        } finally {
            return data;
        }
    }

    protected void setFitDropDown( AutoCompleteTextView dropDown,int  resourceFile){
        dropDown.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.dropdown_menu_list_item, readTextFile(resourceFile)));
        dropDown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setSelected(parent.getItemAtPosition(position).toString().trim());
                Log.d("DDM", "Institute: " + selected);
            }
        });
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }
}
