import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(8081)) {
            System.out.println("Server started...");

            try (Socket socket = server.accept();
                 BufferedWriter writer =
                         new BufferedWriter(
                                 new OutputStreamWriter(
                                         socket.getOutputStream()));
                 BufferedReader reader =
                         new BufferedReader(
                                 new InputStreamReader(
                                         socket.getInputStream()))) {

                System.out.println("New connection accepted");
                writeToUser(writer, String.format("Hi, User, your port is %d", socket.getPort()));
                writeToUser(writer, "Please write your name");
                String name = reader.readLine();
                writeToUser(writer, String.format("Ok, %s, are you a child? (yes/no)", name));
                String answer = reader.readLine();

                switch (answer) {
                    case "yes":
                        writeToUser(writer, String.format("Welcome to the kids area, %s! Let's play!", name));
                    case "no":
                        writeToUser(writer,
                            String.format("Welcome to the adult zone, %s! " +
                                    "Have a good rest, or a good working day!", name));
                    default:
                        writeToUser(writer, "Unknown response, connection is closed");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToUser(BufferedWriter writer, String message) throws IOException {
        writer.write(message);
        writer.newLine();
        writer.flush();
    }
}
