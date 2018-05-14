package com.ryadita.www.tgs_datamahasiswa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UpdateActivity extends AppCompatActivity {

    EditText namaMhs, nimMhs, prodiMhs, emailMhs;
    Button btn_ubah, btn_hapus, btn_balik;

    int id;
    String nama, nim, prodi, email;

    public DataMhsAdapter mhsAdapter;
    public List<DataMahasiswa> dataMhsList = new ArrayList<>();
    public RecyclerView recyclerView;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        db = new DatabaseHelper(this);

        namaMhs =findViewById((R.id.masukkan_nama);
        nimMhs =findViewById(R.id.masukkan_induk);
        prodiMhs =findViewById(R.id.masukkan_prodi);
        emailMhs =findViewById(R.id.masukkan_email);
        btn_hapus = findViewById(R.id.btn_empty);
        btn_ubah =findViewById(R.id.btn_update);
        btn_balik =findViewById(R.id.btn_bck);

        Intent receiveIntent = getIntent();

        id =  receiveIntent.getIntExtra("id_mhs", 0);
        nama = receiveIntent.getStringExtra("nama_mhs");
        nim = receiveIntent.getStringExtra("nim_mhs");
        prodi = receiveIntent.getStringExtra("prodi_mhs");
        email = receiveIntent.getStringExtra("email_mhs");

        namaMhs.setText(nama);
        nimMhs.setText(nim);
        prodiMhs.setText(prodi);
        emailMhs.setText(email);
    }

    public void ubahData(View view) {

        String nama = namaMhs.getText().toString();
        String noinduk = nimMhs.getText().toString();
        String depart = prodiMhs.getText().toString();
        String emails = emailMhs.getText().toString();

        DataMahasiswa d = db.getDataMhs(id);

        d.setNama(nama);
        d.setNim(noinduk);
        d.setProdi(depart);
        d.setEmail(emails);

        // updating note in db
        db.updateData(d);

        Toast.makeText(this, "Data berhasil diubah", Toast.LENGTH_SHORT).show();
    }

    public void emptyData(View view) {
        namaMhs.setText("");
        nimMhs.setText("");
        prodiMhs.setText("");
        emailMhs.setText("");
    }

    public void backLihat(View view) {
        Intent intent8 = new Intent(this, LihatActivity.class);
        startActivity(intent8);
    }
}
