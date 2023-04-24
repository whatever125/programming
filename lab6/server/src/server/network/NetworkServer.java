package server.network;

import java.nio.ByteBuffer;

public interface NetworkServer {
    void openConnection();
    void closeConnection();
    ByteBuffer receiveData();
    int sendData(byte[] bytesToSend);
}
