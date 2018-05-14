package com.ryadita.www.tgs_datamahasiswa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView namaMhs, nimMhs, prodiMhs, emailMhs;
    String nama, nim, prodi, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        namaMhs = findViewById(R.id.isi_nama);
        nimMhs = findViewById(R.id.isi_nim);
        prodiMhs = findViewById(R.id.isi_prodi);
        emailMhs = findViewById(R.id.isi_email);

        Intent receiveIntent = getIntent();
        nama = receiveIntent.getStringExtra("nama_mhs");
        nim = receiveIntent.getStringExtra("nim_mhs");
        prodi = receiveIntent.getStringExtra("prodi_mhs");
        email = receiveIntent.getStringExtra("email_mhs");

        namaMhs.setText(nama);
        nimMhs.setText(nim);
        prodiMhs.setText(prodi);
        emailMhs.setText(email);
    }

    public void kembalikeLihat(View view) {
        Intent intent6 = new Intent(this, LihatActivity.class);
        startActivity(intent6);
    }
}