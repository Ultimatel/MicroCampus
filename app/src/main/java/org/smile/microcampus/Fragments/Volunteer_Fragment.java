package org.smile.microcampus.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.smile.microcampus.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Volunteer_Fragment extends Fragment {


    public Volunteer_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());

        return textView;
    }


}
