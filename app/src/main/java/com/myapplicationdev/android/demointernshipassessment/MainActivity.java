package com.myapplicationdev.android.demointernshipassessment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etCustomerName, etContact, etEmail, etHome;
    RadioGroup radioGroup;
    Button btnInsert, btnShow;
    RadioButton rdButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCustomerName = findViewById(R.id.etCustomerName);
        etContact = findViewById(R.id.etContact);
        etEmail = findViewById(R.id.etEmail);
        etHome = findViewById(R.id.etHome);
        radioGroup = findViewById(R.id.radioGrp);
        btnInsert = findViewById(R.id.btnInsert);
        btnShow = findViewById(R.id.btnShow);


        btnInsert.setOnClickListener(view -> {

            String name = etCustomerName.getText().toString();
            String contact = etContact.getText().toString();
            String email = etEmail.getText().toString();
            String home = etHome.getText().toString();

            int selectedId = radioGroup.getCheckedRadioButtonId();
            rdButton = findViewById(selectedId);
            int stars = Integer.parseInt(rdButton.getText().toString());
            DBHelper dbh = new DBHelper(MainActivity.this);
            long inserted_id = dbh.insertcustomer(name, contact, email, home, stars);

            if (inserted_id != -1) {
                Toast.makeText(MainActivity.this, "Insert successful",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Insert failed",
                        Toast.LENGTH_SHORT).show();
            }
            dbh.close();
        });

        btnShow.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this,
                    ShowActivity.class);
            startActivity(i);
        });

    }
}