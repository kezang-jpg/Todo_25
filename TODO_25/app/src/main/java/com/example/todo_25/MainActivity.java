package com.example.todo_25;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btn1,btn2,btn3,btn4;
    private EditText txt1,txt2,txt3,txt4;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.adddata);
        btn2 = findViewById(R.id.viewall);
        btn3 = findViewById(R.id.update);
        btn4 = findViewById(R.id.delete);
        txt1 = findViewById(R.id.firstname);
        txt2 = findViewById(R.id.lastname);
        txt3 = findViewById(R.id.ITW202);
        txt4 = findViewById(R.id.id);
        myDb=new DatabaseHelper(this);
    }

    public void AddData(View view) {
        boolean isInserted=myDb.insertData(txt4.getText().toString(),
                txt1.getText().toString(),
                txt2.getText().toString(),
                txt3.getText().toString());
        if (isInserted==true){
            Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Data not Inserted", Toast.LENGTH_SHORT).show();
        }
    }

    public void View_All(View view) {
        Cursor res=myDb.getAllData();
        if (res.getCount()==0){
            showMessage("Error","Nothing found");
            return;
        }
        StringBuffer buffer=new StringBuffer();
        while (res.moveToNext()){
            buffer.append("Student Id:"+res.getString(0)+"\n");
            buffer.append("First Name:"+res.getString(1)+"\n");
            buffer.append("Last Name:"+res.getString(2)+"\n");
            buffer.append("ITW202 Marks:"+res.getString(3)+"\n\n");
        }
        showMessage("list of student",buffer.toString());

    }

    private void showMessage(String title, String Message) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}