package com.kagwisoftwares.nhms.Facility;

import java.util.ArrayList;

public class AxisLabels {

    String xAxisType, yAxisType;

    public AxisLabels(String xAxisType, String yAxisType) {
        this.xAxisType = xAxisType;
        this.yAxisType = yAxisType;
    }

    public ArrayList<String> setXAxisLabels() {
        if (xAxisType.equals("Weekly"))
                return setMonthlyLabels();
        return null;
    }

    private void setDailyLabels() {

    }

    private void setWeeklyLabels() {

    }

    private ArrayList<String> setMonthlyLabels() {
        ArrayList<String> xlabels = new ArrayList<>();
        xlabels.add("JAN");
        xlabels.add("FEB");
        xlabels.add("MAR");
        xlabels.add("APR");
        xlabels.add("MAY");
        xlabels.add("JUN");
        xlabels.add("JUL");
        xlabels.add("AUG");
        xlabels.add("SEP");
        xlabels.add("OCT");
        xlabels.add("NOV");
        xlabels.add("DEC");
        return xlabels;
    }

    private void setYearlyLabels() {

    }

}
