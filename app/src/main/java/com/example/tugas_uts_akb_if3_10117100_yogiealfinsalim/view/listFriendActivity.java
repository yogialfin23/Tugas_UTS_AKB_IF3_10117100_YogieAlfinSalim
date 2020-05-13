package com.example.tugas_uts_akb_if3_10117100_yogiealfinsalim.view;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.tugas_uts_akb_if3_10117100_yogiealfinsalim.R;
import com.example.tugas_uts_akb_if3_10117100_yogiealfinsalim.database.AdapterFriend;
import com.example.tugas_uts_akb_if3_10117100_yogiealfinsalim.database.FriendHelper;
import com.example.tugas_uts_akb_if3_10117100_yogiealfinsalim.database.MapHelper;
import com.example.tugas_uts_akb_if3_10117100_yogiealfinsalim.database.friend;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/** NIM : 10117100
 * Nama : Yogie Alfin Salim
 * Kelas : IF-3
 * Tanggal : 12-05-2020
 **/

public class listFriendActivity extends Fragment implements LoadFriendCallback {

    private ProgressBar progressBar;
    private RecyclerView rvNotes;
    private AdapterFriend adapter;
    private FriendHelper friendHelper;
    private static final String EXTRA_STATE = "EXTRA_STATE";
    private FloatingActionButton fabAdd;

//    private ImageView fabAdd;

    public listFriendActivity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.list_friend, container, false);



//        if ( ((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
//            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Friends");
//            ((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#03AC0E")));

        progressBar = view.findViewById(R.id.progressbar);
        rvNotes = view.findViewById(R.id.rv_notes);
        rvNotes.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvNotes.setHasFixedSize(true);
        adapter = new AdapterFriend(getActivity());
        rvNotes.setAdapter(adapter);

        fabAdd = view.findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FriendUpdateActivity.class);
                startActivityForResult(intent, FriendUpdateActivity.REQUEST_ADD);
            }
        });
//        }
        friendHelper = FriendHelper.getInstance(getActivity());
        friendHelper.open();

        if (savedInstanceState == null) {
            new LoadNotesAsync(friendHelper, this).execute();
        } else {
            ArrayList<friend> list = savedInstanceState.getParcelableArrayList(EXTRA_STATE);
            if (list != null) {
                adapter.setListNotes(list);
            }
        }


        return view;
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_STATE, adapter.getListNotes());
    }


    @Override
    public void preExecute() {
        new Runnable(){
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
            }
        };
    }

    @Override
    public void postExecute(ArrayList<friend> notes) {
        progressBar.setVisibility(View.INVISIBLE);
        if (notes.size() > 0) {
            adapter.setListNotes(notes);
        } else {
            adapter.setListNotes(new ArrayList<friend>());
            showSnackbarMessage("Tidak ada data saat ini");
        }
    }

    private static class LoadNotesAsync extends AsyncTask<Void, Void, ArrayList<friend>> {
        private final WeakReference<FriendHelper> weakNoteHelper;
        private final WeakReference<LoadFriendCallback> weakCallback;

        private LoadNotesAsync(FriendHelper noteHelper, LoadFriendCallback callback) {
            weakNoteHelper = new WeakReference<>(noteHelper);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().preExecute();
        }

        @Override
        protected ArrayList<friend> doInBackground(Void... voids) {
            Cursor dataCursor = weakNoteHelper.get().queryAll();
            return MapHelper.mapCursorToArrayList(dataCursor);
        }

        @Override
        protected void onPostExecute(ArrayList<friend> notes) {
            super.onPostExecute(notes);
            weakCallback.get().postExecute(notes);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            if (requestCode == FriendUpdateActivity.REQUEST_ADD) {
                if (resultCode == FriendUpdateActivity.RESULT_ADD) {
                    friend note = data.getParcelableExtra(FriendUpdateActivity.EXTRA_NOTE);

                    adapter.addItem(note);
                    rvNotes.smoothScrollToPosition(adapter.getItemCount() - 1);

                    showSnackbarMessage("Satu item berhasil ditambahkan");
                }
            } else if (requestCode == FriendUpdateActivity.REQUEST_UPDATE) {
                if (resultCode == FriendUpdateActivity.RESULT_UPDATE) {
                    friend note = data.getParcelableExtra(FriendUpdateActivity.EXTRA_NOTE);
                    int position = data.getIntExtra(FriendUpdateActivity.EXTRA_POSITION, 0);

                    adapter.updateItem(position, note);
                    rvNotes.smoothScrollToPosition(position);

                    showSnackbarMessage("Satu item berhasil dirubah");
                } else if (resultCode == FriendUpdateActivity.RESULT_DELETE) {
                    int position = data.getIntExtra(FriendUpdateActivity.EXTRA_POSITION, 0);

                    adapter.removeItem(position);

                    showSnackbarMessage("Satu item berhasil dihapus");
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        friendHelper.close();
    }

    private void showSnackbarMessage(String message) {
        Snackbar.make(rvNotes, message, Snackbar.LENGTH_SHORT).show();
    }
}

interface LoadFriendCallback{
    void preExecute();
    void postExecute(ArrayList<friend> notes);
}
