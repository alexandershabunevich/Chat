package by.stormnet.tcpchat.commons.messaging;

import java.io.Serializable;

public abstract class Message implements Serializable {

    private static final long serialVersionUID = 7332812220954487923L;

    public static final int MESSAGE_TYPE_PUBLIC     = 1;
    public static final int MESSAGE_TYPE_SERVICE    = 2;

    private int type;
    protected long crDate;
    protected String messageBody;

    public Message(int type, long crDate, String messageBody) {
        this.type = type;
        this.crDate = crDate;
        this.messageBody = messageBody;
    }

    public long getCrDate() {
        return crDate;
    }

    public int getType() {
        return type;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
}