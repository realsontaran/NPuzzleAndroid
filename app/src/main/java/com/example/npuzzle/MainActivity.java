package com.example.npuzzle;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
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
    TextView counter;

    boolean click = false;
    boolean created = false;
    int Row;
    int Col;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counter = findViewById(R.id.movecount);

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


                if (click) {
                    tableClear();
                }

                if (!click) {
                    created = true;
                    click = true;
                    shuffleBoard();
                }
            }
        });

        shuffle = findViewById(R.id.shuffleButton);
        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuffleBoard();
            }
        });

        TableLayout tableLayout = findViewById(R.id.myTable);

        tableLayout.setOnTouchListener(new SwipeDetect(MainActivity.this) {
            @SuppressLint("SetTextI18n")
            public void onSwipeRight() {
                if (!puzzle.isSolved()) {
                    if (puzzle.move('r')) {
                        loadBoard();
                        counter.setText("Move Counter: " + puzzle.numberOfMoves());
                    }
                }
            }

            @SuppressLint("SetTextI18n")
            public void onSwipeLeft() {
                if (!puzzle.isSolved()) {
                    if (puzzle.move('l')) {
                        loadBoard();
                        counter.setText("Move Counter: " + puzzle.numberOfMoves());
                    }
                }
            }

            @SuppressLint("SetTextI18n")
            public void onSwipeTop() {
                if (!puzzle.isSolved()) {
                    if (puzzle.move('u')) {
                        loadBoard();
                        counter.setText("Move Counter: " + puzzle.numberOfMoves());
                    }
                }
            }

            @SuppressLint("SetTextI18n")
            public void onSwipeBottom() {
                if (!puzzle.isSolved()) {
                    if (puzzle.move('d')) {
                        loadBoard();
                        counter.setText("Move Counter: " + puzzle.numberOfMoves());
                    }
                }
            }
        });

    }

    /**
     * Shuffle the board, clear the tableview and load the board to the tableview
     */
    @SuppressLint("SetTextI18n")
    private void shuffleBoard() {
        if (created) {
            puzzle.shuffle();
            tableClear();
            loadBoard();
            counter.setText("Move Counter: 0");
        }
    }

    /**
     * Get the index' from board and load it to the Table View
     */
    public void loadBoard() {
        tableClear();

        for (int i = 0; i < Row; i++) {
            for (int j = 0; j < Col; j++) {
                tableCreate(puzzle.getValue(i, j));
            }
        }

        if (puzzle.isSolved()) {
            Toast.makeText(MainActivity.this, "CONGRATULATIONS PUZZLE SOLVED!", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Clears the Table View for next Board
     */
    public void tableClear() {
        TableLayout table = findViewById(R.id.myTable);
        for (int i = 0; i < table.getChildCount(); i++) {
            View child = table.getChildAt(i);
            if (child instanceof TableRow)
                ((ViewGroup) child).removeAllViews();
        }
        click = false;
        System.gc();
    }

    /**
     * Take an index for loading it to the sub views of Table view
     *
     */
    public void tableCreate(final int index) {

        TableLayout table = findViewById(R.id.myTable);

        int textOnRow = 0;

        int numRows = table.getChildCount();

        TableRow row = null;
        if (numRows > 0) {
            row = (TableRow) table.getChildAt(numRows - 1);
            textOnRow = row.getChildCount();
        }

        if (numRows == 0 || textOnRow == Col) {
            row = new TableRow(this);
            table.addView(row);
            textOnRow = 0;
        }

        if (textOnRow < Col) {
            TextView tile = new TextView(this);
            tile.setBackgroundResource(R.drawable.border);
            tile.setTextSize(19);
            tile.setTextColor(Color.WHITE);
            tile.setGravity(Gravity.CENTER);

            if (index != -1) {
                tile.setText(String.format("%s", index));
                tile.setId(index);
            } else {
                tile.setBackgroundResource(R.drawable.empty_border);
                tile.setText(" ");
                tile.setId(index);
            }
            assert row != null;
            row.addView(tile, table.getWidth() / Col, table.getHeight() / Row);
        }
    }
}