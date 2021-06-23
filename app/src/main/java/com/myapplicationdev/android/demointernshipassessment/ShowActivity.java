package com.myapplicationdev.android.demointernshipassessment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    Button btnFilter, btnShowAll;
    ListView lvCustomers;
    ArrayList<Customer> al, filterAL;
    ArrayList<String> spinnerAL;
    CustomerAdapter aa;
    ArrayAdapter spinnerAA;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);


        btnShowAll = (Button) findViewById(R.id.btnShowAll);
        btnFilter = (Button) findViewById(R.id.btnFilter);
        lvCustomers = (ListView) findViewById(R.id.lvCustomers);
        spinner = (Spinner) findViewById(R.id.spinner);

        DBHelper dbh = new DBHelper(ShowActivity.this);
        al = dbh.getAllcustomers();
        filterAL = al;
        aa = new CustomerAdapter(this, R.layout.row, al);
        lvCustomers.setAdapter(aa);

        spinnerAL = new ArrayList<>();

        for (Customer i : al) {
            spinnerAL.add(i.getName());
        }
        spinnerAA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerAL);
        spinnerAA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAA);


        btnShowAll.setOnClickListener(v -> {
            al = dbh.getAllcustomers();
            filterAL.clear();
            filterAL.addAll(al);
            aa = new CustomerAdapter(this, R.layout.row, filterAL);
            lvCustomers.setAdapter(aa);
        });


        btnFilter.setOnClickListener(v -> {
            al = dbh.getAllcustomers();
            filterAL.clear();

            for (Customer i : al) {
                if (i.getStars() == 5) {
                    filterAL.add(i);
                }
            }
            aa = new CustomerAdapter(this, R.layout.row, filterAL);
            lvCustomers.setAdapter(aa);

            Toast.makeText(ShowActivity.this, "Here are the 5 star rating customers.",
                    Toast.LENGTH_SHORT).show();
        });

        lvCustomers.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(ShowActivity.this, ModifyActivity.class);
            i.putExtra("Customer", filterAL.get(position));
            startActivity(i);
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                al = dbh.getAllcustomers();
                String selected = spinnerAL.get(position);
                filterAL.clear();

                for (Customer i : al) {
                    if (i.getName().equalsIgnoreCase(selected))
                        filterAL.add(i);
                }

                aa = new CustomerAdapter(ShowActivity.this, R.layout.row, filterAL);
                lvCustomers.setAdapter(aa);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        lvCustomers = findViewById(R.id.lvCustomers);
        spinner = findViewById(R.id.spinner);
        DBHelper dbh = new DBHelper(ShowActivity.this);
        al = dbh.getAllcustomers();
        aa = new CustomerAdapter(this, R.layout.row, al);
        lvCustomers.setAdapter(aa);

        spinnerAL = new ArrayList<>();
        for (Customer i : al) {
            spinnerAL.add(i.getName());
        }
        spinnerAA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerAL);
        spinnerAA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAA);
    }
}