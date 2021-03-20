package com.example.g_zonev2.Profile;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.g_zonev2.R;
import com.mikhaellopez.circularimageview.CircularImageView;


public class Profile extends Fragment {

    String name;
    public Profile(String user){
        name = user;
    }

    CircularImageView circularImageView;
    TextView t;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        t=view.findViewById(R.id.textView3);
        t.setText(name);


        return view;
    }
}