package com.ktu.dev.melvin.whatsanyone;

public interface MainView {
    void hideActionBar();
    void prepareList();
    void onWebBtnClick();
    void onSendClick();
    void onRateClick();
    void onSeekBarChanged(int i);

}
