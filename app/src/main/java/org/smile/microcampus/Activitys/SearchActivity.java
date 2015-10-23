package org.smile.microcampus.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import org.smile.microcampus.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.os.Build;
public class SearchActivity extends AppCompatActivity {

    EditText searchContent;
    ImageView ivDeleteText;
    ListView mListView;
    LinearLayout backBtn;

    ArrayList<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();
    ArrayList<String> mListTitle = new ArrayList<String>();
    ArrayList<String> mListText = new ArrayList<String>();
    SimpleAdapter adapter;
    Handler myhandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        set_eSearch_TextChanged();//设置eSearch搜索框的文本改变时监听器
        set_ivDeleteText_OnClick();//设置叉叉的监听器

        initView();
    }

    public void initView(){
        mListView = (ListView) findViewById(R.id.list_search_result);
        getmData(mData);
        adapter = new SimpleAdapter(this,mData,android.R.layout.simple_list_item_2,
                new String[]{"title","text"},new int[]{android.R.id.text1,android.R.id.text2});
        mListView.setAdapter(adapter);
        backBtn = (LinearLayout)findViewById(R.id.back_button);
        backBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    /**
     * 设置搜索框的文本更改时的监听器
     */
    private void set_eSearch_TextChanged(){
        searchContent = (EditText) findViewById(R.id.search_content);
        searchContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //这个应该是在改变的时候会做的动作吧，具体还没用到过。
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                //这是文本框改变之前会执行的动作
            }

            @Override
            public void afterTextChanged(Editable s) {
                /**这是文本框改变之后 会执行的动作
                 * 因为我们要做的就是，在文本框改变的同时，我们的listview的数据也进行相应的变动，并且如一的显示在界面上。
                 * 所以这里我们就需要加上数据的修改的动作了。
                 */
                if(s.length() == 0){
                    ivDeleteText.setVisibility(View.GONE);//当文本框为空时，则叉叉消失
                }
                else {
                    ivDeleteText.setVisibility(View.VISIBLE);//当文本框不为空时，出现叉叉
                }
                myhandler.post(eChanged);
            }
        });
    }

    Runnable eChanged = new Runnable() {
        @Override
        public void run() {
            String data = searchContent.getText().toString();
            mData.clear();//先要清空，不然会叠加
            getmDataSub(mData, data);//获取更新数据
            adapter.notifyDataSetChanged();//更新
        }
    };

    /**
     * 获得根据搜索框的数据data来从元数据筛选，筛选出来的数据放入mDataSubs里
     * @param mDataSubs
     * @param data
     */
    private void getmDataSub(ArrayList<Map<String, Object>> mDataSubs, String data)
    {
        int length = mListTitle.size();
        for(int i = 0; i < length; ++i){
            if(mListTitle.get(i).contains(data) || mListText.get(i).contains(data)){
                Map<String,Object> item = new HashMap<String,Object>();
                item.put("title", mListTitle.get(i));
                item.put("text",  mListText.get(i));
                mDataSubs.add(item);
            }
        }
    }

    /**
     * 设置叉叉的点击事件，即清空功能
     */
    private void set_ivDeleteText_OnClick(){
        ivDeleteText = (ImageView) findViewById(R.id.ivDeleteText);
        ivDeleteText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                searchContent.setText("");
            }
        });
    }

    /**
     * 获得元数据 并初始化mDatas
     * @param mDatas
     */
    private void getmData(ArrayList<Map<String, Object>> mDatas){
        Map<String, Object> item ;

        for(int i = 0; i < 5; i++){
            item = new HashMap<String, Object>();
            mListTitle.add("title" + i);
            mListText.add("text" + i);

            item.put("title", mListTitle.get(i));
            item.put("text", mListText.get(i));
            mDatas.add(item);
        }
        for(int i = 0; i < 5; i++){
            item = new HashMap<String, Object>();
            mListTitle.add("add" + i);
            mListText.add("bus" + i);

            item.put("title", mListTitle.get(i));
            item.put("text", mListText.get(i));
            mDatas.add(item);
        }
    }

}
