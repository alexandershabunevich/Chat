package by.stormnet.tcpchat.client.screen;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ScreenManager {
    public static final double SCREEN_WIDTH = 1024.0;
    public static final double SCREEN_HEIGHT = 600.0;

    private Stage window;
    private Map<Screens, Scene> screens;

    private static volatile ScreenManager instance = null;

    private ScreenManager(){
        screens = new HashMap<>();
    }

    public static ScreenManager getInstance() {
        if (instance == null){
            synchronized (ScreenManager.class){
                if (instance == null){
                    instance = new ScreenManager();
                }
            }
        }

        return instance;
    }

    public Stage getWindow() {
        return window;
    }

    public void registerWindow(Stage window) {
        this.window = window;
    }

    private Scene createScene(Screens screen, boolean cache) throws IOException {
        Scene scene;
        scene = new Scene(FXMLLoader.load(getClass().getResource(String.format("/views/%s.fxml", screen.getValue()))),
                SCREEN_WIDTH,
                SCREEN_HEIGHT);
        if (cache){
            screens.put(screen, scene);
        }

        return scene;
    }

    public void switchScreen(Screens screen) throws IOException {
        if (window == null){
            System.out.println("please init window first!");
            return;
        }

        window.setScene(screens.get(screen) == null ? createScene(screen, true) : screens.get(screen));
    }
}
