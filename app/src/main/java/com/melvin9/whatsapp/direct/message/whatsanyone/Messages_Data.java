package com.melvin9.whatsapp.direct.message.whatsanyone;

/**
 * Created by Melvin on 12/20/2017.
 */

public class Messages_Data {
    private String Message;
    Messages_Data(String message) {
        Message = message;
    }
    public String getMessage() {
        return Message;
    }
    public void setMessage(String message) {
        Message = message;
    }
}
