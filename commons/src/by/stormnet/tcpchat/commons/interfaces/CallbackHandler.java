package by.stormnet.tcpchat.commons.interfaces;

import by.stormnet.tcpchat.commons.messaging.Message;

public interface CallbackHandler {
    void handleCallback(Message message);
}
