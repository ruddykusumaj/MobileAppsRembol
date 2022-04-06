package ProjectMobile.com;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;

public class UpdateDataDialog extends AppCompatDialogFragment {
    private TextView upidmobil, upjenismobil, uphargamobil, upnamamobil, upidlist, upstatus;
    private UpdateDataDialogListener listener;

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view =  inflater.inflate(R.layout.update_data_dialog,null);

        builder.setView(view)
                .setTitle("Update Data Mobil")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                         String idlist = upidlist.getText().toString();
                         String namamobil = upnamamobil.getText().toString();
                         String harga = uphargamobil.getText().toString();
                         listener.update(idlist,namamobil, harga);
                    }
                });
        upidmobil = view.findViewById(R.id.txt_idmobilup);
        upjenismobil = view.findViewById(R.id.txt_jenismobil);
        uphargamobil = view.findViewById(R.id.txt_hargamobil);
        upnamamobil = view.findViewById(R.id.txt_namamobil);
        upidlist = view.findViewById(R.id.txt_idListup);
        upstatus = view.findViewById(R.id.txt_statusup);

        //SET TEXT dengan GLOBAL VARIABLE
        upidmobil.setText(global.gidmobil);
        upidlist.setText(global.gidlist);
        upjenismobil.setText(global.gkategori);
        uphargamobil.setText(global.ghargasewa);
        upnamamobil.setText(global.gnamamobil);
        upstatus.setText(global.gstatusmobil);


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
        void update(String idlist, String namamobil, String harga);
    }
}

































