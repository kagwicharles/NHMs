package com.kagwisoftwares.nhms.Facility;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kagwisoftwares.nhms.R;

import java.util.ArrayList;
import java.util.List;

import app.futured.donut.DonutProgressView;
import app.futured.donut.DonutSection;

public class DeptRecyclerAdapter extends RecyclerView.Adapter<DeptRecyclerAdapter.ViewHolder> {

    private ArrayList<Department> data;
    private ArrayList<Integer> colors;

    public DeptRecyclerAdapter(ArrayList<Department> data, ArrayList<Integer> colors) {
        this.data = data;
        this.colors = colors;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name, visits;
        private DonutProgressView donut;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.deptName);
            visits = (TextView) view.findViewById(R.id.deptVisits);
            donut = (DonutProgressView) view.findViewById(R.id.deptIcon);
        }

        public TextView getName() {
            return name;
        }

        public TextView getVisits() {
            return visits;
        }

        public DonutProgressView getDonut() { return donut;}
    }

    @NonNull
    @Override
    public DeptRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dept_item, parent, false);
        return new DeptRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Department department = data.get(position);
        List<DonutSection> sections = new ArrayList<>();
        sections.add(new DonutSection("Section", colors.get(position), 1f));
        sections.add(new DonutSection("Section2", Color.LTGRAY, 1f));
        holder.getName().setText(String.valueOf(department.getDeptName()));
        holder.getVisits().setText(String.valueOf(department.getDeptVisits()));
        holder.getDonut().submitData(sections);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
