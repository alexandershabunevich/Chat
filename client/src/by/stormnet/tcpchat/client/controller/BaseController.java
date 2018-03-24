package by.stormnet.tcpchat.client.controller;

import by.stormnet.tcpchat.client.screen.ScreenManager;
import by.stormnet.tcpchat.client.screen.Screens;
import javafx.fxml.Initializable;

import java.io.IOException;

public abstract class BaseController implements Initializable {

    protected void navigate(Screens screen) throws IOException {
        ScreenManager.getInstance().switchScreen(screen);
    }
}