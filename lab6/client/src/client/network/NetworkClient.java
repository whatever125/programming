package client.network;

import java.nio.ByteBuffer;

public interface NetworkClient {
    void openConnection();
    void closeConnection();
    ByteBuffer receiveData();
    int sendData(byte[] bytesToSend);
}