package by.stormnet.tcpchat.client.logic;

import by.stormnet.tcpchat.commons.interfaces.CallbackHandler;
import by.stormnet.tcpchat.commons.interfaces.IClientThread;
import by.stormnet.tcpchat.commons.interfaces.MessageDispatcher;
import by.stormnet.tcpchat.commons.messaging.Message;

public abstract class Logic<T extends Thread & IClientThread> implements MessageDispatcher<T>, CallbackHandler {
    protected IControllerBridge bridge;

    public Logic(IControllerBridge bridge){
        this.bridge = bridge;
    }

    public IControllerBridge getBridge() {
        return bridge;
    }

    public void setBridge(IControllerBridge bridge) {
        this.bridge = bridge;
    }

    public abstract void sendMessage(Message message);
}