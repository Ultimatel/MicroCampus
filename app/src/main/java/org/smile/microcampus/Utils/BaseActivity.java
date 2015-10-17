package org.smile.microcampus.Utils;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by Ben on 2015/10/17.
 */
public class BaseActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initActionBar();
    }

   protected void initActionBar() {}
   protected void initView() {}
}
