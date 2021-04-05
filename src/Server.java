import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server extends Thread{

    private static Socket clientSocket; //сокет для общения
    private static ServerSocket server; // серверсокет
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    public  void  run() {
        String name = "";
        try {
            server = new ServerSocket(Config.PORT);
            System.out.println("->Server: Server started");
            while (true) {
                clientSocket = server.accept();
                try {
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                    name = in.readLine();
                    System.out.println("->Server: " + name + " connected");
                    while (true) {
                        out.write(Main.field.getMapAsString() + "\n");
                        out.flush();
                        Thread.sleep(1000);
                    }

                } catch (SocketException i) {
                    System.out.println("->Server: " + name +"  disconnected");
                }
            }
        } catch (Exception ignored) { }
    }
}