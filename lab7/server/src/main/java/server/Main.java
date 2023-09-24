package server;

import server.IOHandlers.*;
import server.collection.Database;
import server.collection.DatabaseConnection;
import server.collection.PostgresDatabase;
import server.exceptions.IOHandlers.IOHandlerException;
import server.exceptions.EnvironmentVariableException;
import server.handlers.ClientCommandHandler;
import server.handlers.CommandHandler;
import server.handlers.Executor;
import server.network.NetworkServer;
import server.network.TCPServer;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Server app launched");

            Database database = new PostgresDatabase(".pgpass");
            DatabaseConnection databaseConnection = database.createConnection();

            String path = System.getenv("PROGRAMMING_LAB");
            if (path == null) {
                throw new EnvironmentVariableException("Environment variable PROGRAMMING_LAB is null");
            }

//            MovieCollectionReader movieCollectionReader = new MovieCollectionXMLFileReader(path);
            MovieCollectionReader movieCollectionReader = new MovieDatabaseReader(databaseConnection);
            MovieCollectionWriter movieCollectionWriter = new MovieCollectionXMLFileWriter(path);

            Executor executor = new Executor(movieCollectionReader, movieCollectionWriter, databaseConnection);
            CommandHandler clientCommandHandler = new ClientCommandHandler(executor, databaseConnection);
            NetworkServer server = new TCPServer(executor, clientCommandHandler);

            System.out.println("Server app stopped");
        } catch (IOHandlerException | EnvironmentVariableException | IOException | SQLException e) {
            System.out.println("Fatal error: " + e.getMessage());
            System.exit(-1);
        }
    }
}
