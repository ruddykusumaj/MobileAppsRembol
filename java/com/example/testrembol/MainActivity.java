package com.example.testrembol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

public class MainActivity extends AppCompatActivity {

    //-----------carousell-------------
    private int[] gambar = new int[] {
            R.drawable.mobil1, R.drawable.mobil2, R.drawable.mobil3, R.drawable.mobil4, R.drawable.mobil5
    };
    private String[] judulGambar = new String[] {
            "mobil1", "mobil2", "mobil3", "mobil4","mobil5"
    };
    //---------------------------------




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //---------------------carousell--------------------------------
        CarouselView carouselView = findViewById(R.id.slide);
        carouselView.setPageCount(gambar.length);

        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(gambar[position]);
            }
        });
        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(MainActivity.this, gambar[position], Toast.LENGTH_SHORT).show();
            }
        });
        //--------------------------------------------------------------


        //------------------tombol mobil 5 penumpang---------------------
        CardView mobil5 = findViewById(R.id.card_view);
        mobil5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Pemesanan.class);
                global.jenismob = String.valueOf(mobil5);
                global.globalURL ="http://192.168.8.107/REMBOL/mobil.php?jenismobil=Penumpang 5 Orang";
                startActivity(intent);
            }
        });
        //---------------------------------------------------------------

        //------------------tombol mobil 8 penumpang---------------------
        CardView mobil8 = findViewById(R.id.card_view2);
        mobil8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Pemesanan.class);
                global.jenismob = String.valueOf(mobil8);
                global.globalURL ="http://192.168.8.107/REMBOL/mobil.php?jenismobil=Penumpang 8 Orang";
                startActivity(intent);
            }
        });
        //---------------------------------------------------------------


        //------------------tombol mobil pickup---------------------
        CardView minibus = findViewById(R.id.card_view3);
        minibus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Pemesanan.class);
                global.jenismob = String.valueOf(minibus);
                global.globalURL ="http://192.168.8.107/REMBOL/mobil.php?jenismobil=Minibus";
                startActivity(intent);
            }
        });
        //---------------------------------------------------------------


        //------------------tombol mobil pickup---------------------
        CardView pickup = findViewById(R.id.card_view4);
        pickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Pemesanan.class);
                global.jenismob = String.valueOf(pickup);
                global.globalURL ="http://192.168.8.107/REMBOL/mobil.php?jenismobil=Pickup";
                startActivity(intent);
            }
        });
        //---------------------------------------------------------------



//        NAVBAR SECTION
        LinearLayout home = findViewById(R.id.homeSection);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainActivity.class));
                overridePendingTransition(0, 0);
            }
        });
        LinearLayout payment = findViewById(R.id.paymentSection);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PaymentActivity.class));
                overridePendingTransition(0, 0);
            }
        });
        LinearLayout profile = findViewById(R.id.profileSection);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ProfileActivity.class));
                overridePendingTransition(0, 0);
            }
        });

    }
}