package org.smile.microcampus.Model;

import cn.bmob.v3.BmobUser;

/**
 * Created by guang on 2015/11/9.
 */
public class MyUser extends BmobUser {

    private String studentId; //学号
    private String gender;    //性别
    private String nickname;  //昵称

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }




}