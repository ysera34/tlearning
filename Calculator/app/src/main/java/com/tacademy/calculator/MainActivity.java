package com.tacademy.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView resultTextView;
    private ScrollView mScrollView;
    // 계산식
    public String calculusStr = "";
    public String calculus = "";

    public String operator = "";
    public int number1;
    public int number2;

    // 결과
    public int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewWidget();

    }

    private void findViewWidget() {
        resultTextView = (TextView) findViewById(R.id.result_text_view);
        mScrollView = (ScrollView) findViewById(R.id.scroll_view);
        findViewById(R.id.eraser_button).setOnClickListener(this);
        findViewById(R.id.number7_button).setOnClickListener(this);
        findViewById(R.id.number8_button).setOnClickListener(this);
        findViewById(R.id.number9_button).setOnClickListener(this);
        findViewById(R.id.option_divide_button).setOnClickListener(this);
        findViewById(R.id.number4_button).setOnClickListener(this);
        findViewById(R.id.number5_button).setOnClickListener(this);
        findViewById(R.id.number6_button).setOnClickListener(this);
        findViewById(R.id.option_multiply_button).setOnClickListener(this);
        findViewById(R.id.number1_button).setOnClickListener(this);
        findViewById(R.id.number2_button).setOnClickListener(this);
        findViewById(R.id.number3_button).setOnClickListener(this);
        findViewById(R.id.option_subtract_button).setOnClickListener(this);
        findViewById(R.id.number_dot_button).setOnClickListener(this);
        findViewById(R.id.number0_button).setOnClickListener(this);
        findViewById(R.id.option_equal_button).setOnClickListener(this);
        findViewById(R.id.option_add_button).setOnClickListener(this);
    }

    private int calculateResult(int num1, int num2, String operator) {

        switch (operator) {

            case "A":
                result = num1 + num2;
                break;
            case "S":
                result = num1 - num2;
                break;
            case "M":
                result = num1 * num2;
                break;
            case "D":
                result = num1 / num2;
                break;
        }

        return result;
    }

    private void enableButton() {
        findViewById(R.id.option_add_button).setEnabled(true);
        findViewById(R.id.option_subtract_button).setEnabled(true);
        findViewById(R.id.option_multiply_button).setEnabled(true);
        findViewById(R.id.option_divide_button).setEnabled(true);
    }

    private void disableButton() {
        findViewById(R.id.option_add_button).setEnabled(false);
        findViewById(R.id.option_subtract_button).setEnabled(false);
        findViewById(R.id.option_multiply_button).setEnabled(false);
        findViewById(R.id.option_divide_button).setEnabled(false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.eraser_button:
                //TODO implement
                calculusStr = "";
                calculus = "";
                resultTextView.setText(calculusStr);
                break;
            case R.id.number7_button:
                //TODO implement
                calculusStr += "7";
                calculus += "7";
                resultTextView.setText(calculusStr);
                break;
            case R.id.number8_button:
                //TODO implement
                calculusStr += "8";
                calculus += "8";
                resultTextView.setText(calculusStr);
                break;
            case R.id.number9_button:
                //TODO implement
                calculusStr += "9";
                calculus += "9";
                resultTextView.setText(calculusStr);
                break;
            case R.id.number4_button:
                //TODO implement
                calculusStr += "4";
                calculus += "4";
                resultTextView.setText(calculusStr);
                break;
            case R.id.number5_button:
                //TODO implement
                calculusStr += "5";
                calculus += "5";
                resultTextView.setText(calculusStr);
                break;
            case R.id.number6_button:
                //TODO implement
                calculusStr += "6";
                calculus += "6";
                resultTextView.setText(calculusStr);
                break;
            case R.id.number1_button:
                //TODO implement
                calculusStr += "1";
                calculus += "1";
                resultTextView.setText(calculusStr);
                break;
            case R.id.number2_button:
                //TODO implement
                calculusStr += "2";
                calculus += "2";
                resultTextView.setText(calculusStr);
                break;
            case R.id.number3_button:
                //TODO implement
                calculusStr += "3";
                calculus += "3";
                resultTextView.setText(calculusStr);
                break;
            case R.id.number_dot_button:
                //TODO implement
                calculusStr += ".";
                resultTextView.setText(calculusStr);
                break;
            case R.id.number0_button:
                //TODO implement
                calculusStr += "0";
                calculus += "0";
                resultTextView.setText(calculusStr);
                break;

            case R.id.option_add_button:
                //TODO implement
                number1 = Integer.parseInt(calculus);
                operator = "A";
                calculusStr += "+";
                calculus += "A";
                resultTextView.setText(calculusStr);
                disableButton();
                break;
            case R.id.option_subtract_button:
                //TODO implement
                number1 = Integer.parseInt(calculus);
                operator = "S";
                calculusStr += "-";
                calculus += "S";
                resultTextView.setText(calculusStr);
                disableButton();
                break;
            case R.id.option_multiply_button:
                //TODO implement
                number1 = Integer.parseInt(calculus);
                operator = "M";
                calculusStr += "*";
                calculus += "M";
                resultTextView.setText(calculusStr);
                disableButton();
                break;
            case R.id.option_divide_button:
                //TODO implement
                number1 = Integer.parseInt(calculus);
                operator = "D";
                calculusStr += "/";
                calculus += "D";
                resultTextView.setText(calculusStr);
                disableButton();
                break;

            case R.id.option_equal_button:
                //TODO implement
                number2 = Integer.parseInt(calculus.split(operator)[1]);
                result = calculateResult(number1, number2, operator);
                calculusStr += "=\n" + result;
                resultTextView.setText(calculusStr);
                mScrollView.fullScroll(View.FOCUS_DOWN);

                calculusStr = String.valueOf(result);
                calculus = String.valueOf(result);
                enableButton();
                break;
        }
    }
}
