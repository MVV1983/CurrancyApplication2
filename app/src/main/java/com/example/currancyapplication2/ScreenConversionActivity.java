package com.example.currancyapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class ScreenConversionActivity extends AppCompatActivity {

    private TextView afterConversionValue;
    private EditText conversionValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_conversion);

        Button conversionButton = findViewById(R.id.convertButton);
        TextView currencyName = findViewById(R.id.currencyName);
        conversionValue = findViewById(R.id.inputConvertibleValue);
        afterConversionValue = findViewById(R.id.afterConversionValue);

        String value = getIntent().getStringExtra("value");
        currencyName.setText(getIntent().getStringExtra("select"));
        String code = getIntent().getStringExtra("code");

        conversionButton.setOnClickListener(v -> beginConversion(value, code));

    }


    @SuppressLint("SetTextI18n")
    public void beginConversion(String value, String code) {
        String convertibleValueText = conversionValue.getText().toString();

        if (convertibleValueText.length() != 0) {
            double result = Double.parseDouble(convertibleValueText) / Double.parseDouble(value);

            afterConversionValue.setText(convertibleValueText + "рублей" + " = " + String.format(Locale.ENGLISH, "%.4f", result) + code);
        } else {
            afterConversionValue.setText("Введите значение для конвертации!");
        }
    }
}