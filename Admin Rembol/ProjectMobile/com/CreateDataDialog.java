package ProjectMobile.com;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

public class CreateDataDialog extends AppCompatDialogFragment {

    private EditText addkomen, namamobil1, hargamobil, statusmob1;
    private CreateDialogListener listener;
    RadioGroup radioGroup;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view =  inflater.inflate(R.layout.create_data_dialog,null);

        radioGroup = view.findViewById(R.id.radiogroup);




        builder.setView(view)
                .setTitle("Tambah Mobil")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String namamobil = namamobil1.getText().toString();
                        String harga = hargamobil.getText().toString();
                        String statusmob = statusmob1.getText().toString();
                        String idjenis = global.cidjenis;
                        listener.post(idjenis,namamobil,harga,statusmob);
                        global.cidjenis = "";



                    }
                });

        namamobil1 = view.findViewById(R.id.input_namamobil);
        hargamobil = view.findViewById(R.id.input_hargamobil);
        statusmob1 = view.findViewById(R.id.input_statusmobil);

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
        void post(String idJenis, String namamobil, String harga, String status);
    }

}





























