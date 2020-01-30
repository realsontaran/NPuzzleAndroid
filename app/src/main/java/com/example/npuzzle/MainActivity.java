package com.example.npuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button create = findViewById(R.id.createButton);

        create.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                editText = findViewById(R.id.colText);
                textView = findViewById(R.id.colView);
                textView.setText("col = "+editText.getText().toString());
                editText = findViewById(R.id.rowText);
                textView = findViewById(R.id.rowView);
                textView.setText("row = "+editText.getText().toString());
            }
        });

    }
}
