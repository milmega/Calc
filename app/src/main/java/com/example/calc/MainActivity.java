package com.example.calc;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.displayID);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getString(R.string.display).equals(display.getText().toString())){
                    display.setText("");
                }
            }
        });
    }

    private void updateText(String strToAdd){
        String oldStr = display.getText().toString();
        int cursPos = display.getSelectionStart();
        String left = oldStr.substring(0, cursPos);
        String right = oldStr.substring(cursPos);

        if(getString(R.string.display).equals(display.getText().toString())){
            display.setText(strToAdd);
            display.setSelection(cursPos + 1);
        }
        else{
            display.setText(String.format("%s%s%s", left, strToAdd, right));
            display.setSelection(cursPos + 1);
        }


    }

    public void zeroBTN(View view) {
        updateText("0");
    }

    public void oneBTN(View view) {
        updateText("1");
    }
    public void twoBTN(View view) {
        updateText("2");
    }
    public void threeBTN(View view) {
        updateText("3");
    }

    public void fourBTN(View view) {
        updateText("4");
    }

    public void fiveBTN(View view) {
        updateText("5");
    }
    public void sixBTN(View view) {
        updateText("6");
    }
    public void sevenBTN(View view) {
        updateText("7");
    }

    public void eightBTN(View view) {
        updateText("8");
    }

    public void nineBTN(View view) {
        updateText("9");
    }
    public void addBTN(View view) {
        updateText("+");
    }
    public void subtractBTN(View view) {
        updateText("-");
    }

    public void divideBTN(View view) {
        updateText("÷");
    }

    public void multiplyBTN(View view) {
        updateText("×");
    }
    public void plusMinusBTN(View view) {
        updateText("-");
    }
    public void equalsBTN(View view) {
        String userExp = display.getText().toString();

        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");

        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());
    }

    public void clearBTN(View view) {
        display.setText("");
    }

    public void backspaceBTN(View view) {
        int cursPos = display.getSelectionStart();
        int textLength = display.getText().length();

        if(cursPos != 0 && textLength != 0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursPos - 1, cursPos, "");
            display.setText(selection);
            display.setSelection(cursPos - 1);
        }

    }
    public void dotBTN(View view) {
        updateText(".");
    }
    public void powerBTN(View view) {
        updateText("^");
    }

    public int i = 0;

    public void parenthesesBTN(View view) {
        int cursPos = display.getSelectionStart();
        int open = 0;
        int closed = 0;
        int textLength = display.getText().length();

        for(int i = 0; i < cursPos; i++){
            if(display.getText().toString().substring(i, i+1).equals("(")){
                open++;
            }

            if(display.getText().toString().substring(i,i+1).equals(")")){
                closed++;
            }
        }

        if(open == closed || display.getText().toString().substring(textLength-1, textLength).equals("(")){
            updateText("(");
        }

        else if(open < closed || !display.getText().toString().substring(textLength-1, textLength).equals("(")){
            updateText(")");
        }
        display.setSelection(cursPos+1);
    }

}