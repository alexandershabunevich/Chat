package by.stormnet.tcpchat.server.core;

import by.stormnet.tcpchat.commons.interfaces.CallbackHandler;
import by.stormnet.tcpchat.commons.interfaces.IClientThread;
import by.stormnet.tcpchat.commons.interfaces.MessageDispatcher;
import by.stormnet.tcpchat.commons.messaging.Message;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class MessageDispatcherImpl<T extends Thread & IClientThread> implements MessageDispatcher<T>, CallbackHandler {
    private List<T> clients;

    public MessageDispatcherImpl() {
        clients = new LinkedList<>();
    }

    @Override
    public void handleCallback(Message message) {
        dispatchMessage(message);
    }

    @Override
    public void registerClientThread(T client) {
        client.registerCallbackHandler(this);
        clients.add(client);
        client.start();
    }

    @Override
    public void closeDispatcher() {
        for (T client : clients) {
            if (client != null){
                client.finish();
            }
        }
        clients.clear();
    }

    @Override
    public void dispatchMessage(Message message){
        switch (message.getType()){
            case Message.MESSAGE_TYPE_PUBLIC:
                sendToAll(message);
                break;
            case Message.MESSAGE_TYPE_SERVICE:
                System.out.println("service message received");
                sendToAll(message);
                break;
            default:
                System.out.println("Unknown message type.");
        }
    }

    private void sendToAll(Message message, T... clients) {
        if (clients.length > 0) {
            for (T client : clients) {
                try {
                    client.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            for (T client : this.clients) {
                try {
                    client.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
