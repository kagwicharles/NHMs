package com.kagwisoftwares.nhms.ui.dashboard;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.kagwisoftwares.nhms.Facility.AxisLabels;
import com.kagwisoftwares.nhms.Facility.DashMenu;
import com.kagwisoftwares.nhms.Facility.DashboardData;
import com.kagwisoftwares.nhms.Facility.DashboardListAdapter;
import com.kagwisoftwares.nhms.Facility.StartSnapHelper;
import com.kagwisoftwares.nhms.Facility.VolleySingleton;
import com.kagwisoftwares.nhms.R;
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener;
import com.skydoves.powerspinner.PowerSpinnerView;

import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DashboardFragment extends Fragment {

    private BarChart barChart;
    private PowerSpinnerView spinner;
    private Context context;
    private RecyclerView dashRv;
    private LottieAnimationView loading;
    private int Year, Month, Day;
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;

    private  ArrayList<BarEntry> set;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbarMain);
        toolbar.setTitle("Dashboard");
        toolbar.inflateMenu(R.menu.toolbar_menu);

        context = getContext().getApplicationContext();
        set = new ArrayList<>();
        spinner = view.findViewById(R.id.rangeSpinner);
        spinnerSetup();

        loading = view.findViewById(R.id.loading);

        dashRv = view.findViewById(R.id.dashRV);

        barChart = view.findViewById(R.id.dashBarchart);
        getMonthlyDataSet(context, "Monthly");

        spinner.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener<Object>() {
            @Override
            public void onItemSelected(int i, @Nullable Object o, int i1, Object t1) {
                switch (i1) {
                    case 0:
                        Toast.makeText(getContext(), "daily selected!", Toast.LENGTH_SHORT).show();
                        //fillBarChart(t1.toString());
                        break;
                    case 1:
                        Toast.makeText(getContext(), "Weekly selected!", Toast.LENGTH_SHORT).show();
                        datePicker();
                        fillBarChart(t1.toString(), getDataSet(t1.toString()));
                        break;
                    case 2:
                        Toast.makeText(getContext(), "Monthly selected!", Toast.LENGTH_SHORT).show();
                        getMonthlyDataSet(context, t1.toString());
                        break;
                    case 3:
                        Toast.makeText(getContext(), "Yearly selected!", Toast.LENGTH_SHORT).show();
                        fillBarChart(t1.toString(), getDataSet(t1.toString()));
                        break;
                    default:
                        Toast.makeText(getContext(), "Not set up!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        StartSnapHelper startSnapHelper = new StartSnapHelper();
        startSnapHelper.attachToRecyclerView(dashRv);
        return view;
    }

    public void fillBarChart(String frequency, BarDataSet dataSet) {
        xAxisSetup(frequency);
        yAxisSetup();
        BarData barData = new BarData(dataSet);
        barChartSetup();
        barChart.setData(barData);
        barChart.animateXY(500, 500);
        barChart.invalidate();
    }

    public ArrayList<DashMenu> setDashData(String totalRev) {
        ArrayList<DashMenu> dashItems = new ArrayList<>();
        dashItems.add(new DashMenu("Totals", totalRev, R.drawable.ic_salary));
        dashItems.add(new DashMenu("Profits", pettyCount(350932), R.drawable.ic_profit));
        dashItems.add(new DashMenu("Expenses", pettyCount(81983), R.drawable.ic_expenses));
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

    private void xAxisSetup(String frequeny) {
        XAxis xAxis = barChart.getXAxis();
        if (frequeny.equals("weekly"))
            xAxis.setLabelCount(7);
        if (frequeny.equals("Monthly"))
            xAxis.setLabelCount(12);
        if (frequeny.equals("Yearly"))
            xAxis.setLabelCount(5);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            final ArrayList<String> xlabels = new AxisLabels(frequeny, "").setXAxisLabels();
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if(((int)value) < xlabels.size())
                    return xlabels.get((int) value) ;
                else
                    return "0";
            }
        });
    }

    private void yAxisSetup() {
        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setValueFormatter(new LargeValueFormatter());
    }

    private void barChartSetup() {
        barChart.fitScreen();
        barChart.setDrawGridBackground(false);
        barChart.setDrawValueAboveBar(false);
        barChart.getDescription().setEnabled(false);
        barChart.getAxisRight().setDrawGridLines(true);
        barChart.getAxisLeft().setDrawGridLines(true);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getAxisRight().setEnabled(false);
    }

    private BarDataSet getDataSet(String frequency) {
        if (frequency.equals("Weekly"))
            return new DashboardData().getWeeklyDataSet();
        if (frequency.equals("Yearly"))
            return new DashboardData().getYearlyDataSet();
        return null;
    }

    public void getMonthlyDataSet(Context context, String frequency) {
        final String url = "http://kagwi.heliohost.us/NHMs/revenue/totalRegistrationRevenue/1";
        RequestQueue requestQueue = VolleySingleton.getInstance(context).getRequestQueue();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String res = response.toString();

                        try {
                            JSONObject object = new JSONObject(res);
                            JSONArray jsonArray = object.getJSONArray("Registration revenue (Mukuruweini)");
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            JSONArray regObj = jsonObject.getJSONArray("Monthly");
                            JSONArray totalObj = jsonObject.getJSONArray("Total revenue");

                            int i = 0;
                            while (i<12) {
                                JSONObject monthObj = regObj.getJSONObject(i);
                                set.add(new BarEntry(i, (float) monthObj.getDouble(new DashboardData().getMonths()[i])));
                                i++;
                            }

                            JSONObject totalRevenueObj = totalObj.getJSONObject(0);
                            Double amount = totalRevenueObj.getDouble("Totals");
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                            dashRv.setLayoutManager(linearLayoutManager);
                            dashRv.setAdapter(new DashboardListAdapter(setDashData(pettyCount(amount))));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        fillBarChart(frequency, new DashboardData().dataSetSetup(set));
                        loading.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.setVisibility(View.GONE);
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public String pettyCount(Number number) {
        char[] suffix = {' ', 'K', 'M', 'B', 'T'};
        long numValue = number.longValue();
        int value = (int) Math.floor(Math.log10(numValue));
        int base = value / 3;
        if (value >= 3 && base < suffix.length) {
            return new DecimalFormat("#0.0").format(numValue / Math.pow(10, base *3)) + suffix[base];
        } else {
            return new DecimalFormat("#,##0").format(numValue);
        }
    }

    public void datePicker() {
        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                String date = String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(day);
                Toast.makeText(getContext(), date, Toast.LENGTH_SHORT).show();
                calendar.set(year, month, day);
                Log.d("Date ", sdf.format(calendar.getTime()));
                calendar.add(Calendar.DAY_OF_MONTH, 8);
                Log.d("After adding 8 days ", sdf.format(calendar.getTime()));
            }
        }, Year, Month, Day);
        datePickerDialog.show();
    }

    public void getWeeklyDataSet(Context context, String frequency, String date1, String date2) {
        final String url = "http://kagwi.heliohost.us/NHMs/revenue/weeklyRegistrationRevenue/1/"+date1+"/"+date2;
        RequestQueue requestQueue = VolleySingleton.getInstance(context).getRequestQueue();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String res = response.toString();
                        Log.d("RESPONSE ", res);

                        try {
                            JSONObject object = new JSONObject(res);
                            JSONArray jsonArray = object.getJSONArray("Registration revenue (Mukuruweini)");
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            JSONArray regObj = jsonObject.getJSONArray("Registrations");
                            JSONArray totalObj = jsonObject.getJSONArray("Total revenue");

                            int i = 0;
                            while (i<8) {
                                JSONObject dayObj = regObj.getJSONObject(i);
                                set.add(new BarEntry(i, (float) dayObj.getDouble("")));
                                i++;
                            }

                            JSONObject totalRevenueObj = totalObj.getJSONObject(0);
                            Double amount = totalRevenueObj.getDouble("Totals");
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                            dashRv.setLayoutManager(linearLayoutManager);
                            dashRv.setAdapter(new DashboardListAdapter(setDashData(pettyCount(amount))));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        fillBarChart(frequency, new DashboardData().dataSetSetup(set));
                        loading.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error ", error.toString());
                loading.setVisibility(View.GONE);
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

}