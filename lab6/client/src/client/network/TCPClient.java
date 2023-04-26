package client.network;

import common.requests.Request;
import common.responses.Response;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient implements NetworkClient{
    private final String host;
    private final int port;
    private Socket socket;

    private InputStream inputStream;
    private OutputStream outputStream;

    private static final int BUFFER_SIZE = 4096;

    public TCPClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void openConnection() {
        try {
            socket = new Socket(host, port);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void closeConnection() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] receiveData() {
        try {
            byte[] responseBytes = new byte[BUFFER_SIZE];
            inputStream.read(responseBytes);
            return responseBytes;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendData(byte[] bytesToSend) {
        try {
            outputStream.write(bytesToSend);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Response sendRequest(Request request) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(request);
            oos.close();

            sendData(baos.toByteArray());

            byte[] receivedBytes = receiveData();

            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(receivedBytes));
            Response response = (Response) ois.readObject();
            ois.close();

            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
