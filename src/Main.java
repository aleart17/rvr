import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);//input console create
    public static  Server server = new Server();
    public static Rover rover = new Rover();
    public static Field field = new Field();
    public static void main(String[] args) throws Exception {

        USB.begin();//USB serial port initialising

        System.out.println(USB.recieve());//welcome message
        server.start();
        System.out.println(USB.recieve());//welcome message
        rover.start();
        System.out.println(USB.recieve());//welcome message


    }
}



