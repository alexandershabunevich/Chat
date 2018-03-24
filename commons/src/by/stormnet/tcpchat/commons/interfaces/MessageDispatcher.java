package by.stormnet.tcpchat.commons.interfaces;

import by.stormnet.tcpchat.commons.messaging.Message;

public interface MessageDispatcher<T extends Thread & IClientThread> {

    void registerClientThread(T client);
    void closeDispatcher();

    default void dispatchMessage(Message message){}

    default void dispatchMessage(Message message, T client){}
}
