package by.stormnet.tcpchat.commons.interfaces;

import by.stormnet.tcpchat.commons.messaging.Message;

import java.io.IOException;

public interface IClientThread {
    void sendMessage(Message message) throws IOException;
    void registerCallbackHandler(CallbackHandler handler);
    void finish();
}