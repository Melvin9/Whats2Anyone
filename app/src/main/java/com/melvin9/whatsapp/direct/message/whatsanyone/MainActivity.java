package com.melvin9.whatsapp.direct.message.whatsanyone;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.hbb20.CountryCodePicker;
import com.melvin9.whatsapp.direct.message.whatsanyone.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements MainView {
    @SuppressLint("StaticFieldLeak")

    public static EditText number, message;
    Button donate;
    CountryCodePicker ccp;
    Button send,resc_status,saved;
    ImageButton webBtn,rate;
    RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Messages_Data> data_main;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        initialDeclaration();
        hideActionBar();
        showCard();
        showAdd();

    }
    @Override
    public void prepareList() {
        Messages_Data a = new Messages_Data("Hi I am Good");
        data_main.add(a);
        a = new Messages_Data("Hey how r u?");
        data_main.add(a);
        a = new Messages_Data("I didn't Save Your Number");
        data_main.add(a);
        a = new Messages_Data("Hey");
        data_main.add(a);
        a = new Messages_Data("WhatsUp");
        data_main.add(a);
        a = new Messages_Data("Long Time No see");
        data_main.add(a);
        a = new Messages_Data("Hello");
        data_main.add(a);
        a = new Messages_Data("Do i know you?");
        data_main.add(a);
        a = new Messages_Data("What's your name?");
        data_main.add(a);
        a = new Messages_Data("Where are you from?");
        data_main.add(a);
        a = new Messages_Data("Bye");
        data_main.add(a);
        adapter.notifyDataSetChanged();
    }
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onWebBtnClick() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

        WebView wv = new WebView(MainActivity.this);
        wv.loadUrl("https://melvin9.github.io/");
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setSupportZoom(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        wv.setWebChromeClient(new WebChromeClient() {
            private ProgressDialog mProgress;
            @Override
            public void onProgressChanged(WebView view, int progress) {
                if (mProgress == null) {
                    mProgress = new ProgressDialog(MainActivity.this);
                    mProgress.show();
                }
                mProgress.setMessage("Loading " + String.valueOf(progress) + "%");
                if (progress == 100) {
                    mProgress.dismiss();
                    mProgress = null;
                }
            }
        });
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        alert.setView(wv);
        alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        alert.show();
    }
    @Override
    public void onSendClick() {
        if(number.getText().toString().length()>4&&number.getText().toString().length()<12){
            if(message.getText().toString().isEmpty())
                Toast.makeText(MainActivity.this, "Message Cannot be Empty", Toast.LENGTH_SHORT).show();
            else{
                String url = "https://wa.me/" + ccp.getSelectedCountryCode() + number.getText().toString().trim() + "?text=" + message.getText().toString().trim();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        }
        else Toast.makeText(MainActivity.this, "Number Not Valid", Toast.LENGTH_SHORT).show();
       }
    @Override
    public void onRateClick() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+getPackageName())));
    }
    @Override
    public void showAdd() {
        MobileAds.initialize(this, "ca-app-pub-5841415504299472~3573597901");
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
    @Override
    public void showCard() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView= findViewById(R.id.recyclerView);
        data_main = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Messages_Adapter(data_main, getApplicationContext());
        recyclerView.setAdapter(adapter);
        prepareList();
    }
    @Override
    public void initialDeclaration() {
        number = findViewById(R.id.number);
        webBtn = findViewById(R.id.web);
        message = findViewById(R.id.main_message);
        send = findViewById(R.id.send);
        resc_status = findViewById(R.id.recent_status);
        ccp = findViewById(R.id.ccp);
        saved = findViewById(R.id.saved_status);
        rate = findViewById(R.id.rate);
        resc_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RecentStatus.class));
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSendClick();
            }
        });
        webBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onWebBtnClick();
            }
        });
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRateClick();
            }
        });
        saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SavedStatus.class));

            }
        });
    }
    @Override
    public void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();
    }
}
