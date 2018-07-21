package com.melvin9.whatsapp.direct.message.whatsanyone;

public interface MainView {
    void hideActionBar();
    void prepareList();
    void onWebBtnClick();
    void onSendClick();
    void onRateClick();
    void onSeekBarChanged(int i);
    void showAdd();
    void showCard();
    void initialDeclaration();
    void onDonate();
}
