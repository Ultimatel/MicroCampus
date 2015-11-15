package org.smile.microcampus.Activitys;

import android.app.Application;

import io.rong.imkit.RongIM;

/**
 * Created by Administrator on 2015/11/15.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 初始化融云
         */
        RongIM.init(this);
    }
}
