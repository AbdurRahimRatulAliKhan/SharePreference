package com.example.administrator.sharepreference;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editName,editNumber;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editName = findViewById(R.id.editTextId);
        editNumber = findViewById(R.id.editTextIdTwo);
        textView = findViewById(R.id.textViewId);
    }

    public void ButtonClick(View view) {
        if(view.getId()==R.id.saveButton){
            viewData();
        }
        else if(view.getId()==R.id.viewButton){
            viewData();
        }
    }

    private void viewData(){
        SharedPreferences preferences = getSharedPreferences("database",MODE_PRIVATE);

        String name = preferences.getString("name","No data found");
        String number = preferences.getString("number","-1");

        textView.setText("Name: "+name+" Number: "+number);
    }
    private void saveData(){
        String name,number;
        name = editName.getText().toString().trim();
        number = editNumber.getText().toString();

        if(name.isEmpty()){
            editName.setError("Name is required");
            editName.requestFocus();
            return;
        }
        if(number.isEmpty()){
            editNumber.setError("Number is required");
            editNumber.requestFocus();
            return;
        }

        SharedPreferences preferences = getSharedPreferences("database",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("name",name);
        editor.putString("number",number);
        boolean result = editor.commit();

        if(result){
            Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
}
