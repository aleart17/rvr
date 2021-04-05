import com.pi4j.io.serial.*;


import java.io.IOException;


public class USB {
    private static final Serial serial = SerialFactory.createInstance();

    public static void begin() throws IOException {
       serial.open(Serial.FIRST_USB_COM_PORT, 115200);
    }
    public static String recieve() throws IOException, InterruptedException {
        StringBuilder sb = new StringBuilder("->");
        while (serial.available() <= 0) { }
        while (serial.available() > 0) {
            byte[] bytes = serial.read(1);
            char symbol = (char) (bytes[0]);
            if (symbol == '~') {
                break;
            } else sb.append(symbol);;

        }
        return sb.toString();
    }


    public static double[] parseMeasurements(String recievedString){
String[] numbers = recievedString.split(" ");
        double[] result = new double[4];
        result[0] = Double.parseDouble(numbers[1]);
        result[1] = Double.parseDouble(numbers[3]);
        result[2] = Double.parseDouble(numbers[4]);
        result[3] = Double.parseDouble(numbers[5]);
        return result;
    }


    public static void transmit(String string) throws IOException {

        serial.writeln(string);

    }
    public static boolean ready() throws IOException {

        return serial.available() > 0;

    }
}

