package ProjectMobile.com;

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
    public void onBindViewHolder(ProjectMobile.com.RecyclerAdapter.MyViewHolder holder, int position) {

        holder.jenismob.setText(arrayList.get(position).getJenismobil());
        holder.namamob.setText(arrayList.get(position).getNamamobil());
        holder.hargasewa.setText(arrayList.get(position).getHargamobil());
        holder.idList.setText(arrayList.get(position).getIdList());
        holder.idmobil.setText(arrayList.get(position).getIdMobil());
        holder.rstat.setText(arrayList.get(position).getStatusmobil());




        if (holder.rstat.getText().toString().equals("belum")) {
            holder.CV.setCardBackgroundColor(Color.parseColor("#FF0000"));
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView jenismob, idList, namamob, hargasewa, rstat, idmobil;
        CardView CV;


        public MyViewHolder(View itemView) {
            super(itemView);
            jenismob = itemView.findViewById(R.id.txt_jenismob);
            namamob = itemView.findViewById(R.id.txt_namamob);
            idList = itemView.findViewById(R.id.txt_idList);
            hargasewa = itemView.findViewById(R.id.txt_hargamob);
            rstat = itemView.findViewById(R.id.txt_status);
            idmobil = itemView.findViewById(R.id.txt_AidMobil);

            CV = itemView.findViewById(R.id.CV);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    global.gkategori = jenismob.getText().toString();
                    global.gnamamobil = namamob.getText().toString();
                    global.ghargasewa = hargasewa.getText().toString();
                    global.gidlist = idList.getText().toString();
                    global.gidmobil = idmobil.getText().toString();
                    global.gstatusmobil = rstat.getText().toString();
                    openUpdateDialog();


                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    global.gkategori = jenismob.getText().toString();
                    global.gnamamobil = namamob.getText().toString();
                    global.ghargasewa = hargasewa.getText().toString();
                    global.gidlist = idList.getText().toString();
                    global.gidmobil = idmobil.getText().toString();
                    global.gstatusmobil = rstat.getText().toString();
                    openDeleteDialog();

                    return false;
                }
            });

        }
        FragmentManager fragmentManager = ((FragmentActivity) itemView.getContext()).getSupportFragmentManager();

        public void openUpdateDialog() {
            UpdateDataDialog updateDataDialog = new UpdateDataDialog();

            updateDataDialog.show(fragmentManager, "update dialog");
        }

        public void openDeleteDialog() {
            DeleteDataDialog deleteDataDialog = new DeleteDataDialog();
            deleteDataDialog.show(fragmentManager, "delete dialog");
        }
    }
}


