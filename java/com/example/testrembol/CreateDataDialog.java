package com.example.testrembol;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;

public class CreateDataDialog extends AppCompatDialogFragment {

    private EditText addkomen;
    private CreateDialogListener listener;

    TextView vmobil, valamat, vtelp, vtanggal, vharga, vidm, vidu;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view =  inflater.inflate(R.layout.create_data_dialog,null);

        builder.setView(view)
                .setTitle("Pesan Mobil")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mobil = vmobil.getText().toString();
                        String alamat = valamat.getText().toString();
                        String telp = vtelp.getText().toString();
                        String tanggal = vtanggal.getText().toString();
                        String harga = vharga.getText().toString();
                        String iduserp = vidu.getText().toString();
                        String idlist = vidm.getText().toString();
                        listener.post(mobil, alamat,telp,tanggal,harga,iduserp,idlist);

                    }
                });

        vmobil = view.findViewById(R.id.mobil);
        valamat = view.findViewById(R.id.alamatuser);
        vharga = view.findViewById(R.id.hargasewa);
        vtanggal = view.findViewById(R.id.tanggalpsn);
        vtelp = view.findViewById(R.id.telpuser);
        vidm = view.findViewById(R.id.idlistmbl);
        vidu = view.findViewById(R.id.iduserpesan);

        vmobil.setText(global.mobil);
        valamat.setText(global.ualamat);
        vharga.setText(global.uprice);
        vtanggal.setText(global.udate);
        vtelp.setText(global.utelp);
        vidm.setText(global.idlist);
        vidu.setText(global.iduser);


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (CreateDialogListener) context;
        } catch (ClassCastException e){

        }
    }

    public interface CreateDialogListener{
        void post(String mobil,String alamat,String telp,String tanggal,String harga,String iduserp,String idlist);
    }

}





























