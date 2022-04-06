package ProjectMobile.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Lihat_Data extends AppCompatActivity implements CreateDataDialog.CreateDialogListener,
        UpdateDataDialog.UpdateDataDialogListener, DeleteDataDialog.DeleteDataDialogListener{
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerAdapter adapter;
    ArrayList<Data> arrayList = new ArrayList<>();
    String DATA_JSON_STRING, data_json_string;
    ProgressDialog progressDialog;
    Button btntambah, btnrefresh;
    int countData = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat__data);
        btntambah = (Button) findViewById(R.id.button_tambahdata);
        recyclerView = (RecyclerView) findViewById(R.id.list_data);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapter(arrayList);
        recyclerView.setAdapter(adapter);
        btnrefresh = findViewById(R.id.button_refresh);
        progressDialog = new ProgressDialog(Lihat_Data.this);
        recyclerView.invalidate();


        btnrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Lihat_Data.this,"Refreshing...", Toast.LENGTH_SHORT).show();
                getJSON();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        readDataFromServer();

                    }
                }, 1000);
            }
        });
        //READ DATA
        getJSON();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                readDataFromServer();

            }
        }, 1000);



        btntambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateDialog();
            }
        });
    }
    public void openCreateDialog() {
        CreateDataDialog createDataDialog = new CreateDataDialog();
        createDataDialog.show(getSupportFragmentManager(), "create dialog");
    }

    //------------------------------- Radio Button Section ---------------------------------------
    public void checkButton(View v){
        // Is the button now checked?
        boolean checked = ((RadioButton) v).isChecked();

        // Check which radio button was clicked
        switch(v.getId()) {
            case R.id.radio5orang:
                if (checked)
                    Toast.makeText(Lihat_Data.this,"Penumpang 5 orang", Toast.LENGTH_SHORT).show();
                    global.cidjenis = "1";
                break;
            case R.id.radio8orang:
                if (checked)
                    Toast.makeText(Lihat_Data.this,"Penumpang 8 orang", Toast.LENGTH_SHORT).show();
                    global.cidjenis = "2";

                break;
            case R.id.radioPickup:
                if (checked)
                    Toast.makeText(Lihat_Data.this,"Pickup", Toast.LENGTH_SHORT).show();
                    global.cidjenis = "3";

                break;
            case R.id.radioMinibus:
                if (checked)
                    Toast.makeText(Lihat_Data.this,"Minibus", Toast.LENGTH_SHORT).show();
                    global.cidjenis = "4";

                break;
        }
    }

    //---------------------------------------method read data-----------------------------------------
    public void readDataFromServer() {
        if (chechNetworkConnection()) {
            arrayList.clear();
            try {
                JSONObject object = new JSONObject(data_json_string);
                JSONArray serverResponse = object.getJSONArray("server_response");
                String namamobil, hargasewa, kategori, idList, idMobil, statusmobil;

                while (countData < serverResponse.length()) {
                    JSONObject jsonObject = serverResponse.getJSONObject(countData);

                    idList = jsonObject.getString("id");
                    idMobil = jsonObject.getString("idmobil");
                    namamobil = jsonObject.getString("namamobil");
                    hargasewa = jsonObject.getString("hargasewa");
                    statusmobil = jsonObject.getString("statusmobil");
                    kategori = jsonObject.getString("kategori");

                    arrayList.add(new Data(kategori, namamobil, hargasewa, idList, idMobil, statusmobil));
                    countData++;
                }
                countData = 0;

                adapter.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

    //------------------------------------------------------------------------------------------------
    public void createDataToServer(final String idJenis, final String namamobil,final String harga, final String status ) {
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
                                } else if (resp.equals("FAILED")) {
                                    getJSON();
                                    Toast.makeText(getApplicationContext(), resp, Toast.LENGTH_SHORT).show();
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
                    params.put("id", idJenis);
                    params.put("namamobil", namamobil);
                    params.put("harga", harga);
                    params.put("status", status);
                    return params;
                }
            };
            VolleySingleton.getInstance(Lihat_Data.this).addToRequestQue(stringRequest);

            getJSON();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    readDataFromServer();
                    progressDialog.cancel();
                }
            }, 2000);
        } else {
            Toast.makeText(getApplicationContext(), "Tidak ada Koneksi Internet", Toast.LENGTH_SHORT).show();


        }

    }
    //------------------------------------------------------------------------------------------------
    public void UpdataDataToServer(final String idlist, final String namamobil, final String harga) {
        if (chechNetworkConnection()) {
            progressDialog.show();
            getJSON();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, DbContract.SERVER_PUT_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String resp = jsonObject.getString("server_response");
                                if (resp.equals("OK")) {
                                    getJSON();
                                    Toast.makeText(getApplicationContext(), "Berhasil Update Data", Toast.LENGTH_SHORT).show();
                                } else {
                                    getJSON();
                                    Toast.makeText(getApplicationContext(), resp, Toast.LENGTH_SHORT).show();
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
                    params.put("id", idlist);
                    params.put("namamobil", namamobil);
                    params.put("harga", harga);

                    return params;
                }
            };
            VolleySingleton.getInstance(Lihat_Data.this).addToRequestQue(stringRequest);

            getJSON();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    readDataFromServer();
                    progressDialog.cancel();
                }
            }, 2000);
        } else {
            Toast.makeText(getApplicationContext(), "Tidak ada Koneksi Internet", Toast.LENGTH_SHORT).show();


        }

    }

    public void DeleteDataToServer(final String id) {
        if (chechNetworkConnection()) {
            progressDialog.show();
            getJSON();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, DbContract.SERVER_DELETE_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String resp = jsonObject.getString("server_response");
                                if (resp.equals("OK")) {
                                    getJSON();
                                    Toast.makeText(getApplicationContext(), "Berhasil Delete Data", Toast.LENGTH_SHORT).show();
                                } else {
                                    getJSON();
                                    Toast.makeText(getApplicationContext(), resp, Toast.LENGTH_SHORT).show();
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
                    params.put("id", id);

                    return params;
                }
            };
            VolleySingleton.getInstance(Lihat_Data.this).addToRequestQue(stringRequest);

            getJSON();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    readDataFromServer();
                    progressDialog.cancel();
                }
            }, 2000);
        } else {
            Toast.makeText(getApplicationContext(), "Tidak ada Koneksi Internet", Toast.LENGTH_SHORT).show();


        }
    }

    //----------------------method network------------------------------
    public boolean chechNetworkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    //---------------------------------method get Json-------------------------------------------------
    public void getJSON() {
        new BackgroundTask().execute();
    }

    @Override
    public void update(String idlist, String namamobil, String harga) {
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        UpdataDataToServer(idlist, namamobil, harga);
    }

    @Override
    public void delete(String id) {
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        DeleteDataToServer(id);
    }


    @Override
    public void post(String idJenis, String namamobil, String harga, String status) {
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        createDataToServer(idJenis, namamobil, harga, status);
    }


    //---------------------------------method Background Task-------------------------------------------------
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

                while ((DATA_JSON_STRING = bufferedReader.readLine()) != null){
                    stringBuilder.append(DATA_JSON_STRING + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return  stringBuilder.toString().trim();

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
    //---------------------------------------------------------------------------------------------------
}

