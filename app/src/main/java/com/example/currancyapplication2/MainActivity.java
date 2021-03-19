package com.example.currancyapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private static final String uri = "https://www.cbr-xml-daily.ru/daily_json.js";
    private JsonParser jsonParser;
    private List<CurrancyItem> currencyItems;
    private CustomAdapter adapter;
    private Thread secondThread;
    private ListView listView;
    private Runnable runnable;

    ImageButton updateButton;
    private TextView updateExchangeDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        currencyItems = new ArrayList<>();

        updateButton = findViewById(R.id.updateCurrancyBtn);
        updateExchangeDate = findViewById(R.id.updateExchangeDate);


        init();
        timerForUpdate();
        adapter = new CustomAdapter(this, R.layout.list_item_layout, currencyItems, getLayoutInflater());

        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                openScreenConversion();
            }
        });
    }

    public void openScreenConversion(){
        Intent intent = new Intent(this,ScreenConversionActivity.class);

        startActivity(intent);
    }


    @SuppressLint("SetTextI18n")
    private void init() {
        if (checkInternetConnection()) {
            runnable = () -> {
                jsonParser = new JsonParser();
                currencyItems = jsonParser.getAllCurrencies(uri);
                runOnUiThread(() -> adapter.update(currencyItems));
            };
            secondThread = new Thread(runnable);
            secondThread.start();
        }
        updateExchangeDate.setText("Курс обновлен: " + getCurrentTime());
    }

    public void updateClick(View v) {
        new Thread(() -> runOnUiThread(new Runnable() {
            @Override
            public void run() {
                init();
            }
        })).start();
    }

    private boolean checkInternetConnection() {
        ConnectivityManager connManager =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        if (networkInfo == null) {
            Toast.makeText(this, "No default network is currently active",
                    Toast.LENGTH_LONG).show();

            return false;
        }

        if (!networkInfo.isConnected()) {
            Toast.makeText(this, "Network is not connected",
                    Toast.LENGTH_LONG).show();

            return false;
        }

        if (!networkInfo.isAvailable()) {
            Toast.makeText(this, "Network not available",
                    Toast.LENGTH_LONG).show();

            return false;
        }

        return true;
    }

    private void timerForUpdate() {
        new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                init();
                timerForUpdate();
            }
        }.start();
    }

    private String getCurrentTime() {
        Date currentDate = new Date();

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy / HH:mm:ss", Locale.getDefault());
        return dateFormat.format(currentDate);
    }
}