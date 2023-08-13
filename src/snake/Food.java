package snake;

/**
 *
 * @author Leo
 */
import java.awt.Point;

public class Food {

    private Point position;

    public Food(int x, int y) {
        position = new Point(x, y);
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        position.setLocation(x, y);
    }

}
