package com.kagwisoftwares.nhms.Facility;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kagwisoftwares.nhms.R;

import java.util.ArrayList;

public class DeptStaffRecyclerAdapter extends RecyclerView.Adapter<DeptStaffRecyclerAdapter.ViewHolder> {

    private ArrayList<DeptStaff> data;

    public DeptStaffRecyclerAdapter(ArrayList<DeptStaff> data) {
        this.data = data;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private ImageView deptIcon;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.staffdeptName);
            deptIcon = (ImageView) view.findViewById(R.id.staffdeptIcon);
        }

        public TextView getName() {
            return name;
        }

        public ImageView getDeptIcon() {
            return deptIcon;
        }
    }

    @NonNull
    @Override
    public DeptStaffRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.staff_dept_item, parent, false);
        return new DeptStaffRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DeptStaff department = data.get(position);
        holder.getName().setText(String.valueOf(department.getDeptName()));
        holder.getDeptIcon().setImageResource(department.getDeptIcon());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
