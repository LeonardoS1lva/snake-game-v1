package snake;

/**
 *
 * @author Leo
 */
public class GameBoard {

    private boolean[][] cells;
    private int width;
    private int height;

    public GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
        cells = new boolean[width][height];
    }

    public boolean[][] getCells() {
        return cells;
    }

    public void setCell(int x, int y, boolean value) {
        cells[x][y] = value;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
