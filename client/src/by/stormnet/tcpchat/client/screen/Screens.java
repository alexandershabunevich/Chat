package by.stormnet.tcpchat.client.screen;

public enum Screens {
    LOGIN_SCREEN{
        @Override
        public String getValue() {
            return "LoginScreenView";
        }
    },
    MAIN_SCREEN {
        @Override
        public String getValue() {
            return "MainScreenView";
        }
    };

    public abstract String getValue();
}
