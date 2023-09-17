package client.commands;

import client.consoleClient.Client;
import client.network.NetworkClient;

import java.io.Serializable;

public abstract class AbstractCommand implements Command, Serializable {
    final transient Client client;
    final transient NetworkClient networkClient;
    final String name;
    final String login;
    final transient String password;

    public AbstractCommand(String name, Client client, NetworkClient networkClient, String login, String password) {
        this.name = name;
        this.client = client;
        this.networkClient = networkClient;
        this.login = login;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
