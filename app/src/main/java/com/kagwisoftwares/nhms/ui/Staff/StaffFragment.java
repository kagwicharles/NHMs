package com.kagwisoftwares.nhms.ui.Staff;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.kagwisoftwares.nhms.Facility.Department;
import com.kagwisoftwares.nhms.Facility.DeptStaff;
import com.kagwisoftwares.nhms.Facility.DeptStaffRecyclerAdapter;
import com.kagwisoftwares.nhms.Facility.OtherRecyclerAdapter;
import com.kagwisoftwares.nhms.Facility.OtherResources;
import com.kagwisoftwares.nhms.Facility.StartSnapHelper;
import com.kagwisoftwares.nhms.R;

import java.util.ArrayList;

public class StaffFragment extends Fragment {
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_staff, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbarStaff);
        toolbar.setTitle("Resources");

        RecyclerView staffRV = view.findViewById(R.id.staffRV);
        staffRV.setAdapter(new DeptStaffRecyclerAdapter(setDepartments()));
        staffRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(staffRV);

        RecyclerView othersRV = view.findViewById(R.id.othersRV);
        othersRV.setAdapter(new OtherRecyclerAdapter(setOtherResources()));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        othersRV.setLayoutManager(linearLayoutManager);
        othersRV.addItemDecoration(new DividerItemDecoration(getContext(), linearLayoutManager.getOrientation()));

        StartSnapHelper startSnapHelper = new StartSnapHelper();
        startSnapHelper.attachToRecyclerView(othersRV);

        return view;
    }

    private ArrayList<DeptStaff> setDepartments() {
        ArrayList<DeptStaff> list = new ArrayList<>();
        list.add(new DeptStaff("Orthopedic", R.drawable.orthopedic));
        list.add(new DeptStaff("Radiology", R.drawable.radiology));
        list.add(new DeptStaff("Pharmacy", R.drawable.pharmacy));
        list.add(new DeptStaff("Clinicians", R.drawable.clinician));
        list.add(new DeptStaff("Surgeons", R.drawable.surgeon));
        list.add(new DeptStaff("Dentists", R.drawable.dentist));
        list.add(new DeptStaff("Administrators", R.drawable.administrator));
        list.add(new DeptStaff("Support staff", R.drawable.support));
        return list;
    }

    private ArrayList<OtherResources> setOtherResources() {
        ArrayList<OtherResources> list = new ArrayList<>();
        list.add(new OtherResources("Transport", R.drawable.ambulance));
        list.add(new OtherResources("Wards", R.drawable.ward));
        list.add(new OtherResources("IT dept", R.drawable.it));
        list.add(new OtherResources("Store", R.drawable.hospital));
        return list;
    }
}