package com.abim.lks_vaksin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().hide();
        context = this;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.card_hasil){
            Intent intent = new Intent(DashboardActivity.this, HasilActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.card_profil){
            Intent intent = new Intent(DashboardActivity.this, ProfilActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.card_info){
            Intent intent = new Intent(DashboardActivity.this, InfoActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.card_logout){
            AlertDialog dialog = new AlertDialog.Builder(context).create();
            dialog.setTitle("Konfirmasi");
            dialog.setMessage("Apakah anda yakin ingin logout?");
            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Tidak", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ya", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });
            dialog.show();
        }
    }
}