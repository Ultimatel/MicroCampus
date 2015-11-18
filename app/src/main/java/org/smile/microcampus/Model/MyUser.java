package org.smile.microcampus.Model;

import com.bmob.BmobProFile;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by guang on 2015/11/9.
 */
public class MyUser extends BmobUser {

    private String studentId; //学号
    private String gender;    //性别
    private String nickname;  //昵称
    private BmobFile icon;   //头像
    private String grade;   //年级
    private String classes;  //班级

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

    public BmobFile getIcon(){
        return icon;
    }

    public void setIcon(BmobFile icon){
        this.icon = icon;
    }

    public String getGrade(){
        return grade;
    }

    public void setGrade(String grade){
        this.grade = grade;
    }

    public String getClasses(){
        return classes;
    }

    public void setClasses(String classes){
        this.classes = classes;
    }




}