package org.smile.microcampus.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.smile.microcampus.R;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * 分享模块
 */
public class ShareFragment extends Fragment implements View.OnClickListener{

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_share, container, false);
        Button button=(Button)view.findViewById(R.id.star_chat);
        button.setOnClickListener(this);
        

        return view;
    }


    @Override
    public void onClick(View v) {
        RongIM.getInstance().startPrivateChat(getActivity(), "1", "hello");
    }
}
