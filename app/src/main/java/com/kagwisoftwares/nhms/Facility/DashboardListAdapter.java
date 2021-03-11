package com.kagwisoftwares.nhms.Facility;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kagwisoftwares.nhms.R;

import java.util.ArrayList;

public class DashboardListAdapter extends RecyclerView.Adapter<DashboardListAdapter.ViewHolder> {

    private ArrayList<DashMenu> data;

    public DashboardListAdapter(ArrayList<DashMenu> data) {
        this.data = data;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView item1;
        private TextView item2;
        private ImageView item3;

        public ViewHolder(View view) {
            super(view);
            item1 = (TextView) view.findViewById(R.id.item1);
            item2 = (TextView) view.findViewById(R.id.item2);
            item3 = (ImageView) view.findViewById(R.id.dashIcon);
        }

        public TextView getItem1() {
            return item1;
        }
        public TextView getItem2() {return item2;}
        public ImageView getItem3() {return item3;}
    }

    @NonNull
    @Override
    public DashboardListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dash_item, parent, false);
        return new DashboardListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DashMenu item = data.get(position);
        holder.getItem1().setText(item.getItem1());
        Log.d("ITEM 1", item.getItem1());
        holder.getItem2().setText(item.getItem2());
        holder.getItem3().setImageResource(item.getItem3());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
