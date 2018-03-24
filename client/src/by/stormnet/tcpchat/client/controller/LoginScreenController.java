package by.stormnet.tcpchat.client.controller;

import by.stormnet.tcpchat.client.core.Core;
import by.stormnet.tcpchat.client.screen.ScreenManager;
import by.stormnet.tcpchat.client.screen.Screens;
import by.stormnet.tcpchat.commons.threading.ClientThread;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreenController extends BaseController {
    @FXML
    private Label errorMessage;
    @FXML
    private TextField loginText;
    @FXML
    private TextField hostText;
    @FXML
    private TextField portText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorMessage.setStyle("-fx-text-fill: #FF0000");
        ScreenManager.getInstance().getWindow().setTitle("Chat - Login");
    }

    @FXML
    private void onConnectButtonClicked(MouseEvent event) {
        if (!checkUserInput()){
            errorMessage.setText("One or more fields not filled!");
            return;
        }
        try {
            Core.getInstance().setPortNumber(Integer.parseInt(portText.getText()));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            errorMessage.setText(e.getMessage());
            return;
        }
        Core.getInstance().setUserName(loginText.getText());
        Core.getInstance().setHostAddress(hostText.getText());

        try {
            Core.getInstance().setClientThread(new ClientThread(new Socket(hostText.getText(), Core.getInstance().getPortNumber())));
            ScreenManager.getInstance().switchScreen(Screens.MAIN_SCREEN);
        } catch (IOException e) {
            e.printStackTrace();
            errorMessage.setText(e.getMessage());
        }
    }

    private boolean checkUserInput(){
        return !loginText.getText().isEmpty() && !hostText.getText().isEmpty() && !portText.getText().isEmpty();
    }
}
