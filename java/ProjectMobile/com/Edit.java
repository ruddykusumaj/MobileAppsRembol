package ProjectMobile.com;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Edit extends AppCompatActivity {

    private long id;
    private String catatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        this.setTitle("Edit Catatan");
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
        Bundle bundle = this.getIntent().getExtras();
        id = bundle.getLong("id");
        catatan = bundle.getString("catatan");
        this.setTitle("Edit Catatan ID: ");
        edtCatatan.setText(catatan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Catatan catatan = new Catatan();
                catatan.setID(id);
                catatan.set_catatan(edtCatatan.getText().toString());
                dbHandler.updateCatatan(catatan);

                Toast.makeText(ProjectMobile.com.Edit.this, "Catatan berhasil diedit",Toast.LENGTH_LONG).show();
                Intent i = new Intent(ProjectMobile.com.Edit.this, catatanAdmin.class);
                startActivity(i);
                ProjectMobile.com.Edit.this.finish();
                dbHandler.close();
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProjectMobile.com.Edit.this, catatanAdmin.class);
                startActivity(i);
                ProjectMobile.com.Edit.this.finish();
                dbHandler.close();
            }
        });
    }

}