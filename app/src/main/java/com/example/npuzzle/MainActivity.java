package com.example.npuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Board puzzle;
    Spinner spinner;
    Button button;
    Button shuffle;
    Boolean clicked = false;
    int Row;
    int Col;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = findViewById(R.id.createButton);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                spinner = findViewById(R.id.spnCol);
                String tmp = spinner.getSelectedItem().toString();

                Col = Integer.parseInt(tmp);
                spinner = findViewById(R.id.spnRow);

                tmp = spinner.getSelectedItem().toString();
                Row = Integer.parseInt(tmp);

                puzzle = new Board(Row, Col);


                if (clicked) {
                    tableClear();
                }

                if (!clicked) {
                    loadBoard();
                    clicked = true;
                }


                Toast.makeText(MainActivity.this, "Col: " + puzzle.getHeight() + " Row: " + puzzle.getWidth(), Toast.LENGTH_LONG).show();
            }
        });

        shuffle = findViewById(R.id.shuffleButton);
        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puzzle.shuffle();
                tableClear();
                loadBoard();
            }
        });

    }

    public void loadBoard() {
        tableClear();

        for (int i = 0; i < Row; i++) {
            for (int j = 0; j < Col; j++) {
                tableCreate(puzzle.getValue(i, j));
            }
        }
    }

    public void tableClear() {
        TableLayout table = findViewById(R.id.myTable);
        for (int i = 0; i < table.getChildCount(); i++) {
            View child = table.getChildAt(i);
            if (child instanceof TableRow) ((ViewGroup) child).removeAllViews();
        }
        clicked = false;
        System.gc();
    }

    @SuppressLint("SetTextI18n")
    public void tableCreate(final int index) {

        TableLayout table = findViewById(R.id.myTable);

        int buttonsInRow = 0;

        int numRows = table.getChildCount();

        TableRow row = null;

        if (numRows > 0) {
            row = (TableRow) table.getChildAt(numRows - 1);
            buttonsInRow = row.getChildCount();
        }

        if (numRows == 0 || buttonsInRow == Col) {
            row = new TableRow(this);
            table.addView(row);
            buttonsInRow = 0;
        }

        if (buttonsInRow < Col) {
            Button b = new Button(this);

            if (index != -1) {
                b.setText("" + index);
                b.setId(index);
            } else {
                b.setText(" ");
                b.setId(index);
            }

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkClick((Button) v);
                    loadBoard();
                    if (v.getId() == -1) {
                        Toast.makeText(MainActivity.this, "EMPTY", Toast.LENGTH_LONG).show();
                    }
                }
            });
            assert row != null;
            row.addView(b, table.getWidth() / Col, table.getHeight() / Row);
        }
    }

    @SuppressLint("SetTextI18n")
    private void checkClick(Button button) {

        TableLayout tableLayout = findViewById(R.id.myTable);
        TextView view = findViewById(R.id.errorView);
        view.setText(""+button.getId());

        Button right = null,
                left = null,
                up = null,
                down = null;

        TableRow tableRow,
                upper,
                lower;

        int tmpId = button.getId();
        int k = 0;

        for (int i = 0; i < Row; i++) {
            tableRow = (TableRow) tableLayout.getChildAt(i);
            for (int j = 0; j < Col; j++) {
                ++k;
                if (k == tmpId) {
                    if (j != Col - 1) {
                        right = (Button) tableRow.getChildAt(j + 1);
                    }
                    if (j != 0) {
                        left = (Button) tableRow.getChildAt(j - 1);
                    }
                    if (i != 0) {
                        upper = (TableRow) tableLayout.getChildAt(i - 1);
                        up = (Button) upper.getChildAt(j);
                    }
                    if (i != Row - 1) {
                        lower = (TableRow) tableLayout.getChildAt(i + 1);
                        down = (Button) lower.getChildAt(j);
                    }


                    if (right != null && right.getId() == -1) {
                        puzzle.move('l');
                        Toast.makeText(MainActivity.this, "left", Toast.LENGTH_LONG).show();
                    }
                    if (left != null && left.getId() == -1) {
                        puzzle.move('r');
                        Toast.makeText(MainActivity.this, "right", Toast.LENGTH_LONG).show();
                    }
                    if (up != null && up.getId() == -1) {
                        puzzle.move('d');
                        Toast.makeText(MainActivity.this, "down", Toast.LENGTH_LONG).show();
                    }
                    if (down != null && down.getId() == -1) {
                        puzzle.move('u');
                        Toast.makeText(MainActivity.this, "up", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }
}


















