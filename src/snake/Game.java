package snake;

/**
 *
 * @author Leo
 */
import java.awt.Point;
import java.util.Random;

public class Game {

    private Snake snake;
    private Food food;
    private GamePanel gamePanel;
    private GameBoard gameBoard;
    private Random random;

    public Game(GameBoard gameBoard) {
        random = new Random();
        this.gameBoard = gameBoard;
        spawnSnake();
    }

    private void spawnSnake() {
        int x = gameBoard.getWidth() / 2;
        int y = gameBoard.getHeight() / 2;
        int length = 3;
        snake = new Snake(x, y, length);
        spawnFood();
        updateBoard();
    }

    private void spawnFood() {
        int x = random.nextInt(gameBoard.getWidth());
        int y = random.nextInt(gameBoard.getHeight());
        food = new Food(x, y);
    }

    public Food getFood() {
        return food;
    }

    public boolean isGameOver() {
        Point head = snake.getBody().get(0);
        if (head.x < 0 || head.x >= gameBoard.getWidth() || head.y < 0 || head.y >= gameBoard.getHeight()) {
            return true; // hit the wall
        }
        for (int i = 1; i < snake.getBody().size(); i++) {
            if (head.equals(snake.getBody().get(i))) {
                return true; // hit itself
            }
        }
        return false;
    }

    public void update() {
        snake.move();
        Point head = snake.getBody().get(0);
        if (head.equals(food.getPosition())) {
            snake.grow();
            Point lastDirection = snake.getLastDirection();
            int dx = lastDirection.x;
            int dy = lastDirection.y;
            Point newHeadPosition = new Point(head.x + dx, head.y + dy);
            snake.move(newHeadPosition);
            spawnFood();

        }
        updateBoard();
        if (isGameOver()) {
            System.out.println("Game over!");
            // You can add more logic here, like resetting the game, displaying score, etc.
        }

    }

    public void restartGame() {
        spawnSnake();

        gamePanel.repaint();
    }

    public void setDirection(int dx, int dy) {
        snake.setDirection(dx, dy);
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    private void updateBoard() {
        boolean[][] cells = gameBoard.getCells();
        for (int x = 0; x < gameBoard.getWidth(); x++) {
            for (int y = 0; y < gameBoard.getHeight(); y++) {
                cells[x][y] = false;
            }
        }
        for (Point bodyPart : snake.getBody()) {
            int x = bodyPart.x;
            int y = bodyPart.y;
            if (x >= 0 && x < gameBoard.getWidth() && y >= 0 && y < gameBoard.getHeight()) {
                cells[x][y] = true;
            }
        }
        Point foodPosition = food.getPosition();
        int x = foodPosition.x;
        int y = foodPosition.y;
        if (x >= 0 && x < gameBoard.getWidth() && y >= 0 && y < gameBoard.getHeight()) {
            cells[x][y] = true;
        }
    }

}
