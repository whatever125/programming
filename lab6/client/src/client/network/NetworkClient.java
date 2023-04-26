package client.network;

import common.requests.Request;
import common.responses.Response;

public interface NetworkClient {
    void openConnection();
    void closeConnection();
    byte[] receiveData();
    void sendData(byte[] bytesToSend);
    Response sendRequest(Request request);
}