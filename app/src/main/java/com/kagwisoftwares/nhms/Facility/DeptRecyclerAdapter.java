package com.kagwisoftwares.nhms.Facility;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kagwisoftwares.nhms.R;

import java.util.ArrayList;

public class DeptRecyclerAdapter extends RecyclerView.Adapter<DeptRecyclerAdapter.ViewHolder> {

    private ArrayList<Department> data;

    public DeptRecyclerAdapter(ArrayList<Department> data) {
        this.data = data;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name, visits;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.deptName);
            visits = (TextView) view.findViewById(R.id.deptVisits);
        }

        public TextView getName() {
            return name;
        }

        public TextView getVisits() {
            return visits;
        }
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
        holder.getName().setText(String.valueOf(department.getDeptName()));
        holder.getVisits().setText(String.valueOf(department.getDeptVisits()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
