package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class datshAdmin extends AppCompatActivity {

    DBHelper db = new DBHelper(this);

    EditText name, email, phone, ID;
    ListView lst;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datsh_admin);

        name = findViewById(R.id.editName);
        email = findViewById(R.id.editEmail);
        phone = findViewById(R.id.editPhone);
        ID = findViewById(R.id.editText_id);

        lst = findViewById(R.id.listView_data);
        showData();


    }


    public void add(View view) {

        String Name = name.getText().toString();
        String Email = email.getText().toString();
        String Phone = phone.getText().toString();
        Boolean result = db.insertData(Name, Email, Phone);
        if (result == true) {
            Toast.makeText(datshAdmin.this, "OK", Toast.LENGTH_SHORT).show();
            name.setText("");
            email.setText("");
            phone.setText("");
            showData();
        } else {
            Toast.makeText(datshAdmin.this, "NO", Toast.LENGTH_SHORT).show();


        }

    }
    public void showData(){
        ArrayList<String> listData = db.getAllUsers();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listData);

        lst.setAdapter(arrayAdapter);
    }

    public void edit(View view) {


        String Name = name.getText().toString();
        String Email = email.getText().toString();
        String Phone = phone.getText().toString();
        String id = ID.getText().toString();

        Boolean result = db.updateData(id, Name, Email, Phone);
        if (result == true) {
            Toast.makeText(datshAdmin.this, "OK", Toast.LENGTH_SHORT).show();
            name.setText("");
            email.setText("");
            phone.setText("");
            ID.setText("");
            showData();
        } else {
            Toast.makeText(datshAdmin.this, "NO", Toast.LENGTH_SHORT).show();


        }
    }

    public void delete(View view) {
        String Id = ID.getText().toString();
        Integer result = db.deleteDate(Id);
        if (result > 0){
            Toast.makeText(datshAdmin.this, "DELETE", Toast.LENGTH_SHORT).show();
            name.setText("");
            email.setText("");
            phone.setText("");
            ID.setText("");
            showData();
        }else{
            Toast.makeText(datshAdmin.this, "NO", Toast.LENGTH_SHORT).show();
        }
    }
}