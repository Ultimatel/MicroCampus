package org.smile.microcampus.Activitys;

import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import org.smile.microcampus.Adapters.ChildAdapter;
import org.smile.microcampus.R;

public class ShowImageActivity extends AppCompatActivity {
	private GridView mGridView;
	private List<String> list;
	private ChildAdapter adapter;
    private Toolbar mToolbar;
	private List<String>selectList=new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_girdview_pictures);
		initView();
		
	}
	public void initView(){
		//获取toolbar组件
		mToolbar= (Toolbar) findViewById(R.id.toolbar);
		mToolbar.setTitle("选择图片");
		mToolbar.setTitleTextColor(Color.WHITE);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				simple(v);
			}
		});

		mGridView = (GridView) findViewById(R.id.child_grid);
		list = getIntent().getStringArrayListExtra("data");
		adapter = new ChildAdapter(this, list, mGridView);
		mGridView.setAdapter(adapter);
	}
	
	//点击返回按钮弹出提示对话框
	private void simple(View source){
		AlertDialog.Builder builder=new AlertDialog.Builder(this)
				.setIcon(R.drawable.ic_theme)
				.setMessage("是否退出当前操作！");
		setPositiveButton(builder);
		setNegativeButton(builder)
				.create()
				.show();
	}
	private AlertDialog.Builder setPositiveButton(AlertDialog.Builder builder){
		return  builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(),"退出成功！",Toast.LENGTH_SHORT).show();
				onBackPressed();
			}
		});


	}
	private AlertDialog.Builder setNegativeButton(final AlertDialog.Builder builder){
		return builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//取消提交，不做任何事情,并退出
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater=new MenuInflater(this);
		inflater.inflate(R.menu.menu_successed,menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
			case R.id.menu_success:

				Intent mIntent=new Intent(getApplicationContext(),Post_ActivityMessages.class);
				//mIntent.putStringArrayListExtra("data", (ArrayList<String>) selectList);
				startActivity(mIntent);
				finish();
		}
		return true;
	}
}
