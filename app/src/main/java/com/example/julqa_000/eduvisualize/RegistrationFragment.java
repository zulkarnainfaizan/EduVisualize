package com.example.julqa_000.eduvisualize;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by julqa_000 on 18-07-2018.
 */

public class RegistrationFragment extends Fragment{
    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.activity_registeration,container,false);
        return myView;
    }
}
