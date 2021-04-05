import java.io.IOException;

public class Rover extends Thread{

    private  double roverX=Config.FIELD_SIZE/2;
    private  double roverY=Config.FIELD_SIZE/2;
    private  double roverAzimuth = 0;


    public void run()  {
            try {
                while(true) {
                    //move(Main.scan.nextLine(), Integer.parseInt(Main.scan.nextLine()));

                    move("l", 78);
                    Thread.sleep(1000);

                }
            } catch (Exception e) {
                e.printStackTrace();

        }
    }

    public  void  move(String direction, int distance) throws IOException, InterruptedException {
        USB.transmit(direction + distance);
        for (int j = 0; j<distance; j+=Config.DISTANCE_INCREMENT) {
            //if (Main.scan.hasNext()) {break;}
           defineNextCoord(direction);
            String packet = USB.recieve();
            double[] measurments = (USB.parseMeasurements(packet));
            setPointDanger(measurments[1], Config.LEFT_SONAR_ANGLE);
            setPointDanger(measurments[2], Config.CENTER_SONAR_ANGLE);
            setPointDanger(measurments[3], Config.RIGHT_SONAR_ANGLE);
            System.out.print(packet);
            System.out.print("  ");
            System.out.printf("ROVER: X=%d Y=%d Az=%d", (int)roverX, (int)roverY, (int)Math.toDegrees(roverAzimuth));
            System.out.print("\n");
        }
    }
    public  double getRoverX() {
        return roverX;
    }
    public  void setRoverX(double x) {
        roverX = x;
    }
    public  double getRoverY() {
        return roverY;
    }
    public  void setRoverY(double y) {
        roverY = y;
    }
    public  double getRoverAzimuth() {
        return roverAzimuth;
    }
    public  void setRoverAzimuth(double a) {
        roverAzimuth = a;
    }
    private  void defineNextCoord(String direction){
        switch (direction){
            case "f": {
                roverY += Config.DISTANCE_INCREMENT*Math.cos(roverAzimuth);
                roverX += Config.DISTANCE_INCREMENT*Math.sin(roverAzimuth);
                break;
            }
            case "b": {
                roverY -= Config.DISTANCE_INCREMENT*Math.cos(roverAzimuth);
                roverX -= Config.DISTANCE_INCREMENT*Math.sin(roverAzimuth);
                break;
            }
            case "l": {
                roverAzimuth -= Config.AZIMUTH_INCREMENT;
                if (roverAzimuth <= -Math.PI) {roverAzimuth+=2*Math.PI;}
                break;
            }
            case "r": {
                roverAzimuth += Config.AZIMUTH_INCREMENT;
                if (roverAzimuth >= Math.PI) {roverAzimuth-=2*Math.PI;}
                break;
            }
        }
    }
    private  void setPointDanger (double measurement, double sensorAngle) {
        int xCoord = (int) (roverX+(measurement+6)*Math.sin(roverAzimuth+sensorAngle));
        int yCoord = (int) (roverY+(measurement+6)*Math.cos(roverAzimuth+sensorAngle));
            if (xCoord>Config.FIELD_SIZE-1||xCoord<0) return;
            if (yCoord>Config.FIELD_SIZE-1||yCoord<0) return;
        Main.field.map[xCoord][yCoord].danger ++;

    }

}
