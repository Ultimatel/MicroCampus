package org.smile.microcampus.Activitys;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import android.widget.ImageView;
import android.widget.Toast;

import org.smile.microcampus.Adapters.CommonAdapter;
import org.smile.microcampus.Adapters.ViewHolder;
import org.smile.microcampus.R;
import org.smile.microcampus.Utils.ImageBean;
import org.smile.microcampus.Utils.NativeImageLoader;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Ben on 2015/10/25.
 */
public class Post_ActivityMessages extends AppCompatActivity {

    private List<Map> pDatas=new ArrayList<>();
    private Toolbar mToolbar;
    private GridView sGridview;
    private EditText et;
    private List<String> plist = new ArrayList<String>();
    private static final int MESSAGEES=1;
    private List<String>tempList=new ArrayList<>();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MESSAGEES:
                    sGridview.setAdapter(new CommonAdapter<Map>(getApplicationContext(), pDatas, R.layout.grid_view_image) {
                        @Override
                        public void convert(ViewHolder holder, Map map) {
                            if (tempList!=null) {
                                holder.setImageBitmap(R.id.gird_image, (Bitmap) map.get("image"));
                            }
                        }
                    });
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.school_fragment_post_form);
        initView();

    }

    /**
     * 初始化界面和其他数据
     */
    public void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);//toolbar布局
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle("发布活动信息");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        plist = getIntent().getStringArrayListExtra("checkphoto");
        tempList=plist;//把获取的图片路径给tempList来存
        Datas();
        //给编辑文本框的字体设置为黑色
        et= (EditText) findViewById(R.id.school_fragment_post_tv);
        et.setTextColor(getResources().getColor(R.color.black));
        sGridview= (GridView) findViewById(R.id.grid_post);
        sGridview.setAdapter(new CommonAdapter<Map>(this, pDatas, R.layout.grid_view_image) {
            @Override
            public void convert(ViewHolder holder, Map map) {
                if (plist!=null) {
                    holder.setImageBitmap(R.id.gird_image, (Bitmap) map.get("image"));
                }
            }
        });
        sGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent = new Intent(getApplicationContext(), Activity_ShowPictureFiles.class);
                        startActivity(intent);
                        // finish();//关闭当前的活动
                        break;
                }
            }
        });
      sGridview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
          @Override
          public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
              switch (position){
                  case 1:pDatas.remove(1);handler.sendEmptyMessage(MESSAGEES);break;
                  case 2:pDatas.remove(2);handler.sendEmptyMessage(MESSAGEES);break;
                  case 3:pDatas.remove(3);handler.sendEmptyMessage(MESSAGEES);break;
                  case 4:pDatas.remove(4);handler.sendEmptyMessage(MESSAGEES);break;
                  case 5:pDatas.remove(5);handler.sendEmptyMessage(MESSAGEES);break;
                  case 6:pDatas.remove(6);handler.sendEmptyMessage(MESSAGEES);break;
                  case 7:pDatas.remove(7);handler.sendEmptyMessage(MESSAGEES);break;
                  case 8:pDatas.remove(8);handler.sendEmptyMessage(MESSAGEES);break;
              }
              return true;
          }
      });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu_send, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mend_send:
                Toast.makeText(getApplicationContext(), "提交数据到数据库", Toast.LENGTH_SHORT).show();   //把girdview中的图片（也就是plist集合中的图片）提交到数据库
                //关闭相册相关的活动
                ShowImageActivity.showImageActivity.finish();
                Activity_ShowPictureFiles.activityShowPictureFiles.finish();
                finish();
                break;
        }
        return true;
    }

    /**
     * 开启子线程来添加
     * 返回的图片，填充到GridView中
     */
    public void Datas() {
        new Thread(new Runnable() {
            @Override
            public void run() {
              //  Map<String, Object> map = new HashMap<>();
                    if (tempList != null) {
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.picture_plus);
                        Map<String, Object> map1 = new HashMap<String, Object>();
                        map1.put("image", bitmap);
                        pDatas.add(map1);
                        for (int i = 0; i < (tempList.size()); i++) {
                            Map<String, Object> map = new HashMap<>();
                            Bitmap bitmap1 = BitmapFactory.decodeFile(tempList.get(i));
                            map.put("image", bitmap1);
                            pDatas.add(map);
                        }
                        Log.v("打印pDatas的大小，大小为：",pDatas.size()+"");
                    }
            }
        }).start();
    }

    /**
     * 因为开启的模式为singleTask,所以当在栈中存在当前的activity时，
     * 调用 onNewIntent(Intent intent)方法，重回栈顶。所以要重新设置新的intent
     * 来获取传递过来的新数据
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        pDatas.clear();
        //获取新的图片集合
        plist = getIntent().getStringArrayListExtra("checkphoto");
        //如果集合不为空，添加图片进temgplst集合中
        if(plist!=null){
            for(int i=0;i<plist.size();i++){
                String tempPath=plist.get(i).toString();
                tempList.add(tempPath);
            }
        }
        Datas();
        handler.sendEmptyMessage(MESSAGEES);
    }
}
