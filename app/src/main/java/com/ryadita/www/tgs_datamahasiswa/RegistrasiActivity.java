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

public class RegistrasiActivity extends AppCompatActivity {

    EditText namaMhs, nimMhs, prodiMhs, emailMhs;
    Button btn_save, btn_hapus, btn_balik;

    public DataMhsAdapter mhsAdapter;
    private List<DataMahasiswa> dataMhsList = new ArrayList<>();
    public RecyclerView recyclerView;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        db = new DatabaseHelper(this);

        namaMhs = findViewById(R.id.input_nama);
        nimMhs = findViewById(R.id.input_nim);
        prodiMhs = findViewById(R.id.input_prodi);
        emailMhs = findViewById(R.id.input_email);
        btn_hapus = findViewById(R.id.btn_kosong);
        btn_save = findViewById(R.id.btn_simpan);
        btn_balik = findViewById(R.id.btn_kembali);
    }

    public void createData(View view) {

        String nama = namaMhs.getText().toString();
        String noinduk = nimMhs.getText().toString();
        String depart = prodiMhs.getText().toString();
        String emails = emailMhs.getText().toString();

        long id = db.insertData(nama, noinduk, depart, emails);

        //get the newly inserted data from db
        DataMahasiswa d = db.getDataMhs(id);

        if (d != null) {
            //adding new note to array list at 0 position
            dataMhsList.add(0, d);

            //refreshing the list
            //mhsAdapter.notifyDataSetChanged();
        }

        Toast.makeText(this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
    }

    public void kosongkanData(View view) {
        namaMhs.setText("");
        nimMhs.setText("");
        prodiMhs.setText("");
        emailMhs.setText("");
    }

    public void backtoMain(View view) {
        Intent intent5 = new Intent(this, MainActivity.class);
        startActivity(intent5);
    }
}

