package com.kagwisoftwares.nhms.Facility;

public class Department {

    private String deptName, deptIcon, deptVisits;

    public Department(String deptName, String deptVisits) {
        this.deptName = deptName;
        this.deptVisits = deptVisits;
    }

    public String getDeptName() {
        return deptName;
    }

    public String getDeptVisits() {
        return deptVisits;
    }
}
