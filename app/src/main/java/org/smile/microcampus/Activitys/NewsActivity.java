package org.smile.microcampus.Activitys;


import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.smile.microcampus.R;
import org.smile.microcampus.Utils.BaseActivity;


/**
 * Created by Ben on 2015/10/14.
 */
public class NewsActivity extends BaseActivity implements View.OnClickListener {
    private ActionBar actionBar;
    private TextView textView;
    private Button baoming;

    public void initView() {
        setContentView(R.layout.news_actvity);
        baoming = (Button) findViewById(R.id.btn_baoming);
        baoming.setOnClickListener(this);
        textView = (TextView) findViewById(R.id.content_textview);
        textView.setText("      是孤独久了，是寂寞惯了，还是太过伤感了，我不知道，突然想一个人去旅行，去完成向往已久的单人旅行，在旅行中寻找那个迷失的自己，找回那个积极乐观的我。\n" +
                        "       喜欢一个人的旅行，喜欢一个人、一个包，踏上远去的旅程。走在乡间的小路，靠在古镇的拱桥边，看着岸边女子衣袂飘飘，惹我阵阵遐想，思绪飘渺。想象她们的生活，是那么的安逸祥和，那样的宁静，那样的令人向往，与此刻的我形成了鲜明的对比，让我尽是羡慕。看着桥下的涓涓流水，那也许就是净化我心灵的解药，也或许是我突然一个人旅行的落脚地。\n" +
                        "       一个人的旅行的那样的美好，那样的随意，不用考虑太多的东西，旅行的目的地随自己的心情而设定，说去哪就去哪，想干嘛就干嘛，无拘无束，甚是让人喜悦，更重要的是单人旅行可以安抚那个被世事挫伤的心灵。一个人可以独自享受安静的快乐，去追寻曾经遗失的美好，努力拼凑那些记忆的碎片，生怕会漏掉一片记忆，留下令人追悔的遗憾。那些记忆，有一起开心的笑，一起感动的哭，这些都是属于我们的记忆。"

        );
       int s= textView.getText().length();//用来做展开和隐藏按钮的字数
        if(s>120){
            textView.setMovementMethod(new ScrollingMovementMethod());
        }
    }
    @Override
    protected void initActionBar() {
        super.initActionBar();
        actionBar=this.getSupportActionBar();//获取actionBar对象
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_baoming:
                Intent intent = new Intent(this, BaoMingActivity.class);
                startActivity(intent);
                break;
        }
    }
}