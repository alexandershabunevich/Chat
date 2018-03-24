package by.stormnet.tcpchat.commons.threading;

import by.stormnet.tcpchat.commons.interfaces.CallbackHandler;
import by.stormnet.tcpchat.commons.interfaces.IClientThread;
import by.stormnet.tcpchat.commons.messaging.Message;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ClientThread extends Thread implements IClientThread {
    private boolean isRunnig;

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private CallbackHandler handler;

    public ClientThread(Socket socket) throws IOException {
        this.socket = socket;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    public ClientThread(Socket socket, CallbackHandler handler) throws IOException {
        this(socket);
        this.handler = handler;
    }

    @Override
    public void sendMessage(Message message) throws IOException {
        if (out != null){
            synchronized (out) {
                out.writeObject(message);
            }
        }
    }

    @Override
    public void registerCallbackHandler(CallbackHandler handler) {
        this.handler = handler;
    }

    @Override
    public void finish() {
        isRunnig = false;
    }

    @Override
    public void run() {
        isRunnig = true;
        while (isRunnig){
            if (handler != null){
                try {
                    handler.handleCallback((Message)in.readObject());
                    Thread.sleep(1L);
                } catch (EOFException e){
                    e.printStackTrace();
                    isRunnig = false;
                } catch (SocketException e){
                    e.printStackTrace();
                    isRunnig = false;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    isRunnig = false;
                }
            }
        }
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
