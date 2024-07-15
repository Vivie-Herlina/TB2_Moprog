package com.example.tugas_note;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    FloatingActionButton btnAdd;
    ListView listView;
    StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        listView = findViewById(R.id.list_view);
        FloatingActionButton btnAdd = findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCreateStudentDialog();
            }
        });

        loadStudents();
    }

    private void showCreateStudentDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_create_student, null);
        dialogBuilder.setView(dialogView);

        final EditText inputName = dialogView.findViewById(R.id.edit_name);
        final EditText inputId = dialogView.findViewById(R.id.edit_registration_number);
        final EditText inputPhone = dialogView.findViewById(R.id.edit_phone);
        final EditText inputEmail = dialogView.findViewById(R.id.edit_email);
        Button btnCreate = dialogView.findViewById(R.id.btn_create);
        Button btnCancel = dialogView.findViewById(R.id.btn_cancel);

        final AlertDialog alertDialog = dialogBuilder.create();

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = inputName.getText().toString();
                String id = inputId.getText().toString();
                String phone = inputPhone.getText().toString();
                String email = inputEmail.getText().toString();

                if (!name.isEmpty() && !id.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
                    db.addStudent(name, id, phone, email);
                    loadStudents();
                    Toast.makeText(MainActivity.this, "Student added", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    void showEditStudentDialog(final Student student) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_edit, null);
        dialogBuilder.setView(dialogView);

        final EditText inputName = dialogView.findViewById(R.id.edit_name);
        final EditText inputId = dialogView.findViewById(R.id.edit_registration_number);
        final EditText inputPhone = dialogView.findViewById(R.id.edit_phone);
        final EditText inputEmail = dialogView.findViewById(R.id.edit_email);
        Button btnUpdate = dialogView.findViewById(R.id.btn_update);
        Button btnCancel = dialogView.findViewById(R.id.btn_cancel);

        inputName.setText(student.getName());
        inputId.setText(student.getRegistrationNumber());
        inputPhone.setText(student.getPhone());
        inputEmail.setText(student.getEmail());

        final AlertDialog alertDialog = dialogBuilder.create();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = inputName.getText().toString();
                String registrationNumber = inputId.getText().toString();
                String phone = inputPhone.getText().toString();
                String email = inputEmail.getText().toString();

                if (!name.isEmpty() && !registrationNumber.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
                    db.updateStudent(student.getId(), name, registrationNumber, phone, email);
                    loadStudents();
                    Toast.makeText(MainActivity.this, "Student updated", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    private void loadStudents() {
        List<Student> students = db.getAllStudents();
        adapter = new StudentAdapter(this, students);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student selectedStudent = (Student) parent.getItemAtPosition(position);
                showEditStudentDialog(selectedStudent);
            }
        });
    }
}
