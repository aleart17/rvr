public class Field {
    public  Point[][] map;
    public  Field() {

        this.map = new Point[Config.FIELD_SIZE][Config.FIELD_SIZE];
        for (int y = 0; y < Config.FIELD_SIZE; y++) {
            for (int x = 0; x < Config.FIELD_SIZE; x++) {
                map[x][y] = new Point(x, y);
                map[x][y].danger=0;
            }
        }

    }
    public  String getMapAsString() {
StringBuilder sb = new StringBuilder();
        for (int y = 0; y < Config.FIELD_SIZE; y++) {
            for (int x = 0; x < Config.FIELD_SIZE; x++) {
                sb.append(x+" "+y+" "+this.map[x][y].danger+"-");
            }
            //sb.append("\n");
        }
return sb.toString();
    }
}
