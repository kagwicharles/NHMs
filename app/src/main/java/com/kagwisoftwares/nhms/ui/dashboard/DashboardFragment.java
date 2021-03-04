package com.kagwisoftwares.nhms.ui.dashboard;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.kagwisoftwares.nhms.Facility.AxisLabels;
import com.kagwisoftwares.nhms.Facility.DashMenu;
import com.kagwisoftwares.nhms.Facility.DashboardListAdapter;
import com.kagwisoftwares.nhms.Facility.StartSnapHelper;
import com.kagwisoftwares.nhms.R;
import com.skydoves.powerspinner.PowerSpinnerView;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DashboardFragment extends Fragment {

    private View view;
    private BarChart barChart;
    private PowerSpinnerView spinner;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbarMain);
        toolbar.setTitle("Dashboard");

        spinner = view.findViewById(R.id.rangeSpinner);
        spinnerSetup();
        barChart = view.findViewById(R.id.dashBarchart);
        fillBarChart();

        RecyclerView dashRv = view.findViewById(R.id.dashRV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        dashRv.setLayoutManager(linearLayoutManager);
        dashRv.setAdapter(new DashboardListAdapter(setData()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), linearLayoutManager.getOrientation());
        dashRv.addItemDecoration(dividerItemDecoration);

        StartSnapHelper startSnapHelper = new StartSnapHelper();
        startSnapHelper.attachToRecyclerView(dashRv);
        return view;
    }

    public void fillBarChart() {
        XAxis xAxis = barChart.getXAxis();
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        barChart.fitScreen();
        barChart.setDrawGridBackground(false);
        barChart.setDrawValueAboveBar(false);
        barChart.getDescription().setEnabled(false);
        barChart.getLegend().setTextColor(Color.WHITE);
        barChart.getXAxis().setTextColor(Color.WHITE);
        barChart.getAxisLeft().setTextColor(Color.WHITE);
        barChart.getAxisRight().setTextColor(Color.WHITE);
        barChart.getXAxis().setTextSize(10f);
        barChart.getAxisLeft().setTextSize(10f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setCenterAxisLabels(true);
        xAxis.setGranularityEnabled(true);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            ArrayList<String> xlabels = new AxisLabels("Weekly", "").setXAxisLabels();
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if(((int)value) < xlabels.size())
                    return xlabels.get((int) value) ;
                else
                    return "";
            }
        });

        BarData barData = new BarData(getDataSet1());
        barChart.setData(barData);
        barChart.animateXY(1000, 1000);
        barChart.invalidate();
    }

    private BarDataSet getDataSet1() {
        List<BarEntry> set1 = new ArrayList<>();
        set1.add(new BarEntry(1f, 700));
        set1.add(new BarEntry(2f, 180));
        set1.add(new BarEntry(3f, 200));
        set1.add(new BarEntry(4f, 300));
        set1.add(new BarEntry(5f, 400));
        set1.add(new BarEntry(6f, 500));
        set1.add(new BarEntry(7f, 500));
        set1.add(new BarEntry(8f, 900));
        set1.add(new BarEntry(9f, 30));
        set1.add(new BarEntry(10f, 700));
        set1.add(new BarEntry(11f, 400));
        set1.add(new BarEntry(12f, 190));

        BarDataSet barDataSet1 = new BarDataSet(set1, "Total revenue");
        barDataSet1.setDrawValues(false);
        barDataSet1.setColor(Color.rgb(250, 229, 110));
        return barDataSet1;
    }

    public ArrayList<DashMenu> setData() {
        ArrayList<DashMenu> dashItems = new ArrayList<>();
        dashItems.add(new DashMenu("Totals", "30,000K"));
        dashItems.add(new DashMenu("Profits", "20,000K"));
        dashItems.add(new DashMenu("Expenses", "40,000"));
        return dashItems;
    }

    private void spinnerSetup() {
        List<String> adapter = new ArrayList<>();
        adapter.add("Daily");
        adapter.add("Weekly");
        adapter.add("Monthly");
        adapter.add("Yearly");
        spinner.setItems(adapter);
    }

    private String[] getSpinnerItems() {
        String[] yearsArray = {"2021", "2020", "2019", "2018", "2017"};
        return yearsArray;
    }
}