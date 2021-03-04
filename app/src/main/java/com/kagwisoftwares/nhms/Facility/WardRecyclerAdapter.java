package com.kagwisoftwares.nhms.Facility;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kagwisoftwares.nhms.R;

import java.util.ArrayList;

public class WardRecyclerAdapter extends RecyclerView.Adapter<WardRecyclerAdapter.ViewHolder> {

    private ArrayList<Ward> data;

    public WardRecyclerAdapter(ArrayList<Ward> data) {
        this.data = data;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.wardNum);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    @NonNull
    @Override
    public WardRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ward_item, parent, false);
        return new WardRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ward ward = data.get(position);
        holder.getTextView().setText(String.valueOf(ward.getWardNo()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

 }
