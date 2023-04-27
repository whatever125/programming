package client.network;

import client.exceptions.NetworkClientException;
import common.requests.Request;
import common.responses.Response;

public interface NetworkClient {
    void openConnection() throws NetworkClientException;
    void closeConnection() throws NetworkClientException;
    void silentCloseConnection();

    void sendData(byte[] bytesToSend) throws NetworkClientException;
    Response sendRequest(Request request) throws NetworkClientException;
}