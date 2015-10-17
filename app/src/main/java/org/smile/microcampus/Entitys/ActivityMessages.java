package org.smile.microcampus.Entitys;

import android.widget.ImageView;

/**
 * Created by Ben on 2015/10/17.
 */
public class ActivityMessages {
    private String title;
    private  String content;
    private ImageView imageView;
    public ActivityMessages() {

    }

    public ActivityMessages(String title,String content){
        this.title=title;
        this.content=content;
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

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
