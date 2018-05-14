package com.ryadita.www.tgs_datamahasiswa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LihatActivity extends AppCompatActivity implements DataMhsAdapter.BtnClickListener{

    List<DataMahasiswa> dataMhsList = new ArrayList<>();
    RecyclerView recyclerView;
    DataMhsAdapter dataMhsAdapter;

    private DatabaseHelper db;

    int positions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat);

        db = new DatabaseHelper(this);

        dataMhsList.addAll(db.getAllData());

        recyclerView = findViewById(R.id.data_mhs_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        dataMhsAdapter = new DataMhsAdapter(this);
        dataMhsAdapter.setDataMhs(dataMhsList, getApplicationContext());
        recyclerView.setAdapter(dataMhsAdapter);
    }

    @Override
    public void onItemClicked(int position) {
        positions = position;
    }

    public void detailData(View view) {

        try {
            int position = positions ;

            Intent intent3 = new Intent(this, DetailActivity.class);

            intent3.putExtra("nama_mhs", dataMhsList.get(position).getNama());
            intent3.putExtra("nim_mhs", dataMhsList.get(position).getNim());
            intent3.putExtra("prodi_mhs", dataMhsList.get(position).getProdi());
            intent3.putExtra("email_mhs", dataMhsList.get(position).getEmail());

            startActivity(intent3);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void hapusData(View view) {

        try {
            int position = positions;

            //deleting the note from db
            db.deleteData(dataMhsList.get(position));

            //removing the note from the list
            dataMhsList.remove(position);
            //dataMhsAdapter.notifyItemRemoved(position);
            Toast.makeText(this,"Data telah dihapus!", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void kembalikeMain(View view) {
        Intent intent4 = new Intent(this, MainActivity.class);
        startActivity(intent4);
    }
    public void editData(View view) {
        try {
            int position = positions ;

            Intent intent7 = new Intent(this, UpdateActivity.class);

            intent7.putExtra("id_mhs", dataMhsList.get(position).getId());
            intent7.putExtra("nama_mhs", dataMhsList.get(position).getNama());
            intent7.putExtra("nim_mhs", dataMhsList.get(position).getNim());
            intent7.putExtra("prodi_mhs", dataMhsList.get(position).getProdi());
            intent7.putExtra("email_mhs", dataMhsList.get(position).getEmail());

            startActivity(intent7);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
