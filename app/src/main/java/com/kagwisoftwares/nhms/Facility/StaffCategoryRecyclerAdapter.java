package com.kagwisoftwares.nhms.Facility;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kagwisoftwares.nhms.R;

import java.util.ArrayList;

public class StaffCategoryRecyclerAdapter extends RecyclerView.Adapter<StaffCategoryRecyclerAdapter.ViewHolder> {

    private String[] data;

    public StaffCategoryRecyclerAdapter(String[] data) {
        this.data = data;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.category);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    @NonNull
    @Override
    public StaffCategoryRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.staff_category_item, parent, false);
        return new StaffCategoryRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String cat = data[position];
        holder.getTextView().setText(cat);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

}
