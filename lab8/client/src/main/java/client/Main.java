package client;

import client.GUIClient.GUIClient;
import client.consoleClient.ConsoleClient;

import javax.swing.*;

/**
 * The Main class is responsible for running the application by creating a ConsoleClient object
 * and calling its run method.
 */
public class Main {
    /**
     * The run method creates a ConsoleClient object and calls its run method.
     * @param args an array of command-line arguments
     */
    public static void main(String[] args) throws UnsupportedLookAndFeelException {
//        tcpClient.openConnection();
//        String s = "Hello world";
//        tcpClient.sendData(s.getBytes(StandardCharsets.UTF_8));
//        tcpClient.closeConnection();

//        AbstractClient guiClient = new GUIClient();
//        guiClient.run();

        AbstractClient guiClient = new GUIClient();
        guiClient.run();

//        Runnable runnable = new Runnable(){
//
//            @Override
//            public void run() {
//                AbstractClient consoleClient = new ConsoleClient();
//                consoleClient.run();
//            }
//        };
//        runnable.run();
    }
}
