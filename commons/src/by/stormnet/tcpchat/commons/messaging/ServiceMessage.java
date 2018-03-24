package by.stormnet.tcpchat.commons.messaging;

import java.io.Serializable;

public class ServiceMessage extends Message implements Serializable {

    private static final long serialVersionUID = 8329153823446949923L;
    private String userName;

    private ServiceMessage(String messageBody) {
        super(Message.MESSAGE_TYPE_SERVICE, System.currentTimeMillis(), messageBody);
    }

    public ServiceMessage(String messageBody,String username) {
        this(messageBody);
        this.userName = username;
    }

    @Override
    public String toString() {
        return String.format(userName + messageBody+ "\n");
    }
}
