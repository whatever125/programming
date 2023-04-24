package client.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class TCPClient implements NetworkClient{
    private final InetSocketAddress address;
    private SocketChannel socketChannel;
    private Selector selector;

    private static final int BUFFER_SIZE = 4096;

    public TCPClient(InetSocketAddress address) {
        this.address = address;
    }

    @Override
    public void openConnection() {
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);

//            selector = Selector.open();
//            socketChannel.register(selector, SelectionKey.OP_CONNECT);

            socketChannel.connect(address);

            while (socketChannel.isConnectionPending()) {
                socketChannel.finishConnect();
            }
        } catch (IOException e) {
            // todo
            throw new RuntimeException(e);
        }
    }

    @Override
    public void closeConnection() {
        try {
            socketChannel.close();
        } catch (IOException e) {
            // todo
            throw new RuntimeException(e);
        }
    }

    @Override
    public ByteBuffer receiveData() {
        try {
            // todo
            assert socketChannel.isConnected();

            ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
            socketChannel.read(byteBuffer);
            return byteBuffer;
        } catch (IOException e) {
            // todo
            throw new RuntimeException(e);
        }
    }

    @Override
    public int sendData(byte[] bytesToSend) {
        try {
            // todo
            assert socketChannel.isConnected();

            ByteBuffer byteBuffer = ByteBuffer.wrap(bytesToSend);

            socketChannel.write(byteBuffer);
            int numberOfWrittenBytes = socketChannel.write(byteBuffer);
//            int numberOfWrittenBytes = 1;
            assert numberOfWrittenBytes != -1; // todo
            return numberOfWrittenBytes;
        } catch (IOException e) {
            // todo
            throw new RuntimeException(e);
        }
    }
}
