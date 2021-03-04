package com.kagwisoftwares.nhms.ui.Staff;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.kagwisoftwares.nhms.R;

import java.util.ArrayList;

public class HomeMainFragment extends Fragment {
    private PieChart pieChart;
    private LineChart lineChart;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbarMain);
        toolbar.setTitle("Dashboard");
        pieChart = view.findViewById(R.id.totalsPieChart);
        fillPie();
        lineChart = view.findViewById(R.id.totalsLineChart);
        fillLineChart();
        return view;
    }

    public void fillPie() {
        pieChart.setUsePercentValues(true);
        pieChart.setExtraOffsets(2, 5, 2, 2);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);
        pieChart.getDescription().setEnabled(false);

        ArrayList<PieEntry> arrayList1 = new ArrayList<>();
        arrayList1.add(new PieEntry(20f, "Registrations"));
        arrayList1.add(new PieEntry(30f, "Services"));
        arrayList1.add(new PieEntry(20f, "Other fees"));
        PieDataSet pieDataSet1 = new PieDataSet(arrayList1, "");
        pieDataSet1.setSliceSpace(3f);
        pieDataSet1.setSelectionShift(5f);
        int[] colors = {Color.rgb(50, 143, 168), Color.rgb(50, 86, 168), Color.rgb(58, 168, 50)};
        ArrayList<Integer> list1 = new ArrayList<>();
        for (int c: colors) {
            list1.add(c);
        }
        pieDataSet1.setColors(list1);
        pieDataSet1.setColors(ColorTemplate.createColors(colors));

        PieData pieData = new PieData(pieDataSet1);
        pieData.setValueTextSize(18f);
        pieData.setValueTextColor(Color.WHITE);
        pieChart.setData(pieData);
        pieChart.setCenterTextSize(30f);
        pieChart.setDrawEntryLabels(false);
        pieChart.animateY(1000, Easing.getEasingFunctionFromOption(Easing.EasingOption.EaseInOutCubic));
    }

    public String[] getSpinnerText() {
        return new String[]{"Select facility...", "Mukuru-weini Hospital", "Karatina Hospital", "Othaya Hospital", "Naromoru Hospital"};
    }

    public void fillLineChart() {
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
        linedataentries.add(new Entry(10f, 900f));
        linedataentries.add(new Entry(11f, 175f));
        LineDataSet lineDataSet = new LineDataSet(linedataentries, "");
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.setColor(Color.GREEN);
        lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);

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
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if(((int)value) < xlabels.size())
                    return xlabels.get((int) value) ;
                else
                    return "0";
                }
            });
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineChart.setDrawBorders(false);
        lineChart.setDrawGridBackground(false);
        lineChart.animateXY(2000, 2000);
        lineChart.invalidate();
    }
}
