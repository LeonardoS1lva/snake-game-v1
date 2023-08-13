package snake;

/**
 *
 * @author Leo
 */
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Snake {

    private List<Point> body;
    private int length;
    private int dx;
    private int dy;
    private Point lastDirection;

    public Snake(int x, int y, int length) {
        this.lastDirection = new Point(1, 0);  // Direção inicial é à direita
        this.length = length;
        body = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            body.add(new Point(x - i, y));
        }

        dx = 1;
        dy = 0;
    }

    public Point getLastDirection() {
        return lastDirection;
    }

    public List<Point> getBody() {
        return body;
    }

    public void move() {
        Point head = new Point(body.get(0));
        head.translate(dx, dy);
        body.add(0, head);
        body.remove(length);
    }

    public void move(Point lastDirection) {
        Point head = new Point(body.get(0));
        lastDirection = new Point(head.x + dx, head.y + dy);
        head.translate(dx, dy);
        body.add(0, head);
        body.remove(length);
    }

    public void setDirection(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void grow() {
        Point lastPosition = body.get(body.size() - 1);
        int px = body.get(body.size() - 2).x - lastPosition.x;
        int py = body.get(body.size() - 2).y - lastPosition.y;
        Point newPosition = new Point(lastPosition.x - px, lastPosition.y - py);
        body.add(newPosition);
        length++;
    }

}
