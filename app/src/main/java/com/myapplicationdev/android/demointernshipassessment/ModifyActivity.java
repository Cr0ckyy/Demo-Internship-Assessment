package com.myapplicationdev.android.demointernshipassessment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ModifyActivity extends AppCompatActivity {

    TextView tvEditId;
    EditText etCustomerName, etContact, etEmail, etHome;
    RadioGroup rgStars;
    Button btnUpdate, btnDelete, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        Intent i = getIntent();
        Customer customer = (Customer) i.getSerializableExtra("Customer");

        tvEditId = findViewById(R.id.tvEditID);
        etCustomerName = findViewById(R.id.etCustomerName);
        etContact = findViewById(R.id.etContact);
        etEmail = findViewById(R.id.etEmail);
        etHome = findViewById(R.id.etHome);
        rgStars = findViewById(R.id.rgStars);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);


        tvEditId.setText(String.valueOf(customer.getId()));
        etCustomerName.setText(customer.getName());
        etContact.setText(customer.getContact());
        etEmail.setText(customer.getEmail());
        etHome.setText(customer.getHome());

        if (customer.getStars() == 1) {
            rgStars.check(R.id.rb1);
        } else if (customer.getStars() == 2) {
            rgStars.check(R.id.rb2);
        } else if (customer.getStars() == 3) {
            rgStars.check(R.id.rb3);
        } else if (customer.getStars() == 4) {
            rgStars.check(R.id.rb4);
        } else if (customer.getStars() == 5) {
            rgStars.check(R.id.rb5);
        }

        DBHelper dbh = new DBHelper(ModifyActivity.this);

        btnUpdate.setOnClickListener(v -> {
            RadioButton rb = findViewById(rgStars.getCheckedRadioButtonId());

            int result = dbh.updatecustomer(new Customer(


                    customer.getId(),

                    etCustomerName.getText().toString(),
                    etContact.getText().toString(),
                    etEmail.getText().toString(),
                    etHome.getText().toString(),


                    Integer.parseInt(rb.getText().toString())));

            Toast.makeText(ModifyActivity.this, "This customer has been successfully updated by you.",
                    Toast.LENGTH_SHORT).show();
            this.finish();
        });

        btnDelete.setOnClickListener(v -> {
            int result = dbh.deletecustomer(customer.getId());
            Toast.makeText(ModifyActivity.this, "This customer has been successfully deleted by you.",
                    Toast.LENGTH_SHORT).show();
            this.finish();

        });

        btnCancel.setOnClickListener(v -> this.finish());
    }
}