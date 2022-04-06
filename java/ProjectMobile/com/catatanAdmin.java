package ProjectMobile.com;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class catatanAdmin extends ListActivity implements AdapterView.OnItemLongClickListener {

    private MyDBHandler dbHandler;
    private ArrayList<Catatan> values;
    private Button btnEdit;
    private Button btnDelete;
    private ListView list;
    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catatan_admin);


        dbHandler = new MyDBHandler(this);

        try {
            dbHandler.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        values = dbHandler.getAllCatatan();

        ArrayAdapter<Catatan> adapter = new ArrayAdapter<Catatan>(this, android.R.layout.simple_expandable_list_item_1, values);

        setListAdapter(adapter);

        list = (ListView)findViewById(android.R.id.list);
        list.setOnItemLongClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Tambah.class);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.setTitle("Pilih Aksi");
        dialog.show();

        final Catatan catatan = (Catatan) getListAdapter().getItem(i);
        final long id = catatan.getID();

        btnEdit = dialog.findViewById(R.id.btnEdit);
        btnDelete = dialog.findViewById(R.id.btnHapus);


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catatan catatan = dbHandler.getCatatan(id);
                Intent i = new Intent(getApplicationContext(), Edit.class);
                Bundle bundle = new Bundle();
                bundle.putLong("id",catatan.getID());
                bundle.putString("catatan", catatan.get_catatan());
                i.putExtras(bundle);
                startActivity(i);
                dialog.dismiss();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder konfirm = new AlertDialog.Builder(context);
                konfirm.setTitle("Hapus Catatan");
                konfirm.setMessage("Anda yakin akan menghapus catatan ini ?");
                konfirm.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHandler.deleteCatatan(id);

                        finish();
                        startActivity(getIntent());

                        Toast.makeText(catatanAdmin.this, "Catatan berhasil dihapus", Toast.LENGTH_LONG).show();
                    }
                });
                konfirm.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                konfirm.show();
                dialog.dismiss();
            }
        });

        return true;
    }

}
