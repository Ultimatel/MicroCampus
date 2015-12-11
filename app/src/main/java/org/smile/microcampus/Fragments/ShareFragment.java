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
import io.rong.imlib.model.Conversation;

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

        String Token = "2WpK2Lbs4r3DG8/X7JgVsehfvVfP+0RfM/vke2v2ZOfLtzGIgs5WOVycN8torIffl8I1zwwew5RdA8UVa9A7ag==";//test
        /**
         * IMKit SDK调用第二步
         *
         * 建立与服务器的连接
         *
         */
        RongIM.connect(Token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                //Connect Token 失效的状态处理，需要重新获取 Token
            }

            @Override
            public void onSuccess(String userId) {
                Log.e("MainActivity", "——onSuccess— -" + userId);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.e("MainActivity", "——onError— -" + errorCode);
            }
        });

    return view;
    }


    @Override
    public void onClick(View v) {
        RongIM.getInstance().startConversation(getActivity(),Conversation.ConversationType.CHATROOM, "chatroom001", "融云客服");
    }
}
