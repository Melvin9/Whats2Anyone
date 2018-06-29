package com.ktu.dev.melvin.whatsanyone;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {
    public static EditText number, message;
    TextView money;
    CountryCodePicker ccp;
    ImageView send;
    SeekBar seekBar;
    int donate_money;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private List<public_data> data_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();
        number = findViewById(R.id.number);
        money = findViewById(R.id.money);
        message = findViewById(R.id.main_message);
        send = findViewById(R.id.send);
        ccp = findViewById(R.id.ccp);
        seekBar = findViewById(R.id.seekBar);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        data_main = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new public_adapter(data_main, getApplicationContext());
        recyclerView.setAdapter(adapter);
        prepareList();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://wa.me/" + ccp.getSelectedCountryCode() + number.getText().toString().trim() + "?text=" + message.getText().toString().trim();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                switch (i) {
                    case 0:
                        donate_money = 1;
                        break;
                    case 1:
                        donate_money = 2;
                        break;
                    case 2:
                        donate_money = 3;
                        break;
                    case 3:
                        donate_money = 5;
                        break;
                }
                money.setText(String.format("%s$", String.valueOf(donate_money)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void prepareList() {
        public_data a = new public_data("Hi I am Good");
        data_main.add(a);
        a = new public_data("Hey how r u?");
        data_main.add(a);
        a = new public_data("Hello");
        data_main.add(a);
        a = new public_data("Hey");
        data_main.add(a);
        a = new public_data("WhatsUp");
        data_main.add(a);
        a = new public_data("Long Time No see");
        data_main.add(a);
        a = new public_data("I didn't Save Your Number");
        data_main.add(a);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void showMessage(String Message) {
        try {
            Toast.makeText(MainActivity.this, Message, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }
}
