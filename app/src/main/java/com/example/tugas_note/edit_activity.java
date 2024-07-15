package com.example.tugas_note;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class edit_activity extends AppCompatActivity {

    private EditText editTextName, editTextRegistrationNo, editTextEmail, editTextPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editTextName = findViewById(R.id.edit_name);
        editTextRegistrationNo = findViewById(R.id.edit_registration_number);
        editTextEmail = findViewById(R.id.edit_email);
        editTextPhone = findViewById(R.id.edit_phone);

        Button btn_update = findViewById(R.id.btn_update);
        Button btn_cancel = findViewById(R.id.btn_cancel);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Proses untuk update data
                String newName = editTextName.getText().toString().trim();
                String newRegistrationNo = editTextRegistrationNo.getText().toString().trim();
                String newEmail = editTextEmail.getText().toString().trim();
                String newPhone = editTextPhone.getText().toString().trim();

                // Lakukan sesuatu dengan data yang sudah diubah

                // Selesai, kembali ke activity sebelumnya
                finish();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Batalkan perubahan dan kembali ke activity sebelumnya
                finish();
            }
        });
    }
}
