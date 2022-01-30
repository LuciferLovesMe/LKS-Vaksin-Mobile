package com.abim.lks_vaksin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class ProfilActivity extends AppCompatActivity {
    TextView textnama, textnik, textnohp, textttl;
    Context ctx;
    Session s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        getSupportActionBar().setTitle("Profil0");

        ctx = this;
        s = new Session(ctx);
        textnama = findViewById(R.id.textnama);
        textnik = findViewById(R.id.textnik);
        textnohp = findViewById(R.id.texthp);
        textttl = findViewById(R.id.textttl);

        textnama.setText(s.getNama());
        textnik.setText(s.getNik());
        textnohp.setText(s.getNoHp());
        textttl.setText(s.getTempat_lahir() + ", "+s.getTanggal_lahir());
    }
}