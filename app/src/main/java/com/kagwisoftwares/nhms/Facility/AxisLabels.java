package com.kagwisoftwares.nhms.Facility;

import java.util.ArrayList;

public class AxisLabels {

    String xAxisType, yAxisType;

    public AxisLabels(String xAxisType, String yAxisType) {
        this.xAxisType = xAxisType;
        this.yAxisType = yAxisType;
    }

    public ArrayList<String> setXAxisLabels() {
        if (xAxisType.equals("Monthly"))
                return setMonthlyLabels();
        if (xAxisType.equals("Weekly"))
            return setWeeklyLabels();
        if (xAxisType.equals("Yearly"))
            return setYearlyLabels();
        return null;
    }

    private void setDailyLabels() {

    }

    private ArrayList<String> setWeeklyLabels() {
        ArrayList<String> xlabels = new ArrayList<>();
        xlabels.add("Mon");
        xlabels.add("Tue");
        xlabels.add("Wed");
        xlabels.add("Thur");
        xlabels.add("Fri");
        xlabels.add("Sat");
        xlabels.add("Sun");
        return xlabels;
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

    private ArrayList<String> setYearlyLabels() {
        ArrayList<String> xlabels = new ArrayList<>();
        xlabels.add("2021");
        xlabels.add("2022");
        xlabels.add("2023");
        xlabels.add("2024");
        xlabels.add("2025");
        return xlabels;
    }

}
