package com.example.sqlitedatabase;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText txt_id, txt_name, txt_marks, txt_remarks;
    Button insert_btn, show_btn, update_btn, delete_btn;

    DatabaseHelper myDB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        myDB = new DatabaseHelper(this);

        txt_id = findViewById(R.id.id_txt);
        txt_name = findViewById(R.id.name_txt);
        txt_marks = findViewById(R.id.marks_txt);
        txt_remarks = findViewById(R.id.remark_txt);

        insert_btn = findViewById(R.id.insert);
        show_btn = findViewById(R.id.show);
        update_btn = findViewById(R.id.edit);
        delete_btn = findViewById(R.id.delete);

        insert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean inserted = myDB.insertData(
                  txt_name.getText().toString().trim(),
                  txt_marks.getText().toString().trim(),
                  txt_remarks.getText().toString().trim()
                );
                if (inserted){
                    Toast.makeText(MainActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Error Occured!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        show_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = myDB.getALlData();
                if (cursor.getCount() == 0){
                    Toast.makeText(MainActivity.this, "Data is Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()){
                buffer.append("ID" + cursor.getString(0)+"\n");
                buffer.append("Name" + cursor.getString(1)+"\n");
                buffer.append("Marks" + cursor.getString(2)+"\n");
                buffer.append("Remarks" + cursor.getString(3)+"\n");
                }
                show("Data", buffer.toString());
            }
        });
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void show(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Data");
        builder.setMessage("Message");
        builder.show();
    }
}