package com.example.npuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Board puzzle;
    Spinner spinner;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spnCol);
        String tmp = spinner.getSelectedItem().toString();
        int col = Integer.parseInt(tmp);
        spinner = findViewById(R.id.spnRow);
        tmp = spinner.getSelectedItem().toString();
        int row = Integer.parseInt(tmp);
        puzzle = new Board(col, row);

        button = findViewById(R.id.createButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}