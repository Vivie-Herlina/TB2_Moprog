package com.example.tugas_note;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class StudentAdapter extends BaseAdapter {
    private Context context;
    private List<Student> students;
    private DatabaseHelper db;

    public StudentAdapter(Context context, List<Student> students) {
        this.context = context;
        this.students = students;
        db = new DatabaseHelper(context);
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        TextView textName = convertView.findViewById(R.id.text_name);
        TextView textRegistrationNo = convertView.findViewById(R.id.text_registration_no);
        TextView textEmail = convertView.findViewById(R.id.text_email);
        TextView textPhone = convertView.findViewById(R.id.text_phone);
        ImageButton btnEdit = convertView.findViewById(R.id.btn_edit);
        ImageButton btnDelete = convertView.findViewById(R.id.btn_delete);

        final Student student = students.get(position);

        textName.setText(student.getName());
        textRegistrationNo.setText("Registration No " + student.getRegistrationNumber());
        textEmail.setText("Email " + student.getEmail());
        textPhone.setText("Phone " + student.getPhone());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).showEditStudentDialog(student);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteStudent(student.getId());
                students.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "Student deleted", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
