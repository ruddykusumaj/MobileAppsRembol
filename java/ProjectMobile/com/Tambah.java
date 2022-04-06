package ProjectMobile.com;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Tambah extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        final EditText edtCatatan = (EditText)findViewById(R.id.edtCatatan);
        Button btnReset = (Button)findViewById(R.id.btnReset);
        Button btnSimpan = (Button)findViewById(R.id.btnSimpan);
        //Buat objek untuk Class MyDBHandler
        final MyDBHandler dbHandler = new MyDBHandler(this);
        //Membuka koneksi database
        try {
            dbHandler.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catatan CATATAN = new Catatan();
                String catatan = edtCatatan.getText().toString();
                dbHandler.createCatatan(catatan);
                Intent i = new Intent(ProjectMobile.com.Tambah.this, catatanAdmin.class);
                startActivity(i);
                Toast.makeText(ProjectMobile.com.Tambah.this, "Catatan Berhasil dicatat", Toast.LENGTH_LONG).show();
                edtCatatan.setText("");
                edtCatatan.requestFocus();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtCatatan.setText("");
                edtCatatan.requestFocus();

                Intent i = new Intent(ProjectMobile.com.Tambah.this, catatanAdmin.class);
                startActivity(i);
                ProjectMobile.com.Tambah.this.finish();
                dbHandler.close();
            }
        });
    }
}