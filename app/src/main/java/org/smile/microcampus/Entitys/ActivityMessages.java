package org.smile.microcampus.Entitys;

import android.view.View;
import android.widget.ImageView;

/**
 * Created by Ben on 2015/10/17.
 */
public class ActivityMessages {
    private String title;
    private  String content;
    private int imageId;
    public ActivityMessages() {

    }

    public ActivityMessages(String title,String content,int imageId){
        this.title=title;
        this.content=content;
        this.imageId=imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImageView() {
        return imageId;
    }

    public void setImageView(int  imageId) {
        this.imageId = imageId;
    }


}
