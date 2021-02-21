import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try (Socket socket = new Socket("netology.homework", 8081);
             BufferedWriter writer =
                     new BufferedWriter(
                             new OutputStreamWriter(
                                     socket.getOutputStream()));
             BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(
                                     socket.getInputStream()))) {
            System.out.println("Connected to server");
            System.out.println(reader.readLine());
            System.out.println(reader.readLine());
            writeToServer(writer, scanner.nextLine());
            System.out.println(reader.readLine());
            writeToServer(writer, scanner.nextLine());
            System.out.println(reader.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToServer(BufferedWriter writer, String message) throws IOException {
        writer.write(message);
        writer.newLine();
        writer.flush();
    }
}
