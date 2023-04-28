package client.network;

import client.exceptions.NetworkClientException;
import common.requests.Request;
import common.responses.Response;

import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;

public class TCPClient implements NetworkClient{
    private final String host;
    private final int port;
    private Socket socket;

    private InputStream inputStream;
    private OutputStream outputStream;

    private static final int CONNECTION_TIMEOUT = 5 * 1000;
    private static final int READ_TIMEOUT = 5 * 1000;
    private static final int MAX_TRIES = 3;

    public TCPClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void openConnection() throws NetworkClientException {
        int count = 1;
        boolean connected = false;
        while (!connected && count <= MAX_TRIES) {
            try {
                connect();
                connected = true;
            } catch (IOException e) {
                System.out.println("Attempt №" + count + " failed");
                count += 1;
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        if (!connected) {
            try {
                connect();
            } catch (ConnectException e) {
                throw new NetworkClientException("Connection refused");
            } catch (SocketTimeoutException e) {
                throw new NetworkClientException("Socket timeout");
            } catch (UnknownHostException e) {
                throw new NetworkClientException("Unknown host exception");
            } catch (IOException e) {
                throw new NetworkClientException(e.getMessage());
            }
        }
    }

    private void connect() throws IOException {
        socket = new Socket();
        socket.setSoTimeout(READ_TIMEOUT);
        socket.connect(new InetSocketAddress(host, port), CONNECTION_TIMEOUT);
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
    }

    public void closeConnection() throws NetworkClientException {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            throw new NetworkClientException("Unable to close connection: " + e.getMessage());
        }
    }

    public void silentCloseConnection() {
        try {
            closeConnection();
        } catch (NetworkClientException e) {
            System.out.println("Unable to close connection: " + e.getMessage());
        }
    }

    public Response sendRequest(Request request) throws NetworkClientException {
        try {
            openConnection();
//            inputStream.skip(inputStream.available());
            sendObject(request);

//            try {
//                TimeUnit.SECONDS.sleep(10);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }

            Response response = (Response) receiveObject();

            return response;
        } catch (ClassNotFoundException e) {
            throw new NetworkClientException("Unknown response from server");
        } catch (EOFException e) {
            throw new NetworkClientException("No response");
        } catch (IOException e) {
            throw new NetworkClientException(e.getMessage());
        } finally {
            silentCloseConnection();
        }
    }

    public void sendObject(Object object) throws NetworkClientException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(object);
        oos.close();

        sendData(baos.toByteArray());
    }

    public void sendData(byte[] bytesToSend) throws NetworkClientException {
        try {
            outputStream.write(bytesToSend);
        } catch (IOException e) {
            throw new NetworkClientException(e.getMessage());
        }
    }

    public Object receiveObject() throws IOException, ClassNotFoundException, NetworkClientException {
        try {
            checkIfHostIsAvailable();
        } catch (NetworkClientException e) {
            throw new NetworkClientException(e.getMessage());
        }

        ObjectInputStream ois = new ObjectInputStream(inputStream);
        Object object = ois.readObject();

        return object;
    }

    private void checkIfHostIsAvailable() throws NetworkClientException {
        try {
            Socket s = new Socket();
            s.connect(new InetSocketAddress(host, port), CONNECTION_TIMEOUT);
            s.close();
        } catch (ConnectException e) {
            throw new NetworkClientException("Server unavailable");
        } catch (SocketTimeoutException e) {
            throw new NetworkClientException("Socket timeout");
        } catch (IOException e) {
            throw new NetworkClientException("Server unavailable: " + e.getMessage());
        }
    }
}
