package by.stormnet.tcpchat.client.logic;

import by.stormnet.tcpchat.client.core.Core;
import by.stormnet.tcpchat.commons.interfaces.IClientThread;
import by.stormnet.tcpchat.commons.messaging.Message;

import java.io.IOException;

public class TCPLogic<T extends Thread & IClientThread> extends Logic<T> {


    public TCPLogic(IControllerBridge bridge) {
        super(bridge);
    }

    @Override
    public void sendMessage(Message message) {
        try {
            Core.getInstance().getClientThread().sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleCallback(Message message) {
        dispatchMessage(message);
    }

    @Override
    public void registerClientThread(T client) {
        client.registerCallbackHandler(this);
        client.start();
    }

    @Override
    public void closeDispatcher() {
        Core.getInstance().getClientThread().finish();
    }

    @Override
    public void dispatchMessage(Message message) {
        if (bridge != null){
            bridge.handleLogicEvent(message);
        }
    }
}
