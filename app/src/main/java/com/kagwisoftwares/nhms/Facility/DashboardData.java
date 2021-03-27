package com.kagwisoftwares.nhms.Facility;

import android.appwidget.AppWidgetProviderInfo;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.kagwisoftwares.nhms.ui.dashboard.DashboardFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DashboardData {

    private BarDataSet barDataSet;
    private RequestQueue requestQueue;
    private JsonObjectRequest jsonObjectRequest;
    final String url = "https://nhms-nyeri.osc-fr1.scalingo.io/revenue/registrationRevenue/1";

    public DashboardData() {

    }

    public BarDataSet getMonthlyDataSet(Context context) {
        requestQueue = VolleySingleton.getInstance(context).getRequestQueue();

        ArrayList<BarEntry> set = new ArrayList<>();
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                        public void onResponse(JSONObject response) {
                            String res = response.toString();

                            try {
                                JSONObject object = new JSONObject(res);
                                JSONArray jsonArray = object.getJSONArray("Registration revenue (Mukuruweini)");
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                JSONArray regObj = jsonObject.getJSONArray("Monthly");

                                int i = 0;
                                while (i<12) {
                                    JSONObject monthObj = regObj.getJSONObject(i);
                                    set.add(new BarEntry(i, (float) monthObj.getDouble(getMonths()[i])));
                                    i++;
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.d("SET ", set.toString());
                    }
                 }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
         });

        requestQueue.add(jsonObjectRequest);
        Log.d("SET ", set.toString());
        return dataSetSetup(set);
    }

    public BarDataSet getWeeklyDataSet() {
        List<BarEntry> set1 = new ArrayList<>();
        set1.add(new BarEntry(0f, 100));
        set1.add(new BarEntry(1f, 80));
        set1.add(new BarEntry(2f, 20));
        set1.add(new BarEntry(3f, 300));
        set1.add(new BarEntry(4f, 40));
        set1.add(new BarEntry(5f, 50));
        set1.add(new BarEntry(6f, 50));
        return dataSetSetup(set1);
    }

    public BarDataSet getYearlyDataSet() {
        List<BarEntry> set1 = new ArrayList<>();
        set1.add(new BarEntry(0f, 100));
        set1.add(new BarEntry(1f, 80));
        set1.add(new BarEntry(2f, 20));
        set1.add(new BarEntry(3f, 300));
        set1.add(new BarEntry(4f, 40));
        return dataSetSetup(set1);
    }

    public BarDataSet dataSetSetup(List<BarEntry> entries) {
        barDataSet = new BarDataSet(entries, "Total revenue");
        barDataSet.setDrawValues(false);
        barDataSet.setColor(Color.rgb(0, 48, 89));
        return barDataSet;
    }

    public String[] getMonths() {
        String[] xlabels = new String[12];
        xlabels[0]=("JAN");
        xlabels[1]=("FEB");
        xlabels[2]=("MAR");
        xlabels[3]=("APR");
        xlabels[4]=("MAY");
        xlabels[5]=("JUN");
        xlabels[6]=("JUL");
        xlabels[7]=("AUG");
        xlabels[8]=("SEP");
        xlabels[9]=("OCT");
        xlabels[10]=("NOV");
        xlabels[11]=("DEC");
        return xlabels;
    }

    public String[] getDays() {
        String[] xlabels = new String[12];
        xlabels[0]=("MON");
        xlabels[1]=("TUE");
        xlabels[2]=("WED");
        xlabels[3]=("THU");
        xlabels[4]=("FRI");
        xlabels[5]=("SAT");
        xlabels[6]=("SUN");
        return xlabels;
    }

}
