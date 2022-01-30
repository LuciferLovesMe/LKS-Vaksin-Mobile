package com.abim.lks_vaksin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    Button btn;
    Context ctx;
    Session s;
    RequestQueue queue;
    EditText et_hp, et_nik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        ctx = this;
        btn = findViewById(R.id.btn);
        queue = Volley.newRequestQueue(ctx);
        et_nik = findViewById(R.id.et_nik);
        et_hp = findViewById(R.id.et_hp);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_hp.getText().toString().equalsIgnoreCase("") ||et_nik.getText().toString().equalsIgnoreCase("")){
                    AlertDialog dialog = new AlertDialog.Builder(ctx).create();
                    dialog.setTitle("Terjadi Kesalahan");
                    dialog.setMessage("Semua Field Harap Diisi!");
                    dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
                else{
                    doLogin();
                }
            }
        });
    }

    void doLogin(){
        String nik, nohp;
        nik = et_nik.getText().toString();
        nohp = et_hp.getText().toString();

        StringRequest request = new StringRequest(Request.Method.POST, MyRequest.getLoginURL(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    s = new Session(ctx);
                    s.setUser(obj.getInt("id"), obj.getString("nama"), obj.getString("nik"), obj.getString("noHp"), obj.getString("tempat_lahir"), obj.getString("tanggal_lahir"));
                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception ex) {
                    AlertDialog dialog = new AlertDialog.Builder(ctx).create();
                    dialog.setTitle("Terjadi Kesalahan");
                    dialog.setMessage("Tidak dapat menemukan pengguna!");
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
                params.put("noHp", nohp);
                return params;
            }
        };

        queue.add(request);
    }
}