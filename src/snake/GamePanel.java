package snake;

/**
 *
 * @author Leo
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Color SNAKE_COLOR = Color.GREEN;
    private static final Color FOOD_COLOR = Color.RED;
    private static final int CELL_SIZE = 20;
    private GameBoard gameBoard;
    private Game game;

    public GamePanel(GameBoard gameBoard, Game game) {
        this.gameBoard = gameBoard;
        this.game = game;
        setPreferredSize(new Dimension(gameBoard.getWidth() * CELL_SIZE, gameBoard.getHeight() * CELL_SIZE));
        setBackground(BACKGROUND_COLOR);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        boolean[][] cells = gameBoard.getCells();

        for (int x = 0; x < gameBoard.getWidth(); x++) {
            for (int y = 0; y < gameBoard.getHeight(); y++) {
                if (x == game.getFood().getPosition().x && y == game.getFood().getPosition().y) {
                    g.setColor(FOOD_COLOR);
                    g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                } else if (cells[x][y]) {
                    g.setColor(SNAKE_COLOR);
                    g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                } else {
                    g.setColor(BACKGROUND_COLOR);
                    g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }
}
