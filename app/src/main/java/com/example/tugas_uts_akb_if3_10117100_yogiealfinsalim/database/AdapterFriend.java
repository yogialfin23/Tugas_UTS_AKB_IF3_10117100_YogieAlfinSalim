package com.example.tugas_uts_akb_if3_10117100_yogiealfinsalim.database;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tugas_uts_akb_if3_10117100_yogiealfinsalim.R;
import com.example.tugas_uts_akb_if3_10117100_yogiealfinsalim.view.FriendUpdateActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

/** NIM : 10117091
 * Nama : Dida Handian
 * Kelas : IF-3
 * Tanggal : 11-05-2020
 **/

public class AdapterFriend extends RecyclerView.Adapter<AdapterFriend.FriendViewHolder> {
    private ArrayList<friend> listFriend = new ArrayList<>();
    private Activity activity;

    public AdapterFriend(Activity activity){
        this.activity = activity;
    }

    public ArrayList<friend> getListNotes(){
        return listFriend;
    }

    public void setListNotes(ArrayList<friend> listNotes){
        if (listNotes.size() > 0 ){
            this.listFriend.clear();
        }
        this.listFriend.addAll(listNotes);

        notifyDataSetChanged();
    }

    public void addItem(friend note){
        this.listFriend.add(note);
        notifyItemInserted(listFriend.size() -1);
    }

    public void updateItem(int position, friend note){
        this.listFriend.set(position, note);
        notifyItemChanged(position, note);
    }

    public void removeItem(int position){
        this.listFriend.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listFriend.size());
    }

    @NonNull
    @Override
    public AdapterFriend.FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFriend.FriendViewHolder holder, int position) {
        holder.tvNim.setText(listFriend.get(position).getNim());
        holder.tvNama.setText(listFriend.get(position).getNama());
        holder.tvKelas.setText(listFriend.get(position).getKelas());
        holder.tvTelp.setText(listFriend.get(position).getTelpon());
        holder.tvEmail.setText(listFriend.get(position).getEmail());
        holder.tvIg.setText(listFriend.get(position).getIg());
        holder.tvDate.setText(listFriend.get(position).getDate());
        holder.cvNote.setOnClickListener(new CustomClickListener(position, new CustomClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(activity, FriendUpdateActivity.class);
                intent.putExtra(FriendUpdateActivity.EXTRA_POSITION, position);
                intent.putExtra(FriendUpdateActivity.EXTRA_NOTE, listFriend.get(position));
                activity.startActivityForResult(intent, FriendUpdateActivity.REQUEST_UPDATE);
            }
        }));
    }

    @Override
    public int getItemCount() {
        return listFriend.size();
    }

    public class FriendViewHolder extends RecyclerView.ViewHolder{
        final TextView tvNim, tvNama, tvKelas, tvTelp, tvEmail, tvIg, tvDate;
        final CardView cvNote;

        public FriendViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNim = itemView.findViewById(R.id.tv_item_nim);
            tvNama = itemView.findViewById(R.id.tv_item_nama);
            tvKelas = itemView.findViewById(R.id.tv_item_kelas);
            tvTelp = itemView.findViewById(R.id.tv_item_telpon);
            tvEmail = itemView.findViewById(R.id.tv_item_email);
            tvIg = itemView.findViewById(R.id.tv_item_ig);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            cvNote = itemView.findViewById(R.id.cv_item_note);
        }
    }
}
