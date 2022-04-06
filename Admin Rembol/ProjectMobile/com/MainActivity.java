package ProjectMobile.com;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView isi, isi2, isi3;
    Button logout;
    ArrayAdapter<String> profilAdapter;
    RequestQueue requestQueue;
    ArrayList<String> listuser = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listuser.clear();
        isi = findViewById(R.id.isi);
        isi2 = findViewById(R.id.isi2);
        isi3 = findViewById(R.id.isi3);
        isi.setText(global.uname);
        String profuser =global.uname;
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                global.uname = "";
                startActivity(intent);
                finish();
            }
        });

        String url = "http://192.168.8.107/REMBOL/admin/profil.php?username="+profuser;
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("users");
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String email = jsonObject.getString("email");
                        String full = jsonObject.getString("full");
                        isi2.setText(email);
                        isi3.setText(full);

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

