package com.example.testrembol;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Pemesanan extends AppCompatActivity implements AdapterView.OnItemSelectedListener,CreateDataDialog.CreateDialogListener {
   Button buttonpsn;

    //----------spinner----------------
    TextView isi, isi2;
    Spinner spinnerList;
    TextView txtHarga, iduser,idList;
    EditText txtTanggal, txtNotelp, txtAlamat;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;
    String profuser = global.uname;
    ProgressDialog progressDialog;

    //--------------------------
    ArrayList<Data> arrayList = new ArrayList<>();
    String DATA_JSON_STRING, data_json_string;
    RequestQueue requestQueue;
    ArrayList<String> namamobil = new ArrayList<>();
    ArrayAdapter<String> mobilAdapter;
    ArrayList<String> hargasewa = new ArrayList<>();
    //---------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);
        buttonpsn = findViewById(R.id.buttonpsn);
        iduser = findViewById(R.id.iduser);
        idList = findViewById(R.id.idList);
        txtAlamat = findViewById(R.id.alamat);
        txtNotelp = findViewById(R.id.edtTlpn);


        //-----------TEXT EDIT------------
        txtHarga = findViewById(R.id.txtHarga);
        txtTanggal = findViewById(R.id.txtTanggal);
        isi = findViewById(R.id.iduser);
        isi2 = findViewById(R.id.idList);

        //-----------Tanggal------------
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd");


        //-----------spinner------------
        requestQueue = Volley.newRequestQueue(Pemesanan.this);
        spinnerList = findViewById(R.id.spinnerList);
        String URL = global.globalURL;
        //-------------------------------

        progressDialog = new ProgressDialog(Pemesanan.this);



        buttonpsn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                global.ualamat = txtAlamat.getText().toString();
                global.utelp = txtNotelp.getText().toString();
                global.udate = txtTanggal.getText().toString();
                global.uprice = txtHarga.getText().toString();
                global.mobil = spinnerList.getSelectedItem().toString();


                //Parse String To Int
                global.idlist = idList.getText().toString();


                global.iduser = iduser.getText().toString();


                openCreateDialog();
            }
        });


        requestQueue = Volley.newRequestQueue(Pemesanan.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, null, new Response.Listener<JSONObject>() {




            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("listmobil");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String namaMobil = jsonObject.optString("namamobil");
                        namamobil.add(namaMobil);
                        mobilAdapter = new ArrayAdapter<>(Pemesanan.this, android.R.layout.simple_spinner_dropdown_item, namamobil);
                        mobilAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerList.setAdapter(mobilAdapter);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        requestQueue.add(jsonObjectRequest);
        spinnerList.setOnItemSelectedListener(Pemesanan.this);
        txtTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }

            private void showDateDialog() {
                Calendar calendar = Calendar.getInstance();

                datePickerDialog = new DatePickerDialog(Pemesanan.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year,month,dayOfMonth);
                        txtTanggal.setText(dateFormatter.format(newDate.getTime()));
                    }
                }, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        String url = "http://192.168.8.107/REMBOL/profil.php?username=" + profuser;
        requestQueue = Volley.newRequestQueue(Pemesanan.this);
        JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("users");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String email = jsonObject.getString("iduser");
                        isi.setText(email);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest1);
    }
    public void openCreateDialog() {
        CreateDataDialog createDataDialog = new CreateDataDialog();
        createDataDialog.show(getSupportFragmentManager(), "create dialog");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId() == R.id.spinnerList){
            hargasewa.clear();
            String selectedMobil = parent.getSelectedItem().toString();
            String url2 = "http://192.168.8.107/REMBOL/test.php?namamobil="+selectedMobil;
            requestQueue = Volley.newRequestQueue(Pemesanan.this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url2, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("listmobil");
                        for(int i=0; i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String Hargasewa = jsonObject.getString("hargasewa");
                            txtHarga.setText(Hargasewa);
                            String idList = jsonObject.getString("idList");
                            isi2.setText(idList);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){

                }
            });
            requestQueue.add(jsonObjectRequest);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void createDataToServer(final String mobil,final String alamat,final String telp,final String tanggal,final String harga,final String iduserp,final String idlist) {
        if (chechNetworkConnection()) {
            progressDialog.show();
            getJSON();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, DbContract.SERVER_POST_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String resp = jsonObject.getString("server_response");
                                if (resp.equals("OK")) {
                                    getJSON();
                                    Toast.makeText(getApplicationContext(), "Berhasil Menambahkan Data", Toast.LENGTH_SHORT).show();

                                } else {
                                    getJSON();
                                    Toast.makeText(getApplicationContext(), resp, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent (Pemesanan.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    getJSON();
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("mobil", mobil);
                    params.put("alamat", alamat);
                    params.put("telp", telp);
                    params.put("tanggal", tanggal);
                    params.put("harga", harga);
                    params.put("iduser", iduserp);
                    params.put("idlist", idlist);
                    return params;
                }
            };
            VolleySingleton.getInstance(Pemesanan.this).addToRequestQue(stringRequest);

            getJSON();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.cancel();
                }
            }, 2000);
        } else {
            Toast.makeText(getApplicationContext(), "Tidak ada Koneksi Internet", Toast.LENGTH_SHORT).show();


        }

    }


    public boolean chechNetworkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
    public void getJSON() {
        new BackgroundTask().execute();
    }

    @Override
    public void post(String mobil,String alamat,String telp,String tanggal,String harga,String iduserp,String idlist) {
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        createDataToServer(mobil, alamat, telp, tanggal, harga, iduserp, idlist);
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {

        String json_url;

        @Override
        protected void onPreExecute() {
            json_url = DbContract.SERVER_GET_URL;

        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();

                while ((DATA_JSON_STRING = bufferedReader.readLine()) != null) {
                    stringBuilder.append(DATA_JSON_STRING + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            data_json_string = result;

        }
    }
}