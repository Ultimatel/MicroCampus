package org.smile.microcampus.Utils;

/**
 * Created by Ben on 2015/10/16.
 */
public class DateText {
    private String date;
    private String text;
    public DateText(){}
    public DateText(String date, String text){
        this.date=date;
        this.text=text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
