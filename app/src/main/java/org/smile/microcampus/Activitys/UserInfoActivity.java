package org.smile.microcampus.Activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.smile.microcampus.Adapters.MultiItemCommonAdapter;
import org.smile.microcampus.Adapters.MultiItemTypeSupport;
import org.smile.microcampus.Adapters.UserInfoAdapter;
import org.smile.microcampus.Adapters.ViewHolder;
import org.smile.microcampus.Model.MyUser;
import org.smile.microcampus.R;
import org.smile.microcampus.Utils.CircleImageView;
import org.smile.microcampus.Utils.MyImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.UploadFileListener;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public class UserInfoActivity extends AppCompatActivity {

    private ListView listUserInfo;
    private List<Map> mDatas;
    MyUser userInfo;  //当前用户

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        setToolbar();
        initView();
    }

    private void initView(){

        userInfo = MyUser.getCurrentUser(UserInfoActivity.this, MyUser.class);  //获取当前用户
        String[] title = {"头像", "昵称","性别", "学号" ,"手机" ,"邮箱" ,"年级" ,"班级" };
        String url = "http://file.bmob.cn/M02/9A/63/oYYBAFZIf2eADcJfAACrU3v0Z-M919.jpg";
        String[] content = { url, userInfo.getNickname(), userInfo.getGender(), userInfo.getStudentId(),
                            userInfo.getMobilePhoneNumber(), userInfo.getEmail(),
                            userInfo.getGrade(), userInfo.getClasses() };
        mDatas = new ArrayList<Map>();
        for(int i = 0; i < 8; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("title", title[i]);
            if (content[i] != null)
                map.put("content", content[i]);
            else
                map.put("content", "未填写");
            mDatas.add(map);
        }
        listUserInfo = (ListView) findViewById(R.id.list_user_info);
        listUserInfo.setAdapter(new UserInfoAdapter(this, mDatas));
        listUserInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(UserInfoActivity.this, "点击" + position, Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0:   //
                        Toast.makeText(UserInfoActivity.this, "点击" + position, Toast.LENGTH_SHORT).show();
                        getPicture();
                        break;
                    case 1:  //
                        Toast.makeText(UserInfoActivity.this, "点击" + position, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private static final int REQUEST_IMAGE = 2;
    public void getPicture(){
        Intent intent = new Intent(this, MultiImageSelectorActivity.class);
        // 是否显示调用相机拍照
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
        // 最大图片选择数量
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 9);
        // 设置模式 (支持 单选/MultiImageSelectorActivity.MODE_SINGLE 或者
        // 多选/MultiImageSelectorActivity.MODE_MULTI)
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE,
                   MultiImageSelectorActivity.MODE_SINGLE);
        startActivityForResult(intent, REQUEST_IMAGE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE){
            if(resultCode == RESULT_OK){
                // 获取返回的图片列表
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                // 处理你自己的逻辑 ....
            }
        }
    }

    ProgressDialog dialog = null;
    private void uploadIcon(File file) {
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setTitle("上传中...");
        dialog.setIndeterminate(false);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        final BmobFile bmobFile = new BmobFile(file);
        bmobFile.upload(this, new UploadFileListener() {

            @Override
            public void onSuccess() {
                dialog.dismiss();
//                url = bmobFile.getUrl();
//                showToast("文件上传成功");
////                Log.i("life", "电影文件上传成功，返回的名称--" + bmobFile.getFileUrl(UserInfoActivity.this) + "，文件名=" + bmobFile.getFilename());
//                insertObject(new Movie("冰封：重生之门", bmobFile));
            }

            @Override
            public void onProgress(Integer arg0) {
//                Log.i("life", "uploadMovoieFile-->onProgress:" + arg0);
                dialog.setProgress(arg0);
            }

            @Override
            public void onFailure(int arg0, String arg1) {
                dialog.dismiss();
//                showToast("-->uploadMovoieFile-->onFailure:" + arg0 + ",msg = " + arg1);
            }

        });

    }

    public void setToolbar(){
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);   //ToolBar布局
        mToolbar.setTitleTextColor(Color.WHITE);  //设置ToolBar字体颜色为白色
        mToolbar.setTitle("个人信息");
        setSupportActionBar(mToolbar);  //将ToolBar设置为ActionBAr
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //在ToolBar左边，即当前标题前添加图标
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


}

