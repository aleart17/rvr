public class Point {
    Point(int xCoord, int yCoord){
        this.x = xCoord;
        this.y = yCoord;
    }

    private int x;//coordinates of point, in cm
    private int y;
    public boolean measured;
    public int danger;//number of measurments,confirming this is obstacle. negative means is not(clear)

    public int getX(){return x;}
    public int getY(){return y;}




}
