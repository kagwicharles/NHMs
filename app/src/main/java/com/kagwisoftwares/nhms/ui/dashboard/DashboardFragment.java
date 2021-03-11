package com.kagwisoftwares.nhms.ui.dashboard;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
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

import java.util.ArrayList;
import java.util.List;

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
        dashRv.setAdapter(new DashboardListAdapter(setDashData()));
        //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), linearLayoutManager.getOrientation());
        //dashRv.addItemDecoration(dividerItemDecoration);

        StartSnapHelper startSnapHelper = new StartSnapHelper();
        startSnapHelper.attachToRecyclerView(dashRv);
        return view;
    }

    public void fillBarChart() {
        xAxisSetup();
        BarData barData = new BarData(getDataSet1());
        barChartSetup();
        barChart.setData(barData);
        barChart.animateXY(1000, 1000);
        barChart.invalidate();
    }

    private BarDataSet getDataSet1() {
        List<BarEntry> set1 = new ArrayList<>();
        set1.add(new BarEntry(0f, 700));
        set1.add(new BarEntry(1f, 180));
        set1.add(new BarEntry(2f, 200));
        set1.add(new BarEntry(3f, 300));
        set1.add(new BarEntry(4f, 400));
        set1.add(new BarEntry(5f, 500));
        set1.add(new BarEntry(6f, 500));
        set1.add(new BarEntry(7f, 900));
        set1.add(new BarEntry(8f, 30));
        set1.add(new BarEntry(9f, 700));
        set1.add(new BarEntry(10f, 400));
        set1.add(new BarEntry(11f, 190));

        BarDataSet barDataSet1 = new BarDataSet(set1, "Total revenue");
        barDataSet1.setDrawValues(false);
        barDataSet1.setColor(Color.rgb(0, 48, 89));
        return barDataSet1;
    }

    public ArrayList<DashMenu> setDashData() {
        ArrayList<DashMenu> dashItems = new ArrayList<>();
        dashItems.add(new DashMenu("Totals", "30,000K", R.drawable.ic_salary));
        dashItems.add(new DashMenu("Profits", "20,000K", R.drawable.ic_profit));
        dashItems.add(new DashMenu("Expenses", "40,000", R.drawable.ic_expenses));
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

    private void xAxisSetup() {
        XAxis xAxis = barChart.getXAxis();
        xAxis.setLabelCount(12);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            ArrayList<String> xlabels = new AxisLabels("Weekly", "").setXAxisLabels();
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if(((int)value) < xlabels.size())
                    return xlabels.get((int) value) ;
                else
                    return "0";
            }
        });
    }

    private void barChartSetup() {
        barChart.fitScreen();
        barChart.setDrawGridBackground(false);
        barChart.setDrawValueAboveBar(false);
        barChart.getDescription().setEnabled(false);
        /*barChart.getLegend().setTextColor(Color.WHITE);
        barChart.getXAxis().setTextColor(Color.WHITE);
        barChart.getAxisLeft().setTextColor(Color.WHITE);
        barChart.getAxisRight().setTextColor(Color.WHITE); */
        barChart.getAxisRight().setDrawGridLines(true);
        barChart.getAxisLeft().setDrawGridLines(true);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getAxisRight().setEnabled(false);
    }
}