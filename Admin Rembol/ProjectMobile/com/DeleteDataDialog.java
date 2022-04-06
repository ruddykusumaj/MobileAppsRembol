package ProjectMobile.com;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DeleteDataDialog extends AppCompatDialogFragment {
    private TextView delidmobil, deljenismobil, delhargamobil, delnamamobil, delidlist, delstatus;
    private DeleteDataDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view =  inflater.inflate(R.layout.delete_data_dialog,null);

        builder.setView(view)
                .setTitle("Delete Mobil")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      String id = delidlist.getText().toString();
                      listener.delete(id);


                    }
                });
        delidmobil = view.findViewById(R.id.txt_idmobildel);
        deljenismobil = view.findViewById(R.id.txt_jenismobildel);
        delhargamobil = view.findViewById(R.id.txt_hargamobildel);
        delnamamobil = view.findViewById(R.id.txt_namamobildel);
        delidlist = view.findViewById(R.id.txt_idListdel);
        delstatus = view.findViewById(R.id.txt_statusupdel);


        //SET TEXT dengan GLOBAL VARIABLE
        delidmobil.setText(global.gidmobil);
        delidlist.setText(global.gidlist);
        deljenismobil.setText(global.gkategori);
        delhargamobil.setText(global.ghargasewa);
        delnamamobil.setText(global.gnamamobil);
        delstatus.setText(global.gstatusmobil);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (DeleteDataDialogListener) context;
        } catch (ClassCastException e){

        }


    }

    public interface DeleteDataDialogListener {
        void delete(String id);
    }
}













































