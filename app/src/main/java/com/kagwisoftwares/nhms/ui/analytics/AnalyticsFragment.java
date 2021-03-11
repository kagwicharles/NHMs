package com.kagwisoftwares.nhms.ui.analytics;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.kagwisoftwares.nhms.Facility.AxisLabels;
import com.kagwisoftwares.nhms.Facility.Department;
import com.kagwisoftwares.nhms.Facility.DeptRecyclerAdapter;
import com.kagwisoftwares.nhms.Facility.StartSnapHelper;
import com.kagwisoftwares.nhms.R;
import com.skydoves.powerspinner.PowerSpinnerView;

import java.util.ArrayList;
import java.util.List;

public class AnalyticsFragment extends Fragment {

    private View view;
    private LineChart lineChart;
    private PowerSpinnerView spinner;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_analytics, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbarAnalytics);
        toolbar.setTitle("Analysis");

        spinner = view.findViewById(R.id.rangeSpinner);
        spinnerSetup();
        lineChart = view.findViewById(R.id.visitsLinechart);
        fillLineChart();

        RecyclerView deptRV = view.findViewById(R.id.deptRV);
        deptRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        deptRV.setAdapter(new DeptRecyclerAdapter(getDepts(), getColors()));
        StartSnapHelper snapHelper = new StartSnapHelper();
        snapHelper.attachToRecyclerView(deptRV);
        return view;
    }

    public void fillLineChart() {
        LineDataSet lineDataSet = new LineDataSet(getLineData(), "Total visits");
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.setColor(Color.parseColor("#7280a3"));
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFillDrawable(ContextCompat.getDrawable(getContext(), R.drawable.linechart_fill));
        xAxisSetup();
        LineData lineData = new LineData(lineDataSet);
        lineChartSetup();
        lineChart.setData(lineData);
        lineChart.animateXY(1000, 1000);
        lineChart.invalidate();
    }

    private ArrayList<Department> getDepts() {
        ArrayList<Department> departments = new ArrayList<>();
        departments.add(new Department("Radiology", "3,000"));
        departments.add(new Department("Orthopedic", "1,000"));
        departments.add(new Department("Pharmacy", "3,000"));
        return departments;
    }

    private void spinnerSetup() {
        List<String> adapter = new ArrayList<>();
        adapter.add("Daily");
        adapter.add("Weekly");
        adapter.add("Monthly");
        adapter.add("Yearly");
        spinner.setItems(adapter);
    }

    private ArrayList<Entry> getLineData() {
        ArrayList<Entry> linedataentries = new ArrayList<>();
        linedataentries.add(new Entry(0f, 10f));
        linedataentries.add(new Entry(1f, 300f));
        linedataentries.add(new Entry(2f, 500f));
        linedataentries.add(new Entry(3f, 69f));
        linedataentries.add(new Entry(4f, 10f));
        linedataentries.add(new Entry(5f, 160f));
        linedataentries.add(new Entry(6f, 109f));
        linedataentries.add(new Entry(7f, 205f));
        linedataentries.add(new Entry(8f, 300f));
        linedataentries.add(new Entry(9f, 450f));
        linedataentries.add(new Entry(10f, 1200f));
        linedataentries.add(new Entry(11f, 175f));
        return linedataentries;
    }

    private void xAxisSetup() {
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(12);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            final ArrayList<String> xAxisLabels = new AxisLabels("Weekly","").setXAxisLabels();
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if(((int)value) < xAxisLabels.size())
                    return xAxisLabels.get((int) value) ;
                else
                    return "0";
            }
        });
    }

    private void lineChartSetup() {
        lineChart.fitScreen();
        lineChart.setDrawBorders(false);
        lineChart.setDrawGridBackground(false);
        lineChart.getDescription().setEnabled(false);
        lineChart.getAxisRight().setDrawGridLines(false);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getAxisRight().setEnabled(false);
    }

    public ArrayList<Integer> getColors() {
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(0, 48, 89));
        colors.add(Color.rgb(191, 182, 0));
        colors.add(Color.rgb(163, 87, 0));
        return colors;
    }

}