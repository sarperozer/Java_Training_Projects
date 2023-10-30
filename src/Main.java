import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame gameWindow = new JFrame("Snake Game");
        GamePanel gamePanel = new GamePanel();

        gameWindow.add(gamePanel);
        gamePanel.StartGame();

        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.pack();
        gameWindow.setVisible(true);
        gameWindow.setLocationRelativeTo(null);
    }
}