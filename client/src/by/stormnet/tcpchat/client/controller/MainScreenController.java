package by.stormnet.tcpchat.client.controller;

import by.stormnet.tcpchat.client.core.Core;
import by.stormnet.tcpchat.client.logic.IControllerBridge;
import by.stormnet.tcpchat.client.logic.Logic;
import by.stormnet.tcpchat.client.logic.TCPLogic;
import by.stormnet.tcpchat.client.screen.ScreenManager;
import by.stormnet.tcpchat.commons.messaging.Message;
import by.stormnet.tcpchat.commons.messaging.PublicMessage;
import by.stormnet.tcpchat.commons.messaging.ServiceMessage;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController extends BaseController implements IControllerBridge {
    @FXML
    private TextArea chatLog;
    @FXML
    private TextArea message;

    private Logic logic;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logic = new TCPLogic(this);
        logic.registerClientThread(Core.getInstance().getClientThread());
        ScreenManager.getInstance().getWindow().setTitle("Chat - " + Core.getInstance().getUserName());
        ServiceMessage service = new ServiceMessage(" join to channel", Core.getInstance().getUserName());
        logic.sendMessage(service);
    }

    @FXML
    private void keyPressed(KeyEvent e) {
        if (e.isControlDown() && e.getCode() == KeyCode.ENTER){
            send();
        }
    }

    @FXML
    private void sendButtonClicked(MouseEvent e) {
        send();
    }

    @Override
    public void handleLogicEvent(Message message) {
        chatLog.appendText(message.toString());
    }

    private void send(){
        PublicMessage message = new PublicMessage(this.message.getText(), Core.getInstance().getUserName());
        logic.sendMessage(message);
        this.message.clear();
    }
}