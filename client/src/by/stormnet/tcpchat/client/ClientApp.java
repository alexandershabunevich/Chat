package by.stormnet.tcpchat.client;

import by.stormnet.tcpchat.client.core.Core;
import by.stormnet.tcpchat.client.screen.ScreenManager;
import by.stormnet.tcpchat.client.screen.Screens;
import javafx.application.Application;
import javafx.stage.Stage;

public class ClientApp extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage window) throws Exception {
        ScreenManager.getInstance().registerWindow(window);
        ScreenManager.getInstance().switchScreen(Screens.LOGIN_SCREEN);
        window.setOnCloseRequest(event -> Core.getInstance().getClientThread().finish());
        window.show();
    }
}
