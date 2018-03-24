package by.stormnet.tcpchat.server.core;

import by.stormnet.tcpchat.commons.interfaces.MessageDispatcher;
import by.stormnet.tcpchat.commons.threading.ClientThread;

import java.io.IOException;
import java.net.ServerSocket;

public class Server extends Thread {
    private boolean isRunning;
    private ServerSocket serverSocket;

    private MessageDispatcher<ClientThread> messageDispatcher;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        messageDispatcher = new MessageDispatcherImpl<>();
    }

    @Override
    public void run() {
        System.out.println("server is started on port " + serverSocket.getLocalPort());
        isRunning = true;
        while (isRunning){
            try {
                messageDispatcher.registerClientThread(new ClientThread(serverSocket.accept()));
                System.out.println("new connection accepted");
                Thread.sleep(1L);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
                isRunning = false;
            }
        }
        try {
            serverSocket.close();
            messageDispatcher.closeDispatcher();
            System.out.println("server is stopped...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
