package com.kagwisoftwares.nhms.ui.Staff;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.kagwisoftwares.nhms.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private PieChart mPieChart;
    private BarChart barChart;

    private View view;
    private ArrayAdapter<String> spinnerAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        mPieChart = view.findViewById(R.id.totalsPieChart);
        ArrayList<PieEntry> data = new ArrayList<>();
        data.add(new PieEntry(20));

        ArrayList<PieEntry> data1 = new ArrayList<>();
        data.add(new PieEntry(50));
        data.add(new PieEntry(60));
        fillPieChart(mPieChart, data, data1);

        barChart = view.findViewById(R.id.barChart);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        barChart.fitScreen();

        BarData barData = new BarData(getDataSet1(), getDataSet2());
        barData.setBarWidth(0.45f);
        barChart.setData(barData);
        barChart.groupBars(0.5f, 0.06f, 0.02f);
        barChart.animateXY(1000, 1000);
        barChart.invalidate();

        spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, getSpinnerText());
        Spinner spinner = view.findViewById(R.id.monthspinner);
        spinner.setAdapter(spinnerAdapter);

        return view;
    }

    public static void fillPieChart(PieChart pieChart, ArrayList<PieEntry> arrayList1, ArrayList<PieEntry> arrayList2) {

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(true);
        pieChart.setExtraOffsets(2, 5, 2, 2);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);
        pieChart.getDescription().setEnabled(false);
        PieDataSet pieDataSet1 = new PieDataSet(arrayList1, "New Visits");
        pieDataSet1.setSliceSpace(3f);
        pieDataSet1.setSelectionShift(5f);
        int[] colors = {Color.rgb(13, 166, 10), Color.rgb(255, 140, 0)};
        ArrayList<Integer> list1 = new ArrayList<>();
        for (int c: colors) {
            list1.add(c);
        }
        pieDataSet1.setColors(list1);
        pieDataSet1.setColors(ColorTemplate.createColors(colors));

        PieDataSet pieDataSet2 = new PieDataSet(arrayList2, "Revisits");
        pieDataSet2.setSliceSpace(3f);
        pieDataSet2.setSelectionShift(5f);
        pieDataSet2.setColors(Color.rgb(100, 150, 160));

        PieData pieData = new PieData(pieDataSet1);
        pieData.setValueTextSize(18f);
        pieData.setValueTextColor(Color.WHITE);
        pieChart.setData(pieData);
        pieChart.setCenterTextSize(30f);
        pieChart.setDrawEntryLabels(false);
        pieChart.animateY(1000, Easing.getEasingFunctionFromOption(Easing.EasingOption.EaseInOutCubic));
    }

    public String[] getSpinnerText() {
        return new String[]{"Mukuru-weini Hospital", "Karatina Hospital", "Othaya Hospital", "Naromoru Hospital"};
    }

    public String[] getXvalues() {
        String[] xAxis = new String[5];
        xAxis[0] = "JAN";
        xAxis[1] = "FEB";
        xAxis[2] = "MAR";
        xAxis[3] = "APR";
        xAxis[4] = "MAY";
        return xAxis;
    }

    private BarDataSet getDataSet1() {
        List<BarEntry> set1 = new ArrayList<>();
        set1.add(new BarEntry(1f, 700));
        set1.add(new BarEntry(2f, 180));
        set1.add(new BarEntry(3f, 200));
        set1.add(new BarEntry(4f, 300));
        set1.add(new BarEntry(5f, 400));
        set1.add(new BarEntry(6f, 500));
        set1.add(new BarEntry(7f, 50));

        BarDataSet barDataSet1 = new BarDataSet(set1, "Admissions");
        barDataSet1.setColor(Color.rgb(0, 155, 0));
        return barDataSet1;
    }

    private BarDataSet getDataSet2() {
        List<BarEntry> set2 = new ArrayList<>();
        set2.add(new BarEntry(1f, 900));
        set2.add(new BarEntry(2f, 100));
        set2.add(new BarEntry(3f, 290));
        set2.add(new BarEntry(4f, 300));
        set2.add(new BarEntry(5f, 89));
        set2.add(new BarEntry(6f, 500));
        set2.add(new BarEntry(7f, 90));

        BarDataSet barDataSet = new BarDataSet(set2, "Discharge");
        barDataSet.setStackLabels(getXvalues());
        barDataSet.setColor(Color.rgb(135, 206, 250));
        return barDataSet;
    }

}