package com.kagwisoftwares.nhms.Facility;

public class OtherResources {

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

    public OtherResources(String deptName,int deptIcon) {
        this.deptName = deptName;
        this.deptIcon = deptIcon;
    }
}
