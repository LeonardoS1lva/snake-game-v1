package snake;

/**
 *
 * @author Leo
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Main implements KeyListener {

    private GamePanel gamePanel;
    private Game game;
    private Timer timer;
    private boolean gameRunning;
    private JButton restartButton;

    public Main() {
        GameBoard gameBoard = new GameBoard(20, 20);
        game = new Game(gameBoard);
        gamePanel = new GamePanel(gameBoard, game);
        game.setGamePanel(gamePanel);

        JFrame frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        restartButton = new JButton("Restart");
        restartButton.setEnabled(false);

        frame.addKeyListener(this);
        startGame();
    }

    private void startGame() {
        int delay = 100;
        ActionListener gameUpdater = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.update();
                gamePanel.repaint();
                if (game.isGameOver()) {
                    restartButton.setEnabled(true);
                    gameRunning = false;
                    timer.stop();
                    JOptionPane.showMessageDialog(gamePanel, "Game Over!");
                }
            }
        };
        timer = new Timer(delay, gameUpdater);
        timer.start();
        gameRunning = true;
        restart();
    }

    private void restart() {
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.restartGame();
                gameRunning = true;
                timer.start();
                restartButton.setEnabled(false);
            }
        });
        restartButton.setFocusable(false);
        gamePanel.add(restartButton);

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameRunning) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    game.setDirection(0, -1);
                    break;
                case KeyEvent.VK_DOWN:
                    game.setDirection(0, 1);
                    break;
                case KeyEvent.VK_LEFT:
                    game.setDirection(-1, 0);
                    break;
                case KeyEvent.VK_RIGHT:
                    game.setDirection(1, 0);
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }

}
