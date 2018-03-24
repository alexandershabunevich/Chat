package by.stormnet.tcpchat.commons.messaging;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PublicMessage extends Message implements Serializable {
    private static final long serialVersionUID = -4239666830926214110L;

    private String senderName;

    private PublicMessage(String messageBody) {
        super(Message.MESSAGE_TYPE_PUBLIC, System.currentTimeMillis(), messageBody);
    }

    public PublicMessage(String messageBody, String senderName) {
        this(messageBody);
        this.senderName = senderName;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MMM.yyyy 'at' HH:mm:ss");

        return String.format("%s - %s: %s\n",
                senderName, simpleDateFormat.format(new Date(crDate)), messageBody);
    }
}
