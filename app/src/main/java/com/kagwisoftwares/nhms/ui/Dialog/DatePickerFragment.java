package com.kagwisoftwares.nhms.ui.Dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;

import com.kagwisoftwares.nhms.R;

public class DatePickerFragment extends DialogFragment {

    public DatePickerFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.date_picker, container, false);

        return view;
    }

}
