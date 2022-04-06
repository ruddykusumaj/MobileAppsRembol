package ProjectMobile.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    TextView namaadmin;
    Button listmobil, tambahmobil, btn_catatan, logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        namaadmin = findViewById(R.id.namaadmin);
        namaadmin.setText("Hello, " + global.uname);

        listmobil = findViewById(R.id.btn_listmobil);
        btn_catatan = findViewById(R.id.btn_catatan);
        logout = findViewById(R.id.logout);

        listmobil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Lihat_Data.class);
                startActivity(i);
            }
        });



        btn_catatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), catatanAdmin.class);
                startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                global.uname = "";
                startActivity(intent);
                finish();
            }
        });
    }
}