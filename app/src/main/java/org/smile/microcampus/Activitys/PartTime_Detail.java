package org.smile.microcampus.Activitys;


import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;

import org.smile.microcampus.R;
import org.smile.microcampus.Utils.BaseActivity;


/**
 * Created by Ben on 2015/10/17.
 */
public class PartTime_Detail extends BaseActivity {
    private ImageView detail_imageview;
    private TextView detail_name;
    private TextView content_jianjie;
    private TextView tv_content;
    private ActionBar actionbar;

    public void initView(){
        setContentView(R.layout.detail_part_timejob);
        detail_imageview= (ImageView) findViewById(R.id.detail_imageview);
        detail_name= (TextView) findViewById(R.id.detail_name);
        tv_content= (TextView) findViewById(R.id.tv_content);
        content_jianjie= (TextView) findViewById(R.id.text_jianjie);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        actionbar=getSupportActionBar();
        if(actionbar!=null){
            actionbar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
