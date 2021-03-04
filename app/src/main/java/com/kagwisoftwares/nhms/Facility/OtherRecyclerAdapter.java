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

public class OtherRecyclerAdapter extends RecyclerView.Adapter<OtherRecyclerAdapter.ViewHolder> {

    private ArrayList<OtherResources> data;

    public OtherRecyclerAdapter(ArrayList<OtherResources> data) {
        this.data = data;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private ImageView deptIcon;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            deptIcon = (ImageView) view.findViewById(R.id.icon);
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
    public OtherRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.other_resource_item, parent, false);
        return new OtherRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OtherResources department = data.get(position);
        holder.getName().setText(String.valueOf(department.getDeptName()));
        holder.getDeptIcon().setImageResource(department.getDeptIcon());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
