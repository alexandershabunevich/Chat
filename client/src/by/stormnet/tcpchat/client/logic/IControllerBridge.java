package by.stormnet.tcpchat.client.logic;

import by.stormnet.tcpchat.commons.messaging.Message;

public interface IControllerBridge {
    void handleLogicEvent(Message message);
}
