package com.example.tugas_uts_akb_if3_10117100_yogiealfinsalim.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tugas_uts_akb_if3_10117100_yogiealfinsalim.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

/** NIM : 10117100
 * Nama : Yogie Alfin Salim
 * Kelas : IF-3
 * Tanggal : 12-05-2020
 **/

public class ContactActivity extends Fragment {
    public ContactActivity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        // Inflate the layout for this fragment
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        return inflater.inflate(R.layout.activity_contact, container, false);

    }
}
