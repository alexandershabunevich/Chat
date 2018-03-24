package by.stormnet.tcpchat.client.core;

import by.stormnet.tcpchat.commons.threading.ClientThread;

public class Core {
    private int portNumber;
    private String hostAddress;
    private String userName;
    private ClientThread clientThread;

    private static volatile Core instance = null;

    private Core() {
    }

    public static Core getInstance() {
        if (instance == null){
            synchronized (Core.class){
                if (instance == null){
                    instance = new Core();
                }
            }
        }

        return instance;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ClientThread getClientThread() {
        return clientThread;
    }

    public void setClientThread(ClientThread clientThread) {
        this.clientThread = clientThread;
    }
}