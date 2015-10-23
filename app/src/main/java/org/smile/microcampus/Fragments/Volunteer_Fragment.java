package org.smile.microcampus.Fragments;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.smile.microcampus.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Volunteer_Fragment extends Fragment  {
    private View view;
    public Volunteer_Fragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.voluntee_listview,container,false);
        return view;
    }

}
