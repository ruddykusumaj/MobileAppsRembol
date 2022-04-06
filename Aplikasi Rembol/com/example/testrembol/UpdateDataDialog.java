package com.example.testrembol;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;

public class UpdateDataDialog extends AppCompatDialogFragment {
    private TextView updatekomen, idmobil, unamamobil, utelp, ualamats, txtidorder;
    private UpdateDataDialogListener listener;

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view =  inflater.inflate(R.layout.update_data_dialog,null);

        builder.setView(view)
                .setTitle("Konfirmasi Pesanan")
                .setNegativeButton("Belum", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Selesai", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String idorder = txtidorder.getText().toString();
                        String idmobils = idmobil.getText().toString();
                        String id = idmobil.getText().toString();
                        listener.update(idorder,idmobils);
                        Intent intent = new Intent (getContext(), PaymentActivity.class);
                        getContext().startActivity(intent);



                    }
                });
        idmobil = view.findViewById(R.id.txt_idmobil);
        unamamobil = view.findViewById(R.id.txt_namamobil);
        utelp = view.findViewById(R.id.txt_telpuser);
        ualamats = view.findViewById(R.id.txt_alamatuser);
        txtidorder = view.findViewById(R.id.txt_idorders);


        //SET TEXT dengan GLOBAL VARIABLE
        idmobil.setText(global.Ridmobil);
        unamamobil.setText(global.Rnmobil);
        utelp.setText(global.RTelp);
        ualamats.setText(global.Ralamat);
        txtidorder.setText(global.idorder);



        return builder.create();
    }

    @Override
    public void onAttach( Context context) {
        super.onAttach(context);

        try {
            listener =(UpdateDataDialogListener) context;
        }catch (ClassCastException e){

        }
    }

    public interface UpdateDataDialogListener{
        void update(String idorder, String idmobil);
    }
}

































