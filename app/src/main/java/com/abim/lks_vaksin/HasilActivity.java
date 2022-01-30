package com.abim.lks_vaksin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HasilActivity extends AppCompatActivity {
    Context ctx;
    Session s;
    RequestQueue queue;
    List<Vaksin> list;
    Adapter adapter;
    RecyclerView rv;
    String nik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);
        getSupportActionBar().setTitle("Hasil Vaksin");

        ctx = this;
        s = new Session(ctx);
        queue = Volley.newRequestQueue(ctx);
        list = new ArrayList<>();
        rv = findViewById(R.id.rv);
        nik = s.getNik();

        getdata();
    }

    void getdata(){
        StringRequest request = new StringRequest(Request.Method.POST, MyRequest.getVaksinURL(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray json = new JSONArray(response);
                    for (int i = 0; i < json.length(); i++){
                        JSONObject obj = json.getJSONObject(i);
                        list.add(new Vaksin(obj.getInt("periode"), obj.getString("nama"),obj.getString("nama_vaksin"), obj.getString("tanggal_vaksin")));
                    }

                    adapter = new Adapter(list, ctx);
                    rv.setLayoutManager(new LinearLayoutManager(ctx));
                    rv.setAdapter(adapter);
                }catch (Exception ex){
                    AlertDialog dialog = new AlertDialog.Builder(ctx).create();
                    dialog.setTitle("Terjadi Kesalahan");
                    dialog.setMessage(""+ex);
                    dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog dialog = new AlertDialog.Builder(ctx).create();
                dialog.setTitle("Terjadi Kesalahan");
                dialog.setMessage(""+error);
                dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nik", nik);

                return params;
            }
        };

        queue.add(request);
    }

//    void getdata(){
//        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, MyRequest.getVaksinURL(), null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                try {
//                    for (int i = 0; i < response.length(); i++){
//                        JSONObject obj = response.getJSONObject(i);
//                        list.add(new Vaksin(obj.getInt("periode"), obj.getString("nama"),obj.getString("nama_vaksin"), obj.getString("tanggal_vaksin")));
//                    }
//
//                    adapter = new Adapter(list, ctx);
//                    rv.setLayoutManager(new LinearLayoutManager(ctx));
//                    rv.setAdapter(adapter);
//                }catch (Exception e){
//                    Toast.makeText(ctx, ""+e, Toast.LENGTH_SHORT).show();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                AlertDialog dialog = new AlertDialog.Builder(ctx).create();
//                dialog.setTitle("Terjadi Kesalahan");
//                dialog.setMessage(""+error);
//                dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//                dialog.show();
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("nik", nik);
//                return super.getParams();
//            }
//        };
//
//        queue.add(request);
//    }
}