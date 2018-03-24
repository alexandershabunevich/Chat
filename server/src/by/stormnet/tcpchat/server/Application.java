package by.stormnet.tcpchat.server;

import by.stormnet.tcpchat.server.core.Server;

import java.io.IOException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Application application = new Application();
        application.runTheApp(args.length > 0 ? args[0] : "9999");
    }

    private void runTheApp(String... portValue) {
        int port = Integer.parseInt(portValue[0]);
        if (!checkPortValue(port)){
            port = readUserPort();
        }

        System.out.println("server will start at port " + port);
        try {
            Server server = new Server(port);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
            runTheApp(String.valueOf(readUserPort()));
        }
    }

    private boolean checkPortValue(int port) {
        return port > 1024 && port <= 65535;
    }

    private int readUserPort() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter a port number:");
        int port;
        if (scanner.hasNextInt()){
            port = scanner.nextInt();
            if (checkPortValue(port))
                return port;
            System.out.println("port value should be more then 1024 and less or equals 65535");
        }
        return readUserPort();
    }
}