package com.example.testrembol;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<Data> arrayList = new ArrayList<>();

    public RecyclerAdapter(ArrayList<Data> arrayList) {
        this.arrayList = arrayList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.MyViewHolder holder, int position) {
        holder.nmobil.setText(arrayList.get(position).getNmobil());
        holder.alamat.setText(arrayList.get(position).getAlamat());
        holder.Notelp.setText(arrayList.get(position).getRtelp());
        holder.rid.setText(arrayList.get(position).getRidlist());
        holder.rstat.setText(arrayList.get(position).getRstat());
        holder.ridorder.setText(arrayList.get(position).getRidorder());

        if (holder.rstat.getText().toString().equals("belum")) {
            holder.CV.setCardBackgroundColor(Color.parseColor("#FF0000"));
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nmobil, alamat, Notelp, rid, rstat, ridorder;
        CardView CV;


        public MyViewHolder(View itemView) {
            super(itemView);
            nmobil = (TextView) itemView.findViewById(R.id.txt_mob);
            Notelp = (TextView) itemView.findViewById(R.id.txt_telp);
            alamat = (TextView) itemView.findViewById(R.id.txt_lokasi);
            rid = (TextView) itemView.findViewById(R.id.txt_idlist);
            rstat = (TextView) itemView.findViewById(R.id.txt_stat);
            ridorder = (TextView) itemView.findViewById(R.id.txt_idorder);

            CV = (CardView) itemView.findViewById(R.id.CV);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    global.Ridmobil = rid.getText().toString();
                    global.idorder =ridorder.getText().toString();
                    global.Rnmobil = nmobil.getText().toString();
                    global.RTelp = Notelp.getText().toString();
                    global.Ralamat = alamat.getText().toString();
//                    v.getContext().startActivity(intent);
                    openUpdateDialog();

                }
            });


        }

        public void openUpdateDialog() {
            UpdateDataDialog updateDataDialog = new UpdateDataDialog();
            FragmentManager fragmentManager = ((FragmentActivity) itemView.getContext()).getSupportFragmentManager();
            updateDataDialog.show(fragmentManager, "update dialog");
        }
    }
}


