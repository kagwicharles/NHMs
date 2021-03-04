package com.kagwisoftwares.nhms.Facility;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.widget.ImageView;

public class DeptStaff {

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getDeptIcon() {
        return deptIcon;
    }

    public void setDeptIcon(int deptIcon) {
        this.deptIcon = deptIcon;
    }

    private String deptName;
    private int deptIcon;

    public DeptStaff(String deptName,int deptIcon) {
        this.deptName = deptName;
        this.deptIcon = deptIcon;
    }
 }
